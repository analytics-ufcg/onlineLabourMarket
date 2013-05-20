import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class GetSkillInTime {
	
	public static void filterDataBySkill(String fileName, String skill)
			throws IOException {

		String outputFileName = "skillsInTime2/" + skill + ".txt";

		FileWriter fileWriter = new FileWriter(outputFileName, false);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		Scanner input = new Scanner(new File(fileName));
		//ArrayList<Integer> lista = new ArrayList<Integer>();
		
		String currentTime = "";
		String currentDay = "";
		int freq = 0;
		
		while (input.hasNextLine()) {

			String[] split = input.nextLine().split(";");

			String lineDay = split[0];
			

			if (!currentDay.equals(lineDay)) {

				if (!currentDay.isEmpty()) {

					printWriter.println(currentTime + "," + freq);
				}

				currentDay = lineDay;
				//currentTime = lineTime;
				
				freq = 0;
			}
			
			if (split[3].equals(skill)) {
				freq++;
			}
			currentTime = split[1];
		}

		printWriter.println(currentTime + "," + freq);

		input.close();

		printWriter.flush();
		printWriter.close();
	}

	/**
	 * Entry point
	 * 
	 * @param args
	 *            "input demand file name" "input raking file name"
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		if (args.length < 3) {
			System.err
					.print("uage: java GetSkillInTime <input_demand_file> <skill ranking> <line_number>");
		}

		int lineNumber = Integer.valueOf(args[2]);

		Scanner scanner = new Scanner(new File(args[1]));
		for (int i = 0; i < lineNumber; i++) {
			filterDataBySkill(args[0], scanner.nextLine().split(";")[0]);
		}

		scanner.close();
	}



}
