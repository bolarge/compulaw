package com.congruent.compulaw.web.form;

public class ActNode {
	
	private String title;
	private String key;
	private boolean folder;
	private boolean lazy;
	private String url;
	private String tooltip;
	private String href;
	//private String activate;
	//private String children;
	
	public ActNode(){}
	
	public ActNode(String title, boolean folder, boolean lazy, String key){
		this.title = title;
		this.folder = folder;
		this.lazy = lazy;
		this.key = key;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public boolean isFolder() {
		return folder;
	}
	public void setFolder(boolean folder) {
		this.folder = folder;
	}
	public boolean isLazy() {
		return lazy;
	}
	public void setLazy(boolean lazy) {
		this.lazy = lazy;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTooltip() {
		return tooltip;
	}
	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	
	

}
