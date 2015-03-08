package org.transgalactica.web.recent.impl;

import java.util.Queue;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;

public class RecentVaisseauHandlerInterceptor extends HandlerInterceptorAdapter {

	@Resource(name = "recentVaisseaux")
	private Queue<RecentVaisseauLabel> recentVaisseaux;

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) {
		Object candidat = modelAndView.getModel().get("vaisseau");
		if (candidat != null && request.getMethod().equals("GET") && candidat instanceof VaisseauEntity) {
			recentVaisseaux.offer(new RecentVaisseauLabel((VaisseauEntity) candidat));
		}
	}
}
