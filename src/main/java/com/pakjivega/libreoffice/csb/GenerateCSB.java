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
	
	
	public static void generateCSB19(XScriptContext xSc) {
		// getting the text document object
		XSpreadsheetDocument xsheetdocument = (XSpreadsheetDocument) UnoRuntime.queryInterface(XSpreadsheetDocument.class, xSc.getDocument());
		XSpreadsheets xSpread = xsheetdocument.getSheets();
		CSB csb = new CSB19PrimerSimple();
		try {
			XIndexAccess aSheetsIA = UnoRuntime.queryInterface(XIndexAccess.class, xSpread);
			String wherewritten = csb.generateFile(aSheetsIA);

		} catch (Exception ex) {
			System.out.println("Exception" + ex.getMessage());
		}
	}
}
