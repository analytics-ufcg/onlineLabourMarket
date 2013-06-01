

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


/*
 * Classe usada para conhecer um pouco dos dados.
 */
public class PriceFormat {

	public static void searchPriceFormat(String elanceFile) throws IOException {

		Scanner scanner = new Scanner(new File(elanceFile));
		FileWriter typePaymentFile = new FileWriter(new File(
				"TypePaymentFile.txt"), false);
		PrintWriter printTypePaymentFile = new PrintWriter(typePaymentFile);
		
		FileWriter categoryFile = new FileWriter(new File(
				"TypeCategorysFile.txt"), false);
		PrintWriter printCategoryFile = new PrintWriter(categoryFile);
		
		FileWriter priceFile = new FileWriter(new File(
				"TypePricesFile.txt"), false);
		PrintWriter printPriceFile = new PrintWriter(priceFile);
		
		FileWriter scalePriceFile = new FileWriter(new File(
				"TypeScalePriceFile.txt"), false);
		PrintWriter printScalePriceFile = new PrintWriter(scalePriceFile);
		
		ArrayList<String> arrayTypePrice = new ArrayList<String>();
		ArrayList<String> arrayCategorias = new ArrayList<String>();
		ArrayList<String> arrayPrices = new ArrayList<String>();
		ArrayList<String> arrayScalePrices = new ArrayList<String>();
		int countLines = 0;
		int countGoodLines = 0;
		
		
		
		int countHourly = 0;
		int countNotSure = 0;
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] lineField = line.split("\\&\\|\\&");
			String typePrice = lineField[3];
			String price = lineField[7];
			
			if((price.toLowerCase().contains("not sure"))){
				countNotSure++;
			}
		
			if (typePrice.equals("Fixed Price")) {
				
				if(price.contains("-") & !arrayScalePrices.contains(price)){
					printScalePriceFile.println(price);
				}

				if (!(price.contains("-$")) & !(price.toLowerCase().contains("not sure")) & !price.contains("Under $")
						& !(!price.contains("-") & ("\\" + price).split("\\$").length == 3)) {
					
					if(!arrayPrices.contains(price)){
						printPriceFile.println(price);
						arrayPrices.add(price);
					}
					
					
				}
				countGoodLines++;

			} else  {
				countHourly++;
				if(!arrayTypePrice.contains(typePrice)){
					printTypePaymentFile.println(typePrice);
					arrayTypePrice.add(typePrice);
				}
				
			}
			
			String categoria = lineField[11];
			if(!arrayCategorias.contains(categoria)){
				printCategoryFile.println(categoria);
				arrayCategorias.add(categoria);
			}
			countLines++;
		}
		
		
		
		FileWriter fileWriter = new FileWriter(new File(
			"guruSummary.txt"), false);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println("Lines" + ";" + countLines);
		printWriter.println("Good Lines" + ";" + countGoodLines);
		printWriter.println("Skill per Hourly" + ";" + countHourly);

		printWriter.println("Payment not sure" + ";" + countNotSure);

		scanner.close();
		printWriter.flush();
		printWriter.close();

		printTypePaymentFile.flush();
		printTypePaymentFile.close();
		
		printCategoryFile.flush();
		printCategoryFile.close();
		
		printPriceFile.flush();
		printPriceFile.close();
		
		printScalePriceFile.flush();
		printScalePriceFile.close();
	}

	/**
	 * Entry point
	 * 
	 * @param args
	 *            "input demand file name" "input raking file name"
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		searchPriceFormat(args[0]);
//		String line = "1362095806&|&Logo Completion&|&https://www.guru.com/jobs/Logo-Completion/923142&|&Fixed Price&|&Gwyn&|&/pro/employerhistory.aspx?compid=572742&|&- Expires&|&Not Sure&|&Not Sure&|&22&|&Have designed the concept for five logos: Need a freelancer to: 1. lay the lettering so that each logo is the same: 2. Change the font size and correct wording on the logos that go around each&|&Graphic Design & Multimedia&|&logos & identity packages;creative, innovate, cooperative is most impt.&|&$45";
//		String[] linefield = line.split("\\&\\|\\&");
//		System.out.println(linefield.length);
//		System.out.println(linefield[7]);
//		System.out.println(("\\" + linefield[7]).toLowerCase().contains("not sure"));
//		System.out.println((linefield[7]).toLowerCase().contains("not sure"));
//		System.out.println(("\\" + linefield[7]).contains("not sure"));
//		System.out.println(!("\\" + linefield[7]).toLowerCase().contains("not sure"));
//		System.out.println(false &  false);
//		System.out.println(false &&  false);
	}

}
