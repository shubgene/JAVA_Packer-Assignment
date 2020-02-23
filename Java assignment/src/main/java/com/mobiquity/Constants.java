package com.mobiquity;
public class Constants {

	public static final String INDEX = "index";
	public static final String QUANTITY = "qty";
	public static final String PRICE = "price";

	public static final String REGEX = "((?<" + INDEX + ">\\d+),(?<" + QUANTITY + ">[\\d.]+),€(?<" + PRICE
			+ ">\\d+)\\)\\s*)+";
}
