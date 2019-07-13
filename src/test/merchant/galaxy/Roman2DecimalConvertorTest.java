package test.merchant.galaxy;


import merchant.galaxy.core.Roman2DecimalConvertor;
import org.junit.Assert;
import org.junit.Test;

public class Roman2DecimalConvertorTest {
	@Test
	public void testRomanToDecimal(){
		Roman2DecimalConvertor convertor = new Roman2DecimalConvertor();
		float numericValue = convertor.romanToDecimal("MCMXLIV");
		Assert.assertEquals(1944.00, numericValue, 00.00);
	}

	@Test
	public void testRomanToDecimalFailing(){
		Roman2DecimalConvertor convertor = new Roman2DecimalConvertor();
		float value = convertor.romanToDecimal("XXXIX");
		Assert.assertEquals(39.00, value, 00.00);
	}

}
