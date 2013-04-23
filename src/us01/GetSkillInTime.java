package us01;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class GetSkillInTime {
	
	public static void filterDataBySkill(String fileName, String skill)
			throws IOException, FileNotFoundException {

		String outputFileName = skill + ".txt";
		
		FileWriter fileWriter = new FileWriter(outputFileName, false);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		Scanner input = new Scanner(fileName);
		
		String currentTime = "";
		int freq = 0;
		
		while(input.hasNextLine()){
			
			String[] split = input.nextLine().split("\\&\\|\\&");
			
			String lineTime = split[0];
			
			if(!currentTime.equals(lineTime)){
				
				if(!currentTime.isEmpty()){
					printWriter.println(lineTime + ";" + freq);
				}
				
				currentTime = lineTime;
				freq = 0;
			}
			
			List<String> skillList = Arrays.asList(split[12].substring(1, split[12].length()-1).split(";"));
			if(skillList.contains(skill)){
				freq++;
			}
			
			
			
			
//			String time = dataFileLine.split("\\|")[0];
//			//System.out.println(dataFileLine);
//			if(lineTime.equals(time)){
//				System.out.println("first:" + lineTime + "--" + "time:" + time + "--" + skill);
//				if(skillList.contains(skill)){
//
//					freq++;
//				}
//			}else{
//				System.out.println(skill +"--"+lineTime+"--"+freq);
//				System.out.println("entrou no else");
//				printWriter.println(lineTime + ";" + freq);
//				lineTime = time;
//				freq = 0;
//
//				//	    			if(skillList.contains(skillRanking)){
//				//	    				 
//				//	    				freq++;
//				//		    		}
//
//			}
		}

		printWriter.flush();
		printWriter.close();
	}
	
	/**
	 * Entry point
	 * @param args "input demand file name" "input raking file name" 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		
		if (args.length < 3) {
			System.err.print("uage: java GetSkillInTime <input_demand_file> <skill ranking> <line_number>");
		}
		
		int lineNumber = Integer.valueOf(args[2]);
		
		Scanner scanner = new Scanner(args[1]);
		for (int i = 0; i < lineNumber; i++) {
			scanner.nextLine();
		}
		
		String skillString = scanner.nextLine().split(";")[0];
		
		scanner.close();
		
		filterDataBySkill(args[0], skillString);
	}

}
