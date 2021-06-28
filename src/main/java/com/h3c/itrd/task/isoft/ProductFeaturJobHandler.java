package com.h3c.itrd.task.isoft;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.h3c.itrd.common.consts.LengthLimit;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.entity.IsoftProductFeatureDO;
import com.h3c.itrd.core.service.IsoftProductFeatureService;
import com.h3c.itrd.util.AbstractExcelReader;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

@JobHandler(value="productFeaturJobHandler")
@Component
public class ProductFeaturJobHandler extends IJobHandler {
	@Autowired
	IsoftProductFeatureService isoftProductFeatureService;
	@Value("${product.feature.file.path}")
	private String featureFilePath;
	@Value("${product.feature.sheet.name}")
	private String featureSheetName;
	
	@Override
	public ReturnT<String> execute(String param) throws Exception {
		FileInputStream file=new FileInputStream(featureFilePath);
		List<IsoftProductFeatureDO> lstEdit = new ArrayList<>();
		String fileName = featureFilePath.substring(featureFilePath.lastIndexOf("/")+1);
		AbstractExcelReader excelReader = new AbstractExcelReader(file, fileName,featureSheetName);
		DecimalFormat df = new DecimalFormat("0.000");
		while (excelReader.hasNextRow()) {
			IsoftProductFeatureDO dic = new IsoftProductFeatureDO();
			Row row = excelReader.nextRow();
			String projectName = excelReader.readCellValue(row.getCell(0));
			String businessGroup = excelReader.readCellValue(row.getCell(1));
			String featureTeam = excelReader.readCellValue(row.getCell(2));
			String devPerson = excelReader.readCellValue(row.getCell(3));
			String autoAuditResult = excelReader.readCellValue(row.getCell(4));
			String manAuditResult = excelReader.readCellValue(row.getCell(5));
			double autoAuditDeduct = row.getCell(6).getNumericCellValue();
			double manAuditDeduct = row.getCell(7).getNumericCellValue();
			double totalScore = row.getCell(8).getNumericCellValue();
			double auditScore = row.getCell(9).getNumericCellValue();
			dic.setProjectName(projectName);
			dic.setBusinessGroup(businessGroup.substring(0, businessGroup.length()>LengthLimit.PF_BUSINESS_GROUP?LengthLimit.PF_BUSINESS_GROUP:businessGroup.length()));
			dic.setFeatureTeam(featureTeam);
			dic.setDevPerson(devPerson.substring(0, devPerson.length()>LengthLimit.PF_DEV_PERSON?200:devPerson.length()));
			dic.setAutoAuditResult(autoAuditResult.substring(0, autoAuditResult.length()>LengthLimit.PF_AUTO_AUDIT_RESULT?LengthLimit.PF_AUTO_AUDIT_RESULT:autoAuditResult.length()));
			dic.setManAuditResult(manAuditResult.substring(0, manAuditResult.length()>LengthLimit.PF_MAN_AUDIT_RESULT?LengthLimit.PF_MAN_AUDIT_RESULT:manAuditResult.length()));
			dic.setAutoAuditDeduct(new BigDecimal(String.valueOf(df.format(autoAuditDeduct))));
			dic.setManAuditDeduct(new BigDecimal(String.valueOf(df.format(manAuditDeduct))));
			dic.setTotalScore(new BigDecimal(String.valueOf(df.format(totalScore))));
			dic.setAuditScore(new BigDecimal(String.valueOf(df.format(auditScore))));
			dic.setSyncTime(new Date());
			lstEdit.add(dic);
		}
		Boolean flag =false;
		//更新同步数据
		if(CollectionUtils.isNotEmpty(lstEdit)) {
			flag = isoftProductFeatureService.batchRenew(lstEdit);
		}
		if (flag)
			return SUCCESS;
		else 
			return FAIL;
	}

}
