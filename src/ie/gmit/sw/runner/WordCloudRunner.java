package ie.gmit.sw.runner;

import java.io.*;
import java.util.*;

import ie.gmit.sw.io.DataReader;
import ie.gmit.sw.draw.ImageDrawer;;

public class WordCloudRunner {
	static DataReader dr;
	static ImageDrawer id;
	
	public static void main(String[] args) {
		String inputDataFileName = "SampleText.txt";
//		String inputDataUrlname = "http://www.oracle.com/";
		String inputDataUrlname = "http://www.ronanconnolly.ie/";
		
		dr = new DataReader();
		id = new ImageDrawer();
		
//		System.out.println("Reading in sample text file.");
//		dr.fileReader(inputDataFileName);
		
		System.out.println("Reading in sample url.");
		dr.urlReader(inputDataUrlname);
		
		System.out.println("\nprinting valid words");
		dr.printValidWords();
		
		System.out.println("\nprinting sorted words");
		dr.printSortedWords();
		
		System.out.println("\nfin.");
	}
}


