package test.merchant.galaxy;

import merchant.galaxy.utils.RulesUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RulesUtilsTest {

	protected Character[] characterArray;
	protected Character character;

	@Before
	public void setUp() throws Exception {
		character = 'M';
		characterArray = new Character[]{'I','X','V','L'};
	}

	@Test
	public void testIfPresent(){
		boolean result = RulesUtils.checkIfLiteralPresent(characterArray, character);
		Assert.assertEquals(false, result);
	}

	@Test
	public void testSubtraction(){
		float result = RulesUtils.subtractionLogic(123f, 10f, 50f);
		Assert.assertEquals(113f, result, 00.00);
	}
}
