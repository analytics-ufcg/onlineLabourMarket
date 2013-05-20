import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class AgrupaPorDia {
	
	public String getDay(String timeStamp){
		
		Integer inteiro = Integer.parseInt(timeStamp);
		Timestamp stamp = new Timestamp(inteiro);
		Date date = new Date(stamp.getTime() * 1000);
		int mes = date.getMonth() + 1;
		String ano = "" + date.getYear();
		String data = date.getDate() + "/" + mes + "/" + ano.substring(1);
		return data;
	}
	
	public  void filterDataBySkill(String fileName) throws IOException{

		String outputFileName =  "elanceAgrupadoPorDia.txt";
		ArrayList<String> list = new ArrayList<String>();
		FileWriter fileWriter = new FileWriter(outputFileName, false);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		Scanner input = new Scanner(new File(fileName));
		
		String currentDay = "";
		
		String line = "";
		
		while(input.hasNextLine()){
			
			line = input.nextLine();
			
			String[] split = line.split(";");
			
			String lineDay = getDay(split[0]);
			
			if(!currentDay.split("/")[0].equals(lineDay.split("/")[0])){
				
				currentDay = lineDay;
				list.clear();
			}
			
			
			String job = split[1];
			if(!list.contains(job)){
				printWriter.println(currentDay + ";" + line);
				list.add(job);
				
			}
			
		}
//		printWriter.println(currentDay + ";" + line);
		
		input.close();

		printWriter.flush();
		printWriter.close();
	}
	
	/**
	 * Entry point
	 * @param args "input demand file name" "input raking file name" 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException{
		
		AgrupaPorDia agrupando = new AgrupaPorDia();
		agrupando.filterDataBySkill(args[0]);
		
//		System.out.println(agrupando.getDay("1351114848"));
//		System.out.println(agrupando.getDay("1351114988"));
//		System.out.println(agrupando.getDay("1351115537"));
//		System.out.println(agrupando.getDay("1351116290"));
//		System.out.println(agrupando.getDay("1351118280"));
//		System.out.println(agrupando.getDay("1362095789"));
		//System.out.println(agrupando.getDay("1351116290"));
	}

}