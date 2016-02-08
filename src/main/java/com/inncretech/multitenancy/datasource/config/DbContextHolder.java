package com.inncretech.multitenancy.datasource.config;

public class DbContextHolder {

	private static final ThreadLocal<Long> CONTEXT_HOLDER = new ThreadLocal<Long>();

	public static void setDbType(Long dbType) {
		if (dbType == null) {
			throw new NullPointerException();
		}
		CONTEXT_HOLDER.set(dbType);
	}

	public static Long getDbType() {
		return CONTEXT_HOLDER.get();
	}

	public static void clearDbType() {
		CONTEXT_HOLDER.remove();
	}
}
