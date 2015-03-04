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
}
