package numberConverter;

import java.util.Scanner;

public class numberConverter {

    public static String[] placeNotation = {
        "",
        "Thousand",
        "Million",
        "Billion"
    };

    public static String convertOnes(char c) {
        switch (c) {
            case '1': return "One";
            case '2': return "Two";
            case '3': return "Three";
            case '4': return "Four";
            case '5': return "Five";
            case '6': return "Six";
            case '7': return "Seven";
            case '8': return "Eight";
            case '9': return "Nine";
        }
        return "";
    }

    public static String convertTeens(char c) {
        switch (c) {
            case '1': return "Eleven";
            case '2': return "Twelve";
            case '3': return "Thirteen";
            case '4': return "Fourteen";
            case '5': return "Fifeteen";
            case '6': return "Sixteen";
            case '7': return "Seventeen";
            case '8': return "Eightteen";
            case '9': return "Nineteen";
        }
        return "";
    }

    public static String convertTens(char c) {
        switch (c) {
            case '1': return "Ten";
            case '2': return "Twenty";
            case '3': return "Thrity";
            case '4': return "Fourty";
            case '5': return "Fifety";
            case '6': return "Sixty";
            case '7': return "Seventy";
            case '8': return "Eightty";
            case '9': return "Ninety";
        }
        return "";
    }

    public static String convertHundreds(String s) {
        String returningString = "";
        boolean checkForTeen;
        
        switch (s.length()) {
            case 3:
                checkForTeen = s.charAt(1) == '1' && s.charAt(2) != '0'; 

                if (s.charAt(0) != '0') {
                    returningString = convertOnes(s.charAt(0)) + " Hundred ";
                }

                if (s.charAt(1) != '0') {
                    if (checkForTeen) {
                        returningString = returningString + convertTeens(s.charAt(2)) + " ";
                    } else {
                        returningString = returningString + convertTens(s.charAt(1)) + " ";
                    }
                }

                if (s.charAt(2) != '0' && !checkForTeen) {
                    returningString = returningString + convertOnes(s.charAt(2)) + " ";
                }

                return returningString;
            
            case 2:
                checkForTeen = s.charAt(0) == '1' && s.charAt(1) != '0';

                if (!checkForTeen && s.charAt(1) != '0') {
                    returningString = convertTens(s.charAt(0)) + " ";
                    returningString = returningString + convertOnes(s.charAt(1)) + " "; 
                } else if (!checkForTeen) {
                    returningString = convertTens(s.charAt(0)) + " ";
                } else {
                    returningString = convertTeens(s.charAt(1)) + " ";
                }

                return returningString;

            case 1:
                return returningString = convertOnes(s.charAt(0)) + " ";

        }

        return returningString;
    }

    public static String[] convertToChunks(String s) {        
        int i = 0;
        int numOfChunks = s.length() / 3;
        int lastIndex = 0;
        int endIndex = 0;
        
        if (s.length() % 3 > 0) {
            numOfChunks++;
        }

        String[] chunks = new String[numOfChunks];

        if (s.length() % 3 > 0) {
            chunks[i] = new StringBuilder(s.substring(0, s.length() % 3)).toString();
            i++;
            lastIndex = s.length() % 3;
        }

        while (i < numOfChunks) {
            endIndex = Math.min(lastIndex + 3, s.length());
            chunks[i] = new StringBuilder(s.substring(lastIndex, endIndex)).toString();
            lastIndex += 3;           
            i++;
        }

        return chunks;
    }

    public static String convertNumber(String s) {
        String[] parts = s.split("\\.");
        String s1 = parts[0], s2 = parts[1];
        String[] chunks = convertToChunks(s1);
        String returningString = "";

        for (int i = 0; i < chunks.length; i++) {
            returningString = returningString + convertHundreds(chunks[i]);

            returningString = returningString + placeNotation[chunks.length - i - 1] + " ";
        }

        returningString = returningString.substring(0, returningString.length() - 1);
        returningString = returningString + "and " + s2 + "/100 Dollars";

        return returningString;
    }

    public static String validateString(String s) {
    	String goodSalary = "";
    	
		for (int x=0; x < s.length(); x++) {
			
			switch(s.charAt(x)) {
			   case '0':
			   case '1':
			   case '2':	   
			   case '3':
			   case '4':
			   case '5':
			   case '6':
			   case '7':
			   case '8':				   
			   case '9':
			   case '.': goodSalary = goodSalary + s.charAt(x);
			             break;
			   default:
				         break; 
			}
	  	}

        if (s.indexOf('.') == -1) {
            goodSalary = goodSalary + ".00";
        }
		
    	return goodSalary;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String salary;

        System.out.print("Please input your salary: ");
        salary = scanner.nextLine();

        salary = validateString(salary);

        salary = convertNumber(salary);
        System.out.println(salary);

        scanner.close();
        
    }
}
