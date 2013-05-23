

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


/*
 * Classe usada para conhecer um pouco dos dados.
 */
public class PriceFormat {

	public static void searchPriceFormat(String elanceFile) throws IOException {

		Scanner scanner = new Scanner(new File(elanceFile));
		FileWriter defaltPaymentFile = new FileWriter(new File(
				"defaltPaymentFile.txt"), false);
		PrintWriter printDefaltPayment = new PrintWriter(defaltPaymentFile);
		
		FileWriter paymentFormatFile = new FileWriter(new File(
				"paymentFormatFile.txt"), false);
		PrintWriter printPaymentFormat = new PrintWriter(paymentFormatFile);
		
		int countLines = 0;
		int countGoodLines = 0;
		int countHourlyRate = 0;
		String defaltPayment = "";
		String paymentFormat = "";
		String price = null;
		int countHourly = 0;
		int countOtherPayment = 0;
		int countOtherPaymentFormat = 0;
		int countNotSure = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] lineField = line.split("\\&\\|\\&");
			
			if (lineField[3].equals("Fixed Price")) {

				if (lineField[7].contains(" - $")) {
					
					 countGoodLines++;
					
				} else if (lineField[7].contains("About $")) {
					
					 countGoodLines++;
					
				} else if (lineField[7].contains("Less than $")) {
					
					 countGoodLines++;
					
				} else if (lineField[7].contains(" or less")) {
					
					 countGoodLines++;
					
				} else if (lineField[7].contains(" or more")) {
					
					 countGoodLines++;
					
				} else if (lineField[7].contains(" Not Sure")) {
					countNotSure++;
				} else if (lineField[7].contains("More than")) {
					countGoodLines++;
				} else {
					if(!defaltPayment.contains(lineField[7])){
						defaltPayment = defaltPayment + lineField[7];
					}
					countOtherPaymentFormat++;
					
				}

			} else if (lineField[3].equals("Hourly")) {
				countHourly++;
			}else if (lineField[3].equals("Hourly Rate")) {
				
				countHourlyRate++;
			} else {
				if(!paymentFormat.contains(lineField[3])){
					paymentFormat = paymentFormat + lineField[3] + "\n"; 
				}
				countOtherPayment++;
				
			}
			countLines++;
		}
		
		printDefaltPayment.println(defaltPayment);
		printPaymentFormat.println(paymentFormat);
		FileWriter fileWriter = new FileWriter(new File(
				"elanceSummary.txt"), false);
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println("Lines" + ";" + countLines);
		printWriter.println("Good Lines" + ";" + countGoodLines);
		printWriter.println("Skill per Hourly" + ";" + countHourly);
		printWriter.println("Skill per Hourly Rate" + ";" + countHourlyRate);
		printWriter.println("Skill with other payment" + ";" + countOtherPayment);
		printWriter.println("Skill with other payment format" + ";"
				+ countOtherPaymentFormat);
		printWriter.println("Payment not sure" + ";" + countNotSure);

		scanner.close();
		printWriter.flush();
		printWriter.close();

		printDefaltPayment.flush();
		printDefaltPayment.close();
		
		printPaymentFormat.flush();
		printPaymentFormat.close();
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
	}

}
