package com.h3c.itrd.core.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.h3c.itrd.common.annotation.OperLog;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.entity.AddScoreDO;
import com.h3c.itrd.core.entity.EmployeeListDO;
import com.h3c.itrd.core.entity.NoticeListDO;
import com.h3c.itrd.core.service.AddScoreService;
import com.h3c.itrd.core.service.EmployeeListService;
import com.h3c.itrd.core.service.NoticeListService;
import com.h3c.itrd.util.AbstractExcelReader;
import com.h3c.itrd.util.DateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * <p>
 * 管理员 前端控制器
 * </p>
 *
 * @author cYS1425
 * @since 2021-04-30
 */
@RestController
@RequestMapping("/api/admin")
@ApiIgnore
@Api(value = "管理员模块相关接口", tags = {"管理员模块相关接口"})
public class AdminController {
	private static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
    private AddScoreService addScoreService;
    @Autowired
    private EmployeeListService employeeListService;


    @GetMapping("/addScoreAdmin")
    @ApiOperation(value = "关键事件导入页面展示列表接口—add_score_admin")
    @OperLog(operModelName="关键事件导入页面展示列表接口",operContent="关键事件导入页面展示列表接口")
    public ResponseResult addScoreAdmin(@RequestParam @ApiParam(name = "userId", value = "userId", required = true) String userId) throws Exception{
        List<AddScoreDO> list = addScoreService.getListByInsertUserId(Integer.parseInt(userId));
        return ResponseResult.success(0, "查询成功",list,list.size());
    }

    @PostMapping("/importAddScoreExcel")
    @ApiOperation(value = "关键事件导入接口—add_score")
    @OperLog(operModelName="关键事件导入接口",operContent="关键事件导入接口")
    public ResponseResult importAddScoreExcel(@RequestParam("file")MultipartFile file,
                @RequestParam @ApiParam(name = "insertUserId", value = "insertUserId", required = true) String insertUserId) throws Exception {
        StringBuffer  err= new StringBuffer();
        String fileName = file.getOriginalFilename();
        if(fileName.indexOf("\\")>-1){
            fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
        }
        AbstractExcelReader excelReader = new AbstractExcelReader(file.getInputStream(), fileName);

        //数据验证处理
        List<AddScoreDO> list = new ArrayList<>();
        while (excelReader.hasNextRow()){
            AddScoreDO addScoreDO=new AddScoreDO();
            Row row = excelReader.nextRow();
            String userId = this.modifyUserId(excelReader.readCellValue(row.getCell(1)));
            if(StringUtils.isBlank(userId)) {
                err.append("第 "+(row.getRowNum()+1)+"行数据有误，工号不能为空，请核对数据！<br/>");
            }else {
                EmployeeListDO employee = employeeListService.getEmployeeById(userId);
                if (employee == null) {
                    err.append("第 "+(row.getRowNum()+1)+"行数据有误，工号有误，请核对数据！<br/>");
                }else{
                    addScoreDO.setUserId(userId);
                    addScoreDO.setName(employee.getName());
                    addScoreDO.setDptcode(employee.getSeconddptcode());
                }
            }
            addScoreDO.setProject(excelReader.readCellValue(row.getCell(3)));
            addScoreDO.setScore(new BigDecimal(excelReader.readCellValue(row.getCell(4))));
            addScoreDO.setQuarter(excelReader.readCellValue(row.getCell(5)));
            addScoreDO.setRemark(excelReader.readCellValue(row.getCell(6)));
            addScoreDO.setInsertUserId(Integer.parseInt(insertUserId));
            Date date =new Date();
            addScoreDO.setInsertTime(DateUtil.getDateStrForDefault(date));
            addScoreDO.setAuditId(0);
            addScoreDO.setAuditTime("");
            list.add(addScoreDO);
        }
        //保存数据
        if(StringUtils.isNotBlank(err.toString())) {
            return ResponseResult.fail(1,err.toString());
        }else {
            //存储数据，更新表
            for (int i = 0; i< list.size(); i++) {
                int row = addScoreService.add(list.get(i));
                if(row==0){
                    return ResponseResult.fail(1,"第"+(i+2)+"行数据插入失败");
                }
            }
        }
        return ResponseResult.success("导入成功");
    }

    public String modifyUserId(String userId) throws Exception{
        if (userId.length() < 5){
            userId = "0"+userId;
            if (userId.length() < 5){
                userId = "0"+userId;
            }
        }
        return userId;
    }
	
	
	
	
	
	
	
}

