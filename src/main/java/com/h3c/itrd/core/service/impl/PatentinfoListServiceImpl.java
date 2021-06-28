package com.h3c.itrd.core.service.impl;

import com.h3c.itrd.core.entity.PatentinfoListDO;
import com.h3c.itrd.core.mapper.PatentinfoListMapper;
import com.h3c.itrd.core.service.PatentinfoListService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 专利信息表 服务实现类
 * </p>
 *
 * @author cYS1425
 * @since 2021-05-07
 */
@Service
public class PatentinfoListServiceImpl extends ServiceImpl<PatentinfoListMapper, PatentinfoListDO> implements PatentinfoListService {
	@Autowired
    private PatentinfoListMapper patentinfoListMapper;
    @Override
    public List<PatentinfoListDO> getPatentinfoList(String account, String start, String end) {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.eq("inventorid",account);
        queryWrapper.between("applyDate",start,end);
        queryWrapper.select("patentName","patentType","patentNo","applyDate");
        return patentinfoListMapper.selectList(queryWrapper);
    }
    @Override
    public void batchInsertInfo(List<PatentinfoListDO> patentinfoList) {
    	patentinfoListMapper.batchInsertInfo(patentinfoList);
    };
    
    @Override
    public void deleteAllInfo() {
    	patentinfoListMapper.deleteAllInfo();
    };
    @Override
    public int selectCount() {
    	return patentinfoListMapper.selectCount();
    }
    @Override
    public int selectPatentNoCount() {
    	return patentinfoListMapper.selectPatentNoCount();
    }
    @Override
    @Transactional
	public void batchRenewInfo(List<PatentinfoListDO> patentinfoList) {
    	deleteAllInfo();
    	batchInsertInfo(patentinfoList);
    }
	@Override
	public List<PatentinfoListDO> getListForPlatform(String start, String end) {
		QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.between("applyDate",start,end);
	    return patentinfoListMapper.selectList(queryWrapper);
	};
}
