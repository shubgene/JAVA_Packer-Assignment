package com.mobiquity.model;

public class Item {

	public double getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}

	final double weight;
	final int value;

	public Item(double
			weight, int value) {
		this.weight = Double.valueOf(weight).intValue();	// The output in the problem statement is taken considering 
															// integer value. Hence keeping the type cast
		this.value = value;
	}

	@Override
	public String toString() {
		return "Item [weight=" + weight + ", value=" + value + "]";
	}
}