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
	public static String validateCCC(String entidad, String oficina, String dc, String cuenta) throws CCCInvalidException{
		entidad = entidad.replace("'", "");
		oficina = oficina.replace("'", "");
		dc = dc.replace("'", "");
		cuenta = cuenta.replace("'", "");		
		if (( ( entidad != null) && (entidad.length() == 4) && ( oficina != null) && (oficina.length() == 4) 
			&& ( dc != null) && (dc.length() == 2) && ( cuenta != null) && (cuenta.length() == 10) )  && 
			 ( (getDigit("00" + entidad + oficina) == dc.charAt(0) ) && ( getDigit(cuenta) == dc.charAt(1)) ) ) {
			
			return entidad + oficina + dc + cuenta;
		} else {
			throw new CCCInvalidException("Numero de cuenta invalida" + entidad +"/" + oficina + "/" + dc + "/" + cuenta);
		}

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
