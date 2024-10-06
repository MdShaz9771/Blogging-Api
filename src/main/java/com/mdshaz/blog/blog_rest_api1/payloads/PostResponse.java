package com.mdshaz.blog.blog_rest_api1.payloads;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response object for paginated post data")
public class PostResponse {

    @Schema(description = "List of posts in the current page")
    private List<PostDto> content;

    @Schema(description = "Current page number", example = "0")
    private int pageNumber;

    @Schema(description = "Size of the page (number of posts per page)", example = "10")
    private int pageSize;

    @Schema(description = "Total number of records available", example = "100")
    private long totalRecords;

    @Schema(description = "Total number of pages available", example = "10")
    private int totalPages;

    @Schema(description = "Indicates whether this is the last page", example = "false")
    private boolean isLastPage;

    public List<PostDto> getContent() {
        return content;
    }

    public void setContent(List<PostDto> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }
}
