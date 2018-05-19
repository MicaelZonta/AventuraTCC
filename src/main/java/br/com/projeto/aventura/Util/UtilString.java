package br.com.projeto.aventura.Util;

public class UtilString {

	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		} else if (str.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
