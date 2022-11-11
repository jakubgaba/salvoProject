package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd --- HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	@Bean
	public CommandLineRunner initData(GamePlayerRepository repository){
		return(args) -> {
			repository.save(new GamePlayer());
			//save
//			repository.save(new Game(dtf.format(now)));
//			repository.save(new Game(dtf.format(now.plus(1, ChronoUnit.HOURS))));
//			repository.save(new Game(dtf.format(now.plus(2, ChronoUnit.HOURS))));
		};
	}

}
