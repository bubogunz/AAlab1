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
import lab1.test.TestMinHeap;

public class Main {

	public static void main(String[] args) throws InterruptedException {
//		TestMinHeap.testStructure();
		compute("kruskal"); 
//		test("prim");
		// TestPriorityQueue.test();
		// TestDisjoinSet.test();
	}

	public static void compute(String algorithm) throws InterruptedException {
		// fetch files
		try (Stream<Path> walk = Files.walk(Paths.get("mst_dataset"))) {
			List<String> mst_dataset = walk.filter(Files::isRegularFile).map(x -> x.toString()).sorted()
					.collect(Collectors.toList());  
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

			 mst_dataset.stream().forEach(entryset -> {
//			for(int i = 0; i<57; i++){
//				String entryset = mst_dataset.get(i);
				try {
//					entryset = "mst_dataset/input_random_57_40000.txt";
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
					buffer += "Time elapsed: " + time + "s\n\n";
					fw.write(buffer);

				} catch (FileNotFoundException e) {
				} catch (IOException e) {
				}
			 });
//			}
			fw.close();
			System.out.println("Finish!");
			test(algorithm);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void test(String algorithm) {
		try { TestAlgorithm.test(algorithm); }
		catch(AssertionError e) { System.out.println("Test not passed. " + e.getMessage());}
	}
}
