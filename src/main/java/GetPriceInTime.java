

import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class GetPriceInTime {

	public static void filterDataBySkill(String fileName, String skill)
			throws IOException {

		String outputFileName =  "pricesInTime2/" + skill + ".csv";

		FileWriter fileWriter = new FileWriter(outputFileName, false);
		PrintWriter printWriter = new PrintWriter(fileWriter);

		Scanner input = new Scanner(new File(fileName));
		ArrayList<Integer> lista = new ArrayList<Integer>();
		String currentDay = "";
		String currentTime = "";
		int freq = 0;
		// int[] array = null;
		printWriter.println("currentTime,elance,lista");
		while (input.hasNextLine()) {

			String[] split = input.nextLine().split(";");

			String lineDay = split[0];

			if (!currentDay.equals(lineDay)) {

				if (!currentDay.isEmpty()) {

					int mediana = 0;
					Collections.sort(lista);
					if (lista.size() != 0) {
						if ((lista.size() % 2) == 0) {
							int indice1 = (lista.size() / 2) - 1;
							int indice2 = (lista.size() / 2);
							mediana = (lista.get(indice1) + lista.get(indice2)) / 2;

						} else {
							mediana = lista.get(((lista.size() + 1) / 2) - 1);
						}
						String listaFormatada = lista.toString().replace("[",
								"");
						listaFormatada = listaFormatada.replace("]", "");
						listaFormatada = listaFormatada.replace(",", ";");
						listaFormatada = listaFormatada.replace(" ", "");
						printWriter.println(currentTime + "," + mediana + ","
								+ listaFormatada);
					} else {
						printWriter.println(currentTime + "," + mediana + ",NULL");
					}
				}

				currentDay = lineDay;
				lista.clear();
			}

			if (split[3].equals(skill)) {
				String price = split[4];
				price = price.replace(",", "");
				if(price.length() <= 7){
					int intPrice = Integer.parseInt(price);
					lista.add(intPrice);
				}
			}
			currentTime = split[1];

		}

		int mediana = 0;
		Collections.sort(lista);
		if (lista.size() != 0) {
			if ((lista.size() % 2) == 0) {
				int indice1 = (lista.size() / 2) - 1;
				int indice2 = (lista.size() / 2);
				mediana = (lista.get(indice1) + lista.get(indice2)) / 2;

			} else {
				mediana = lista.get(((lista.size() + 1) / 2) - 1);
			}
			
			String listaFormatada = lista.toString().replace("[",
					"");
			listaFormatada = listaFormatada.replace("]", "");
			listaFormatada = listaFormatada.replace(",", ";");
			listaFormatada = listaFormatada.replace(" ", "");
			printWriter.println(currentTime + "," + mediana + "," + listaFormatada);
		} else {
			printWriter.println(currentTime + "," + mediana + ",NULL" );
		}

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
