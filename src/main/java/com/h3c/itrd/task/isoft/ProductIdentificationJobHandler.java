package com.h3c.itrd.task.isoft;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import com.h3c.itrd.core.entity.IsoftProductIdentificationDO;
import com.h3c.itrd.core.service.IsoftProductIdentificationService;
import com.h3c.itrd.util.AbstractExcelReader;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

@JobHandler(value="productIdentiJobHandler")
@Component
public class ProductIdentificationJobHandler extends IJobHandler {
	@Autowired
	IsoftProductIdentificationService isoftProductIdentificationService;
	@Value("${product.code.identification.file.path}")
	private String identificationFilePath;
	@Value("${product.code.identification.sheet.name}")
	private String identificationSheetName;

	@Override
	public ReturnT<String> execute(String param) throws Exception {
		FileInputStream file=new FileInputStream(identificationFilePath);
		List<IsoftProductIdentificationDO> lstEdit = new ArrayList<>();
		String fileName = identificationFilePath.substring(identificationFilePath.lastIndexOf("/")+1);
		AbstractExcelReader excelReader = new AbstractExcelReader(file, fileName,identificationSheetName);
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat df = new DecimalFormat("0.000");
		while (excelReader.hasNextRow()) {
			IsoftProductIdentificationDO dic = new IsoftProductIdentificationDO();
			Row row = excelReader.nextRow();
			String createDate = excelReader.readCellValue(row.getCell(0));
			String pdStatus = excelReader.readCellValue(row.getCell(1));
			String appraise = excelReader.readCellValue(row.getCell(2));
			String appraiseId = appraise.substring(0, appraise.indexOf(" "));
			String place = excelReader.readCellValue(row.getCell(3));
			String assembly = excelReader.readCellValue(row.getCell(4));
			String module = excelReader.readCellValue(row.getCell(5));
			Double scale = row.getCell(6).getNumericCellValue();
			Double spotCheck = row.getCell(7).getNumericCellValue();
			String devPerson = excelReader.readCellValue(row.getCell(8));
			String identPerson = excelReader.readCellValue(row.getCell(9));
			Double di = row.getCell(10).getNumericCellValue();
			String diEva = excelReader.readCellValue(row.getCell(11));
			String appraiseEva = row.getCell(12).getStringCellValue();
			String description = excelReader.readCellValue(row.getCell(13));
			Double deadly = row.getCell(14).getNumericCellValue();
			Double serious = row.getCell(15).getNumericCellValue();
			Double common = row.getCell(16).getNumericCellValue();
			Double prompt = row.getCell(17).getNumericCellValue();
			String form = excelReader.readCellValue(row.getCell(18));
			String tag = excelReader.readCellValue(row.getCell(19));
			String failed = excelReader.readCellValue(row.getCell(20));
			String warning = excelReader.readCellValue(row.getCell(21));
			String featureTeam = excelReader.readCellValue(row.getCell(22));
			String density = excelReader.readCellValue(row.getCell(23));
			String beComponent = excelReader.readCellValue(row.getCell(24));
			String base = excelReader.readCellValue(row.getCell(25));
			String identifyDays = excelReader.readCellValue(row.getCell(26));
			String Ext1 = excelReader.readCellValue(row.getCell(27));
			String other = excelReader.readCellValue(row.getCell(28));
			String v7V9 = excelReader.readCellValue(row.getCell(29));
			dic.setCreateDate(" ".equals(createDate)? null:sdf.parse(createDate));
			dic.setPdStatus(pdStatus.substring(0, pdStatus.length()>LengthLimit.PI_PD_STATUS?LengthLimit.PI_PD_STATUS:pdStatus.length()));
			dic.setAppraiseId(appraiseId);
			dic.setAppraise(appraise.substring(0, appraise.length()>LengthLimit.PI_APPRAISE?LengthLimit.PI_APPRAISE:appraise.length()));
			dic.setPlace(place.substring(0, place.length()>LengthLimit.PI_PLACE?LengthLimit.PI_PLACE:place.length()));
			dic.setAssembly(assembly.substring(0, assembly.length()>LengthLimit.PI_ASSEMBLY?LengthLimit.PI_ASSEMBLY:assembly.length()));
			dic.setModule(module.substring(0, module.length()>LengthLimit.PI_MODULE?LengthLimit.PI_MODULE:module.length()));
			dic.setScale(new BigDecimal(String.valueOf(df.format(scale))));
			dic.setSpotCheck(new BigDecimal(String.valueOf(df.format(spotCheck))));
			dic.setDevPerson(devPerson.substring(0, devPerson.length()>LengthLimit.PI_DEV_PERSON?LengthLimit.PI_DEV_PERSON:devPerson.length()));
			dic.setIdentPerson(identPerson.substring(0, identPerson.length()>LengthLimit.PI_IDENT_PERSON?LengthLimit.PI_IDENT_PERSON:identPerson.length()));
			dic.setDi(new BigDecimal(String.valueOf(df.format(di))));
			dic.setDiEva(diEva.substring(0, diEva.length()>LengthLimit.PI_DI_EVA?LengthLimit.PI_DI_EVA:diEva.length()));
			dic.setAppraiseEva(appraiseEva.substring(0, appraiseEva.length()>LengthLimit.PI_APPRAISE_EVA?LengthLimit.PI_APPRAISE_EVA:appraiseEva.length()));
			dic.setDescription(description.substring(0, description.length()>LengthLimit.PI_DESCRIPTION?LengthLimit.PI_DESCRIPTION:description.length()));
			dic.setDeadly(new BigDecimal(String.valueOf(df.format(deadly))));
			dic.setSerious(new BigDecimal(String.valueOf(df.format(serious))));
			dic.setCommon(new BigDecimal(String.valueOf(df.format(common))));
			dic.setPrompt(new BigDecimal(String.valueOf(df.format(prompt))));
			dic.setForm(form.substring(0, form.length()>LengthLimit.PI_FORM?LengthLimit.PI_FORM:form.length()));
			dic.setTag(tag.substring(0, tag.length()>LengthLimit.PI_TAG?LengthLimit.PI_TAG:tag.length()));
			dic.setFailed(failed =="" ? 0:Integer.valueOf(failed));
			dic.setWarning(warning.substring(0, warning.length()>LengthLimit.PI_WARNING?LengthLimit.PI_WARNING:warning.length()));
			dic.setFeatureTeam(featureTeam.substring(0, featureTeam.length()>LengthLimit.PI_FEATURE_TEAM?LengthLimit.PI_FEATURE_TEAM:featureTeam.length()));
			dic.setDensity(density.substring(0, density.length()>LengthLimit.PI_DENSITY?LengthLimit.PI_DENSITY:density.length()));
			dic.setBeComponent(beComponent.substring(0, beComponent.length()>LengthLimit.PI_BE_COMPONENT?LengthLimit.PI_BE_COMPONENT:beComponent.length()));
			dic.setBase(base =="" ? 0:Integer.valueOf(base));
			dic.setIdentifyDays(identifyDays =="" ? 0:Integer.valueOf(identifyDays));
			dic.setExt1(Ext1.substring(0, Ext1.length()>LengthLimit.PI_EXT1?LengthLimit.PI_EXT1:Ext1.length()));
			dic.setOther(other =="" ? 0:Integer.valueOf(other));
			dic.setV7V9(v7V9.substring(0, v7V9.length()>LengthLimit.PI_V7_V9?LengthLimit.PI_V7_V9:v7V9.length()));
			dic.setSyncTime(new Date());
			lstEdit.add(dic);
		}
		Boolean flag =false;
		//修改
		if(CollectionUtils.isNotEmpty(lstEdit)) {
			flag=isoftProductIdentificationService.batchRenew(lstEdit);
		}
		if (flag)
			return SUCCESS;
		else 
			return FAIL;
	}
}
