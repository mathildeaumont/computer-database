package com.excilys.model;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {

	private int nbResult;
	private List<T> entities;
	private int currentPage;
	
	public Page(int nbResult, List<T> entities) {
		this.setNbResult(nbResult);
		this.setEntities(entities);
		this.setCurrentPage(1);
	}

	public int getNbResult() {
		return nbResult;
	}

	public void setNbResult(int nbResult) {
		this.nbResult = nbResult;
	}

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}
	
	public List<T> getEntitiesByPage(int nbPage) {
		List<T> entities = new ArrayList<T>();
		int begin = (nbPage * nbResult) - nbResult;
		int end = begin + nbResult;
		for (int i = begin; i < end; i++) {
			entities.add(this.entities.get(i));
		}
		return entities;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public int nextPage() {
		return currentPage + 1;
	}
	
	public int previousPage() {
		if (currentPage > 1) {
			return currentPage - 1;
		}
		return 1;
	}
	
}
