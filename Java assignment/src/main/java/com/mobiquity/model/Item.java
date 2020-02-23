package com.mobiquity.model;

/**
 * Item class to store the item information - weight and value.
 */
public class Item {

	/**
	 * Weight of the item.
	 */
	final double weight;

	/**
	 * Price of the item.
	 */
	final int value;

	public Item(double weight, int value) {
		this.weight = Double.valueOf(weight).intValue(); // The output in the problem statement is taken considering
															// integer value. Hence adding the type cast
		this.value = value;
	}

	public double getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Item [weight=" + weight + ", value=" + value + "]";
	}
}