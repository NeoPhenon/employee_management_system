package com.example.employee_management_system.repository;

import org.apache.ibatis.annotations.Mapper;

import com.example.employee_management_system.model.userDetailModel;

@Mapper
public interface userDetailModelRepository {
	public abstract userDetailModel loginProcess(String username );
}
