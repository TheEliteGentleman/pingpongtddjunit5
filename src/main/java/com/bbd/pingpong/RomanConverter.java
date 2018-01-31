package com.bbd.pingpong;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RomanConverter {
	
	private static final Map<Character, Integer> ROMAN_NUMERALS;
	private static Set<String> SUBTRACTIVE_NUMERALS;
	
	static {
		Map<Character, Integer> romanNumerals = new HashMap<>();
		romanNumerals.put('I', 1);
		romanNumerals.put('V', 5);
		romanNumerals.put('X', 10);
		romanNumerals.put('L', 50);
		romanNumerals.put('C', 100);
		romanNumerals.put('D', 500);
		romanNumerals.put('M', 1000);
		
		ROMAN_NUMERALS = Collections.unmodifiableMap(romanNumerals);
		
		Set<String> subtractiveNumerals = new HashSet<>();
		subtractiveNumerals.add("IV");
		subtractiveNumerals.add("IX");
		subtractiveNumerals.add("XL");
		subtractiveNumerals.add("XC");
		subtractiveNumerals.add("CD");
		subtractiveNumerals.add("CM");
		
		SUBTRACTIVE_NUMERALS = Collections.unmodifiableSet(subtractiveNumerals);
	}
	
    public static int Convert(String s)
    {
    	if (s == null || s.isEmpty())  {
    		throw (new IllegalArgumentException("Not a valid roman"));
		}		     
    		 		     
		// Handle upper case and lower case
		s = s.toUpperCase();
    	
		int total = 0;
    	int length = s.length();
    	
    	for (int i = 0; i < length; i++) {
    		char c = s.charAt(i);
    		int value = toNumeric(c);
    		boolean subtract = ((i < length - 1) && isSubtractiveNumeral(String.valueOf(new char[] {c, s.charAt(i + 1)})));
    		
    		total += subtract ? -value : value;
    	}
    	
        return total;
    }
    
    private static boolean isSubtractiveNumeral(final String s) {
    	return SUBTRACTIVE_NUMERALS.contains(s);
    }
    
    private static int toNumeric(final char c) {
    	if (!ROMAN_NUMERALS.containsKey(c)) {
    		throw new ArithmeticException("Invalid roman character '" + c + "'.");
    	}
    	
    	return ROMAN_NUMERALS.get(c);
    }
}
