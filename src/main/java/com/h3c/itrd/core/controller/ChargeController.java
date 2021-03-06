package com.h3c.itrd.core.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pagehelper.PageInfo;
import com.h3c.itrd.common.annotation.OperLog;
import com.h3c.itrd.common.consts.SystemConstant;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.entity.AddScoreDO;
import com.h3c.itrd.core.entity.AdminListDO;
import com.h3c.itrd.core.entity.DepartmentListDO;
import com.h3c.itrd.core.entity.DptmanagerListDO;
import com.h3c.itrd.core.entity.EmployeeListDO;
import com.h3c.itrd.core.entity.NoticeListDO;
import com.h3c.itrd.core.service.AddScoreService;
import com.h3c.itrd.core.service.AdminListService;
import com.h3c.itrd.core.service.DepartmentListService;
import com.h3c.itrd.core.service.DptmanagerListService;
import com.h3c.itrd.core.service.EmployeeListService;
import com.h3c.itrd.core.service.NoticeListService;
import com.h3c.itrd.core.service.QuarterScoreService;
import com.h3c.itrd.core.vo.DepartmentListReqVO;
import com.h3c.itrd.core.vo.DepartmentListResVO;
import com.h3c.itrd.core.vo.DepartmentListVO;
import com.h3c.itrd.core.vo.EmployeeListReqVO;
import com.h3c.itrd.core.vo.EmployeeListResVO;
import com.h3c.itrd.core.vo.OverviewReqVO;
import com.h3c.itrd.core.vo.RankResVO;
import com.h3c.itrd.util.AbstractExcelReader;
import com.h3c.itrd.util.DateUtil;
import com.h3c.itrd.util.ExportExcelWrapper;
import com.h3c.itrd.util.PagingList;
import com.h3c.itrd.util.RankUtil;
import com.h3c.itrd.util.SystemCommonUtil;
import com.h3c.itrd.util.UserUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * ?????? ???????????????
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@RestController
@RequestMapping("/api/charge")
@Api(value = "????????????????????????", tags = {"????????????????????????"})
public class ChargeController {
	private static Logger logger = LoggerFactory.getLogger(ChargeController.class);
	
	@Autowired
	private QuarterScoreService quarterScoreService;
	@Autowired
	private EmployeeListService employeeListService;
	@Autowired
	private DepartmentListService departmentListService;
	@Autowired
	private AdminListService adminListService;
	@Autowired
	private DptmanagerListService dptmanagerListService;
	
