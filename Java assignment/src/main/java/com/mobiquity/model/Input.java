package com.mobiquity.model;

import java.util.ArrayList;

/**
 * Input class to store the total weight and the list of available items to
 * choose from.
 */
public class Input {

	public Input() {
		super();
	}

	/**
	 * Maximum possible weight of the package.
	 */
	private int total;

	/**
	 * List of available items.
	 */
	private ArrayList<Item> items;

	public Input(int total, ArrayList<Item> list) {
		this.total = total;
		this.items = list;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
}
