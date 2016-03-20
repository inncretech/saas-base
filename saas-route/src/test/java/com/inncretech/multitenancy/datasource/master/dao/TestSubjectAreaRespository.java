package com.inncretech.multitenancy.datasource.master.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.multitenancy.datatsource.BaseTest;

public class TestSubjectAreaRespository extends BaseTest {

    @Autowired
    private SubjectAreaRepository subjectAreaRepository;

    @Test
    public void findAll() {
        subjectAreaRepository.findAll();
    }

}
