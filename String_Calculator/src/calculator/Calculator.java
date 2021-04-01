package calculator;

import ch.lambdaj.function.convert.Converter;
import static ch.lambdaj.Lambda.*;
import java.util.List;
import java.util.regex.Pattern;	
import java.util.regex.Matcher;	
import static org.hamcrest.Matchers.*;

public class Calculator {

	public static int add(String text) {
		List<Integer> numbers = parseNumbers(text);
		allNegative(numbers);
		return sum(numbers).intValue();
	}
	
	private static List<Integer> parseNumbers(String text) {
		String[] tokens = tokenize(text);
		List<Integer> numbers = convert(tokens, toInt());
		return numbers;
	}
	
	private static void allNegative(List<Integer> numbers) throws RuntimeException{
		List<Integer> negatives = filter(lessThan(0), numbers);
		if(negatives.size()>0) {
			throw new RuntimeException("Negatives not allowed: " + join(negatives));
		}
	}
	
	private static String[] tokenize(String text) {
		if(text.isEmpty()) {
			return new String[0];
		}else if(customDelimeterSyntax(text)) {
			return splitCustomerDelimeter(text);
		}
		else {
			return splitCommasAndNewLines(text);
		}
	}
	
	private static boolean customDelimeterSyntax(String text) {
		return text.startsWith("//");
	}
	
	private static String[] splitCommasAndNewLines(String text) {
		String[] tokens = text.split(",|\n");
		return tokens;
	}
	
	private static String[] splitCustomerDelimeter(String text) {
		Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);
		m.matches();
		String customDelimeter = m.group(1);
		String numbers = m.group(2);
		return numbers.split(Pattern.quote(customDelimeter));
	}
	
	private static Converter<String, Integer> toInt() {
	return new Converter<String, Integer>() {
		public Integer convert(String from) {
			return toInt(from);
		}
	};
}
	private static int toInt(String text) throws NumberFormatException{
		return Integer.parseInt(text);	
	}

}
