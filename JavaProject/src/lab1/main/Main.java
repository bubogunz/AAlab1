package lab1.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lab1.algorithm.MinimumSpanningTreeFinding;
import lab1.model.Edge;
import lab1.model.Graph;
import lab1.test.TestAlgorithm;
/**
 * The main class which contains the main(String[] args) method. Use this class
 * to run algorithms, or just to test them.
 */
public class Main {
	/**
	 * To execute the algorithm selected and run test, uncomment compute function.
	 * To execute only the test, uncomment test function.
	 * @param args
	 * @throws InterruptedException
	 * 
	 */
	public static void main(String[] args) throws InterruptedException {
		compute("kruskal"); 
//		test("prim");
	}
	/**
	 * @param algorithm = the algorithm to compute. This would be:
	 * - prim -> Prim algorithm with Priority queue data structure
	 * - naivekruskal -> Naive Kruskal algorithm
	 * - kruskal -> Kruskal algorithm with DisjointSet data structure
	 * @throws InterruptedException
	 * 
	 * This function executes the algorithm choosen in 68 graphs that are
	 * builded with mst_dataset folder. The results are stored in a file with the name of
	 * the chosen algorithm. 
	 */
	public static void compute(String algorithm) throws InterruptedException {
		// fetch files
		try (Stream<Path> walk = Files.walk(Paths.get("mst_dataset"))) {
			List<String> mst_dataset = walk
					.filter(Files::isRegularFile)
					.map(x -> x.toString()).sorted()
					.collect(Collectors.toList());  
			//initialize the path for the output file
			final File outputPath ;
			if(algorithm == "prim")
				outputPath = new File("Prim.txt");
			else if(algorithm == "naivekruskal")
				outputPath = new File("NaiveKruskal.txt");
			else if(algorithm == "kruskal")
				outputPath = new File("Kruskal.txt");
			else
				throw new InvalidParameterException("Wrong choice of algorithm");
			FileWriter fw = new FileWriter(outputPath, false);

			System.out.println("Executing " + algorithm + " algorithm");
			
			mst_dataset.stream().forEach(entryset -> {
				try {
					System.out.println("Input: " + entryset);

					Graph G = new Graph();
					int cost = 0;
					

					String buffer = new String("File:" + entryset + "\n");

					File myObj = new File(entryset);
					Scanner myReader = new Scanner(myObj);
					String line = myReader.nextLine();
					
					int nodes = Integer.valueOf(line.split(" ")[0]);
					G.buildNodes(nodes);

					while (myReader.hasNextLine()) {
						line = myReader.nextLine();
						String[] data = line.split(" ");
						G.addEdge(new Edge(G.getNodeByID(Integer.valueOf(data[0])), G.getNodeByID(Integer.valueOf(data[1])), Integer.valueOf(data[2])));
					}

					myReader.close();

					long start = System.nanoTime();
					if(algorithm == "prim")
						cost = MinimumSpanningTreeFinding.Prim(G, G.getNodeByID(1));
					else if(algorithm == "naivekruskal")
						cost = MinimumSpanningTreeFinding.NaiveKruskal(G);
					else if(algorithm == "kruskal")
						cost = MinimumSpanningTreeFinding.Kruskal(G);
					else
						throw new InvalidParameterException("Wrong choice of algorithm");

					long stop = System.nanoTime();

					long timeElapsed = stop - start;
					double time = timeElapsed;
					time = time / 1000000000;
					buffer += "MST costs " + cost + "\n";
					buffer += "Time elapsed: " + time + " s\n\n";
					fw.write(buffer);

				} catch (FileNotFoundException e) {
					System.out.println("Error! File not found.");
				} catch (IOException e) {
					System.out.println("Error! Cannot write the result into a .txt file.");
				}
			 });
			fw.close();
			System.out.println("Finish!");
			test(algorithm);
    			
		} catch (IOException e) {
			System.out.println("Error! Folder mst_dataset not found.");
		} 
	}
	
	/**
	 * @param algorithm = the algorithm chosen for the test
	 * 
	 * This function compare the results of compute function with the right solutions in results.txt,
	 * so first execute this function make sure to have the results of chosen algorithm.
	 */
	public static void test(String algorithm) {
		try { TestAlgorithm.test(algorithm); }
		catch(AssertionError e) { System.out.println("Test not passed. " + e.getMessage());}
	}
}
