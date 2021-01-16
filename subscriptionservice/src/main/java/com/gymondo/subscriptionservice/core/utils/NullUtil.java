package com.gymondo.subscriptionservice.core.utils;

/**
 * Utility class for null checking and handling
 */
public final class NullUtil {

	/**
	 * Private constructor
	 */
	private NullUtil(){}

	/**
	 * Method to check if object is null 
	 * @param obj - Object
	 * @return
	 */
	public static boolean isNull(Object obj){
		return null == obj;
	}

	/**
	 * Method to check if object is not null 
	 * @param obj - Object
	 * @return
	 */
	public static boolean isNotNull(Object obj){
		return !isNull(obj);
	}

	/**
	 * Method to check if string is null
	 * @param str
	 * @return
	 */
	public static boolean isNullString(String str){
		return null == str || "null".equalsIgnoreCase(str.trim()) || "".equals(str.trim());
	}

	/**
	 * Method to check if string is not null
	 * @param str
	 * @return
	 */
	public static boolean isNotNullString(String str) {
		return !isNullString(str);
	}

}
