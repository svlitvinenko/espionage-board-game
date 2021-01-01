package ru.svlit.espionage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "ru.svlit.espionage")
public class EspionageApplication {

	public static void main(String[] args) {
		SpringApplication.run(EspionageApplication.class, args);
	}

}
