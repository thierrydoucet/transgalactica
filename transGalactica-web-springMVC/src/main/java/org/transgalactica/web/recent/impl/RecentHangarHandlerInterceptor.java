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
		if (request.getMethod().equals("GET")) {
			HangarEntity hangar = (HangarEntity) modelAndView.getModel().get("hangar");
			recentHangars.offer(new RecentHangarLabel(hangar));
		}
	}
}
