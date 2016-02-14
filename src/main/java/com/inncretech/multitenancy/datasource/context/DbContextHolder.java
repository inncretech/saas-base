package com.inncretech.multitenancy.datasource.context;

public class DbContextHolder {

	private static final ThreadLocal<Integer> CONTEXT_HOLDER = new ThreadLocal<Integer>();

	public static void setShardId(Integer shardId) {
		if (shardId == null) {
			throw new NullPointerException();
		}
		CONTEXT_HOLDER.set(shardId);
	}

	public static Integer getShardId() {
		return CONTEXT_HOLDER.get();
	}

	public static void clearShardId() {
		CONTEXT_HOLDER.remove();
	}
}
