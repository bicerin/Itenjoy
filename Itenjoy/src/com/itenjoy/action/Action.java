package com.itenjoy.action;

import javax.servlet.http.*;

import com.itenjoy.vo.ActionForward;

public interface Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception;
}
