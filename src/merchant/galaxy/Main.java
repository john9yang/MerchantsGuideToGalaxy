package merchant.galaxy;

import merchant.galaxy.input.InputHandler;
import merchant.galaxy.output.OutputHandler;

public class Main {

	public static void main(String[] args) {
		String filePath = null;

		if (args.length != 0)
			filePath = args[0];

		try{
           InputHandler inputHandler = new InputHandler(filePath);
           OutputHandler outputHandler = new OutputHandler(inputHandler);
		   outputHandler.computeReplyForQuestion();
		}
		catch(Exception e){
			e.printStackTrace();
			System.err.println("Input File Not Found!!");
		}
	}
}