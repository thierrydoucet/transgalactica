package org.transgalactica.web.recent.impl;

import java.util.Queue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.transgalactica.management.data.materiel.bo.HangarEntity;

public class RecentHangarHandlerInterceptor extends HandlerInterceptorAdapter {

	@Resource(name = "recentHangars")
	private Queue<RecentHangarLabel> recentHangars;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		Object candidat = modelAndView.getModel().get("hangar");
		if (candidat != null && request.getMethod().equals("GET") && candidat instanceof HangarEntity) {
			recentHangars.offer(new RecentHangarLabel((HangarEntity) candidat));
		}
	}
}
