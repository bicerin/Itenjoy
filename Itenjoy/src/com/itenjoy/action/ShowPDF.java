package com.itenjoy.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	FileInputStream fis = null;
    	BufferedOutputStream bos = null;
    	
    	try {
    		String pdfFileName = "C:/Temp/aaa.pdf";
    		File pdfFile = new File(pdfFileName);

    		//클라이언트 브라우져에서 바로 보는 방법(헤더 변경)
    		response.setContentType("application/pdf");

    		fis = new FileInputStream(pdfFile);
    		int size = fis.available(); //지정 파일에서 읽을 수 있는 바이트 수를 반환
    		byte[] buf = new byte[size]; //버퍼설정
    		int readCount = fis.read(buf);

    		response.flushBuffer();

    		bos = new BufferedOutputStream(response.getOutputStream());
    		bos.write(buf, 0, readCount);
    		bos.flush();
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
    		try { if (fis != null) fis.close(); if (bos != null) bos.close();} catch (IOException e) {}
    	}
    }
	
}
