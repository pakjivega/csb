package com.pakjivega.libreoffice.csb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import com.pakjivega.libreoffice.csb.exception.CCCInvalidException;
 
public class UtilFormatTest  {

	@Test
	public void testFillLeftSpace() {
		String response = UtilFormat.fillLeftSpace("NAME", 20);
		Assert.assertEquals("                NAME", response);
		Assert.assertEquals("                ", UtilFormat.fillLeftSpace("", 16));
	}
	@Test
	public void testFillLeftCeros() {
		String response = UtilFormat.fillLeftCeros("NAME", 20);
		Assert.assertEquals("0000000000000000NAME", response);
	}
	@Test
	public void testFillRightSpace() {
		String response = UtilFormat.fillRightSpace("NAME", 20);
		Assert.assertEquals("NAME                ", response);
	}
	@Test
	public void testFillRightCeros() {
		String response = UtilFormat.fillRightCeros("NAME", 20);
		Assert.assertEquals("NAME0000000000000000",response);
	}
	@Test
	public void testformatImporte() {
		String response = UtilFormat.formatImporte(23.5, 20);
		String response1 = UtilFormat.formatImporte(23.534, 10);
		Assert.assertTrue(response.length() == 20);
		Assert.assertTrue(response1.length() == 10);
	}
	@Test
	public void testformatNumero() {
		String response = UtilFormat.formatNumero(23, 10);
		Assert.assertTrue(response.length() == 10);
	}
	@Test
	public void testRightCodigoCuenta() {
		String cuenta1 = null;
		try {
			cuenta1 = UtilFormat.validateCCC("1234", "5678", "06", "1234567890");			
		} catch ( CCCInvalidException ex) {
		}
		Assert.assertEquals(cuenta1,"1234" + "5678" + "06" + "1234567890");
	}
	@Test(expected = CCCInvalidException.class)
	public void testWrongCodigoCuenta() throws CCCInvalidException {
		String cuenta1 = null;
		cuenta1 = UtilFormat.validateCCC("1234", "5678", "16", "1234567890");
	}
	@Test(expected = CCCInvalidException.class)
	public void testWrong2CodigoCuenta() throws CCCInvalidException {
		String cuenta1 = null;
		cuenta1 = UtilFormat.validateCCC("123", "5678", "06", "1234567890");			
	}

}
