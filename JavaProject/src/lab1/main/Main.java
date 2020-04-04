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
import lab1.model.Graph;
import testprim.TestPrim;

public class Main {

	public static void main(String[] args) {
		testPrim();
	}

	public static void compute() {
		// fetch files
		try (Stream<Path> walk = Files.walk(Paths.get("mst_dataset"))) {
			List<String> mst_dataset = walk.filter(Files::isRegularFile).map(x -> x.toString()).sorted()
					.collect(Collectors.toList());
			final File outputPath = new File("Prim.txt");
			//			final File outputPath = new File("NaiveKruskal.txt");
			FileWriter fw = new FileWriter(outputPath, true);

			mst_dataset.forEach(entryset -> {
				try {
					System.out.println("Input " + entryset);

					Graph G = new Graph();
					int cost = 0;
					//entryset = "mst_dataset/input_random_02_10.txt";

					String buffer = new String("File:" + entryset + "\n");

					File myObj = new File(entryset);
					Scanner myReader = new Scanner(myObj);
					String line = myReader.nextLine();

					G.buildNodes(new Integer(line.split(" ")[0]));

					while (myReader.hasNextLine()) {
						line = myReader.nextLine();
						String[] data = line.split(" ");
						G.addEdge(new Edge(new Integer(data[0]), new Integer(data[1]), new Integer(data[2])));
					}

					myReader.close();

					long start = System.nanoTime();
					//							cost = MinimumSpanningTreeFinding.NaiveKruskal(G);
					cost = MinimumSpanningTreeFinding.Prim(G, 1);
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
