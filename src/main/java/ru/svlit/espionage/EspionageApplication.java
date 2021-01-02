package ru.svlit.espionage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.svlit.espionage")
class EspionageApplication {

    public static void main(String[] args) {
        SpringApplication.run(EspionageApplication.class, args);
    }

}
