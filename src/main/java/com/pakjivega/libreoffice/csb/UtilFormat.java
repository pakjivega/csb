package com.pakjivega.libreoffice.csb;

public class UtilFormat {
	public static String fillLeftSpace(String cadena, int length) {
		String response = String.format("%" + length + "s" , cadena);
		return response;
	}
	public static String fillRightSpace(String cadena, int length) {
		String response = String.format("%-" + length + "s" , cadena);
		return response;
	}
	public static String fillRightCeros(String cadena, int length) {
		String response = String.format("%0" + length + "d" , 0);
		response = cadena + response.substring(cadena.length());
		return response;
	}
	/**
	 * Format import with two decimals and filled with 0 in the left 
	 * @param ammount
	 * @param length Number of Characters
	 * @return the String 
	 */
	public static String formatImporte(double importe, int length) {
		String response = String.format("%0" + length + ".0f" , importe*100);
		return response;
	}
	public static String formatNumero(int numero, int length) {
		String response = String.format("%0" + length + "d" , numero);
		return response;
	}
}
