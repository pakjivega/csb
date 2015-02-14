package com.pakjivega.libreoffice.csb;

import com.sun.star.script.provider.XScriptContext;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.text.XTextDocument;
import com.sun.star.text.XTextRange;
import com.sun.star.text.XText;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void printHW(XScriptContext xSc) {

        // getting the text document object
        XTextDocument xtextdocument = (XTextDocument) UnoRuntime.queryInterface(
                                          XTextDocument.class, xSc.getDocument());
        XText xText = xtextdocument.getText();
        XTextRange xTextRange = xText.getEnd();
        xTextRange.setString("Hello World (in Java)");

    }// printHW
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
