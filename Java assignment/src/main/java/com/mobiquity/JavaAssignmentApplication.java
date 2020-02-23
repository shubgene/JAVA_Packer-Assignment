package com.mobiquity;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.Packer;

public class JavaAssignmentApplication {

	public static void main(String[] args) {

		// Actual file path for input is required. Provide a file path
		try {
			System.out.println(Packer.pack("C:\\Users\\User\\Desktop\\file.txt"));
		} catch (APIException ex) {
			System.out.print(ex.getMessage());
		}

	}

}
