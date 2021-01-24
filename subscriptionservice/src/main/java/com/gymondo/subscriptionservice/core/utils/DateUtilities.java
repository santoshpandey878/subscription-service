package com.gymondo.subscriptionservice.core.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

import org.apache.commons.lang3.time.DateUtils;

import com.gymondo.subscriptionservice.core.constant.ApplicationConstant;

/**
 * Utility class for Date operations
 */
public class DateUtilities extends DateUtils {

	/**
	 * Method to get the duration between two date time
	 * @param endDate
	 * @param startDate
	 * @return
	 */
	public static String getDuration(LocalDateTime endDate, LocalDateTime startDate){
		if(null ==  startDate) return  ApplicationConstant.NO_DATA;

		if(null ==  endDate){
			endDate = LocalDateTime.now();
		}
		Duration duration = Duration.between(startDate, endDate);
		duration = duration.minusDays(duration.toDaysPart());
		Period period = Period.between(startDate.toLocalDate(), endDate.toLocalDate());
		return String.format(ApplicationConstant.DURATION_FORMAT, period.getYears(), period.getMonths(),
				period.getDays(), duration.toHoursPart(), duration.toMinutesPart(), duration.toSecondsPart());
	}

}
