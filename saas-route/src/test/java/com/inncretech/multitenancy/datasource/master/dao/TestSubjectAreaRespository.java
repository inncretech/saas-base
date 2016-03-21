package com.inncretech.multitenancy.datasource.master.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.inncretech.multitenancy.datasource.AbstractRoutingTest;
import com.inncretech.multitenancy.datasource.master.entity.SubjectArea;

public class TestSubjectAreaRespository extends AbstractRoutingTest {

    @Autowired
    private SubjectAreaRepository subjectAreaRepository;

    @Test
    public void findAll() {
        List<SubjectArea> findAll = subjectAreaRepository.findAll();
        System.out.println(findAll);
    }

}
