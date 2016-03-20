package com.inncretech.multitenancy.datasource.master.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inncretech.multitenancy.datasource.master.entity.SubjectArea;

public interface SubjectAreaRepository extends JpaRepository<SubjectArea, Long> {

}
