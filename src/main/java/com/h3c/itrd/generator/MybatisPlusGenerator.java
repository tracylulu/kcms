package com.h3c.itrd.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 代码生成器
 *  1.设置相关属性、规则
 *  2.在该文件内直接执行 run
 *  3.检查目标文件是否生成
 * @author s21046
 * @date 2021年4月28日
 */
public class MybatisPlusGenerator {
	public static void main(String[] args) {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        // 是否支持AR模式
        config.setActiveRecord(true)
                // 作者
                .setAuthor("cYS1425")
                // 生成路径，最好使用绝对路径，window路径是不一样的
                //TODO  TODO  TODO  TODO
                .setOutputDir("C:\\mybatisplusGenerateCode")
                // 文件覆盖
                .setFileOverride(true)
                // 主键策略
                .setIdType(IdType.AUTO)

                .setDateType(DateType.ONLY_DATE)
                // 设置生成的service接口的名字的首字母是否为I，默认Service是以I开头的
                .setServiceName("%sService")

                //实体类结尾名称
                .setEntityName("%sDO")

                //生成基本的resultMap
                .setBaseResultMap(true)

                //不使用AR模式
                .setActiveRecord(false)

                //生成基本的SQL片段
                .setBaseColumnList(true);

        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        // 设置数据库类型
        dsConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.cj.jdbc.Driver")
                //TODO  TODO  TODO  TODO
                .setUrl("jdbc:mysql://10.165.25.69:3310/kcmsdbts?useSSL=false")
                .setUsername("h3ckcms")
                .setPassword("gB*mvRHF*57b");

        //3. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();

        //全局大写命名
        stConfig.setCapitalMode(true)
                // 数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.underline_to_camel)

                //使用lombok
                .setEntityLombokModel(true)

                //使用restcontroller注解
                .setRestControllerStyle(true)

                // 生成的表, 支持多表一起生成，以数组形式填写
                //TODO  TODO  TODO  TODO 两个方式，直接写，或者使用命令行输入
                .setInclude(
                        //"add_score" ,  "add_score_audit" , "add_score_select" , "admin_list" , "analyzeinfo_list" , "cbbinfo_list" , "charge_score"
                        //"department_list" ,"design_hardware_score" ,  "design_list" , "design_score","dptaveragescore_list" ,  "dptconfig_hardwaretest_list" , "dptconfig_hardware_list"
                        //"dptconfig_list" , "dptconfig_softtest_list" ,"dptmanager_list" , "employee_list" , "employee_resign_list" ,  "execute_score" ,  "experienceinfo_list"
                        //,"feedback","hardware_test_example","hardware_test_script","hardware_test_tool","hcmminfo_b_list" ,  "hcmminfo_r_list" , "hcmm_summary" , "idmsinfo_list" , "idmsinfo_new_list" , "idm_employee"
                        //"idm_hardware" , "isoft_contribution_list" ,"isoft_project_cloud_list" ,  "isoft_project_list" , "isoft_project_new_list","isoft_workschedule" ,  "limsinfo_list"
                       // ,"lims_summary" , "login_summary" , "mail_info","notice_list" ,"operation_log", "patentinfo_list" , "pdminfo_list" , "platform_summary" ,"project_su"
                       // "project_summary" , "qmsinfo_list","quarter_score" , "reviewinfo_bak_list" , "reviewinfo_cloud_list" , "reviewinfo_list" , "review_summary"
                       // ,"safetyinfo_list" ,"safety_summary" ,  "sharedocinfo_list" ,  "specinfo_list" , "svn_list","white_list" , "ynfxinfo_list"
                      //"post_type_constant","isoft_check_type_constant"  
                	//重新生成"idmsinfo_list"这个表，因位其中的字段release为mysql的关键字，导致查询有问题，换成release1
                	//	"idmsinfo_list"
                	//重新生成"safetyinfo_list"这个表，因位其中的字段release为mysql的关键字，导致查询有问题，换成release1
                	//	"safetyinfo_list"
                	//"isoft_workschedule" "pdminfo_list""hcmminfo_b_list" ,  "hcmminfo_r_list" , "safetyinfo_list" 加上同步标识的2个字段
                	//	"hcmminfo_b_list" ,  "hcmminfo_r_list" ,"safetyinfo_list"
                	//	"limsinfo_list" 加上同步标识的2个字段,新建了一个表
                	//	"limsinfo_list" ,"project_quarter_work_list"
                	//软件开发V模型项目汇算逻辑增加或修改的表
                	//"isoft_project_list","project_acceptance_problems_list","idmsinfo_list","project_summary",
                	//"project_summary_v","project_problems_di"
                	//	"project_summary_v"
                	//软件开发的配置表dptconfig_list中增加5个系数。
                	//	"dptconfig_list"
                	//	"project_summary_v"
                	//notice_read_record 各个员工最新公告阅读记录表
                	//	"notice_read_record"
                	//project_problems_di 新加一个字段 '验收测试总DI'	
                	//	"project_problems_di","project_su_v","project_summary"
                	//litongxin说要建的中间表
                	//	"reviewinfo_middle_list"
                	//idm_employee   review_summary加上定时汇算日期
                	//	"idm_employee","review_summary"
                	//platform_summary 加上定时汇算日期
                	//	"platform_summary"
                	//project_problems_di  中的int类型改成decimal
                	//	"project_problems_di"
                	//验证得分之前php代码有问题，得加上15这个默认配置
                		"dptconfig_list"
                );
                //.setInclude(scanner("表名，多个英文逗号分割").split(","));

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.h3c.itrd.core")
                .setMapper("mapper")
                .setService("service")
                .setController("controller")
                .setEntity("entity")
                .setXml("mapperXml");

        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        //6. 执行操作
        ag.execute();
        System.out.println("======= 相关代码生成完毕  ========");
    }

    
}
