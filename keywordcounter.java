import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author akshaysehgal UFID-14167988 Advanced Data Science Project
 */

/*
 * Class to read the input file and perform various operations of insert,
 * increment count of existing node or keyword and also perform remove max
 * operation if any digit is encountered in the input file. The removed nodes
 * are added in the removedNodes arrayList and are reinserted into the heap
 * after printing them in the output file
 */

public class keywordcounter {

	private static HashMap<String, treeNode> hash = new HashMap<>();

	// main function for execution start
	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();
		// input file to be read
		String inputFile = args[0];
		// output file to be written
		File outputFile = new File("output_file.txt");
		BufferedWriter writer = null;

		fibonacciHeap heap = new fibonacciHeap();

		try {
			FileReader fr = new FileReader(inputFile);
			BufferedReader rd = new BufferedReader(fr);
			String inpString = rd.readLine();

			// pattern defined to match the keywords and the count
			Pattern keywordPattern = Pattern.compile("([$])([^\\s]+)(\\s)(\\d+)");
			// pattern defined to match the number for the write operation
			Pattern digitsPattern = Pattern.compile("(^\\d+$)");

			FileWriter fw = new FileWriter(outputFile);
			writer = new BufferedWriter(fw);

			while (inpString != null) {
				if (inpString.equals("stop")) {
					break;
				} else {
					Matcher matcher = keywordPattern.matcher(inpString);
					Matcher digitMatcher = digitsPattern.matcher(inpString);

					if (matcher.find()) {
						String word = matcher.group(2);
						int count = Integer.parseInt(matcher.group(4));

						// check if the word is already present in hashmap
						if (!hash.containsKey(word)) {

							// make an entry in the hashmap and create a new node in the tree
							treeNode node = new treeNode(word, count);
							heap.insert(node);
							hash.put(word, node);
						} else {
							int increasedCount = hash.get(word).count + count;
							heap.increaseCount(hash.get(word), increasedCount);
						}
					} else if (digitMatcher.find()) {
						int noOfWriteValues = Integer.parseInt(digitMatcher.group(1));

						ArrayList<treeNode> removedNodes = new ArrayList<treeNode>(noOfWriteValues);

						if (noOfWriteValues > hash.size()) {
							noOfWriteValues = hash.size();
						}

						for (int i = 0; i < noOfWriteValues; i++) {
							// remove max operation
							treeNode node = heap.removeMax();
							hash.remove(node.getData());
							treeNode newNode = new treeNode(node.getData(), node.count);

							// adding to the removedNodes ArrayList
							removedNodes.add(newNode);

							if (i < noOfWriteValues - 1) {
								writer.write(node.getData() + ",");
							}

							else {
								writer.write(node.getData());
							}
						}
						
						for(int i=0;i<removedNodes.size();i++) {
							heap.insert(removedNodes.get(i));
							hash.put(removedNodes.get(i).getData(), removedNodes.get(i));
							
						}
						writer.newLine();

					}
					inpString = rd.readLine();
				}

			}
			// close the reader
			rd.close();
		}

		catch (Exception e) {
			System.out.println(e);
		}
		// Close the writer
		finally {

			if (writer != null) {
				try {
					writer.close();
				} catch (IOException ioe2) {

				}
			}
		}

		// to calculate time for execution
		long completionTime = System.currentTimeMillis();
		long totalTime = completionTime - startTime;
		System.out.println("Time taken for execution: " + totalTime);

	}

	// function to return the hash value
	public static HashMap<String, treeNode> getHash() {
		return hash;
	}

}