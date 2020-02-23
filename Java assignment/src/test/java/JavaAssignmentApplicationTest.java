import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mobiquity.model.Input;
import com.mobiquity.model.Item;
import com.mobiquity.packer.Packer;
import com.mobiquity.web.PackerApi;

class JavaAssignmentApplicationTest {

	private static List<String> input;
	private static List<String> output;

	@BeforeAll
	public static void populateResource() {
		input = readClassLoadedFile("example_input");
		output = readClassLoadedFile("example_output");
	}

	@AfterAll
	public static void emptyResource() {
		input = null;
		output = null;
	}

	@Test
	public void testFetchInputList() {
		Input expect = new Input();
		expect.setTotal(81);
		ArrayList<Item> items = new ArrayList<>();
		Item it1 = new Item(53.38, 45);
		Item it2 = new Item(88.62, 98);
		Item it3 = new Item(78.48, 3);
		Item it4 = new Item(72.30, 76);
		Item it5 = new Item(30.18, 9);
		Item it6 = new Item(46.34, 48);
		items.add(it1);
		items.add(it2);
		items.add(it3);
		items.add(it4);
		items.add(it5);
		items.add(it6);
		expect.setItems(items);

		Input actual = PackerApi.scanEachInput(
				"81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)");
		assertNotNull(actual);
		assertNotNull(actual.getItems());
		assertEquals(expect.getTotal(), actual.getTotal());
		assertEquals(expect.getItems().toString(), actual.getItems().toString());
	}

	@Test
	public void testGetPackedItems() {
		for (int i = 0; i < input.size(); i++) {
			Input in = PackerApi.scanEachInput(input.get(i));
			String expected_output = Packer.getPackedItems(in.getTotal(), in.getItems());
			assertEquals(output.get(i), expected_output);
		}
	}

	private static List<String> readClassLoadedFile(String filename) {
		ClassLoader loader = JavaAssignmentApplicationTest.class.getClassLoader();
		List<String> lines;
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(loader.getResourceAsStream(filename), "UTF-8"));
			lines = br.lines().parallel().map(Object::toString).collect(Collectors.toList());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			lines = null;
		}
		return lines;
	}
}
