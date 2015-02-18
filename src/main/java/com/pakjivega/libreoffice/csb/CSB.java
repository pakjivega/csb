package com.pakjivega.libreoffice.csb;

import java.io.File;
import java.io.FileWriter;

import com.sun.star.sheet.XSpreadsheet;

import java.text.SimpleDateFormat;
import java.util.Date;


//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.text.StrBuilder; 
public abstract class CSB {
	private static String nameFile = "adeudo";
	private static File csbFile = new File(nameFile);
	private static final String PRESA1 = "51";
	private static final String PRESA2 = "80";
	
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
			Date now = new Date();
			fw.write("now:" + now);
			fw.write(System.lineSeparator());
			fw.write("now.getDate():" + now.getDate());
			fw.write(System.lineSeparator());
			fw.write("now.getMonth():" + now.getMonth());
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
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
			String sDate= sdf.format(now);
			String NIF = maSheet.getCellByPosition(1, 2).getFormula();
			String NamePresent = maSheet.getCellByPosition(1, 1).getFormula();
			firstLine = PRESA1 + PRESA2 + NIF + "000000000000".substring( NIF.length()) + sDate + "      " + 
					NamePresent + "                                                            ".substring( NamePresent.length()) +
					maSheet.getCellByPosition(1, 4).getFormula() + maSheet.getCellByPosition(2, 4).getFormula();
		} catch (Exception ex) {
			// do stuff with exception
			// response = ex.getMessage();
			ex.printStackTrace();
		}
		return firstLine;
	}

}
