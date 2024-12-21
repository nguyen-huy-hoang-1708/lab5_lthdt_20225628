package src.hust.soict.pfiev.garbage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GarbageCreator {
private static final long MAX_RUNTIME = 10;
	
	public static void main (String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String filepath = "C:\\Users\\HIEP\\eclipse-workspace\\OtherProjects\\src\\src\\hust\\soict\\pfiev\\garbage\\WeirdText.txt";
		String output = "";
		File f = new File(filepath);
		Scanner sc = new Scanner(f);
		long startTime, endTime; 
		startTime = System.currentTimeMillis();
        while (sc.hasNextLine()){
            output += sc.nextLine() + "\n" ;
            if (System.currentTimeMillis() - startTime > MAX_RUNTIME) {	
            	System.out.println("TLE!");
            	break;
            }
        }
        endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
	}
}
