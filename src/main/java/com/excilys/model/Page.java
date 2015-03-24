package com.excilys.model;


public class Page<T> {

	private int currentPage;
	private int nbResults;
	private int offset;
	private int totalPages;
	
	public Page(int currentPage, int nbResults, int offset, int totalPages) {
		this.setNbResults(nbResults);
		this.setOffset(offset);
		this.setTotalPages(totalPages);
		this.currentPage = currentPage;
	}

	public int getNbResults() {
		return nbResults;
	}

	public void setNbResults(int nbResults) {
		this.nbResults = nbResults;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
