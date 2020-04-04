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
import lab1.model.Node;

public class Main {

    public static void main(String[] args) {
	//fetch files 
	try (Stream<Path> walk = Files.walk(Paths.get("mst_dataset"))) {
	    List<String> mst_dataset = walk.filter(Files::isRegularFile)
		    .map(x -> x.toString())
		    .sorted()
		    .collect(Collectors.toList());
	    final File outputPath = new File("out.txt");
	    FileWriter fw = new FileWriter(outputPath, true);
	    mst_dataset.forEach(entryset -> { 
		try {
		    Graph G = new Graph();
		    int cost = 0;
//		    entryset = "mst_dataset/input_random_49_10000.txt";
		    
		    String buffer = new String("File:" + entryset + "\n");

		   
		    File myObj = new File(entryset);
		    Scanner myReader = new Scanner(myObj);
		    String line = myReader.nextLine();
		    
		    
		    G.buildNodes(Integer.valueOf(line.split(" ")[0]));

		    while (myReader.hasNextLine()) {
			line = myReader.nextLine();
			String[] data = line.split(" ");
			G.addEdge(new Edge(new Node(Integer.valueOf(data[0])), new Node(Integer.valueOf(data[1])), Integer.valueOf(data[2])));
		    }	
		    myReader.close();
		    
		    long start = System.nanoTime();
		    cost = MinimumSpanningTreeFinding.Prim(G, 6);
		    long stop = System.nanoTime();
		    
		    long timeElapsed = stop - start;
		    double time = timeElapsed;
		    time = time/1000000000;
		    buffer += "MST costs " + cost + "\n";
		    buffer += "Time elapsed: " + time + "s\n\n";
		    fw.write(buffer);
		    
//		    System.out.println(buffer);
		    
		}catch(FileNotFoundException e) { }
		catch (IOException e) { }
	    });
	    fw.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
    }

}
