package lab1.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lab1.algorithm.MinimumSpanningTreeFinding;
import lab1.model.Edge;
import lab1.model.Node;
import lab1.model.Graph;
import lab1.test.testkruskal.TestNaiveKruskal;
import lab1.test.testprim.TestPrim;
import lab1.test.testprim.TestPriorityQueue;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		compute("prim");
		testPrim();
//		TestNaiveKruskal.test();
		// TestPriorityQueue.test();
	}

	public static void compute(String algorithm) throws InterruptedException {
		// fetch files
		try (Stream<Path> walk = Files.walk(Paths.get("mst_dataset"))) {
			List<String> mst_dataset = walk.filter(Files::isRegularFile).map(x -> x.toString()).sorted()
					.collect(Collectors.toList());
			String file = new String();  
			if(algorithm == "prim"){
				file = "Prim.txt";
			} else if(algorithm == "naivekruskal"){
				file = "NaiveKruskal.txt";
			} else{
				throw new InterruptedException("Bad algorithm selection");
			}
			final File outputPath = new File(file);
			FileWriter fw = new FileWriter(outputPath, false);

			mst_dataset.forEach(entryset -> {
				try {
					System.out.println("Input " + entryset);

					Graph G = new Graph();
					int cost = 0;
//					entryset = "mst_dataset/input_random_03_10.txt";

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
					if(algorithm == "prim"){
						cost = MinimumSpanningTreeFinding.Prim(G, G.getNodeByID(1));
					} else if(algorithm == "naivekruskal"){
						cost = MinimumSpanningTreeFinding.NaiveKruskal(G);
					}
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
			fw.close();
			System.out.println("Finish!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void testPrim() {
		try { TestPrim.test(); }
		catch(AssertionError e) { System.out.println("test not passed.");}
	}
}
