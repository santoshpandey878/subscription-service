package com.gymondo.subscriptionservice.core.utils;

import java.time.Duration;
import java.time.LocalDateTime;

import org.apache.commons.lang3.time.DateUtils;

import com.gymondo.subscriptionservice.core.constant.ApplicationConstant;

/**
 * Utility class for Date operations
 */
public class DateUtilities extends DateUtils {

	/**
	 * Subtract two dates 
	 * @param endDate
	 * @param startDate
	 * @return time in hh:mm:ss format
	 */
	public static String subtractDate(LocalDateTime endDate, LocalDateTime startDate){
		if(null ==  startDate) return  ApplicationConstant.NO_DATA;

		if(null ==  endDate){
			endDate = LocalDateTime.now();
		}
		Duration duration = Duration.between(endDate, startDate);
		return duration.toString();
	}

}
