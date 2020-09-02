/**
 *
 *  @author Piątkowski Marcin S16425
 *
 */

package zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Finder {
	String path;
	StringBuilder sb= new StringBuilder();;
	int countIf=0;
	int countWar = 0;
	String ify = "if";
	String war = "wariant";
	String pat = "(\\w+)(;||}||)(if)(\\s?\\((\\D[^\\)]+)\\))";
	
	public Finder(String path) {
		Scanner s;
		try {
			s = new Scanner(new File(path));
			while(s.hasNext()) {
				sb.append(s.nextLine());
			}
			s.close();
			
		}catch(FileNotFoundException e){
			System.out.println(e.getMessage());
		}
	}

	public int getStringCount(String string) {
		// TODO Auto-generated method stub
		int a = 0;
		Pattern p = Pattern.compile(string);
		Matcher m = p.matcher(sb);
		while(m.find()) {
			a++;
		}
		return a;
	}

	public int getIfCount() {
		// TODO Auto-generated method stub
		int a = 0;
		String a1 = "/*";
		String a2 = "*/";
		String a3 = "//";
		String a4 = "/n";
		String a5 = "\"";
		Pattern p = Pattern.compile(pat);
		Matcher m = p.matcher(sb);
		String string = sb.toString();
		while(m.find()) {
		a++;	
		}
		return a;
	}

}    
