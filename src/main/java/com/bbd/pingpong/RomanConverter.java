package com.bbd.pingpong;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RomanConverter {
	
	private static final Map<Character, Integer> ROMAN_NUMERALS;
	private static Set<Character> SUBTRACTIVE_NUMERALS;
	
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
		
		Set<Character> subtractiveNumerals = new HashSet<>();
		subtractiveNumerals.add('V');
		subtractiveNumerals.add('X');
		subtractiveNumerals.add('L');
		subtractiveNumerals.add('C');
		subtractiveNumerals.add('M');
		
		SUBTRACTIVE_NUMERALS = Collections.unmodifiableSet(subtractiveNumerals);
	}
	
    public static int Convert(String s)
    {
    	int total = 0;
    	int length = s.length();
    	
    	for (int i = 0; i < length; i++) {
    		int value = toNumeric(s.charAt(i));
    		boolean subtract = (i == (length - 2) && isSubtractiveNumeral(s.charAt(length - 1)));
    		
    		total += subtract ? -value : value;
    	}
    	
        return total;
    }
    
    private static boolean isSubtractiveNumeral(final char c) {
    	return SUBTRACTIVE_NUMERALS.contains(c);
    }
    
    private static int toNumeric(final char c) {
    	if (!ROMAN_NUMERALS.containsKey(c)) {
    		throw new ArithmeticException("Invalid character '" + c + "'.");
    	}
    	
    	return ROMAN_NUMERALS.get(c);
    }
}
