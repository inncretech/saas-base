package com.inncretech.multitenancy.datasource.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inncretech.multitenancy.datasource.master.entity.IdEntry;

@Repository
public interface IdEntryRepository extends JpaRepository<IdEntry, Long> {

}