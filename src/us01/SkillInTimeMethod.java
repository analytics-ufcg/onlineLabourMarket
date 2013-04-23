package us01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SkillInTimeMethod {
	
	private static String dataDir = "data/";
	private static String outPutDir = "skillsInTime/";
	
	private static void getSkillInTime(String dataFile,String skillFile) throws IOException{
		 
		
		 FileReader skillFileisntance = new FileReader(skillFile);
	     BufferedReader readSkillFile = new BufferedReader(skillFileisntance);
	     
	     String skillFileLine = readSkillFile.readLine();
	     
	     int cont1 = 0;
	     int cont2 = 0;
	     while(skillFileLine != null){
	    	 
	    	 String skillRanking = skillFileLine.split(";")[0];
	    	
	    	 FileWriter fileWriter = new FileWriter(new File(outPutDir + skillRanking.replace("/", ".") +".txt"), false);
		     PrintWriter printWriter = new PrintWriter(fileWriter);
		     
		     FileReader dataFileInstance = new FileReader(dataFile);
		     BufferedReader readDataFile = new BufferedReader(dataFileInstance);
	    	 String dataFileLine = readDataFile.readLine();
	    	 
	    	 String firstTime = dataFileLine.split("\\|")[0];
	    	 //System.out.println("Processando: " + skillRanking + "...");
	    	
	    	 int freq = 0;
	    	 while(dataFileLine != null){
	    		
	    		
	    		String dataSkills = dataFileLine.split("\\|")[12];
	    		
	    		List skillList = Arrays.asList(dataSkills.substring(1, dataSkills.length()-1).split(";"));
	    		String time = dataFileLine.split("\\|")[0];
	    		//System.out.println(dataFileLine);
	    		if(firstTime.equals(time)){
	    			System.out.println("first:" + firstTime + "--" + "time:" + time + "--" + skillRanking);
	    			if(skillList.contains(skillRanking)){
	    				
	    				freq++;
		    		}
	    		}else{
	    			System.out.println(skillRanking +"--"+firstTime+"--"+freq);
	    			System.out.println("entrou no else");
	    			printWriter.println(firstTime + ";" + freq);
	    			firstTime = time;
	    			freq = 0;
	    			
//	    			if(skillList.contains(skillRanking)){
//	    				 
//	    				freq++;
//		    		}
	    			    			
	    		}
	    		
	    		dataFileLine = readDataFile.readLine();
	    		if(dataFileLine == null){
	    			printWriter.println(firstTime + ";" + freq);
	    			
	    		}
	    		cont2++; 
	    	 }
	    	
		     printWriter.flush();
		     printWriter.close();
		     
	    	 skillFileLine = readSkillFile.readLine(); 
	    	 cont1++;
	     }

		
	}
	
	public static void main(String[] args) throws IOException{
		getSkillInTime(dataDir + "elanceSampleTest.txt", dataDir + "skillTestFile.txt");
		
	}

}
