package com.pakjivega.libreoffice.csb;

import org.junit.Assert;
import org.junit.Test;
 
public class UtilFormatTest  {

	@Test
	public void testUtilLeftFormat() {
		String response = UtilFormat.fillLeftSpace("NAME", 20);
		Assert.assertTrue(response.length() == 20);
		Assert.assertTrue(response.indexOf("NAME") == 16);
		Assert.assertTrue("NAME".equals(response.trim()));
	}
	@Test
	public void testUtilRightFormat() {
		String response = UtilFormat.fillRightSpace("NAME", 20);
		Assert.assertTrue(response.length() == 20);
		Assert.assertTrue(response.indexOf("NAME") == 0);
		Assert.assertTrue("NAME".equals(response.trim()));
	}
	@Test
	public void testUtilLeftFormatWithZero() {
		String response = UtilFormat.fillRightCeros("NAME", 20);
		Assert.assertTrue(response.length() == 20);
		Assert.assertTrue(response.indexOf("NAME") == 0);
		Assert.assertTrue("NAME0000000000000000".equals(response.trim()));
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
		System.out.println("+++ response: " + response);
		Assert.assertTrue(response.length() == 10);
	}
	@Test
	public void testCodigoCuenta() {
		Assert.assertTrue( UtilFormat.validateCCC("1234", "5678", "06", "1234567890"));
		Assert.assertTrue( ! UtilFormat.validateCCC("1234", "5678", "06", "123456789"));
		Assert.assertTrue( UtilFormat.validateCCC("8776", "7656", "25", "3434334549"));
	}
}
