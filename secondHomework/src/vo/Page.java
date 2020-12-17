package vo;

import java.util.HashMap;

public class Page {
	private int pageSize;
	private int pageNumber;
	private String sortOrder;
	private String sort;
	public Page() {}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public Page(int pageSize, int pageNumber, String sortOrder, String sort) {
		super();
		this.pageSize = pageSize;
		this.pageNumber = pageNumber;
		this.sortOrder = sortOrder;
		this.sort = sort;
	}
	public static Page getPageParams(HashMap<String,Object> map) {
		return new Page(Integer.parseInt((String) map.get("pageSize")),Integer.parseInt((String)map.get("pageNumber")),(String)map.get("sortOrder"),(String)map.get("sort"));
	}
}
