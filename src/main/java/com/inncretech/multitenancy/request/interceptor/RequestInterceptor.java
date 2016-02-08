package com.inncretech.multitenancy.request.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.inncretech.multitenancy.datasource.config.DbContextHolder;
import com.inncretech.multitenancy.master.db.repository.TenantRepository;

public class RequestInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private TenantRepository tenantRepository;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// tenantRepository.findOne(request.getParameter("tenantId"));
		DbContextHolder.setDbType(4l);
		return true;
	}
}
