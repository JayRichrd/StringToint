package com.jy;

public class Main {

	public static void main(String[] args) {
		String numStr1 = "-123456";
		String numStr2 = "+234567";
		String numStr3 = "345678";
		String numStr4 = " -45678";
		String numStr5 = " A45678";

		System.out.println("数字字符:" + numStr1);
		System.out.println("对应数字:" + stringToint(numStr1));

		System.out.println();

		System.out.println("数字字符:" + numStr2);
		System.out.println("对应数字:" + stringToint(numStr2));

		System.out.println();

		System.out.println("数字字符:" + numStr3);
		System.out.println("对应数字:" + stringToint(numStr3));

		System.out.println();

		System.out.println("数字字符:" + numStr4);
		System.out.println("对应数字:" + stringToint(numStr4));

		System.out.println();

		System.out.println("数字字符:" + numStr5);
		System.out.println("对应数字:" + stringToint(numStr5));
	}

	/**
	 * 将数字字符转换成对应的数字
	 * 
	 * @param numStr
	 *            待转换的数字字符
	 * @return 返回字符对应的数字
	 */
	public static int stringToint(String numStr) {
		// 鲁棒性检查
		if (numStr == null || numStr.length() < 1)
			throw new NumberFormatException(numStr);
		// 先去除收尾的空格
		numStr = numStr.trim();
		// 根据第一个字符判定正负
		char firstChar = numStr.charAt(0);
		if (firstChar == '-')
			return parseString(numStr, 1, false);
		else if (firstChar == '+')
			return parseString(numStr, 1, true);
		else if (firstChar >= '0' && firstChar <= '9')
			return parseString(numStr, 0, true);
		else
			throw new NumberFormatException(numStr);
	}

	/**
	 * 解析字符串
	 * 
	 * @param numStr
	 *            待解析的字符串
	 * @param index
	 *            解析字符串开始的位置
	 * @param positive
	 *            是否为正数
	 * @return 返回字符串对应的整数
	 */
	public static int parseString(String numStr, int index, boolean positive) {
		int length = numStr.length();
		// 鲁棒性检查
		if (index >= length)
			throw new NumberFormatException(numStr);
		// 最终返回的结果
		int result;
		long temp = 0;
		// 循环将字符装换成整数
		while (index < length && isDigit(numStr.charAt(index))) {
			temp = temp * 10 + numStr.charAt(index) - '0';
			// 不能超过整数数的最大值
			if (temp > 0x8000_0000L)
				throw new NumberFormatException(numStr);
			index++;
		}

		// 根据正负数再做判定
		if (positive) {
			if (temp >= 0x8000_0000L)
				throw new NumberFormatException(numStr);
			else
				result = (int) temp;
		} else {
			if (temp == 0x8000_0000L)
				result = 0x8000_0000;
			else
				result = (int) -temp;
		}

		return result;
	}

	/**
	 * 判断字符是否为数字字符
	 * 
	 * @param c
	 *            待判断的字符
	 * @return 如果是数字字符返回true,否则返回false
	 */
	public static boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}

}
