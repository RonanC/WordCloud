package ie.gmit.sw.runner;

import java.io.*;
import java.util.*;

import ie.gmit.sw.io.DataReader;
import ie.gmit.sw.draw.ImageDrawer;;

public class WordCloudRunner {
	static DataReader dr;
	static ImageDrawer id;
	
	public static void main(String[] args) {
		dr = new DataReader();
		id = new ImageDrawer();
		
		System.out.println("\nprinting valid words");
		dr.printValidWords();
		
		System.out.println("\nprinting sorted words");
		dr.printSortedWords();
		
		System.out.println("\nfin.");
	}
}


