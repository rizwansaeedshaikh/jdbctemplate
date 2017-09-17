package com.rizwan.test.jdbctemplate;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.rizwan.test.jdbctemplate.config.AppConfig;
import com.rizwan.test.jdbctemplate.service.VehicleService;

import junit.framework.TestCase;

public class AppTest extends TestCase {

	ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

	/*
	 * public void testFindByVehicleNo() {
	 * 
	 * VehicleService service = context.getBean(VehicleService.class);
	 * System.out.println(service.findVehicleByNo("2")); }
	 */

	public void testFindVehicles() {

		VehicleService service = context.getBean(VehicleService.class);
		System.out.println(service.findVehicles(null));
	}
}
