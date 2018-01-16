package com.itenjoy.vo;

public class ActionForward {
	private boolean Redirect;
	private String path;
	
	public ActionForward() { }
	
	public ActionForward(String path, boolean redirect) {
		this.path = path;
		this.Redirect = redirect;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public boolean isRedirect(){
		return Redirect;
	}

	public void setRedirect(boolean redirect){
		this.Redirect = redirect;
	}
}