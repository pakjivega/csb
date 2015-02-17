package com.pakjivega.libreoffice.csb;

import java.io.File;
import java.io.FileWriter;

import com.sun.star.sheet.XSpreadsheet;
import java.util.Date;


//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.text.StrBuilder; 
public abstract class CSB {
	private static String nameFile = "adeudo";
	private static File csbFile = new File(nameFile);

	public static String generateFile(XSpreadsheet maSheet, String cadenaa) {
		String response = null;
		try {
			String secondLine = "5380" + "SecondLine" + cadenaa;
			response = csbFile.getPath();
			FileWriter fw = new FileWriter(csbFile);
			fw.write(getCabeceraPresentador(maSheet));
			fw.write(System.lineSeparator());
			fw.write(secondLine);
			fw.write(System.lineSeparator());
			fw.close();
		} catch (Exception ex) {
			// do stuff with exception
			// response = ex.getMessage();
			ex.printStackTrace();
		}
		return response;
	}
	private static String getCabeceraPresentador(XSpreadsheet maSheet) {
		String firstLine = null;
		try {
			Date now = new Date();
			String NIF = maSheet.getCellByPosition(1, 2).getFormula();
			firstLine = "5180" + NIF + "000000000000".substring( NIF.length()) +  "   " + "NombreOrdenante" + "  ";
		} catch (Exception ex) {
			// do stuff with exception
			// response = ex.getMessage();
			ex.printStackTrace();
		}
		return firstLine;
	}

}
