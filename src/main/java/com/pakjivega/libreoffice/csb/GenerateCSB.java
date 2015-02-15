package com.pakjivega.libreoffice.csb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.sun.star.container.XIndexAccess;
import com.sun.star.script.provider.XScriptContext;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.util.Date;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.sheet.XSpreadsheets;
import com.sun.star.table.XCell;
import com.sun.star.text.XTextRange;
import com.sun.star.text.XText;

public class GenerateCSB {
	private static String nameFile = "adeudo2.c19";
	private static File csbFile = new File(nameFile);
	private static XSpreadsheet maSheet;
	public static void generateCSB19(XScriptContext xSc) {
		// getting the text document object
		XSpreadsheetDocument xsheetdocument = (XSpreadsheetDocument) UnoRuntime.queryInterface(XSpreadsheetDocument.class, xSc.getDocument());
		XSpreadsheets xSpread = xsheetdocument.getSheets();
		try {
			XIndexAccess aSheetsIA = UnoRuntime.queryInterface(XIndexAccess.class, xSpread);
			maSheet = UnoRuntime.queryInterface(XSpreadsheet.class, aSheetsIA.getByIndex(0));
			String wherewritten = writeFile("Texto");
			XCell xCell = maSheet.getCellByPosition(0, 20);
			xCell.setFormula(wherewritten);			
		} catch (Exception ex) {
			System.out.println("Exception" + ex.getMessage());
		}
	}

	public static String writeFile(String cadenaa) {
		String response = null;
		try {
			Date now = new Date();
			
			String firstLine = "5180" + maSheet.getCellByPosition(2, 3).getFormula() + "000" + now.Day + now.Month + now.Year + "   " + "NombreOrdenante" + "  ";
			String secondLine = "5380" + "SecondLine";
			response = csbFile.getPath();
			FileWriter fw = new FileWriter(csbFile);
			fw.write(firstLine);
			fw.write(System.lineSeparator());
			fw.write(secondLine);
			fw.write(System.lineSeparator());
			fw.close();
		} catch (Exception ex) {
			// do stuff with exception
			response = ex.getMessage();
			ex.printStackTrace();
		}
		return response;
	}
//	Dim B19 as String
//	Dim B13 as String
//	Dim B2 as String
//	Dim B3 as String
//	Dim C as String
//	Dim D as String
//	B19 = ThisComponent.Sheets(0).getCellbyPosition(1,2).String
//	B13 = "000"
//	Dim today as Date
//	today = Date()
//	B2 =  Day(B2) & Month(B2) & Year(B2)
//	B3 = "      "
//	C = ThisComponent.Sheets(0).getCellbyPosition(1,1).String
//	D = "         "
//	'B2 = Rept(" hello world ";2)
//	FirstLine="5180" & B19 & B13 & B2 & B3 & C & D 
	
}
