package io.ffreedom.common.utils;

public final class StringUtil {

	private StringUtil() {
	}

	public final static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}

	public final static boolean notNullAndEmpty(String str) {
		return str != null && !str.isEmpty();
	}

	public final static boolean isEquals(String str1, String str2) {
		return str1 != null ? str1.equals(str2) : str2 != null ? str2.equals(str1) : true;
	}

	public final static boolean isNumber(String str) {
		if (isNullOrEmpty(str)) {
			return false;
		}
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

}