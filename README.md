## Package Challenge

#### Project setup
* Requires java 8.
* Import as a maven project.

#### Deliverable
Compiled Jar and Source code.

#### Programming Paradigm

##### Approach
Identified the problem as [knapsack problem](https://en.wikipedia.org/wiki/Knapsack_problem).

##### API Details
[public static String pack(String filePath) throws APIException](https://github.com/shubgene/JAVA_Packer-Assignment/blob/b7671a50432710eb63e73bfae6f7e736f1b402aa/Java%20assignment/src/main/java/com/mobiquity/packer/Packer.java#L16)

API is responsible for fetching the list of index of items to be be packed.

[public static List<Input> fetchInputList(String filePath) throws APIException](https://github.com/shubgene/JAVA_Packer-Assignment/blob/b7671a50432710eb63e73bfae6f7e736f1b402aa/Java%20assignment/src/main/java/com/mobiquity/packer/Packer.java#L46)

API reads input from the file path line by line and parse to return the list of Input.

##### Algorithm
Dynamic Programming: The knapsack problem could be devided into multiple sub-problems and the results of the sub-problem is stored to compute the complete problem without solving the sub-problems again and again.

Below is the API defined in the project to get the choosen items using Dynamic Programming.

[public static String getPackedItems(int W, ArrayList<Item> items)](https://github.com/shubgene/JAVA_Packer-Assignment/blob/b7671a50432710eb63e73bfae6f7e736f1b402aa/Java%20assignment/src/main/java/com/mobiquity/packer/Packer.java#L46)

##### Data Structure
* ArrayList - As the list of inputs and the list of items in each input is not predefined, using arrayList to dynamically add the items and input.
