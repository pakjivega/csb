package com.pakjivega.libreoffice.csb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.star.container.XIndexAccess;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.lang.IndexOutOfBoundsException;
import com.sun.star.lang.WrappedTargetException;

import java.util.List;

public abstract class CSB {
	private static String nameFile = "adeudo";
	private static File csbFile = new File(nameFile);
	protected int importeTotal = 0;
	protected int numeroDomicilOrdenante = 0;
	protected int numeroTotalRegistrosOrdenante = 0;
	protected int numeroDomicilTotal = 0;
	protected int numeroTotalRegistrosSoporte = 0;
	protected int numeroOrdenantes = 0;	
	
	public String generateFile(XIndexAccess aSheetsIA ) {
		String response = null;
		try {
			response = csbFile.getPath();
			XSpreadsheet maSheet = UnoRuntime.queryInterface(XSpreadsheet.class, aSheetsIA.getByIndex(0));
			FileWriter fw = new FileWriter(csbFile);
			fw.write(getCabeceraPresentador(maSheet));
			fw.write(System.lineSeparator());
			fw.write(getCabeceraOrdenante(maSheet));
			fw.write(System.lineSeparator());
			XSpreadsheet maAdeudos = UnoRuntime.queryInterface(XSpreadsheet.class, aSheetsIA.getByIndex(1));
			List<String> listAdeudos = getIndividualObligatorio(maAdeudos, maSheet);
			for  (String adeudo: listAdeudos) {
				fw.write(adeudo);
				fw.write(System.lineSeparator());
			}
			fw.write(getTotalOrdenante(maSheet));
			fw.write(System.lineSeparator());
			fw.write(getTotalGeneral(maSheet));
			fw.write(System.lineSeparator());
			fw.close();
			
		} catch (IndexOutOfBoundsException ex) {
			// do stuff with exception
			// response = ex.getMessage();
			ex.printStackTrace();
		} catch (WrappedTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response; 
	}
	public abstract String getCabeceraPresentador(XSpreadsheet maSheet) ;
	public abstract String getCabeceraOrdenante(XSpreadsheet maSheet);
	public abstract List<String> getIndividualObligatorio(XSpreadsheet presentadorSheet, XSpreadsheet adeudosSheet);
	public abstract String getTotalOrdenante(XSpreadsheet maSheet) ;
	public abstract String getTotalGeneral(XSpreadsheet maSheet);
}