    @PostMapping("/departmentList")
    @ApiOperation(value = "?????????????????????????????????department_list")
    @OperLog(operModelName="??????????????????????????????",operContent="??????????????????????????????")
    public ResponseResult departmentList(@RequestBody DepartmentListReqVO departmentListReqVO) throws Exception{
    	//????????????
    	if(!this.checkPerson()) {
    		return ResponseResult.fail(1, "??????????????????");
    	}
    	//??????????????????
    	int pageNum=departmentListReqVO.getPageNum();
        int pageSize=departmentListReqVO.getPageSize();
        if(pageNum<=0) {
        	pageNum=1;
        }
        if(pageSize<=0) {
        	pageSize=20;
        }
    	List<DepartmentListResVO> list =new ArrayList<>();
    	Map<String, Object> param = new HashMap<>();
    	Map<String, Object> rankMap = new HashMap<>();
    	//????????????
    	if(!departmentListReqVO.getSeconddpt().equals("??????") && !departmentListReqVO.getSeconddpt().equals("")) {
    		param.put("seconddptcode", departmentListReqVO.getSeconddpt());
    		rankMap.put("seconddptcode", departmentListReqVO.getSeconddpt());
    	}
    	if(!departmentListReqVO.getCity().equals("??????") && !departmentListReqVO.getCity().equals("")) {
    		param.put("position", departmentListReqVO.getCity());
    		rankMap.put("position", departmentListReqVO.getCity());
    	}
    	if(!departmentListReqVO.getPostType().equals("??????") && !departmentListReqVO.getPostType().equals("")) {
    		param.put("postType", departmentListReqVO.getPostType());
    		rankMap.put("postType", departmentListReqVO.getPostType());
    	}
    	if(departmentListReqVO.getThirddptList().size()>0 && departmentListReqVO.getThirddptList()!=null) {
    		param.put("thirddptcode", departmentListReqVO.getThirddptList());
    	}
    	if(departmentListReqVO.getCurrentdptList().size()>0 && departmentListReqVO.getCurrentdptList()!=null) {
    		param.put("currentdptcode", departmentListReqVO.getCurrentdptList());
    	}
    	if(!departmentListReqVO.getQuarter().equals("") && departmentListReqVO.getQuarter()!=null) {
    		param.put("quarter", departmentListReqVO.getQuarter());
    		rankMap.put("quarter", departmentListReqVO.getQuarter());
    	}
    	if(departmentListReqVO.getSpecial().equals("???") && !departmentListReqVO.getSpecial().equals("")) {
    		param.put("special", '1');
    		rankMap.put("special", '1');
    	}
        //????????????
    	int sortedFlag = departmentListReqVO.getSortedFlag();
    	//????????????????????????0???????????????1???????????????
    	int flag = departmentListReqVO.getFlag();
    	
    	//???????????????????????????
    	if(departmentListReqVO.getGroup().equals("thirddptcode")) {
    		//????????????????????????
    		List<DepartmentListResVO> listForNoSpecial3 = quarterScoreService.getListForNoSpecial3(param);
    		//?????????????????????
    		List<DepartmentListResVO> listForSpecial3 = quarterScoreService.getListForSpecial3(param);
    		int count = employeeListService.getCountGroupByDeptCode(departmentListReqVO.getSeconddpt(), departmentListReqVO.getPostType(), "thirddptcode");
    		List<DepartmentListResVO> rankListFor3 = quarterScoreService.getRankListFor3(rankMap);
    		for (int i = 0; i < listForNoSpecial3.size(); i++) {
    			listForNoSpecial3.get(i).setCity(departmentListReqVO.getCity());
    			listForNoSpecial3.get(i).setRank(count);
    			for (int j = 0; j < rankListFor3.size(); j++) {
					if (listForNoSpecial3.get(i).getCurrentdpt().equals(rankListFor3.get(j).getCurrentdpt())) {
						listForNoSpecial3.get(i).setRank(j+1);
					}
				}
    			//?????????????????????O?????????????????????
    			listForNoSpecial3.get(i).setSpecialMember(0);
    			for (int k = 0; k < listForSpecial3.size(); k++) {
    				if (listForNoSpecial3.get(i).getCurrentdpt().equals(listForSpecial3.get(k).getCurrentdpt())) {
    					listForNoSpecial3.get(i).setSpecialMember(listForSpecial3.get(k).getMember());
    				}
				}
//    			if("???".equals(departmentListReqVO.getSpecial())) {
//    				listForNoSpecial3.get(i).setMember(listForNoSpecial3.get(i).getMember()+listForNoSpecial3.get(i).getSpecialMember());
//    			}
			}
    		//?????????????????????(??????????????????????????????????????????)(?????????1	??????/??????2	????????????3	????????????4	????????????5	???????????????6	  ???????????????7  ??????????????????0	??????8 	??????????????????9)
    		if(flag==0) {
    			listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getRank)).collect(Collectors.toList());
    		}else {
    			listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getRank).reversed()).collect(Collectors.toList());
    		}
    		if(sortedFlag==1) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getCurrentdpt)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getCurrentdpt).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==2) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getTotalScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getTotalScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==3) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getMaintainScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getMaintainScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==4) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getDevelopScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getDevelopScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==5) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getReviewScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getReviewScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==6) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getAddScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getAddScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==7) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getPlatformScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getPlatformScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==8) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getCity)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getCity).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==9) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getSpecialMember)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getSpecialMember).reversed()).collect(Collectors.toList());	
    			}
    		}
    		list.addAll(listForNoSpecial3);
    	//???????????????????????????
    	}else if(departmentListReqVO.getGroup().equals("currentdptcode")){
    		//????????????????????????
    		List<DepartmentListResVO> listForNoSpecial4 = quarterScoreService.getListForNoSpecial4(param);
    		//?????????????????????
    		List<DepartmentListResVO> listForSpecial4 = quarterScoreService.getListForSpecial4(param);
    		int count = employeeListService.getCountGroupByDeptCode(departmentListReqVO.getSeconddpt(), departmentListReqVO.getPostType(), "currentdptcode");
    		List<DepartmentListResVO> rankListFor4 = quarterScoreService.getRankListFor4(rankMap);
    		for (int i = 0; i < listForNoSpecial4.size(); i++) {
    			listForNoSpecial4.get(i).setCity(departmentListReqVO.getCity());
    			listForNoSpecial4.get(i).setRank(count);
    			for (int j = 0; j < rankListFor4.size(); j++) {
					if (listForNoSpecial4.get(i).getCurrentdpt().equals(rankListFor4.get(j).getCurrentdpt())) {
						listForNoSpecial4.get(i).setRank(j+1);
					}
				}
    			//?????????????????????O?????????????????????
    			listForNoSpecial4.get(i).setSpecialMember(0);
    			for (int k = 0; k < listForSpecial4.size(); k++) {
    				if (listForNoSpecial4.get(i).getCurrentdpt().equals(listForSpecial4.get(k).getCurrentdpt())) {
    					listForNoSpecial4.get(i).setSpecialMember(listForSpecial4.get(k).getMember());
    				}
				}
//    			if("???".equals(departmentListReqVO.getSpecial())) {
//    				listForNoSpecial4.get(i).setMember(listForNoSpecial4.get(i).getMember()+listForNoSpecial4.get(i).getSpecialMember());
//    			}
			}
    		//?????????????????????(??????????????????????????????????????????)(?????????1	??????/??????2	????????????3	????????????4	????????????5	???????????????6	  ???????????????7   ??????????????????0	??????8 	??????????????????9)
    		if(flag==0) {
    			listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getRank)).collect(Collectors.toList());
    		}else {
    			listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getRank).reversed()).collect(Collectors.toList());
    		}
    		if(sortedFlag==1) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getCurrentdpt)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getCurrentdpt).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==2) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getTotalScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getTotalScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==3) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getMaintainScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getMaintainScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==4) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getDevelopScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getDevelopScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==5) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getReviewScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getReviewScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==6) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getAddScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getAddScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==7) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getPlatformScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getPlatformScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==8) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getCity)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getCity).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==9) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getSpecialMember)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getSpecialMember).reversed()).collect(Collectors.toList());	
    			}
    		}
    		list.addAll(listForNoSpecial4);
    	}
    	List<DepartmentListResVO> pageList = list.stream().sorted().skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    	
    	return ResponseResult.success(0, "????????????", pageNum, list.size(),null,pageList);
    }

	@PostMapping("/exportDepartmentList")
    @ApiOperation(value = "?????????????????????????????????export_department_list")
    @OperLog(operModelName="??????????????????????????????",operContent="??????????????????????????????")
    public void exportDepartmentList(@RequestBody DepartmentListReqVO departmentListReqVO,
    						HttpServletRequest request,HttpServletResponse response) throws Exception {
    	//??????????????????
//    	int pageNum=departmentListReqVO.getPageNum();
//        int pageSize=departmentListReqVO.getPageSize();
//        if(pageNum<=0) {
//        	pageNum=1;
//        }
//        if(pageSize<=0) {
//        	pageSize=20;
//        }
        //?????????	??????/??????	????????????	????????????	????????????	???????????????	??????????????????	??????	??????????????????
    	String[] header = new String[] { "?????????","??????/??????", "????????????", "????????????", "????????????", "???????????????",
    									"??????????????????", "????????????", "??????","??????????????????","?????????"};
    	String[] column = new String[] { "Currentdpt","TotalScore", "DevelopScore","MaintainScore","ReviewScore",
    			"AddScore","PlatformScore","Rank","City","SpecialMember","Member"};
    	List<DepartmentListResVO> list =new ArrayList<>();
    	Map<String, Object> param = new HashMap<>();
    	Map<String, Object> rankMap = new HashMap<>();
    	//????????????
    	if(!departmentListReqVO.getSeconddpt().equals("??????") && !departmentListReqVO.getSeconddpt().equals("")) {
    		param.put("seconddptcode", departmentListReqVO.getSeconddpt());
    		rankMap.put("seconddptcode", departmentListReqVO.getSeconddpt());
    	}
    	if(!departmentListReqVO.getCity().equals("??????") && !departmentListReqVO.getCity().equals("")) {
    		param.put("position", departmentListReqVO.getCity());
    		rankMap.put("position", departmentListReqVO.getCity());
    	}
    	if(!departmentListReqVO.getPostType().equals("??????") && !departmentListReqVO.getPostType().equals("")) {
    		param.put("postType", departmentListReqVO.getPostType());
    		rankMap.put("postType", departmentListReqVO.getPostType());
    	}
    	if(departmentListReqVO.getThirddptList().size()>0 && departmentListReqVO.getThirddptList()!=null) {
    		param.put("thirddptcode", departmentListReqVO.getThirddptList());
    	}
    	if(departmentListReqVO.getCurrentdptList().size()>0 && departmentListReqVO.getCurrentdptList()!=null) {
    		param.put("currentdptcode", departmentListReqVO.getCurrentdptList());
    	}
    	if(!departmentListReqVO.getQuarter().equals("") && departmentListReqVO.getQuarter()!=null) {
    		param.put("quarter", departmentListReqVO.getQuarter());
    		rankMap.put("quarter", departmentListReqVO.getQuarter());
    	}
    	if(departmentListReqVO.getSpecial().equals("???") && !departmentListReqVO.getSpecial().equals("")) {
    		param.put("special", '1');
    		rankMap.put("special", '1');
    	}
    	//????????????
    	int sortedFlag = departmentListReqVO.getSortedFlag();
    	//????????????????????????0???????????????1???????????????
    	int flag = departmentListReqVO.getFlag();
    	
    	//???????????????????????????
    	if(departmentListReqVO.getGroup().equals("thirddptcode")) {
    		//????????????????????????
    		List<DepartmentListResVO> listForNoSpecial3 = quarterScoreService.getListForNoSpecial3(param);
    		//?????????????????????
    		List<DepartmentListResVO> listForSpecial3 = quarterScoreService.getListForSpecial3(param);
    		int count = employeeListService.getCountGroupByDeptCode(departmentListReqVO.getSeconddpt(), departmentListReqVO.getPostType(), "thirddptcode");
    		List<DepartmentListResVO> rankListFor3 = quarterScoreService.getRankListFor3(rankMap);
    		for (int i = 0; i < listForNoSpecial3.size(); i++) {
    			listForNoSpecial3.get(i).setCity(departmentListReqVO.getCity());
    			listForNoSpecial3.get(i).setRank(count);
    			for (int j = 0; j < rankListFor3.size(); j++) {
					if (listForNoSpecial3.get(i).getCurrentdpt().equals(rankListFor3.get(j).getCurrentdpt())) {
						listForNoSpecial3.get(i).setRank(j+1);
					}
				}
    			//?????????????????????O?????????????????????
    			listForNoSpecial3.get(i).setSpecialMember(0);
    			for (int k = 0; k < listForSpecial3.size(); k++) {
    				if (listForNoSpecial3.get(i).getCurrentdpt().equals(listForSpecial3.get(k).getCurrentdpt())) {
    					listForNoSpecial3.get(i).setSpecialMember(listForSpecial3.get(k).getMember());
    				}
				}
//    			if("???".equals(departmentListReqVO.getSpecial())) {
//    				listForNoSpecial3.get(i).setMember(listForNoSpecial3.get(i).getMember()+listForNoSpecial3.get(i).getSpecialMember());
//    			}
			}
    		//?????????????????????(??????????????????????????????????????????)(?????????1	??????/??????2	????????????3	????????????4	????????????5	???????????????6	  ???????????????7  ??????????????????0	??????8 	??????????????????9)
    		if(flag==0) {
    			listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getRank)).collect(Collectors.toList());
    		}else {
    			listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getRank).reversed()).collect(Collectors.toList());
    		}
    		if(sortedFlag==1) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getCurrentdpt)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getCurrentdpt).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==2) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getTotalScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getTotalScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==3) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getMaintainScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getMaintainScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==4) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getDevelopScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getDevelopScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==5) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getReviewScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getReviewScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==6) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getAddScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getAddScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==7) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getPlatformScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getPlatformScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==8) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getCity)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getCity).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==9) {
    			if(flag==0) {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getSpecialMember)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial3 = listForNoSpecial3.stream().sorted(Comparator.comparing(DepartmentListResVO::getSpecialMember).reversed()).collect(Collectors.toList());	
    			}
    		}
    		list.addAll(listForNoSpecial3);
    	//???????????????????????????
    	}else if(departmentListReqVO.getGroup().equals("currentdptcode")){
    		//????????????????????????
    		List<DepartmentListResVO> listForNoSpecial4 = quarterScoreService.getListForNoSpecial4(param);
    		//?????????????????????
    		List<DepartmentListResVO> listForSpecial4 = quarterScoreService.getListForSpecial4(param);
    		int count = employeeListService.getCountGroupByDeptCode(departmentListReqVO.getSeconddpt(), departmentListReqVO.getPostType(), "currentdptcode");
    		List<DepartmentListResVO> rankListFor4 = quarterScoreService.getRankListFor4(rankMap);
    		for (int i = 0; i < listForNoSpecial4.size(); i++) {
    			listForNoSpecial4.get(i).setCity(departmentListReqVO.getCity());
    			listForNoSpecial4.get(i).setRank(count);
    			for (int j = 0; j < rankListFor4.size(); j++) {
					if (listForNoSpecial4.get(i).getCurrentdpt().equals(rankListFor4.get(j).getCurrentdpt())) {
						listForNoSpecial4.get(i).setRank(j+1);
					}
				}
    			//?????????????????????O?????????????????????
    			listForNoSpecial4.get(i).setSpecialMember(0);
    			for (int k = 0; k < listForSpecial4.size(); k++) {
    				if (listForNoSpecial4.get(i).getCurrentdpt().equals(listForSpecial4.get(k).getCurrentdpt())) {
    					listForNoSpecial4.get(i).setSpecialMember(listForSpecial4.get(k).getMember());
    				}
				}
//    			if("???".equals(departmentListReqVO.getSpecial())) {
//    				listForNoSpecial4.get(i).setMember(listForNoSpecial4.get(i).getMember()+listForNoSpecial4.get(i).getSpecialMember());
//    			}
			}
    		//?????????????????????(??????????????????????????????????????????)(?????????1	??????/??????2	????????????3	????????????4	????????????5	???????????????6	  ???????????????7   ??????????????????0	??????8 	??????????????????9)
    		if(flag==0) {
    			listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getRank)).collect(Collectors.toList());
    		}else {
    			listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getRank).reversed()).collect(Collectors.toList());
    		}
    		if(sortedFlag==1) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getCurrentdpt)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getCurrentdpt).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==2) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getTotalScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getTotalScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==3) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getMaintainScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getMaintainScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==4) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getDevelopScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getDevelopScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==5) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getReviewScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getReviewScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==6) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getAddScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getAddScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==7) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getPlatformScore)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getPlatformScore).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==8) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getCity)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getCity).reversed()).collect(Collectors.toList());
    			}
    		}else if(sortedFlag==9) {
    			if(flag==0) {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getSpecialMember)).collect(Collectors.toList());
    			}else {
    				listForNoSpecial4 = listForNoSpecial4.stream().sorted(Comparator.comparing(DepartmentListResVO::getSpecialMember).reversed()).collect(Collectors.toList());	
    			}
    		}
    		list.addAll(listForNoSpecial4);
    	}
