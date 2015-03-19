package com.excilys.model;


public class Page<T> {

	private int nbResult;
	private int offset;
	private int totalPages;
	
	public Page(int nbResult, int offset, int totalPages) {
		this.setNbResult(nbResult);
		this.setOffset(offset);
		this.setTotalPages(totalPages);
	}

	public int getNbResult() {
		return nbResult;
	}

	public void setNbResult(int nbResult) {
		this.nbResult = nbResult;
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
	
}
