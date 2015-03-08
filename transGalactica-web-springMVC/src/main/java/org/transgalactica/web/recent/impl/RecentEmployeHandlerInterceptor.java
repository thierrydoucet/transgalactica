package org.transgalactica.web.recent.impl;

import java.util.Queue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.transgalactica.management.data.people.bo.EmployeEntity;

public class RecentEmployeHandlerInterceptor extends HandlerInterceptorAdapter {

	@Resource(name = "recentEmployes")
	private Queue<RecentEmployeLabel> recentEmployes;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		Object candidat = modelAndView.getModel().get("employe");
		if (candidat != null && request.getMethod().equals("GET") && candidat instanceof EmployeEntity) {
			recentEmployes.offer(new RecentEmployeLabel((EmployeEntity) candidat));
		}
	}
}
