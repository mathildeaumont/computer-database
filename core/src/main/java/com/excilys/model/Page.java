package com.excilys.model;


// TODO: Auto-generated Javadoc
/**
 * The Class Page.
 *
 * @param <T> the generic type
 */
public class Page<T> {

	/** The current page. */
	private int currentPage;
	
	/** The nb results. */
	private int nbResults;
	
	/** The offset. */
	private int offset;
	
	/** The total pages. */
	private int totalPages;
	
	/** The nb result total. */
	private int nbResultTotal;
	
	/**
	 * Instantiates a new page.
	 */
	public Page() {
	}
	
	/**
	 * Instantiates a new page.
	 *
	 * @param currentPage the current page
	 * @param nbResults the nb results
	 * @param offset the offset
	 * @param totalPages the total pages
	 * @param nbResultTotal the nb result total
	 */
	public Page(int currentPage, int nbResults, int offset, int totalPages, int nbResultTotal) {
		this.setNbResults(nbResults);
		this.setOffset(offset);
		this.setTotalPages(totalPages);
		this.currentPage = currentPage;
		this.setNbResultTotal(nbResultTotal);
	}

	/**
	 * Gets the nb results.
	 *
	 * @return the nb results
	 */
	public int getNbResults() {
		return nbResults;
	}

	/**
	 * Sets the nb results.
	 *
	 * @param nbResults the new nb results
	 */
	public void setNbResults(int nbResults) {
		this.nbResults = nbResults;
	}

	/**
	 * Gets the offset.
	 *
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * Sets the offset.
	 *
	 * @param offset the new offset
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * Gets the total pages.
	 *
	 * @return the total pages
	 */
	public int getTotalPages() {
		return totalPages;
	}

	/**
	 * Sets the total pages.
	 *
	 * @param totalPages the new total pages
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * Gets the current page.
	 *
	 * @return the current page
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * Sets the current page.
	 *
	 * @param currentPage the new current page
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * Gets the number of result total.
	 *
	 * @return the number of result total
	 */
	public int getNbResultTotal() {
		return nbResultTotal;
	}

	/**
	 * Sets the number of result total.
	 *
	 * @param nbResultTotal the new number of result total
	 */
	public void setNbResultTotal(int nbResultTotal) {
		this.nbResultTotal = nbResultTotal;
	}
	
}
