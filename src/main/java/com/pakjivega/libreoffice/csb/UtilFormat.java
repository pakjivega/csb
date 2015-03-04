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
}
