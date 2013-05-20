
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ChangePriceFormat {
	
	public void changeElancePrice(String elanceFile) throws IOException {

		Scanner scanner = new Scanner(new File(elanceFile));
		FileWriter elancePriceWriter = new FileWriter(new File(
				"elanceFiltradoPorPrice.txt"), false);
		PrintWriter printElancePriceFile = new PrintWriter(elancePriceWriter);
		
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] lineField = line.split("\\&\\|\\&");
			String jobId = lineField[2];
			String category = lineField[11];
			String tipoJob = lineField[3]; 
			String price = lineField[7];
			String timeStamp = lineField[0];
			String priceChanged = "";
			
			if (tipoJob.equals("Fixed Price")) {

				if (price.contains(" - $")) {
					
					priceChanged = price.split(" - ")[1].replace("$", "");
					printElancePriceFile.println(timeStamp + ";" + jobId + ";" + category + ";" + priceChanged.trim());
					
					
				} else if (price.contains("About $")) {
					
					priceChanged = price.replace("About $", "");
					printElancePriceFile.println(timeStamp + ";" + jobId + ";" + category + ";" + priceChanged.trim());
					
					
				} else if (price.contains("Less than $")) {
					
					priceChanged = price.replace("Less than $", "");
					printElancePriceFile.println(timeStamp + ";" + jobId + ";" + category + ";" + priceChanged.trim());
					
					
				} else if (price.contains(" or less")) {
					
					priceChanged = price.replace(" or less", "");
					priceChanged = priceChanged.replace("$", "");
					printElancePriceFile.println(timeStamp + ";" + jobId + ";" + category + ";" + priceChanged.trim());
					
					
				} else if (price.contains(" or more")) {
					
					priceChanged = price.replace(" or more", "");
					priceChanged = priceChanged.replace("$", "");
					printElancePriceFile.println(timeStamp + ";" + jobId + ";" + category + ";" + priceChanged.trim());
					
					
				} else if (price.contains("More than")) {
					priceChanged = price.replace("More than $", "");
					printElancePriceFile.println(timeStamp + ";" + jobId + ";" + category + ";" + priceChanged.trim());
				} else {
					
					}
					
					
				}

			
		}
		
		printElancePriceFile.flush();
		printElancePriceFile.close();
		
		
	}
	

	/**
	 * Entry point
	 * 
	 * @param args
	 *            "input demand file name" "input raking file name"
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		ChangePriceFormat changedPrice = new ChangePriceFormat();
		changedPrice.changeElancePrice(args[0]);
		
		
		
	}


}
