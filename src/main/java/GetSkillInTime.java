import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Processa o arquivo de demanda e gera uma linha do tempo com a frequência de uma determinada habilidade
 * 
 * @author Elias Paulino
 */
public class GetSkillInTime {
	
	public static void filterDataBySkill(String fileName, String skill) throws IOException{

		String outputFileName = skill + ".txt";
		
		FileWriter fileWriter = new FileWriter(outputFileName, false);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		Scanner input = new Scanner(new File(fileName));
		
		String currentTime = "";
		int freq = 0;
		
		while(input.hasNextLine()){
			
			String[] split = input.nextLine().split("\\&\\|\\&");
			
			String lineTime = split[0];
			
			if(!currentTime.equals(lineTime)){
				
				if(!currentTime.isEmpty()){
					printWriter.println(currentTime + ";" + freq);
				}
				
				currentTime = lineTime;
				freq = 0;
			}
			
			//List<String> skillList = Arrays.asList(split[12].split(";"));
			if(split[12].equals(skill)){
				freq++;
			}
			
		}
		printWriter.println(currentTime + ";" + freq);
		
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
		
		if (args.length < 3) {
			System.err.print("uage: java GetSkillInTime <input_demand_file> <skill ranking> <line_number>");
		}
		
		int lineNumber = Integer.valueOf(args[2]);
		
		Scanner scanner = new Scanner(new File(args[1]));
		for (int i = 0; i < lineNumber; i++) {
			//System.err.println(scanner.nextLine());
			filterDataBySkill(args[0], scanner.nextLine().split(";")[0]);
		}
		
		String skillString = scanner.nextLine().split(";")[0];
		
		scanner.close();
		
		//filterDataBySkill(args[0], skillString);
	}

}
