package com.h3c.itrd.core.controller;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.transaction.annotation.Transactional;
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
import com.h3c.itrd.core.entity.EmployeeListDO;
import com.h3c.itrd.core.entity.LoginSummaryDO;
import com.h3c.itrd.core.entity.NoticeListDO;
import com.h3c.itrd.core.service.AddScoreService;
import com.h3c.itrd.core.service.AdminListService;
import com.h3c.itrd.core.service.DepartmentListService;
import com.h3c.itrd.core.service.DptmanagerListService;
import com.h3c.itrd.core.service.EmployeeListService;
import com.h3c.itrd.core.service.LoginSummaryService;
import com.h3c.itrd.core.service.NoticeListService;
import com.h3c.itrd.core.service.NoticeReadRecordService;
import com.h3c.itrd.core.service.QuarterScoreService;
import com.h3c.itrd.core.vo.DepartmentListForAdminVO;
import com.h3c.itrd.core.vo.DepartmentListReqVO;
import com.h3c.itrd.core.vo.DepartmentListResVO;
import com.h3c.itrd.core.vo.DepartmentListVO;
import com.h3c.itrd.core.vo.EmployeeListReqVO;
import com.h3c.itrd.core.vo.EmployeeListResVO;
import com.h3c.itrd.core.vo.OverviewReqVO;
import com.h3c.itrd.core.vo.RankResVO;
import com.h3c.itrd.util.AbstractExcelReader;
import com.h3c.itrd.util.DateTimeUtils;
import com.h3c.itrd.util.DateUtil;
import com.h3c.itrd.util.ExportExcelWrapper;
import com.h3c.itrd.util.PagingList;
import com.h3c.itrd.util.RankUtil;
import com.h3c.itrd.util.UserUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 公共方法  前端控制器
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-18
 */
@RestController
@RequestMapping("/api/test")
@Api(value = "测试使用", tags = {"测试使用"})
//@Transactional
public class TestController {
	private static Logger logger = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
    private NoticeReadRecordService noticeReadRecordService;
	@Autowired
    private AdminListService adminListService;
	@Autowired
    private EmployeeListService employeeListService;
	
	@GetMapping("/delete_noticeReadRecord")
    @ApiOperation(value = "删除通知阅读记录接口—delete_noticeReadRecord")
	@OperLog(operModelName="测试使用",operContent="测试使用")
    public ResponseResult setAdmin(@RequestParam @ApiParam(name = "id", value = "工号id", required = true) String id) throws Exception{
    	
		int row = noticeReadRecordService.deleteByEmployeeId(id);
		int i=0;
		i=1/0;
		//报错没有删除成功，但是没有日志记录
		return ResponseResult.success(0, "新增成功");
    }
    
	
	
	
	
}

