Este modulo genera ficheros de adeudos por domiciliaciones CSB19. 
Funciona para un unico ordenante de momento.
Los datos se introducen en la hoja Calc de LibreOffice y el script Java genera el archivo C19.
<br>
En construccion. No se asume ninguna responsabilidad por el uso de este software.<br>
Pendiente : <br>
	- Validacion de campos numericos, de fechas, de numero de cuentas.<br>
	- Dialogo para elegir donde guardar el fichero c19.<br>
	- Fichero C19 con multiples conceptos por adeudo.<br>
	- Multiples ordenantes.<br>
	- Otros ficheros C34, etc.<br>
	

<P>
mvn clean package<br>
Copiar el jar resultante junto con el fichero parcel-descriptor.xml al directorio 
/usr/lib/libreoffice/share/Scripts/java/CSB (Ubuntu) o 
LibreOfficePortable\App\libreoffice\share\Scripts\java\CSB (Windows) 
<br>
Abrir el fichero GenerateCSB19.ods, rellenar y ejecutar la macro 
Tools -> Macro -> Run Macro -> LibreOffice Macros -> CSB -> GenerateCSB19.
EL fichero adeudo es generado en el directorio $HOME en Unix.