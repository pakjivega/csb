package com.pakjivega.libreoffice.csb;

import com.sun.star.container.XIndexAccess;
import com.sun.star.script.provider.XScriptContext;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.sheet.XSpreadsheet;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.sheet.XSpreadsheets;
import com.sun.star.table.XCell;
import com.sun.star.text.XTextRange;
import com.sun.star.text.XText;

public class GenerateCSB {
	// private static XSpreadsheet maSheet;
	public static void generateCSB19(XScriptContext xSc) {

		// getting the text document object
		XSpreadsheetDocument xsheetdocument = (XSpreadsheetDocument) UnoRuntime.queryInterface(XSpreadsheetDocument.class, xSc.getDocument());
		XSpreadsheets xSpread = xsheetdocument.getSheets();
		//String[] names = xSpread.getElementNames();
		//xSpread.insertNewByName("MySheet", (short) 0);
		try {
			XIndexAccess aSheetsIA = UnoRuntime.queryInterface(XIndexAccess.class, xSpread);
			XSpreadsheet maSheet = UnoRuntime.queryInterface(XSpreadsheet.class,aSheetsIA.getByIndex(0));
					
			//Object sheet = xSpread.getByName("MySheet");
			//XSpreadsheet xSpreadsheet =	(XSpreadsheet)UnoRuntime.queryInterface(XSpreadsheet.class, sheet);
			XCell xCell = maSheet.getCellByPosition(0, 0);
			xCell.setValue(21);
		} catch (Exception ex) {
			System.out.println("Exception" + ex.getMessage());
		}
		//System.out.println("names" + names.toString());
		
		// XTextRange xTextRange = xSpread.getElementNames();
		// xTextRange.setString("Hello CSB (in Java)");
		//
		//
		// // getting the text document object
		// XSpreadsheetDocument xsheetdocument = (XSpreadsheetDocument)
		// UnoRuntime
		// .queryInterface(XSpreadsheetDocument.class, xSc.getDocument());
		// // XSpreadsheets xSpread = xsheetdocument.getSheets();
		// // String[] names = xSpread.getElementNames();
		// // xSpread.insertNewByName("MySheet", (short) 0);
		// // try {
		// // Object sheet = xSpread.getByName("MySheet");
		// // XSpreadsheet xSpreadsheet =
		// (XSpreadsheet)UnoRuntime.queryInterface(
		// // XSpreadsheet.class, sheet);
		// //
		// // XCell xCell = xSpreadsheet.getCellByPosition(0, 0);
		// // xCell.setValue(21);
		// // XIndexAccess aSheetsIA = UnoRuntime.queryInterface(
		// // XIndexAccess.class, xSpread);
		// // maSheet = UnoRuntime.queryInterface(XSpreadsheet.class,
		// // aSheetsIA.getByIndex(0));
		// // // enter some values in B3:B11
		// // for (int iCounter = 1; iCounter < 10; iCounter++) {
		// // XCell aCell = maSheet.getCellByPosition(1, 1 + iCounter);
		// // aCell.setValue(iCounter);
		// // }
		//
		// // } catch (Exception ex) {
		// // System.out.println("Exception" + ex.getMessage());
		// // }
		// // System.out.println("names" + names.toString());
		// // XTextRange xTextRange = xSpread.getElementNames();
		// // xTextRange.setString("Hello CSB (in Java)");

	}

}
