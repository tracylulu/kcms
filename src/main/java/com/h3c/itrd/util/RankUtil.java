package com.h3c.itrd.util;

import org.apache.commons.lang.ObjectUtils;

import com.h3c.itrd.core.vo.RankResVO;

import java.util.List;

public class RankUtil {
	/**
	 * 排序公共方法
	 * @param
	 * @return
	 */
	public static  int rank(List<RankResVO> ranklist, String account){
		for (int i = 0; i < ranklist.size(); i++) {
			if(i==0){
				ranklist.get(i).setRownum(1);
			}else{
				ranklist.get(i).setRownum(i+1);
				if(ranklist.get(i).getScore().equals(ranklist.get(i-1).getScore())){
					ranklist.get(i).setRownum(ranklist.get(i-1).getRownum());
				}
			}
		}
		int rank=0;
		for (int j = 0; j < ranklist.size(); j++) {
			if(ranklist.get(j).getAccount().equals(account)){
				rank=ranklist.get(j).getRownum();
			}
		}
		return rank;
	}

}