//    	List<DepartmentListResVO> pageList = list.stream().sorted().skip((pageNum - 1) * pageSize)
//                .limit(pageSize)
//                .collect(Collectors.toList());
    	ExportExcelWrapper<DepartmentListResVO> excelWrapper = new ExportExcelWrapper<>();
		excelWrapper.exportExcel("DepartmentScoreExport", "??????????????????????????????", header, column, list, response, "2007",true, "Currentdpt");
    }
    
    @PostMapping("/searchListForemployee")
    @ApiOperation(value = "???????????????????????????????????????searchListForemployee")
    @OperLog(operModelName="????????????????????????????????????",operContent="????????????????????????????????????")
    public ResponseResult searchListForemployee(@RequestBody EmployeeListReqVO employeeListReqVO) throws Exception{
    	//????????????
    	if(!this.checkPerson()) {
    		return ResponseResult.fail(1, "??????????????????");
    	}
    	
    	Map<String, Object> param = new HashMap<>();
    	//????????????
    	if(!employeeListReqVO.getSeconddpt().equals("??????") && !employeeListReqVO.getSeconddpt().equals("")) {
    		param.put("seconddptcode", employeeListReqVO.getSeconddpt());
    	}
    	if(!employeeListReqVO.getThirddpt().equals("??????") && !employeeListReqVO.getThirddpt().equals("")) {
    		param.put("thirddptcode", employeeListReqVO.getThirddpt());
    	}
    	if(!employeeListReqVO.getCurrentdpt().equals("??????") && !employeeListReqVO.getCurrentdpt().equals("")) {
    		param.put("currentdptcode", employeeListReqVO.getCurrentdpt());
    	}
    	if(!employeeListReqVO.getFivedpt().equals("??????") && !employeeListReqVO.getFivedpt().equals("")) {
    		param.put("fivedptcode", employeeListReqVO.getFivedpt());
    	}
    	if(!employeeListReqVO.getCity().equals("??????") && !employeeListReqVO.getCity().equals("")) {
    		param.put("position", employeeListReqVO.getCity());
    	}
    	if(!employeeListReqVO.getPostType().equals("??????") && !employeeListReqVO.getPostType().equals("")) {
    		param.put("postType", employeeListReqVO.getPostType());
    	}
    	if(!employeeListReqVO.getPositiontype().equals("??????") && !employeeListReqVO.getPositiontype().equals("")) {
    		if(employeeListReqVO.getPositiontype().equals("ODC??????")) {
    			param.put("type", "RDODC");
    		}else {
    			param.put("type", employeeListReqVO.getPositiontype());
    		}
    	}
    	if(StringUtils.isNotBlank(employeeListReqVO.getEmployeeSearchKey())) {
    		param.put("employeeSearchKey", employeeListReqVO.getEmployeeSearchKey());
    	}
    	List<EmployeeListDO> employeelist = employeeListService.getListByQueryParam(param);
    	return ResponseResult.success(0, "????????????", employeelist,employeelist.size());
    }
    
    @PostMapping("/employeeSearchList")
    @ApiOperation(value = "?????????????????????????????????employee_search_list")
    @OperLog(operModelName="??????????????????????????????",operContent="??????????????????????????????")
    public ResponseResult employeeSearchList(@RequestBody EmployeeListReqVO employeeListReqVO) throws Exception{
    	//????????????
    	if(!this.checkPerson()) {
    		return ResponseResult.fail(1, "??????????????????");
    	}
    	//??????????????????
    	int pageNum=employeeListReqVO.getPageNum();
        int pageSize=employeeListReqVO.getPageSize();
        if(pageNum<=0) {
        	pageNum=1;
        }
        if(pageSize<=0) {
        	pageSize=20;
        }
    	Map<String, Object> param = new HashMap<>();
    	Map<String, Object> rankMap = new HashMap<>();
    	//????????????
    	if(!employeeListReqVO.getSeconddpt().equals("??????") && !employeeListReqVO.getSeconddpt().equals("")) {
    		param.put("seconddptcode", employeeListReqVO.getSeconddpt());
    		rankMap.put("seconddptcode", employeeListReqVO.getSeconddpt());
    	}
    	if(!employeeListReqVO.getThirddpt().equals("??????") && !employeeListReqVO.getThirddpt().equals("")) {
    		param.put("thirddptcode", employeeListReqVO.getThirddpt());
    	}
    	if(!employeeListReqVO.getCurrentdpt().equals("??????") && !employeeListReqVO.getCurrentdpt().equals("")) {
    		param.put("currentdptcode", employeeListReqVO.getCurrentdpt());
    	}
    	if(!employeeListReqVO.getFivedpt().equals("??????") && !employeeListReqVO.getFivedpt().equals("")) {
    		param.put("fivedptcode", employeeListReqVO.getFivedpt());
    	}
    	if(!employeeListReqVO.getCity().equals("??????") && !employeeListReqVO.getCity().equals("")) {
    		param.put("position", employeeListReqVO.getCity());
    	}
    	if(!employeeListReqVO.getPostType().equals("??????") && !employeeListReqVO.getPostType().equals("")) {
    		param.put("postType", employeeListReqVO.getPostType());
    		rankMap.put("postType", employeeListReqVO.getPostType());
    	}
    	if(!employeeListReqVO.getPositiontype().equals("??????") && !employeeListReqVO.getPositiontype().equals("")) {
    		if(employeeListReqVO.getPositiontype().equals("ODC??????")) {
    			param.put("type", "RDODC");
    		}else {
    			param.put("type", employeeListReqVO.getPositiontype());
    		}
    	}
    	if(!employeeListReqVO.getQuarter().equals("") && employeeListReqVO.getQuarter()!=null) {
    		param.put("quarter", employeeListReqVO.getQuarter());
    		rankMap.put("quarter", employeeListReqVO.getQuarter());
    	}
    	//????????????
    	int sortedFlag = employeeListReqVO.getSortedFlag();
    	//????????????????????????0???????????????1???????????????
    	int flag = employeeListReqVO.getFlag();
    	
    	if(StringUtils.isNotBlank(employeeListReqVO.getEmployeeSearchKey())) {
    		param.put("employeeSearchKey", employeeListReqVO.getEmployeeSearchKey());
    		//rankMap.put("employeeSearchKey", employeeListReqVO.getEmployeeSearchKey());
    	}
    	//int total = quarterScoreService.getListForEmployee(param).size();
        //com.github.pagehelper.page.PageMethod.startPage(pageNum,pageSize);
    	List<EmployeeListResVO> listForEmployee = quarterScoreService.getListForEmployee(param);
    	List<RankResVO> rankForEmployee = quarterScoreService.getRankForEmployee(rankMap);
    	List<RankResVO> rank1ForEmployee = quarterScoreService.getRank1ForEmployee(rankMap);
    	
    	for (int i = 0; i < listForEmployee.size(); i++) {
    		//??? ODc??????????????????
			if(listForEmployee.get(i).getPositiontype().equals("RDODC")) {
				listForEmployee.get(i).setPositiontype("ODC??????");
			}
			//????????????????????????????????????
			if(listForEmployee.get(i).getSpecial().equals("0")) {
				listForEmployee.get(i).setSpecial("???");
			}else {
				listForEmployee.get(i).setSpecial("???");
			}
			//?????????????????????
			int rank = RankUtil.rank(rankForEmployee, listForEmployee.get(i).getAccount());
			listForEmployee.get(i).setRank(rank);
			int rank1 = RankUtil.rank(rank1ForEmployee, listForEmployee.get(i).getAccount());
			listForEmployee.get(i).setRank1(rank1);
		}
    	
    	//?????????????????????(??????????????????????????????????????????)(??????1  ??????2 ??????3 ????????????4 ????????????5	  ????????????6	???????????????7	  ???????????????8  ??????????????????0	??????9  ??????10)
    	if(flag==0) {
    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getRank)).collect(Collectors.toList());
    	}else {
    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getRank).reversed()).collect(Collectors.toList());
    	}
		if(sortedFlag==1) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getName)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getName).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==2) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getId)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getId).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==3) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getTotalScore)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getTotalScore).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==4) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getDevelopScore)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getDevelopScore).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==5) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getMaintainScore)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getMaintainScore).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==6) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getReviewScore)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getReviewScore).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==7) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getAddScore)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getAddScore).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==8) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getPlatformScore)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getPlatformScore).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==9) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getPosition)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getPosition).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==10) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getPositiontype)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getPositiontype).reversed()).collect(Collectors.toList());
	    	}
		}
    	//PageInfo<EmployeeListResVO> pageInfo = new PageInfo<>(listForEmployee);
		List<EmployeeListResVO> pageList = listForEmployee.stream().sorted().skip((pageNum - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    	return ResponseResult.success(0, "????????????", pageNum, listForEmployee.size(),null,pageList);
    }
    
    @PostMapping("/exportSearchList")
    @ApiOperation(value = "?????????????????????????????????export_search_list")
    @OperLog(operModelName="??????????????????????????????",operContent="??????????????????????????????")
    public void exportSearchList(@RequestBody EmployeeListReqVO employeeListReqVO,
    							HttpServletRequest request,HttpServletResponse response) throws Exception {
    	String urlPrifix = request.getHeader("Origin");// ??? http ??????????????????origin
    	//??????????????????
//    	int pageNum=employeeListReqVO.getPageNum();
//        int pageSize=employeeListReqVO.getPageSize();
//        if(pageNum<=0) {
//        	pageNum=1;
//        }
//        if(pageSize<=0) {
//        	pageSize=20;
//        }
    	//??????	??????	????????????	????????????	????????????	????????????	????????????	??????	??????	??????	?????????????????????	??????	????????????	????????????	????????????	???????????????	??????????????????	???????????????	??????	??????????????????	????????????????????????	??????	????????????
    	/*String[] header = new String[] { "??????","??????", "????????????", "????????????", "????????????","????????????", "????????????","??????", "??????",
				"??????", "?????????????????????", "??????", "????????????", "????????????", "????????????", "???????????????", "??????????????????", "???????????????", "??????",
				"??????????????????","????????????????????????"};
    	String[] column = new String[] { "Name","Id", "Firstdpt", "Seconddpt", "Thirddpt","Currentdpt", 
				"Fivedpt","Quarter", "PostType","Position","Special","Positiontype", "MaintainScore", 
				"DevelopScore","ReviewScore","AddScore","PlatformScore","ChargeScore","TotalScore",
				"Rank","Rank1"};*/
    	String[] header = new String[] { "??????","??????","??????","????????????","????????????","????????????","???????????????","??????????????????",
    									"??????????????????","??????", "??????","??????"};
    	String[] column = new String[] { "Name","Id", "TotalScore", "DevelopScore", "MaintainScore","ReviewScore", 
							"AddScore","PlatformScore", "Rank","Position","Positiontype","DetailUrl"};
    	Map<String, Object> param = new HashMap<>();
    	Map<String, Object> rankMap = new HashMap<>();
    	//????????????
    	if(!employeeListReqVO.getSeconddpt().equals("??????") && !employeeListReqVO.getSeconddpt().equals("")) {
    		param.put("seconddptcode", employeeListReqVO.getSeconddpt());
    		rankMap.put("seconddptcode", employeeListReqVO.getSeconddpt());
    	}
    	if(!employeeListReqVO.getThirddpt().equals("??????") && !employeeListReqVO.getThirddpt().equals("")) {
    		param.put("thirddptcode", employeeListReqVO.getThirddpt());
    	}
    	if(!employeeListReqVO.getCurrentdpt().equals("??????") && !employeeListReqVO.getCurrentdpt().equals("")) {
    		param.put("currentdptcode", employeeListReqVO.getCurrentdpt());
    	}
    	if(!employeeListReqVO.getFivedpt().equals("??????") && !employeeListReqVO.getFivedpt().equals("")) {
    		param.put("fivedptcode", employeeListReqVO.getFivedpt());
    	}
    	if(!employeeListReqVO.getCity().equals("??????") && !employeeListReqVO.getCity().equals("")) {
    		param.put("position", employeeListReqVO.getCity());
    	}
    	if(!employeeListReqVO.getPostType().equals("??????") && !employeeListReqVO.getPostType().equals("")) {
    		param.put("postType", employeeListReqVO.getPostType());
    		rankMap.put("postType", employeeListReqVO.getPostType());
    	}
    	if(!employeeListReqVO.getPositiontype().equals("??????") && !employeeListReqVO.getPositiontype().equals("")) {
    		if(employeeListReqVO.getPositiontype().equals("ODC??????")) {
    			param.put("type", "RDODC");
    		}else {
    			param.put("type", employeeListReqVO.getPositiontype());
    		}
    	}
    	if(!employeeListReqVO.getQuarter().equals("") && employeeListReqVO.getQuarter()!=null) {
    		param.put("quarter", employeeListReqVO.getQuarter());
    		rankMap.put("quarter", employeeListReqVO.getQuarter());
    	}
    	//????????????
    	int sortedFlag = employeeListReqVO.getSortedFlag();
    	//????????????????????????0???????????????1???????????????
    	int flag = employeeListReqVO.getFlag();
    	
    	if(StringUtils.isNotBlank(employeeListReqVO.getEmployeeSearchKey())) {
    		param.put("employeeSearchKey", employeeListReqVO.getEmployeeSearchKey());
    		//rankMap.put("employeeSearchKey", employeeListReqVO.getEmployeeSearchKey());
    	}
    	//com.github.pagehelper.page.PageMethod.startPage(pageNum,pageSize);
    	List<EmployeeListResVO> listForEmployee = quarterScoreService.getListForEmployee(param);
    	List<RankResVO> rankForEmployee = quarterScoreService.getRankForEmployee(rankMap);
    	List<RankResVO> rank1ForEmployee = quarterScoreService.getRank1ForEmployee(rankMap);
    	
    	for (int i = 0; i < listForEmployee.size(); i++) {
    		//??? ODc??????????????????
			if(listForEmployee.get(i).getPositiontype().equals("RDODC")) {
				listForEmployee.get(i).setPositiontype("ODC??????");
			}
			//????????????????????????????????????
			if(listForEmployee.get(i).getSpecial().equals("0")) {
				listForEmployee.get(i).setSpecial("???");
			}else {
				listForEmployee.get(i).setSpecial("???");
			}
			//?????????????????????
			int rank = RankUtil.rank(rankForEmployee, listForEmployee.get(i).getAccount());
			listForEmployee.get(i).setRank(rank);
			int rank1 = RankUtil.rank(rank1ForEmployee, listForEmployee.get(i).getAccount());
			listForEmployee.get(i).setRank1(rank1);
			listForEmployee.get(i).setPostType(SystemConstant.SOFTWARE_DEV);
			//???????????????url:????????????????????????+name=??????&id=??????&postType=??????&currentQuarter=??????????????????????????????
			//???????????????????????????2?????????????????????????????????????????????
			String encoderName = SystemCommonUtil.getURLEncoderString(SystemCommonUtil.getURLEncoderString(listForEmployee.get(i).getName()));
			String encoderPostType = SystemCommonUtil.getURLEncoderString(SystemCommonUtil.getURLEncoderString(listForEmployee.get(i).getPostType()));
			String url=urlPrifix+"/individual?name="+encoderName+"&id="+listForEmployee.get(i).getId()+
					"&postType="+encoderPostType+"&currentQuarter="+listForEmployee.get(i).getQuarter();
			listForEmployee.get(i).setDetailUrl(url);
		}
    	
    	//?????????????????????(??????????????????????????????????????????)(??????1  ??????2 ??????3 ????????????4 ????????????5	  ????????????6	???????????????7	  ???????????????8  ??????????????????0	??????9  ??????10)
    	if(flag==0) {
    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getRank)).collect(Collectors.toList());
    	}else {
    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getRank).reversed()).collect(Collectors.toList());
    	}
		if(sortedFlag==1) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getName)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getName).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==2) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getId)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getId).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==3) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getTotalScore)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getTotalScore).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==4) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getDevelopScore)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getDevelopScore).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==5) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getMaintainScore)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getMaintainScore).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==6) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getReviewScore)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getReviewScore).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==7) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getAddScore)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getAddScore).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==8) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getPlatformScore)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getPlatformScore).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==9) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getPosition)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getPosition).reversed()).collect(Collectors.toList());
	    	}
		}else if(sortedFlag==10) {
	    	if(flag==0) {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getPositiontype)).collect(Collectors.toList());
	    	}else {
	    		listForEmployee = listForEmployee.stream().sorted(Comparator.comparing(EmployeeListResVO::getPositiontype).reversed()).collect(Collectors.toList());
	    	}
		}
    	ExportExcelWrapper<EmployeeListResVO> excelWrapper = new ExportExcelWrapper<>();
    	//PageInfo<EmployeeListResVO> pageInfo = new PageInfo<>(listForEmployee);
		excelWrapper.exportExcel("EmployeeScoreExport", "??????????????????????????????", header, column, listForEmployee, response, "2007",true, "Id");
    }
    
    private Boolean checkPerson() {
    	String currentUserId = UserUtils.getCurrentUserId();
    	//??????????????????,????????????????????????????????????
    	AdminListDO adminListDO = adminListService.getById(currentUserId);
    	List<DptmanagerListDO> dptmanagerListDO = dptmanagerListService.getById(currentUserId);
    	if(adminListDO==null && dptmanagerListDO.isEmpty()) {
    		return false;
    	}
		return true;
	}
    
    
	
	
	
	
}

