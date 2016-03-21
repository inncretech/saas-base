package com.inncretech.multitenancy.datasource.tenant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inncretech.multitenancy.datasource.tenant.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group getGroupByGroupName(String groupName);

}
