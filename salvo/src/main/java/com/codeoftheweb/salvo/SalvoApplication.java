package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd --- HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	@Bean
	public CommandLineRunner initData(GamePlayerRepository gamePlayerRepo, GameRepository gameRep, PlayerRepository playerRep, ShipRepository shipRep){

		return(args) -> {
			Player player1 = new Player("Julio");
			Player player2 = new Player("Paul");
			Player player3 = new Player("Robert");

			Game game1 = new Game(dtf.format(now));
			Game game2 = new Game(dtf.format(now.plus(1, ChronoUnit.HOURS)));
			Game game3 = new Game(dtf.format(now.plus(2, ChronoUnit.HOURS)));

			GamePlayer gamePlayer1 = new GamePlayer(game1,player1);
			GamePlayer gamePlayer2 = new GamePlayer(game2,player2);
			GamePlayer gamePlayer3 = new GamePlayer(game3,player3);

			playerRep.save(player1);
			playerRep.save(player2);
			playerRep.save(player3);

			gameRep.save(game1);
			gameRep.save(game2);
			gameRep.save(game3);

			gamePlayerRepo.save(gamePlayer1);
			gamePlayerRepo.save(gamePlayer2);
			gamePlayerRepo.save(gamePlayer3);



			Ship ship1 = new Ship("Cruiser",gamePlayer1);
			Ship shipToGo = new Ship("HappyShip",gamePlayer1);

			shipRep.save(ship1);
			shipRep.save(shipToGo);







			//save
//			repository.save(new Game(dtf.format(now)));
//			repository.save(new Game(dtf.format(now.plus(1, ChronoUnit.HOURS))));
//			repository.save(new Game(dtf.format(now.plus(2, ChronoUnit.HOURS))));
		};
	}

}
