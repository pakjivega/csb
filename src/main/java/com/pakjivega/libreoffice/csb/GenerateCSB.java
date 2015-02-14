package com.pakjivega.libreoffice.csb;

import com.sun.star.container.XIndexAccess;
import com.sun.star.script.provider.XScriptContext;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.sheet.XSpreadsheetDocument;
import com.sun.star.sheet.XSpreadsheets;

import com.sun.star.text.XTextRange;
import com.sun.star.text.XText;

public class GenerateCSB {
	public static void printHello(XScriptContext xSc) {

        // getting the text document object
        XSpreadsheetDocument xsheetdocument = (XSpreadsheetDocument) UnoRuntime.queryInterface(
        		XSpreadsheetDocument.class, xSc.getDocument());
        XSpreadsheets xSpread = xsheetdocument.getSheets();
        String[] names = xSpread.getElementNames();
        xSpread.insertNewByName("MySheet", (short)0);
        try{
        	Object sheet = xSpread.getByName("MySheet");	
        } catch (Exception ex) {
        	System.out.println("Exception"  + ex.getMessage());
        }
        System.out.println("names"  + names.toString());
//        XTextRange xTextRange = xSpread.getElementNames();
//        xTextRange.setString("Hello CSB (in Java)");

    } 
	
}
