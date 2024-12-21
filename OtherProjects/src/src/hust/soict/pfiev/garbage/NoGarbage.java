package src.hust.soict.pfiev.garbage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NoGarbage {

	public static void main (String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String filepath = "C:\\Users\\HIEP\\eclipse-workspace\\OtherProjects\\src\\src\\hust\\soict\\pfiev\\garbage\\WeirdText.txt";
		String output = "";
		File f = new File(filepath);
		Scanner sc = new Scanner(f);
		long startTime, endTime; 
		
        // Improvement
        StringBuilder outputStringBuilder = new StringBuilder();
        while (sc.hasNextLine()) {
            outputStringBuilder.append(sc.nextLine()) ;
        }
        startTime = System.currentTimeMillis();
        while (sc.hasNextLine()) {
            output += sc.nextLine() + "\n" ;
        }
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        //System.out.println(output);
	}

}