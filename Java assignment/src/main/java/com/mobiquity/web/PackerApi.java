package com.mobiquity.web;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Input;
import com.mobiquity.model.Item;
import com.mobiquity.Constants;

/**
 * PackerApi is responsible for parsing the file and return the list of valid
 * input.
 */
public class PackerApi {

	/**
	 * This method scans input file and returns the list of inputs.
	 * 
	 * @param filePath : path of the file containing the input.
	 * @return list of valid input or throws exception in case of invalid input or
	 *         invalid file path.
	 * @throws APIException
	 */
	public static List<Input> fetchInputList(String filePath) throws APIException {
		File file = new File(filePath);
		Scanner scanner;

		ArrayList<Input> inputList = new ArrayList<>();
		try {
			scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				inputList.add(scanEachInput(line));
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			throw new APIException("Invalid file path", e);
		}

		return inputList.size() > 0 ? inputList : null;
	}

	/**
	 * This method parse each input string to return a Input object.
	 * 
	 * @param line : String input for the package.
	 * @return the parsed input object.
	 */
	public static Input scanEachInput(String line) throws APIException {
		ArrayList<Item> itemList = new ArrayList<>();
		Input input = null;
		String[] arr = line.split(" : ");

		try {
			if (arr.length == 2) {
				int total = Integer.valueOf(arr[0]);

				Pattern p = Pattern.compile(Constants.REGEX);
				Matcher m = p.matcher(arr[1]);

				while (m.find()) {
					Item item = new Item(Double.valueOf(Objects.requireNonNull(m.group(Constants.QUANTITY))).intValue(),
							Integer.valueOf(Objects.requireNonNull(m.group(Constants.PRICE))));
					itemList.add(item);
				}
				if (itemList.size() > 0) {
					input = new Input(total, itemList);
				}
			}
		} catch (NumberFormatException e) {
			throw new APIException("Invalid input", e);
		}
		return input;
	}
}
