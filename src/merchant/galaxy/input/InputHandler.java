package merchant.galaxy.input;

import merchant.galaxy.core.Roman2DecimalConvertor;

import java.io.*;
import java.util.*;

public class InputHandler{
	private Map<String, String> tagSymbolMap;
	private Map<String, Float> tagValueMap;
	private Map<String, String> questionResultMap;
	private List<String> creditListValues;
	private Map<String, Float> elementValueMap;

	public InputHandler(String filePath) throws IOException {
		tagSymbolMap = new HashMap<String, String>();
		tagValueMap = new HashMap<String, Float>();
		questionResultMap = new HashMap<String, String>();
		creditListValues = new ArrayList<String>();
		elementValueMap = new HashMap<String, Float>();

		loadFromFile(filePath);
	}

	private void loadFromFile(String filePath) throws IOException {
		BufferedReader bufferedReader = null;
		if (filePath == null){
			InputStream in = this.getClass().getResourceAsStream("Input.txt");
			bufferedReader =new BufferedReader(new InputStreamReader(in));
		}
		else{
			FileReader fileReader = new FileReader(filePath);
			bufferedReader = new BufferedReader(fileReader);
		}
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			doInit(line);
		}
		bufferedReader.close();

		initTagValueMap();
		initCreditListValues();
	}

	private void doInit(String line){
		String arr[] = line.split(" ");

		if (line.endsWith("?")){
			questionResultMap.put(line,"");
		}
		else if (arr.length == 3 && arr[1].equalsIgnoreCase("is")){
			tagSymbolMap.put(arr[0], arr[arr.length-1]);
		}
		else if(line.toLowerCase().endsWith("credits")){
			creditListValues.add(line);
		}
	}

	public void initTagValueMap(){

		Iterator it = tagSymbolMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry token = (Map.Entry)it.next();
			float integerValue = new Roman2DecimalConvertor().romanToDecimal(token.getValue().toString());
			tagValueMap.put(token.getKey().toString(), integerValue);
		}
	}

	private void initCreditListValues(){
		for (int i = 0; i < creditListValues.size(); i++) {
			parseCreditListElement(creditListValues.get(i));
		}
	}

	private void parseCreditListElement(String query){
		String array[] = query.split(" ");
		int splitIndex = 0;
		int creditValue = 0; String element= null; String[] valueofElement = null;
		for (int i = 0; i < array.length; i++) {
			if(array[i].toLowerCase().equals("credits")){
				creditValue = Integer.parseInt(array[i-1]);
			}
			if(array[i].toLowerCase().equals("is")){
				splitIndex = i-1;
				element = array[i-1];
			}
			valueofElement = java.util.Arrays.copyOfRange(array, 0, splitIndex);
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (int j = 0; j < valueofElement.length; j++) {
			stringBuilder.append(tagSymbolMap.get(valueofElement[j]));
		}

		float valueOfElementInDecimal = new Roman2DecimalConvertor().romanToDecimal(stringBuilder.toString());
		elementValueMap.put(element, creditValue/valueOfElementInDecimal);
	}

	public Map<String, String> getTagSymbolMap() {
		return tagSymbolMap;
	}

	public Map<String, Float> getTagValueMap() {
		return tagValueMap;
	}

	public Map<String, String> getQuestionResultMap() {
		return questionResultMap;
	}

	public List<String> getCreditListValues() {
		return creditListValues;
	}

	public Map<String, Float> getElementValueMap() {
		return elementValueMap;
	}
}
