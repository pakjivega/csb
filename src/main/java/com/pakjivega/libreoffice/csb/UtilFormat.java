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
	public static boolean validateCCC(String entidad, String oficina, String dc, String cuenta){
		boolean response = false;
		if ( ( entidad != null) && (entidad.length() == 4) && ( oficina != null) && (oficina.length() == 4) 
			&& ( dc != null) && (dc.length() == 2) && ( cuenta != null) && (cuenta.length() == 10) ) {
			if ( (getDigit("00" + entidad + oficina) == dc.charAt(0) ) && ( getDigit(cuenta) == dc.charAt(1)) )  {
				response = true;
			}
		}
		return response;
	}
	private static char getDigit(String valor){
		int[] valores = {1, 2, 4, 8, 5, 10, 9, 7, 3, 6};
		int control = 0;
		for (int i=0; i<=9; i++){
			control += (valor.charAt(i)) * valores[i];
		}
		control = 11 - (control % 11);
		if (control == 11) {
			control = 0;
		}
		else if (control == 10) {
			control = 1;
		}
		return Character.forDigit(control, 10);
	}	
}
