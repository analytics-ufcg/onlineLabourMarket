import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class PricePorCategoria {
	
	public  void filterDataBySkill(String fileName, String skill) throws IOException{

		String outputFileName = "pricePorCategoria/" + skill + ".txt";
		
		FileWriter fileWriter = new FileWriter(outputFileName, false);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		Scanner input = new Scanner(new File(fileName));
		
		String currentDay = "";
		String price = "";
		
		while(input.hasNextLine()){
			
			String[] split = input.nextLine().split(";");
			price = split[4];
			String lineDay = split[0];
			
			if(!currentDay.equals(lineDay)){
				
				currentDay = lineDay;
				
				
			}
			
			if(split[3].equals(skill)){
				printWriter.println(currentDay + ";" + price);
				
			}
			
		}
		//printWriter.println(currentDay + ";" + price);
		
		input.close();

		printWriter.flush();
		printWriter.close();
	}


public static void main(String[] args) throws IOException{
		
		PricePorCategoria getSkill = new PricePorCategoria();
		
		if (args.length < 3) {
			System.err.print("uage: java GetSkillInTime <input_demand_file> <skill ranking> <line_number>");
		}
		
		int lineNumber = Integer.valueOf(args[2]);
		
		Scanner scanner = new Scanner(new File(args[1]));
		for (int i = 0; i < lineNumber; i++) {
			//System.err.println(scanner.nextLine());
			System.out.println("Ta rodando");
			getSkill.filterDataBySkill(args[0], scanner.nextLine().split(";")[0]);
		}
		
		//String skillString = scanner.nextLine().split(";")[0];
		
		scanner.close();
		

		
		
	}
}
