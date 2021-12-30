package main;

public class Numerology 
{
	Numerology(){}
	
	int month = 0;
	int day = 0;
	int year = 0;
	int result = 0;
	String prediction = null;
	
	public void crunchPrediction()
	{
		int sumNum = month + day + year;
		int digits = 0;
		int increment = 0;
		increment = sumNum;
		while(increment > 9)
		{
			digits = 0;
			
			while(increment>0)
			{
				digits += increment % 10; 
				increment = increment / 10;
			}
			increment = digits;
		}
		digitToPrediction(digits);
	}
	
	public void digitToPrediction(int digit)
	{
		String predict = "";
		switch(digit)
		{
			case 1: predict = "You will be hungry again in one Hour.";
			break;
			case 2: predict = "You will meet the love of your life today. Maybe.";
			break;
			case 3: predict = "I see money in your future. Its not yours though.";
			break;
			case 4: predict = "Today is going to be a good day. Possibly.";
			break;
			case 5: predict = "You will receive an A on your programming assignment today. Hopefully.";
			break;
			case 6: predict = "Stacks and Queues are in your future.";
			break;
			case 7: predict = "Be kind to strangers today. Some may need it more than you think.";
			break;
			case 8: predict = "Thats a nice birthday, but dang! You're that old?";
			break;
			case 9: predict = "I see many 'ArrayIndexOutOfBounds' in your future.";
			break;
		}
		prediction = predict;
	}
}
