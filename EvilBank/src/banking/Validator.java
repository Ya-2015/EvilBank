package banking;

import java.util.Arrays;
import java.util.Scanner;

public class Validator {

	public static final String[] Type = {"C","DB","DP","W"};
	
	public static String getString(Scanner sc, String prompt)
	{
	    System.out.print(prompt);
	    String s = sc.next();  
	    sc.nextLine();
	    return s;
	}
	
	
	public static String getStringType(Scanner sc, String prompt)
	{
		String str;
		
		do{
		str = getString(sc,prompt).toUpperCase();
		
		}while(!Arrays.asList(Type).contains(str));
		return str;
		
	}
	
	public static double getDouble(Scanner sc, String prompt)
	{
	    double d = 0;
	    boolean isValid = false;
	    while (isValid == false)
	    {
	        System.out.print(prompt);
	        if (sc.hasNextDouble())
	        {
	            d = sc.nextDouble();
	            isValid = true;
	        }
	        else
	        {
	            System.out.println("Error! Invalid decimal value. Try again.");
	        }
	        sc.nextLine();  // discard any other data entered on the line
	    }
	    return d;
	}

	public static double getDouble(Scanner sc, String prompt,
	double min, double max)
	{
	    double d = 0;
	    boolean isValid = false;
	    while (isValid == false)
	    {
	        d = getDouble(sc, prompt);
	        if (d < min || d > max)
	            System.out.println(
	                "Error! Invalid Entry.");
	        else
	            isValid = true;
	    }
	    return d;
	}
	
	public static int getInteger(Scanner sc, String prompt)
	{
	    int d = 0;
	    boolean isValid = false;
	    while (isValid == false)
	    {
	        System.out.print(prompt);
	        if (sc.hasNextInt())
	        {
	            d = sc.nextInt();
	            isValid = true;
	        }
	        else
	        {
	            System.out.println("Error! Invalid entry, value must be an integer. Try again.");
	        }
	        sc.nextLine();  // discard any other data entered on the line
	    }
	    return d;
	}

	public static int getInteger(Scanner sc, String prompt,
	int min, int max)
	{
	    int d = 0;
	    boolean isValid = false;
	    while (isValid == false)
	    {
	        d = getInteger(sc, prompt);
	        if (d < min || d > max)
	            System.out.println(
	                "Error! Invalid Entry.");
	        else
	            isValid = true;
	    }
	    return d;
	}
}
