package glazer.numbers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailMatching {

	private static final Pattern EMAIL_PATTERN = Pattern
			.compile("\\S+?@\\S+?\\.\\S+");

	public static void main(String args[]) {
		String text = "You can email me at shifra.glazer@gmail.com .";
		Matcher match = EMAIL_PATTERN.matcher(text);
		boolean a = match.matches();
		System.out.println(a);
		while (match.find()) {
			String number = text.substring(match.start(), match.end());

			System.out.println(number);
		}
		System.out.println(a);
	}
}
