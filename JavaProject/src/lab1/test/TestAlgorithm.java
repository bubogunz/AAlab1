package lab1.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class TestAlgorithm {

	public static final void test(String algorithm){
		try {
			File src = new File("src/lab1/test/results.txt");
			Scanner scanSrc = new Scanner(src);
			
			File out;
			if(algorithm == "prim")
				out = new File("Prim.txt");
			else if(algorithm == "naivekruskal")
				out = new File("NaiveKruskal.txt");
			else if(algorithm == "kruskal")
				out = new File("Kruskal.txt");
			else{
				scanSrc.close();
				throw new InvalidParameterException("Wrong choice of algorithm");

			}
			Scanner outSrc = new Scanner(out);
			
			int count = 1;
			while (scanSrc.hasNextLine()) {
				String data1 = scanSrc.nextLine();
				String[] words1 =  data1.split(" ");
				if(words1[0].equals("MST")) {
					Boolean skip = false;
					while(outSrc.hasNextLine() && !skip) {
						String data2 = outSrc.nextLine();
						String[] words2 = data2.split(" ");
						if(words2[0].equals("MST")) {
							assert Integer.valueOf(words1[2]).equals(Integer.valueOf(words2[2])) : "MST wrong is n. " + count;
							skip = true;
						}
					}
					count++;
				}
			}	
				
			System.out.println("Test passed.");
			scanSrc.close();
			outSrc.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found.");
			e.printStackTrace();
		}
	}
}
