package merchant.galaxy.output;


import merchant.galaxy.core.Roman2DecimalConvertor;
import merchant.galaxy.input.InputHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutputHandler{

	private InputHandler inputHandler;

	public OutputHandler(InputHandler inputHandler){
		this.inputHandler = inputHandler;
	}


	public  void computeReplyForQuestion(){
		Map<String,String> questionMap = inputHandler.getQuestionResultMap();
		for (Map.Entry<String, String> entry : questionMap.entrySet()) {
			processReply(entry.getKey());
		}
	}

	private void processReply(String question){
		if (question.toLowerCase().startsWith("how much")){
			findValueOfRoman(question);
		}
		else if (question.toLowerCase().startsWith("how many")){
			findValueOfElement(question);
		}
	}

	public  void findValueOfRoman(String query){
		Map<String,String> tagSymbolMap = inputHandler.getTagSymbolMap();
		if (isValidinput(query)== true){
			ArrayList<String> tokenValueToRoman = new ArrayList<String>();
			ArrayList<String> tokenValue = splitQuery(query);
			for (int i = 0; i < tokenValue.size(); i++) {
				tokenValueToRoman.add(tagSymbolMap.get(tokenValue.get(i)));
			}
			float value = new Roman2DecimalConvertor().romanToDecimal(tokenValueToRoman.toString());
			tokenValue.add("is");tokenValue.add(Float.toString(value));
			System.out.println(query+" "+outputFormatter(tokenValue));
		}
		else{
			System.err.println(query+" : I have no idea what you are talking about");
		}
	}


	private void findValueOfElement(String query){
		Map<String,String> tagSymbolMap = inputHandler.getTagSymbolMap();
		Map<String, Float> elementValueMap = inputHandler.getElementValueMap();
		if (isValidinput(query) == true){
			ArrayList<String> tokenValue = splitQuery(query);
			ArrayList<String> tokenValueToRoman = new ArrayList<String>();
			String element = null;
			for (int i = 0; i < tokenValue.size(); i++) {
				if(tagSymbolMap.get(tokenValue.get(i)) != null){
					tokenValueToRoman.add(tagSymbolMap.get(tokenValue.get(i)));
				}
				else if (elementValueMap.get(tokenValue.get(i)) != null){
					element = tokenValue.get(i);
				}
				else{
					System.err.println(query+" : I have no idea what you are talking about");
				}
			}
			float elementValue = (new Roman2DecimalConvertor().romanToDecimal(tokenValueToRoman.toString()) * elementValueMap.get(element));
			tokenValue.add("is");
			tokenValue.add(Float.toString(elementValue));
			tokenValue.add("Credits");
			System.out.println(query+" "+outputFormatter(tokenValue));
		}
		else{
			System.err.println(query+" : I have no idea what you are talking about");
		}
	}

	private static String outputFormatter(ArrayList<String> output){
		return output.toString().replace(",", "").replace("[", "").replace("]", "");
	}

	private static boolean isValidinput(String query){
		Pattern regex = Pattern.compile("[$&+,:;=@#|]");
		Matcher matcher = regex.matcher(query);
		if (matcher.find()){
			return false;
		}
		else{
			return true;
		}

	}

	private static ArrayList<String> splitQuery(String query){
		ArrayList<String> queryArray = new ArrayList<String>(Arrays.asList(query.split("((?<=:)|(?=:))|( )")));
		int startIndex = 0, endIndex = 0;
		for (int i = 0; i < queryArray.size(); i++) {
			if(queryArray.get(i).toLowerCase().equals("is")){
				startIndex = i+1;
			}
			else if(queryArray.get(i).toLowerCase().equals("?")){
				endIndex = i;

			}
		}
		String[] array = queryArray.toArray(new String[queryArray.size()]);
		return new ArrayList<String>(Arrays.asList(Arrays.copyOfRange(array, startIndex, endIndex)));
	}

}

