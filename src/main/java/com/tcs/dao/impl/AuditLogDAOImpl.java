package com.tcs.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tcs.dao.AuditLogDAO;

@Repository
public class AuditLogDAOImpl implements AuditLogDAO{
	
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public int updateAuditLog(String event) throws Exception {
		String sql = "insert into auditlog (event, update_time) values ( :event, CURRENT_TIMESTAMP)";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("event", event);
		
		return jdbcTemplate.update(sql, params);
	}

}
