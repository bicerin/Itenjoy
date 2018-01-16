package com.itenjoy.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itenjoy.svc.GetBoardListService;
import com.itenjoy.vo.ActionForward;
import com.itenjoy.vo.BoardVO;
import com.itenjoy.vo.PagingVO;

public class GetBoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("GetBoardListAction 안으로 들어왔음");
		List<BoardVO> boardVOList = new ArrayList<BoardVO>();
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum = "1";
		request.setAttribute("pageNum", pageNum);
		int currentPage = Integer.parseInt(pageNum);
		int PAGESIZE = 10; //한 페이지에 출력될 게시글의 숫자
		int startRow = (currentPage - 1) * PAGESIZE + 1;
		int endRow = currentPage * PAGESIZE;
		
		GetBoardListService getBoardListService = new GetBoardListService();
		boardVOList = getBoardListService.getBoardList(startRow, endRow);
		request.setAttribute("boardVOList", boardVOList);
		
		// 페이징 처리 부분
		PagingVO pagingVO = new PagingVO();
		int count = getBoardListService.getBoardCount();
		int startPage = 1;
		int pageCount = count / PAGESIZE + (count % PAGESIZE==0 ? 0 : 1);
		if(currentPage % 10 != 0)
			startPage = (int)(currentPage / 10) * 10 + 1;
		else
			startPage = ((int)(currentPage / 10) - 1) * 10 + 1;
		int PAGEBLOCK = 10; // 한페이지에 보여줄 페이징 갯수
		int endPage = startPage + PAGEBLOCK - 1;
		if(endPage > pageCount)
			endPage = pageCount;
		
		pagingVO.setCount(count);
		pagingVO.setNumber(count - (currentPage - 1) * PAGESIZE);
		pagingVO.setPageCount(pageCount);
		pagingVO.setStartPage(startPage);
		pagingVO.setEndPage(endPage);
		request.setAttribute("pagingVO", pagingVO);
		
		ActionForward forward = new ActionForward();
		forward.setPath("board/getBoardList.jsp");
		return forward;
	}

}
