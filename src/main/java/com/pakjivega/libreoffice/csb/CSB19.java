package com.pakjivega.libreoffice.csb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pakjivega.libreoffice.csb.exception.CCCInvalidException;
import com.pakjivega.libreoffice.csb.exception.FieldEmptyException;
import com.sun.star.lang.IndexOutOfBoundsException;
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
	public String getCabeceraPresentador(XSpreadsheet presenterSheet) throws FieldEmptyException {
		String firstLine = null;
		try {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
			String sDate = sdf.format(now);
			String NIF = presenterSheet.getCellByPosition(1, 2).getFormula();
			String namePresenter = presenterSheet.getCellByPosition(1, 1).getFormula();
			if ((NIF == null) || (NIF.length() == 0)) {
				throw new FieldEmptyException("NIF del Presentador esta vacio");
			}
			if ((namePresenter == null) || (namePresenter.length() == 0)) {
				throw new FieldEmptyException("Nombre del Presentador esta vacio");
			}
			firstLine = PRESA1 + PRESA2 + UtilFormat.fillRightCeros(NIF, 12) + sDate + "      " + UtilFormat.fillRightSpace(namePresenter, 60)
					+ presenterSheet.getCellByPosition(1, 3).getFormula() + presenterSheet.getCellByPosition(1, 4).getFormula();
			numeroTotalRegistrosSoporte++;
		} catch (IndexOutOfBoundsException ex) {
			// do stuff with exception
			// response = ex.getMessage();
			ex.printStackTrace();
		}
		return firstLine;
	}

	@Override
	public String getCabeceraOrdenante(XSpreadsheet presenterSheet) throws CCCInvalidException, FieldEmptyException {
		String secondLine = null;
		try {
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if ((presenterSheet.getCellByPosition(1, 10).getFormula() == null) || (presenterSheet.getCellByPosition(1, 10).getFormula().length() == 0)) {
				throw new FieldEmptyException("Fecha de cargo esta vacio");
			}
			Date dateCargo = dateFormat.parse(presenterSheet.getCellByPosition(1, 10).getFormula());
			String sDate = sdf.format(now);
			String NIF = presenterSheet.getCellByPosition(1, 6).getFormula();
			String nameOrdenante = presenterSheet.getCellByPosition(1, 5).getFormula();
			String sDateCargo = sdf.format(dateCargo);
			if ((NIF == null) || (NIF.length() == 0)) {
				throw new FieldEmptyException("NIF del Ordenante esta vacio");
			}
			if ((nameOrdenante == null) || (nameOrdenante.length() == 0)) {
				throw new FieldEmptyException("Nombre del Presentador esta vacio");
			}
			
			secondLine = ORDEA1
					+ ORDEA2
					+ UtilFormat.fillRightCerosUno(NIF, 12)
					+ sDate
					+ sDateCargo
					+ UtilFormat.fillRightSpace(nameOrdenante, 40)
					+ UtilFormat.validateCCC(presenterSheet.getCellByPosition(0, 9).getFormula(), presenterSheet.getCellByPosition(1, 9).getFormula(),
							presenterSheet.getCellByPosition(2, 9).getFormula(), presenterSheet.getCellByPosition(3, 9).getFormula()) + "        " + "01";
			numeroTotalRegistrosOrdenante++;
			numeroTotalRegistrosSoporte++;
			numeroOrdenantes++;
		} catch (IndexOutOfBoundsException ex) {
			// do stuff with exception
			// response = ex.getMessage();
			ex.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new FieldEmptyException("Fecha de cargo mal formada");
		}
		return secondLine;
	}

	@Override
	public List<String> getIndividualObligatorio(XSpreadsheet adeudosSheet, XSpreadsheet presenterSheet) throws CCCInvalidException, FieldEmptyException {

		List<String> listAdeudos = new ArrayList<String>();
		String NIF = null;
		try {
			NIF = presenterSheet.getCellByPosition(1, 2).getFormula();
			int i = 1;
			while ((adeudosSheet.getCellByPosition(0, i).getFormula() != null) && (adeudosSheet.getCellByPosition(0, i).getFormula().toString() != null)
					&& (adeudosSheet.getCellByPosition(0, i).getFormula().toString().trim().length() > 0)) {
				int[] positions = {0,1,3,4,5,6,7,8};//Check Reference Number 0, Name 1, Bank Account 3456, Concepto 7 and Importe 8
				for (int j : positions)
				{
					if ((adeudosSheet.getCellByPosition(j, i).getFormula() == null) || (adeudosSheet.getCellByPosition(j, i).getFormula().toString() == null)
							|| (adeudosSheet.getCellByPosition(j, i).getFormula().toString().trim().length() == 0) ) {
						throw new FieldEmptyException("El registro en la linea " + ( i + 1)  + " tiene el campo " + (j + 1)  + " vacio");
					}
				}
				
				listAdeudos.add(INDIVOBLA1 + INDIVOBLA2 + UtilFormat.fillRightCerosUno(NIF, 12)  //NIF
						//+ UtilFormat.fillLeftCeros(adeudosSheet.getCellByPosition(0, i).getFormula(), 12) //Referencia cliente
						+ UtilFormat.fillRightSpace(adeudosSheet.getCellByPosition(0, i).getFormula(), 12) //Referencia cliente
						+ UtilFormat.fillRightSpace(adeudosSheet.getCellByPosition(1, i).getFormula().toString(), 40) // Nombre cliente
						+ UtilFormat.validateCCC(
								adeudosSheet.getCellByPosition(3, i).getFormula().toString(), // Entidad
								adeudosSheet.getCellByPosition(4, i).getFormula().toString(), // Oficina
								adeudosSheet.getCellByPosition(5, i).getFormula().toString(), // DigitoControl																								
								adeudosSheet.getCellByPosition(6, i).getFormula().toString()) // Cuenta
						+ UtilFormat.formatImporte(adeudosSheet.getCellByPosition(8, i).getValue(), 10) //Importe
						//+ UtilFormat.fillLeftSpace("", 16) //Libre
						+ String.format("%06d", i) 
						+ UtilFormat.fillRightSpace(adeudosSheet.getCellByPosition(0, i).getFormula(), 10) //Libre
						+ UtilFormat.fillRightSpace(adeudosSheet.getCellByPosition(7, i).getFormula(), 40) + UtilFormat.fillRightSpace("", 13)); //Concepto
				importeTotal += adeudosSheet.getCellByPosition(8, i).getValue() * 100;
				i++;
				numeroDomicilOrdenante++;
				numeroDomicilTotal++;
				numeroTotalRegistrosOrdenante++;
				numeroTotalRegistrosSoporte++;
			}
		} catch (IndexOutOfBoundsException ex) {

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
			firstLine = TOTORDA1 + TOTORDA2 + UtilFormat.fillRightCerosUno(NIF, 12) + UtilFormat.fillLeftSpace("", 12) + UtilFormat.fillLeftSpace("", 40)
					+ UtilFormat.fillLeftSpace("", 20) + UtilFormat.formatNumero(importeTotal, 10) + UtilFormat.fillRightSpace("", 6)
					+ UtilFormat.formatNumero(numeroDomicilOrdenante, 10) + UtilFormat.formatNumero(numeroTotalRegistrosOrdenante, 10);
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
			firstLine = TOTGENA1 + TOTGENA2 + UtilFormat.fillRightCeros(NIF, 12) + UtilFormat.fillLeftSpace("", 12) + UtilFormat.fillLeftSpace("", 40)
					+ UtilFormat.formatNumero(numeroOrdenantes, 4) + UtilFormat.fillLeftSpace("", 16) + UtilFormat.formatNumero(importeTotal, 10)
					+ UtilFormat.fillRightSpace("", 6) + UtilFormat.formatNumero(numeroDomicilTotal, 10)
					+ UtilFormat.formatNumero(numeroTotalRegistrosSoporte, 10) + UtilFormat.fillRightSpace("", 20) + UtilFormat.fillRightSpace("", 18);
		} catch (IndexOutOfBoundsException ex) {
			// do stuff with exception
			// response = ex.getMessage();
			ex.printStackTrace();
		}
		return firstLine;
	}
}
