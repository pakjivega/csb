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
		
		try {
			XIndexAccess aSheetsIA = UnoRuntime.queryInterface(XIndexAccess.class, xSpread);
			XSpreadsheet maSheet = UnoRuntime.queryInterface(XSpreadsheet.class,aSheetsIA.getByIndex(0));

			XCell xCell = maSheet.getCellByPosition(0, 0);
			xCell.setValue(22);
		} catch (Exception ex) {
			System.out.println("Exception" + ex.getMessage());
		}

	}

}
