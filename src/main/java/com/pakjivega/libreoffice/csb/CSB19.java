package com.pakjivega.libreoffice.csb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.star.sheet.XSpreadsheet;

public abstract class CSB19 extends CSB {
	private static String nameFile = "adeudo.c19";
	private static final String PRESA1 = "51";
	private static final String PRESA2 = "80";
	private static final String ORDEA1 = "53";
	private static final String ORDEA2 = "80";
	private static final String INDIVOBLA1 = "56";
	private static final String INDIVOBLA2 = "80";
	
	@Override
	public String getCabeceraPresentador(XSpreadsheet presenterSheet) {
		String firstLine = null;
		try {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
			String sDate = sdf.format(now);
			String NIF = presenterSheet.getCellByPosition(1, 2).getFormula();
			String NamePresent = presenterSheet.getCellByPosition(1, 1).getFormula();
			firstLine = PRESA1 + PRESA2 + NIF + "000000000000".substring(NIF.length()) + sDate + "      " + NamePresent
					+ "                                                            ".substring(NamePresent.length())
					+ presenterSheet.getCellByPosition(1, 4).getFormula() + presenterSheet.getCellByPosition(2, 4).getFormula();
		} catch (Exception ex) {
			// do stuff with exception
			// response = ex.getMessage();
			ex.printStackTrace();
		}
		return firstLine;
	}
	@Override
	public String getCabeceraOrdenante(XSpreadsheet presenterSheet) {
		String secondLine = null;
		try {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date dateCargo = dateFormat.parse(presenterSheet.getCellByPosition(1, 5).getFormula());
			String sDate = sdf.format(now);
			String NIF = presenterSheet.getCellByPosition(1, 2).getFormula();
			String NamePresent = presenterSheet.getCellByPosition(1, 1).getFormula();
			String sDateCargo = sdf.format(dateCargo);

			secondLine = ORDEA1 + ORDEA2 + NIF + "000000000000".substring(NIF.length()) + sDate + sDateCargo + NamePresent
					+ "                                        ".substring(NamePresent.length()) + presenterSheet.getCellByPosition(1, 4).getFormula()
					+ presenterSheet.getCellByPosition(2, 4).getFormula() + presenterSheet.getCellByPosition(3, 4).getFormula()
					+ presenterSheet.getCellByPosition(4, 4).getFormula() + "        " + "01";
		} catch (Exception ex) {
			// do stuff with exception
			// response = ex.getMessage();
			ex.printStackTrace();
		}
		return secondLine;
	}
	@Override
	public List<String> getIndividualObligatorio(XSpreadsheet adeudosSheet, XSpreadsheet presenterSheet) {
		
		List<String> listAdeudos = new ArrayList<String>();
		String NIF = null;
		try { 
			NIF = presenterSheet.getCellByPosition(1, 2).getFormula();
			int i = 1;
			while ( ( adeudosSheet.getCellByPosition(i, 0).getFormula() != null) && ( adeudosSheet.getCellByPosition(i, 0).getFormula().toString() != null )
					&& ( adeudosSheet.getCellByPosition(i, 0).getFormula().toString().trim().length() >0 )) {
				listAdeudos.add(INDIVOBLA1+INDIVOBLA2 + NIF + "000000000000".substring(NIF.length()) + adeudosSheet.getCellByPosition(0, i).getFormula().toString() +
						"            ".substring(adeudosSheet.getCellByPosition(0, i).getFormula().toString().length()) +
						 adeudosSheet.getCellByPosition(1, i).getFormula().toString() + 
						 "                                        ".substring(adeudosSheet.getCellByPosition(1, i).getFormula().toString().length()) +
						 adeudosSheet.getCellByPosition(3, i).getFormula().toString()   ) ;
				i++;
			}
		} catch (Exception ex) {
			
		}
		return listAdeudos;
	}
}
