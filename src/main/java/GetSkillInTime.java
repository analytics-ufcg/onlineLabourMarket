import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 
 * @author Elias
 *
 */


public class GetSkillInTime {
	
	/*
	 * Metodo filterDataBySkill(), gera um arquivo para cada categoria.Cada arquivo possui a quantidade de jobs
	 * da categoria disponiveis por dia.  
	 */
	
	private  void geraArquivosParaCadaSkill(String fileName, String skill, String plataforma)
			throws IOException {

		String outputFileName = "skillsInTime2/" + skill + ".csv";

		FileWriter fileWriter = new FileWriter(outputFileName, false);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		
		Scanner input = new Scanner(new File(fileName));
		
		String currentTime = "";
		String currentDay = "";
		int freq = 0;
		
		String cabecalho = escolheCabecalhoArquivo(plataforma);
		printWriter.println("timeStamp," + cabecalho);
		
		while (input.hasNextLine()) {

			String[] split = input.nextLine().split(",");

			String lineDay = (split[0]).split(";")[0];
			
			if (!currentDay.equals(lineDay)) {

				if (!currentDay.isEmpty()) {

					printWriter.println(currentTime + "," + freq);
				}

				currentDay = lineDay;
				freq = 0;
			}
			if (split[2].equals(skill)) {
				freq++;
			}
			currentTime = (split[0]).split(";")[1];
		}

		printWriter.println(currentTime + "," + freq);

		input.close();

		printWriter.flush();
		printWriter.close();
	}
	
	private String escolheCabecalhoArquivo(String plataforma){
		
		String cabecalho = "";
		if(plataforma.toLowerCase().equals("guru")){
			cabecalho = "Guru";
		}else if(plataforma.toLowerCase().equals("elance")){
			cabecalho = "Elance";
		}
		
		return cabecalho;
	}

	/**
	 * Recebe 4 argumentos(elanceAgrupadoPorDia.csv ou guruAgrupadoPorDia.csv, skillRanking.txt, 
	 * quantidade de skills e o nome da plataforma)
	 */
	public static void main(String[] args) throws IOException {

		GetSkillInTime gsit = new GetSkillInTime();
		
		int lineNumber = Integer.valueOf(args[2]);
		String plataforma = args[3];

		Scanner scanner = new Scanner(new File(args[1]));
		for (int i = 0; i < lineNumber; i++) {
			gsit.geraArquivosParaCadaSkill(args[0], scanner.nextLine().split(";")[0],plataforma);
		}

		scanner.close();
	}



}
