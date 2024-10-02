package com.mdshaz.blog.blog_rest_api1.payloads;

import java.util.List;

public class PostResponse
{
	private List<PostDto> content;
	private int pageNumber;
	private int pageSize;
	private long totalRecords;
	private int totalPages;
	private boolean isLastPage;
	
	public List<PostDto> getContent()
	{
		return content;
	}
	public void setContent(List<PostDto> content)
	{
		this.content = content;
	}
	public int getPageNumber()
	{
		return pageNumber;
	}
	public void setPageNumber(int pageNumber)
	{
		this.pageNumber = pageNumber;
	}
	public int getPageSize()
	{
		return pageSize;
	}
	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}
	public long getTotalRecords()
	{
		return totalRecords;
	}
	public void setTotalRecords(long totalRecords)
	{
		this.totalRecords = totalRecords;
	}
	public long getTotalPages()
	{
		return totalPages;
	}
	public void setTotalPages(int totalPages)
	{
		this.totalPages = totalPages;
	}
	public boolean isLastPage()
	{
		return isLastPage;
	}
	public void setLastPage(boolean isLastPage)
	{
		this.isLastPage = isLastPage;
	}
	
	

}
