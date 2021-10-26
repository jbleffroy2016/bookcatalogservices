package com.tcs.dao;

public interface AuditLogDAO {
	
	public int updateAuditLog(String event) throws Exception;

}
