package com.congruent.compulaw.web.form;

import java.util.List;



//@SuppressWarnings("rawtypes")
public class DynaNode {
	private String title;
	private String key;
	private boolean folder;
	private boolean lazy;
	private String url;
	private String tooltip;
	private String href;
	private String activate;
	private List<DynaNode> children;
	private String addClass;
	private String noLink;
	private boolean focus;
	private boolean expand;
	private String icon;
	private boolean hideCheckbox;
	private boolean unselectable;
	private boolean select;

	public DynaNode() {}
	
	public DynaNode(String title, boolean folder, boolean lazy, String key) {
		super();
		this.title = title;
		this.folder = folder;
		this.lazy = lazy;
		this.key = key;		
	}

	public DynaNode(String title, boolean folder, boolean lazy, String url, String key) {
		super();
		this.title = title;
		this.folder = folder;
		this.lazy = lazy;
		this.url = url;
		this.key = key;
		
	}
	
	public DynaNode(String title, boolean folder, boolean lazy, String url, String key, List<DynaNode> children){
		super();
		this.title = title;
		this.folder = folder;
		this.lazy = lazy;
		this.url = url;
		this.key = key;
		this.children = children;
	}
	
	//Initialize Case-law as a parent folder
	public DynaNode(String title, boolean folder, boolean lazy, String key, List<DynaNode> children){
		super();
		this.title = title;
		this.folder = folder;
		this.lazy = lazy;
		this.key = key;
		this.children = children;
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

	public boolean getisFolder() {
		return folder;
	}

	public void setFolder(boolean folder) {
		this.folder = folder;
	}

	public boolean getisLazy() {
		return lazy;
	}

	public void setLazy(boolean lazy) {
		this.lazy = lazy;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getActivate() {
		return activate;
	}

	public void setActivate(String activate) {
		this.activate = activate;
	}

	public List<DynaNode> getChildren() {
		return children;
	}

	public void setChildren(List<DynaNode> childrenActNodes) {
		this.children = childrenActNodes;
	}

	public String getAddClass() {
		return addClass;
	}

	public void setAddClass(String addClass) {
		this.addClass = addClass;
	}

	public String getNoLink() {
		return noLink;
	}

	public void setNoLink(String noLink) {
		this.noLink = noLink;
	}

	public boolean isFocus() {
		return focus;
	}

	public void setFocus(boolean focus) {
		this.focus = focus;
	}

	public boolean isExpand() {
		return expand;
	}

	public void setExpand(boolean expand) {
		this.expand = expand;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isHideCheckbox() {
		return hideCheckbox;
	}

	public void setHideCheckbox(boolean hideCheckbox) {
		this.hideCheckbox = hideCheckbox;
	}

	public boolean isUnselectable() {
		return unselectable;
	}

	public void setUnselectable(boolean unselectable) {
		this.unselectable = unselectable;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	/*@Override
	public int compareTo(Object o) {
		int caseId = ((LawSubCategory) o).getId().intValue(); 
		 
		//ascending order
		return Integer.parseInt(this.key) - caseId;

		//descending order
		//return compareQuantity - this.quantity
	}	*/
}