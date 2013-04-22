package us01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.PrintWriter;
import java.util.Scanner;

public class SkillInTimeMethod {
	
	private static String dataDir = "data/";
	private static String outPutDir = "skillsInTime/";
	
	private static void getSkillInTime(String dataFile,String skillFile) throws IOException{
		 
		
		 FileReader skillFileisntance = new FileReader(skillFile);
	     BufferedReader readSkillFile = new BufferedReader(skillFileisntance);
	     
	     String skillFileLine = readSkillFile.readLine();
	     
	    
	     while(skillFileLine != null){
	    	 
	    	 String skillRanking = skillFileLine.split(";")[0];
	    	
	    	 FileWriter fileWriter = new FileWriter(new File(outPutDir + skillRanking.replace("/", ".") +".txt"), false);
		     PrintWriter printWriter = new PrintWriter(fileWriter);
		     
		     FileReader dataFileInstance = new FileReader(dataFile);
		     BufferedReader readDataFile = new BufferedReader(dataFileInstance);
	    	 String dataFileLine = readDataFile.readLine();
	    	 
	    	 String firstTime = dataFileLine.split("\\|")[0];
	    	 System.out.println("Processando: " + skillRanking + "...");
	    	
	    	 int freq = 0;
	    	 while(dataFileLine != null){
	    		
	    		
	    		String dataSkill = dataFileLine.split("\\|")[12];
	    		String time = dataFileLine.split("\\|")[0];
	    		 System.out.println(dataSkill.substring(1, dataSkill.length()-1));
	    		if(firstTime.equals(time)){
	    			
	    			if(skillRanking.equals(dataSkill.substring(1, dataSkill.length()-1))){
	    				 
	    				freq++;
		    		}
	    		}else{
	    			
	    			printWriter.println(time + ";" + freq);
	    			firstTime = time;
	    			freq = 0;
	    			
	    			if(skillRanking.equals(dataSkill.substring(1, dataSkill.length()-1))){
	    				 
	    				freq++;
		    		}
	    			    			
	    		}
	    		
	    		dataFileLine = readDataFile.readLine();
	    		 
	    	 }
	    	
		     printWriter.flush();
		     printWriter.close();
		     
	    	 skillFileLine = readSkillFile.readLine(); 
	    	 
	     }

		
	}
	
	public static void main(String[] args) throws IOException{
		getSkillInTime(dataDir + "elanceSample.txt", dataDir + "saida2.txt");
		
	}

}
