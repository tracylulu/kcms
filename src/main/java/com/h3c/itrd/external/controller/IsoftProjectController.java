package com.h3c.itrd.external.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.h3c.itrd.common.annotation.OperLog;
import com.h3c.itrd.common.consts.LengthLimit;
import com.h3c.itrd.common.entity.ResponseResult;
import com.h3c.itrd.core.entity.IsoftProductFeatureDO;
import com.h3c.itrd.core.entity.IsoftProductIdentificationDO;
import com.h3c.itrd.core.entity.IsoftProjectListDO;
import com.h3c.itrd.core.service.IsoftProductFeatureService;
import com.h3c.itrd.core.service.IsoftProductIdentificationService;
import com.h3c.itrd.core.service.IsoftProjectListService;
import com.h3c.itrd.util.AbstractExcelReader;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

//@CrossOrigin
@RestController
@RequestMapping("/isoftProject")
@Api(value = "isoft产品相关", tags = "isoft产品相关")
//@PropertySource(value="classpath:resources/application-dev.properties", encoding="UTF-8")
public class IsoftProjectController {
	@Autowired
	IsoftProductFeatureService isoftProductFeatureService;
	@Autowired
	IsoftProductIdentificationService isoftProductIdentificationService;
	@Value("${product.feature.file.path}")
	private String featureFilePath;
	@Value("${product.feature.sheet.name}")
	private String featureSheetName;
	@Value("${product.code.identification.file.path}")
	private String identificationFilePath;
	@Value("${product.code.identification.sheet.name}")
	private String identificationSheetName;
	@Value("${file.path}")
	private String filePath;

	@GetMapping("/getProductFeature")
	@ApiOperation(value = "获取Comware产品特性审计结果")
	@OperLog(operModelName="外围系统-isoft",operContent="获取Comware产品特性审计结果")
	public ResponseResult getProductFeature() throws IOException, Exception {
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
			return ResponseResult.success("更新成功！");
		else 
			return ResponseResult.fail("更新失败");
	}
	
	@GetMapping("/getProductIdentification")
	@ApiOperation(value = "获取产品代码鉴定")
	@OperLog(operModelName="外围系统-isoft",operContent="获取产品代码鉴定")
	public ResponseResult importBudget() throws IOException, Exception {
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

		//修改
		if(CollectionUtils.isNotEmpty(lstEdit)) {
			isoftProductIdentificationService.batchRenew(lstEdit);
		}

		return ResponseResult.success("更新成功！");
	}
	
	@PostMapping("/updateFile")
	@ApiOperation(value = "上传Comware产品审计结果文件")
	@OperLog(operModelName="外围系统-isoft",operContent="上传Comware产品审计结果文件")
	public ResponseResult updateFile(@RequestBody @ApiParam(name = "fileParamMap", value = "fileParamMap", required = true)Map<String,String> fileParamMap) throws IOException, Exception {
		String base64 = fileParamMap.get("base64");
		String fileName =fileParamMap.get("fileName");
		byte[] buffer = new BASE64Decoder().decodeBuffer(base64);
		File dest =new File(filePath+fileName);
		dest.getParentFile().mkdirs();
		dest.createNewFile();
		FileOutputStream outputFile = new FileOutputStream(filePath+fileName);
		outputFile.write(buffer);
		outputFile.close();
		return ResponseResult.success("文件上传成功");
	}
	
//	@PostMapping("/updateFile")
//	@ApiOperation(value = "上传Comware产品审计结果文件")
//	@OperLog(operModelName="外围系统-isoft",operContent="上传Comware产品审计结果文件")
//	public ResponseResult updateFile() throws IOException, Exception {
//		File file=new File("C:\\Users\\c17740\\Downloads\\Report.xlsm");
//		if(!file.isFile()){
//        	return ResponseResult.fail("文件不存在");
//        }
//		FileInputStream inputFile = new FileInputStream(file);
//		byte[] buffer = new byte[(int) file.length()];
//		inputFile.read(buffer);
//		inputFile.close();
//		String base64 = new BASE64Encoder().encode(buffer);
//		byte[] buffer2 = new BASE64Decoder().decodeBuffer(base64);
//		File dest =new File("C:\\Users\\c17740\\Downloads\\new\\Report.xlsm");
//		dest.getParentFile().mkdirs();
//		dest.createNewFile();
//		FileOutputStream outputFile = new FileOutputStream("C:\\Users\\c17740\\Downloads\\new\\Report.xlsm");
//		outputFile.write(buffer2);
//		outputFile.close();
////		String fileName = file.getName();
////			File dest =new File("C:\\Users\\c17740\\Downloads\\new\\Report.xlsm");
////			dest.getParentFile().mkdirs();
////            dest.createNewFile();
////		}
////            file.transferTo(dest);
//		return ResponseResult.success();
//	}
}
