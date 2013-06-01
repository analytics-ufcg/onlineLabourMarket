
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * @author Elias
 *
 */


public class ChangePriceFormatElance {
	
	/*
	 * Metodo changeElancePrice gera o arquivo elanceFiltradoPorPrice.csv, onde o Elance é filtrado , retirando 
	 * os jobs que são "by hourly" e onde o campo price está preenchido "not sure". 
	 */
	
	public void changeElancePrice(String elanceFile) throws IOException {

		Scanner scanner = new Scanner(new File(elanceFile));
		FileWriter elancePriceWriter = new FileWriter(new File(
				"elanceFiltradoPorPreco.csv"), false);
		PrintWriter printElancePriceFile = new PrintWriter(elancePriceWriter);
		
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] lineField = line.split("\\&\\|\\&");
			String jobId = lineField[2];
			String category = lineField[11];
			String tipoJob = lineField[3]; 
			String price = lineField[7];
			String timeStamp = lineField[0];
			
			if (tipoJob.equals("Fixed Price") && !price.toLowerCase().trim().equals("not sure")) {

				String priceChanged = modificaCampoPrice(price);	
				printElancePriceFile.println(timeStamp + "," + jobId + "," + category + "," + priceChanged.trim());	
			}

			
		}
		
		printElancePriceFile.flush();
		printElancePriceFile.close();
		
		
	}
	
	private String modificaCampoPrice(String price){
		
		if (price.contains(" - $")) {
			
			price = price.split(" - ")[1].replace("$", "");
			
		} else if (price.contains("About $")) {
			
			price = price.replace("About $", "");
			
		} else if (price.contains("Less than $")) {
			
			price = price.replace("Less than $", "");
			
		} else if (price.contains(" or less")) {
			
			price = price.replace(" or less", "");
			price = price.replace("$", "");
			
		} else if (price.contains(" or more")) {
			
			price = price.replace(" or more", "");
			price = price.replace("$", "");
			
		} else if (price.contains("More than")) {
			price = price.replace("More than $", "");
			
		}
		
		return price;
	}
	

	/**
	 * Recebe como argumento o arquivo Elance.data
	 */
	public static void main(String[] args) throws IOException {
		
		ChangePriceFormatElance changedPrice = new ChangePriceFormatElance();
		changedPrice.changeElancePrice(args[0]);
		
		
		
	}


}
