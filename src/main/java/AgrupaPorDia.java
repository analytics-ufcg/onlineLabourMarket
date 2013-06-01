import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


/**
 * 
 * @author Elias
 *
 */

public class AgrupaPorDia {
	
	/*
	 * Metodo getDay() recebe um timestamp e retorna a respectiva data formatada(dd/mm/aa).
	 */
	
	private String getDay(String timeStamp){
		
		Integer inteiro = Integer.parseInt(timeStamp);
		Timestamp stamp = new Timestamp(inteiro);
		Date date = new Date(stamp.getTime() * 1000);
		int mes = date.getMonth() + 1;
		String ano = "" + date.getYear();
		String data = date.getDate() + "/" + mes + "/" + ano.substring(1);
		return data;
	}
	
	/*
	 * Metodo getElancAgrupadoPorDia() gera o arquivo elanceAgrupadoPorDia.txt, onde os jobs não se
	 * repetem no mesmo dia.
	 */
	
	private  void getElanceAgrupadoPorDia(String fileName,String plataforma) throws IOException{

		String outputFileName =  escolheNomeArquivo(plataforma);
		ArrayList<String> list = new ArrayList<String>();
		FileWriter fileWriter = new FileWriter(outputFileName, false);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		Scanner input = new Scanner(new File(fileName));
		
		String currentDay = "";
		String line = "";
		
		while(input.hasNextLine()){
			
			line = input.nextLine();
			String[] split = line.split(",");
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

		
		input.close();

		printWriter.flush();
		printWriter.close();
	}
	
	private String escolheNomeArquivo(String plataforma){
		
		String nomeArquivo = "";
		if(plataforma.toLowerCase().equals("guru")){
			nomeArquivo = "guruAgrupadoPorDia.csv";
		}else if(plataforma.toLowerCase().equals("elance")){
			nomeArquivo = "elanceAgrupadoPorDia.csv";
		}
		
		return nomeArquivo;
	}
	
	/**
	 *Recebe como argumento o elanceFiltradoPorPreco.csv ou guruFiltradoPorPreco.csv
	 *(gerado através da classe ChancePriceFormat) e o nome da plataforma
	 */
	public static void main(String[] args) throws IOException{
		
		AgrupaPorDia agrupando = new AgrupaPorDia();
		
		String plataforma = args[1];	
		agrupando.getElanceAgrupadoPorDia(args[0],plataforma);
		

	}

}
