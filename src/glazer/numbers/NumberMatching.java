package glazer.numbers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberMatching {

	
	public NumberMatching(){
		
	}
	public static void main(String args[]){
		
		Pattern p=Pattern.compile("(1-?\\s*)?(\\d{3}-?\\s*){2}\\d{4}");
		String text="This is my number 1-  123-  567- 0890. Give me a call."
				+ " Otherwise, call me at 1 718-683- 3822";
		Matcher m=p.matcher(text);
		boolean b=m.matches();
		while(m.find()){
			String number=text.substring(m.start(),m.end());
			Pattern pattern=Pattern.compile("\\s{2,}");
			Matcher match=pattern.matcher(number);
			String fix=match.replaceAll("");
			System.out.println(fix);
		}
		System.out.println(b);
		
		
		
	}
}
