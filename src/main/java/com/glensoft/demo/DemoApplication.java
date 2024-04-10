package com.glensoft.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		var ctx = SpringApplication.run(DemoApplication.class, args);

		MyFirstService myFirstService = ctx.getBean(MyFirstService.class);
		System.out.println(myFirstService.tellAStory());
		System.out.println(myFirstService.getJavaVersion());
		System.out.println(myFirstService.getOS());
		System.out.println(myFirstService.readProp());
		System.out.println(myFirstService.getMyCustomPropertyFromAnotherFile());
		System.out.println(myFirstService.getMyCustomPropertyFromASecondFile());

	}


}
