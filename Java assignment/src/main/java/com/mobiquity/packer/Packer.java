package com.mobiquity.packer;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import com.mobiquity.exception.APIException;
import com.mobiquity.model.Input;
import com.mobiquity.model.Item;
import com.mobiquity.web.PackerApi;

public class Packer {

	private Packer() {
	}

	public static String pack(String filePath) throws APIException {
		if (filePath == null || filePath.isEmpty()) {
			throw new APIException("File path missing", new NullPointerException());
		}

		// Parsing file to fetch the list of inputs.
		List<Input> list = PackerApi.fetchInputList(filePath);

		if (list == null || list.size() == 0) {
			throw new APIException("Incorrect input", new InputMismatchException());
		}

		StringBuilder output = new StringBuilder();
		for (Input input : list) {
			output.append(getPackedItems(input.getTotal(), input.getItems()));
			output.append("\n");
		}

		return output.toString();
	}

	/**
	 * This method find the list of items which can be packed with maximum cost
	 * possible and a total weight not more than W.
	 * 
	 * @param W     - Maximum possible weight of the package.
	 * @param items - List of items to choose from.
	 * @return - Returns the choosen items with maximum possible weight and maximum
	 *         cost.
	 */
	public static String getPackedItems(int W, ArrayList<Item> items) {
		int i, w;

		int n = items.size();

		if (W <= 0 || items == null || items.size() == 0)
			return "-";

		int K[][] = new int[n + 1][W + 1];
		int[] selected = new int[n + 1];

		for (i = 0; i <= n; i++) {
			for (w = 0; w <= W; w++) {
				if (i == 0 || w == 0) {
					K[i][w] = 0;
				} else if (items.get(i - 1).getWeight() <= w) {
					Item item = items.get(i - 1);
					selected[i] = 1;
					K[i][w] = max(item.getValue() + K[i - 1][w - Double.valueOf(item.getWeight()).intValue()],
							K[i - 1][w]);
				} else {
					selected[i] = 0;
					K[i][w] = K[i - 1][w];
				}
			}
		}

		// Finding the index of the selected item.
		int tempW = W;
		int y = 0;

		for (int x = n; x > 0; x--) {
			Item item = items.get(x - 1);
			if ((tempW - item.getWeight() >= 0) && (K[x][tempW]
					- K[x - 1][tempW - Double.valueOf(item.getWeight()).intValue()] == item.getValue())) {
				selected[y++] = x - 1;
				tempW -= item.getWeight();
			}
		}

		StringBuilder output = new StringBuilder();
		if (y > 0) {
			for (int j = y - 1; j >= 0; j--) {
				output.append(selected[j] + 1);
				if (j > 0) {
					output.append(",");
				}
			}
		} else {
			output.append("-");
		}

		return output.toString();
	}

	/**
	 * This method finds the maximum of two numbers.
	 * 
	 * @param a
	 * @param b
	 * @return the maximum value from two input integers.
	 */
	private static int max(int a, int b) {
		return (a > b) ? a : b;
	}

}
