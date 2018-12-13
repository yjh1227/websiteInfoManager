package com.yjh.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yjh.model.Admin;

/**
 * @author yjh
 */
public class AdminDaoImpi implements AdminDao {
	
	@Override
	public Admin findById(String id) {
		String sql = "SELECT * FROM admin WHERE id = ?";
		List<Map<String, Object>> table = null;
		try {
			table = DBUtil.executeQuery(sql, new Object[] {id});
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(table == null || table.size() != 1) {
			return null;
		}
		
		Map<String, Object> record = table.get(0);
		return new Admin( ((String)record.get("id")).trim(), ((String)record.get("pass")).trim() );
	}
}
