package com.gymondo.subscriptionservice.core.config;

import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.gymondo.subscriptionservice.core.constant.ApplicationConstant;

/**
 * Class responsible to handle application level configuration
 *
 */
@Configuration
public class ApplicationConfiguration {

	/**
	 * Bean to set default date time format for complete application
	 * @return
	 */
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
		return builder -> {
			builder.simpleDateFormat(ApplicationConstant.API_DATE_TIME_FORMAT);
			builder.serializers(new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(ApplicationConstant.API_DATE_TIME_FORMAT)));
		};
	}

	/**
	 * ModelMapper bean used for DTO conversion
	 * @return
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
