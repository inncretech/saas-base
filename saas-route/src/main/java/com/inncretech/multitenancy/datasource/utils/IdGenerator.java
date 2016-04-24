package com.inncretech.multitenancy.datasource.utils;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.inncretech.multitenancy.datasource.context.DbContextHolder;
import com.inncretech.multitenancy.datasource.master.dao.IdEntryRepository;
import com.inncretech.multitenancy.datasource.master.entity.IdEntry;

@Component
public class IdGenerator implements IdentifierGenerator, ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static final int TIME_BITS = 41;

    private static final int SHARD_BITS = 13;

    private static final int TOTAL_BITS = 64;

    @SuppressWarnings("static-access")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Long create(int shardId) {
        Long id = System.currentTimeMillis() << (64 - TIME_BITS);
        id |= shardId << (TOTAL_BITS - TIME_BITS - SHARD_BITS);
        id |= (nextSequence() % (int) Math.pow(2.0, (double) (TOTAL_BITS - SHARD_BITS - TIME_BITS)));
        id &= 0X3FFFFFFFFFFFFFFFL;
        return id;
    }

    private static final int SHARD_ID = 1;

    public Long create() {
        return create(SHARD_ID);
    }

    private Long nextSequence() {
        return applicationContext.getBean(IdEntryRepository.class).save(new IdEntry()).getId();
    }

    public Integer getShardId(Long id) {
        Long newId = new Long(id);
        newId = newId >> 10;
        newId = newId & 0X3FFL;
        return newId.intValue();
    }

    @Override
    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        return create(DbContextHolder.getShardId());
    }
}