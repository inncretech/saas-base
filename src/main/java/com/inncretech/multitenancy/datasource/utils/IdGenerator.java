package com.inncretech.multitenancy.datasource.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inncretech.multitenancy.datasource.master.dao.IdEntryRepository;
import com.inncretech.multitenancy.datasource.master.entity.IdEntry;

@Component
public class IdGenerator {

	private static final int TIME_BITS = 41;

	private static final int SHARD_BITS = 13;

	private static final int TOTAL_BITS = 64;

	private static final int SHARD_ID = 1;

	@Autowired
	private IdEntryRepository idEntryRepository;

	public Long create(int shardId) {
		Long id = System.currentTimeMillis() << (64 - TIME_BITS);
		id |= shardId << (TOTAL_BITS - TIME_BITS - SHARD_BITS);
		id |= (nextSequence() % (int) Math.pow(2.0, (double) (TOTAL_BITS - SHARD_BITS - TIME_BITS)));
		id &= 0X3FFFFFFFFFFFFFFFL;
		return id;
	}

	public Long create() {
		return create(SHARD_ID);
	}

	private Long nextSequence() {
		return idEntryRepository.save(new IdEntry()).getId();
	}

	public Integer getShardId(Long id) {
		Long newId = new Long(id);
		newId = newId >> 10;
		newId = newId & 0X3FFL;
		return newId.intValue();
	}
}
