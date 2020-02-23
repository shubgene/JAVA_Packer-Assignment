package com.mobiquity.model;

import java.util.ArrayList;

public class Input {

	public Input() {
		super();
	}

	private int total;
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
