package lab1.test.testprim;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestPrim {

	public static final void test() {
		try {
			File src = new File("src/lab1/test/results.txt");
			Scanner scanSrc = new Scanner(src);
			
			File out = new File("Prim.txt");
			Scanner outSrc = new Scanner(out);
			
			while (scanSrc.hasNextLine()) {
				String data1 = scanSrc.nextLine();
				String[] words1 =  data1.split(" ");
				if(words1[0].equals("MST")) {
					Boolean skip = false;
					while(outSrc.hasNextLine() && !skip) {
						String data2 = outSrc.nextLine();
						String[] words2 = data2.split(" ");
						if(words2[0].equals("MST")) {
							assert Integer.valueOf(words1[2]).equals(Integer.valueOf(words2[2]));
							skip = true;
						}
					}
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
