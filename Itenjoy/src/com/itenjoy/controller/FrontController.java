package com.itenjoy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.action.*;
import com.itenjoy.vo.ActionForward;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
//		String command = RequestURI.substring(contextPath.length());
		String command = RequestURI;
		ActionForward forward = null;
		Action action = null;

		if(command.equals("/shopMain.do")){
			System.out.println("shopMain 컨트롤러 if문 안으로 들어왔음");
			action = new IntroListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/getProductList.do")){
			System.out.println("getProductList 컨트롤러 if문 안으로 들어왔음");
			action = new GetProductListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/showPDF.do")){
			System.out.println("showPDF 컨트롤러 if문 안으로 들어왔음");
			ShowPDF pdf = new ShowPDF();
			try {
				pdf.doGet(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/getProductOne.do")){
			System.out.println("getProductOne 컨트롤러 if문 안으로 들어왔음");
			action = new GetProductOneAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/joinForm.do")){
			System.out.println("joinForm 컨트롤러 if문 안으로 들어왔음");
			try {
				ActionForward send = new ActionForward();
				send.setPath("shopping/joinForm.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher(send.getPath());
				dispatcher.forward(request, response);
				System.out.println("페이지 이동");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/insertMember.do")){
			System.out.println("insertMember 컨트롤러 if문 안으로 들어왔음");
			action = new InsertMemberAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/login.do")){
			System.out.println("login 컨트롤러 if문 안으로 들어왔음");
			try {
				ActionForward send = new ActionForward();
				send.setPath("shopping/login.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher(send.getPath());
				dispatcher.forward(request, response);
				System.out.println("페이지 이동");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/loginPro.do")){
			System.out.println("loginPro 컨트롤러 if문 안으로 들어왔음");
			action = new LoginProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/logout.do")){
			System.out.println("logout 컨트롤러 if문 안으로 들어왔음");
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/search.do")){
			System.out.println("search 컨트롤러 if문 안으로 들어왔음");
			action = new SearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/getCartList.do")){
			System.out.println("getCartList 컨트롤러 if문 안으로 들어왔음");
			action = new GetCartListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/insertCart.do")){
			System.out.println("insertCart 컨트롤러 if문 안으로 들어왔음");
			action = new InsertCartAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/updateCart.do")){
			System.out.println("updateCart 컨트롤러 if문 안으로 들어왔음");
			action = new UpdateCartAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/deleteCart.do")){
			System.out.println("deleteCart 컨트롤러 if문 안으로 들어왔음");
			action = new DeleteCartAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/buyForm.do")){
			System.out.println("buyForm 컨트롤러 if문 안으로 들어왔음");
			action = new BuyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/buyPro.do")){
			System.out.println("buyPro 컨트롤러 if문 안으로 들어왔음");
			action = new BuyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/getBoardList.do")){
			System.out.println("getBoardList 컨트롤러 if문 안으로 들어왔음");
			action = new GetBoardListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/getBoardOne.do")){
			System.out.println("getBoardOne 컨트롤러 if문 안으로 들어왔음");
			action = new GetBoardOneAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/insertBoardForm.do")){
			System.out.println("insertBoardForm 컨트롤러 if문 안으로 들어왔음");
			try {
				ActionForward send = new ActionForward();
				send.setPath("board/insertBoardForm.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher(send.getPath());
				dispatcher.forward(request, response);
				System.out.println("페이지 이동");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/insertBoardPro.do")){
			System.out.println("insertBoardPro 컨트롤러 if문 안으로 들어왔음");
			action = new InsertBoardProAction(); 
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/updateBoardForm.do")){
			System.out.println("updateBoardForm 컨트롤러 if문 안으로 들어왔음");
			action = new UpdateBoardFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/updateBoardPro.do")){
			System.out.println("updateBoardPro 컨트롤러 if문 안으로 들어왔음");
			action = new UpdateBoardProAction(); 
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/deleteBoard.do")){
			System.out.println("deleteBoard 컨트롤러 if문 안으로 들어왔음");
			action = new DeleteBoardAction(); 
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/orderList.do")){
			System.out.println("orderList 컨트롤러 if문 안으로 들어왔음");
			action = new OrderListAction(); 
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		if(forward != null){
			
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
				System.out.println("페이지 리다이렉트");
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				System.out.println(forward.getPath() + "로 페이지 이동");
			}			
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

}
