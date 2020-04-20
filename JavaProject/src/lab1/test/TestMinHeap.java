package lab1.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lab1.model.Edge;
import lab1.model.Graph;
import lab1.model.MinHeap;
import lab1.model.SortEdgesByWeight;

public class TestMinHeap {

	public static final void testRemove(){
		try (Stream<Path> walk = Files.walk(Paths.get("mst_dataset"))) {
			List<String> mst_dataset = walk.filter(Files::isRegularFile).map(x -> x.toString()).sorted()
					.collect(Collectors.toList());  
			for(int i = 0; i<56; i++){
				String entryset = mst_dataset.get(i);
//        		String entryset = "mst_dataset/input_random_40_2000.txt";
				System.out.println("file: " + entryset );
        		try {
        			MinHeap<Edge> heap = new MinHeap<Edge>(new SortEdgesByWeight());
        			Graph G = new Graph();
        
        			File myObj = new File(entryset);
        			Scanner myReader = new Scanner(myObj);
        			String line = myReader.nextLine();
        
        			int nodes = Integer.valueOf(line.split(" ")[0]);
        			G.buildNodes(nodes);
        
        			while (myReader.hasNextLine()) {
        				line = myReader.nextLine();
        				String[] data = line.split(" ");
        				G.addEdge(new Edge(G.getNodeByID(Integer.valueOf(data[0])), 
        						G.getNodeByID(Integer.valueOf(data[1])), Integer.valueOf(data[2])));
        			}
        			myReader.close();
        
        			G.getEdges().stream().forEach(edge -> heap.insert(edge));
        			
        			if(heap.isMinHeap(0)) 
        				System.out.println("Former heap is min heap");
        			else throw new AssertionError("Former heap is not min heap");
        			
        			heap.extract(0);
        			assert heap.isMinHeap(0): "heap is not min heap anymore when extracting edge " 
        					+ 0 + " having an heap size of " + heap.size();
        			heap.extract(heap.size()/2);
        			assert heap.isMinHeap(0): "heap is not min heap anymore when extracting edge " 
        			+ heap.size()/2 + " having an heap size of " + heap.size();
        			heap.extract(heap.size() - 1);
        			assert heap.isMinHeap(0): "heap is not min heap anymore when extracting edge " 
        			+ heap.size() + " having an heap size of " + heap.size();
        			
        			System.out.println("Test passed.");
        			System.out.println();
        		} catch (FileNotFoundException e) {
        			System.out.println("File " +  entryset + " not found.");
        		} 
			}
    	}catch(IOException e) { }
	}
	
	public static void testStructure() {
		String entryset = "mst_dataset/input_random_68_100000.txt";
		try {
			MinHeap<Edge> heap = new MinHeap<Edge>(new SortEdgesByWeight());
			PriorityQueue<Edge> queue = new PriorityQueue<Edge>(new SortEdgesByWeight());
			
			Graph G = new Graph();

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

			G.getEdges().stream().forEach(edge -> {
				heap.insert(edge);//O(n*log n)
				queue.offer(edge);//O(n*log n)
			});

			while(!heap.isEmpty()) {
				Edge polled = queue.poll();
				Edge extracted = heap.extractMin();
				assert polled.equals(extracted): "not equal";
			}
			System.out.println("Test passed.");
			System.out.println();
		} catch (FileNotFoundException e) {
			System.out.println("File " +  entryset + " not found.");
		} 
	}
}

