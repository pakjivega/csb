package com.pakjivega.libreoffice.csb;

import java.awt.font.NumericShaper;
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
	private static final String TOTORDA1 = "58";
	private static final String TOTORDA2 = "80";
	private static final String TOTGENA1 = "59";
	private static final String TOTGENA2 = "80";
	
	
	@Override
	public String getCabeceraPresentador(XSpreadsheet presenterSheet) {
		String firstLine = null;
		try {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
			String sDate = sdf.format(now);
			String NIF = presenterSheet.getCellByPosition(1, 2).getFormula();
			String namePresenter = presenterSheet.getCellByPosition(1, 1).getFormula();
			firstLine = PRESA1 + PRESA2 + UtilFormat.fillRightCeros(NIF, 12) + sDate + "      " + 
					UtilFormat.fillRightSpace(namePresenter, 60) +
					presenterSheet.getCellByPosition(1, 3).getFormula() + presenterSheet.getCellByPosition(1, 4).getFormula();
			numeroTotalRegistrosSoporte++;
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
			Date dateCargo = dateFormat.parse(presenterSheet.getCellByPosition(1, 10).getFormula());
			String sDate = sdf.format(now);
			String NIF = presenterSheet.getCellByPosition(1, 6).getFormula();
			String nameOrdenante = presenterSheet.getCellByPosition(1, 5).getFormula();
			String sDateCargo = sdf.format(dateCargo);

			secondLine = ORDEA1 + ORDEA2 + UtilFormat.fillRightCeros(NIF, 12) + sDate + sDateCargo +
					UtilFormat.fillRightSpace(nameOrdenante, 40) + 
					presenterSheet.getCellByPosition(0, 9).getFormula() +
					presenterSheet.getCellByPosition(1, 9).getFormula() + presenterSheet.getCellByPosition(2, 9).getFormula() +
					presenterSheet.getCellByPosition(3, 9).getFormula() + "        " + "01";
			numeroTotalRegistrosOrdenante++;
			numeroTotalRegistrosSoporte++;
			numeroOrdenantes++;
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
				
					listAdeudos.add(INDIVOBLA1+INDIVOBLA2 + UtilFormat.fillRightCeros(NIF, 12) + //adeudosSheet.getCellByPosition(0, i).getFormula().toString() +
						UtilFormat.fillRightSpace(adeudosSheet.getCellByPosition(0, i).getFormula(), 12) +
						UtilFormat.fillRightSpace(adeudosSheet.getCellByPosition(1, i).getFormula().toString(), 40) +
						adeudosSheet.getCellByPosition(3, i).getFormula().toString() + adeudosSheet.getCellByPosition(4, i).getFormula().toString() +
						adeudosSheet.getCellByPosition(5, i).getFormula().toString() + adeudosSheet.getCellByPosition(6, i).getFormula().toString() +
						UtilFormat.formatImporte(adeudosSheet.getCellByPosition(8, i).getValue(),10 ) +
						String.format("%06d", i) + 
						UtilFormat.fillRightSpace(adeudosSheet.getCellByPosition(0, i).getFormula(), 10) +
						UtilFormat.fillRightSpace(adeudosSheet.getCellByPosition(7, i).getFormula(), 40) +
						UtilFormat.fillRightSpace("", 13) 
						);
				i++;
				numeroDomicilOrdenante++;
				numeroDomicilTotal++;
				numeroTotalRegistrosOrdenante++;
				numeroTotalRegistrosSoporte++;
				importeTotal += Integer.valueOf(UtilFormat.formatImporte(adeudosSheet.getCellByPosition(8, i).getValue(),10));
			}
		} catch (Exception ex) {
			
		}
		return listAdeudos;
	}
	@Override
	public String getTotalOrdenante(XSpreadsheet presenterSheet) {
		String firstLine = null;
		try {
			String NIF = presenterSheet.getCellByPosition(1, 2).getFormula();
			numeroTotalRegistrosOrdenante++;
			numeroTotalRegistrosSoporte++;
			firstLine = TOTORDA1 + TOTORDA2 + UtilFormat.fillRightCeros(NIF, 12) + 
					UtilFormat.fillLeftSpace("", 12)  +
					UtilFormat.fillLeftSpace("", 40)  +
					UtilFormat.fillLeftSpace("", 20)  +
					UtilFormat.formatImporte(importeTotal, 10) +
					UtilFormat.fillRightSpace("", 6) +
					UtilFormat.formatNumero(numeroDomicilOrdenante, 10) +
					UtilFormat.formatNumero(numeroTotalRegistrosOrdenante, 10) ;
		} catch (Exception ex) {
			// do stuff with exception
			// response = ex.getMessage();
			ex.printStackTrace();
		}
		return firstLine;
	}

	@Override
	public String getTotalGeneral(XSpreadsheet presenterSheet) {
		String firstLine = null;
		try {
			String NIF = presenterSheet.getCellByPosition(1, 2).getFormula();
			numeroTotalRegistrosSoporte++;
			firstLine = TOTGENA1 + TOTGENA2 + 
					UtilFormat.fillRightCeros(NIF, 12) + 
					UtilFormat.fillLeftSpace("", 12)  +
					UtilFormat.fillLeftSpace("", 40)  +
					UtilFormat.formatNumero(numeroOrdenantes, 4) +
					UtilFormat.fillLeftSpace("", 16)  +
					UtilFormat.formatImporte(importeTotal, 10) +
					UtilFormat.fillRightSpace("", 6) +
					UtilFormat.formatNumero(numeroDomicilTotal, 10) +
					UtilFormat.formatNumero(numeroTotalRegistrosSoporte, 10) +
					UtilFormat.fillRightSpace("", 20) +
					UtilFormat.fillRightSpace("", 18) ;
		} catch (Exception ex) {
			// do stuff with exception
			// response = ex.getMessage();
			ex.printStackTrace();
		}
		
		return firstLine;
	}
}
