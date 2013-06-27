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

public class ChangePriceFormatGuru {

	

	/*
	 * Método changeGuruPrice gera o arquivo guruFiltradoPorPrice.csv, onde
	 * é modificado as categorias do Guru, como tambem é filtrado , retirando 
	 * os jobs que são "by hourly" e onde o campo price está preenchido "not sure".
	 */

	public void changeGuruPrice(String elanceFile) throws IOException {

		Scanner scanner = new Scanner(new File(elanceFile));
		FileWriter elancePriceWriter = new FileWriter(new File(
				"guruFiltradoPorPreco.csv"), false);
		PrintWriter printElancePriceFile = new PrintWriter(elancePriceWriter);
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] lineField = line.split("\\&\\|\\&");
			String jobId = lineField[2];
			String category = lineField[11];
			String tipoJob = lineField[3];
			String price = lineField[7];
			String timeStamp = lineField[0];
			

			if (tipoJob.equals("Fixed Price") & !category.equals("None") & !price.toLowerCase().equals("not sure")) {

				String categoria = modificaCategoriaGuru(category);
				String priceChanged = modificaCampoPrice(price);
				priceChanged = modificaEscalaPrice(priceChanged);
				printElancePriceFile.println(timeStamp + "," + jobId + ","
						+ categoria + "," + priceChanged.trim());
			}

		}

		printElancePriceFile.flush();
		printElancePriceFile.close();

	}
	
	/**
	 * Método substitui do valores de preco que contem k ou M.Quando no valor tiver k, multiplica por 1000 
	 * e tiver M, multiplica por 1000000
	 * @param price
	 * @return novoPreco
	 */
	
	private String modificaEscalaPrice(String price){
		
		String novoPreco = "";
		
		if(price.contains("k")){
			double priceTemp = Double.parseDouble(price.replace("k", ""));
			novoPreco = String.valueOf(priceTemp*1000).replace(".0", "");
			
		}else if(price.contains("M")){
			
			double priceTemp = Double.parseDouble(price.replace("M", ""));
			novoPreco = String.valueOf(priceTemp*1000000).replace(".0", "");
		}else{
			novoPreco = price;
		}
		
		return novoPreco;
	}
	
	/**
	 * Método substitui os formatos de preco encontrado por o teto dos valores encontrado no formato.
	 * @param price
	 * @return novoPreco
	 */
	
	private String modificaCampoPrice(String price){
		
		String novoPreco = "";
		
		if (price.contains("-$")) {

			novoPreco = price.split("-")[1].replace("$", "");
			
		} else if (price.contains("Under $")) {

			novoPreco = price.replace("Under $", "");
			

		} else if (price.contains("Over $")) {

			novoPreco = price.replace("Over $", "");
			
		} else if ((!price.contains("-") & price.split("\\$").length == 3)) {

			novoPreco = price.split("\\$")[2];
			
		}else{
			novoPreco = price;
		}
		return novoPreco;
	}
	
	/**
	 * Método substitui categorias encontradas no Guru por suas correspondentes no Elance.
	 * @param category
	 * @return novaCategoria
	 */
	
	private String modificaCategoriaGuru(String category){
		
		String novaCategoria = "";
		
		if (category.equals("Programming & Databases")
				| category.equals("Websites & Ecommerce")
				| category.equals("Networking & Telephone Systems")
				| category.equals("ERP & CRM")) {

			novaCategoria = "IT & Programming";

		} else if (category.equals("Photography & Videography")
				| category.equals("Graphic Design & Multimedia")
				| category.equals("Illustration & Art")) {

			novaCategoria = "Design & Multimedia";

		} else if (category.equals("Writing, Editing & Translation")) {

			novaCategoria = "Writing & Translation";

		} else if (category.equals("Marketing & Communications")
				| category.equals("Sales & Telemarketing")) {

			novaCategoria = "Sales & Marketing";

		} else if (category.equals("Finance & Accounting")
				| category.equals("Business Consulting")) {

			novaCategoria = "Finance & Management";

		} else if (category.equals("Engineering & CAD")) {

			novaCategoria = "Engineering & Manufacturing";
        } else if(category.equals("Fashion & Interior Designs")){
		    novaCategoria = "Fashion & Interior Designs";
		}else{
			novaCategoria = category;
		}
		return novaCategoria;
	}

	/**
	 * Recebe como argumento o arquivo Guru.data
	 */
	public static void main(String[] args) throws IOException {

		ChangePriceFormatGuru changedPrice = new ChangePriceFormatGuru();
		changedPrice.changeGuruPrice(args[0]);

	}

}
