package com.yjh.dao;

import com.yjh.model.Admin;

/**
 * admin表数据访问层接口
 * 
 * @author yjh
 *
 */
public interface AdminDao {
	/**
	 * 根据主键id查找用户，若未找到返回null
	 */
	public Admin findById(String id);
}
