package merchant.galaxy.utils;

import java.util.*;

public class RulesUtils {

	private static final Character[] RomanNonRepeatingSymbols = {'D', 'L', 'V'};
	private static final Character[] RomanRepeatingSymbols = {'I','V','X','M'};
	private static Map<Character,Integer> RepeatableLiteralsCount = getRepeatableSymbolsCount();

	private static Map<Character,Integer> getRepeatableSymbolsCount(){
		Map<Character,Integer>map = new HashMap<Character,Integer>() {
			{
				put('I', 0);
				put('X', 0);
				put('C', 0);
				put('M', 0);
			}
		};
		return map;
	}

	private static Map<Character,Integer> NonRepeatableLiteralsCount = getNonRepeatableSymbolsCount();

	private static Map<Character,Integer> getNonRepeatableSymbolsCount(){
		Map<Character,Integer>map = new HashMap<Character,Integer>() {
			{
				put('V', 0);
				put('L', 0);
				put('D', 0);
			}
		};
		return map;
	}

	private static Map<Integer, Integer[]> ROMAN_SUBTRACTABLE_MAPPING = Collections.unmodifiableMap(
			new HashMap<Integer, Integer[]>() {
				{
					put(1, new Integer[] {5, 10});
					put(5, new Integer[] {});
					put(10, new Integer[] {50,100});
					put(50, new Integer[] {});
					put(100, new Integer[] {100,1000});
					put(500, new Integer[] {});
					put(1000, new Integer[] {});
				}
			});

	private static Map<Character, Integer> ROMAN_TO_NUMERIC_MAPPING = Collections.unmodifiableMap(
			new HashMap<Character, Integer>() {
				{
					put('I', 1);
					put('V', 5);
					put('X', 10);
					put('L', 50);
					put('C', 100);
					put('D', 500);
					put('M', 1000);
				}
			});

	public static void checkLiteralCountValidity(Character CurrentLiteral){
		if(checkIfLiteralPresent(RomanNonRepeatingSymbols, CurrentLiteral)){
			NonRepeatableLiteralsCount.put(CurrentLiteral, NonRepeatableLiteralsCount.get(CurrentLiteral) + 1);
			if(NonRepeatableLiteralsCount.containsValue(3)){
				System.err.println("V,L,D must no be repeated.");
				System.exit(0);
			}
		}
		else if(checkIfLiteralPresent(RomanRepeatingSymbols, CurrentLiteral)){
			Character keyForValueContainingThree = getKeyForValueContainingThree();
			if(keyForValueContainingThree != '\0'){
				if (CurrentLiteral.equals(keyForValueContainingThree)){
					System.err.println(CurrentLiteral+" cannot repeat 4 times successively");
					System.exit(0);
				}
				else if(CurrentLiteralSmallerThanPrevious(CurrentLiteral, keyForValueContainingThree)) {
					RepeatableLiteralsCount.put(CurrentLiteral, RepeatableLiteralsCount.get(CurrentLiteral) +1);
					RepeatableLiteralsCount.put(keyForValueContainingThree, 0);
				}
			}
			else{
				RepeatableLiteralsCount.put(CurrentLiteral, RepeatableLiteralsCount.get(CurrentLiteral) +1);
			}
		}
	}

	private static char getKeyForValueContainingThree(){
		char key = '\0';
		Iterator<Map.Entry<Character,Integer>> iter = RepeatableLiteralsCount.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<Character,Integer> entry = iter.next();
			if (entry.getValue().equals(3)) {
				return Character.valueOf(entry.getKey());
			}
		}
		return key;
	}

	private static boolean CurrentLiteralSmallerThanPrevious(char CurrentLiteral, char keyForValueContainingThree){
		if (ROMAN_TO_NUMERIC_MAPPING.get(CurrentLiteral)> ROMAN_TO_NUMERIC_MAPPING.get(keyForValueContainingThree)){
			System.err.println("must have been a lesser Roman Numeral next instead of "+CurrentLiteral);
			System.exit(0);
			return false;
		}
		else{
			return true;
		}
	}

	public static float subtractionLogic(Float lastDecimal, Float decimal, Float lastNumber){
		if(Arrays.asList(ROMAN_SUBTRACTABLE_MAPPING.get(Math.round(decimal))).contains(Math.round(lastNumber))){
			return lastDecimal - decimal;
		}
		else
			return lastDecimal + decimal;
	}


	public static boolean checkIfLiteralPresent(Character[] array, Character literal){
		boolean result = false;
		for (int i = 0; i < array.length; i++) {
			if(array[i].equals(literal))
				result =  true;
		}
		return result;
	}

	public static void resetLiteralsCounter(){
		RepeatableLiteralsCount = getRepeatableSymbolsCount();
		NonRepeatableLiteralsCount = getNonRepeatableSymbolsCount();
	}
}
