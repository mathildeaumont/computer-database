package com.excilys.model;


public class Page<T> {

	private int nbResult;
	private int offset;
	
	public Page(int nbResult, int offset) {
		this.setNbResult(nbResult);
		this.setOffset(offset);
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
	
}
