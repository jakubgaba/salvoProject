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
	public CommandLineRunner initData(GamePlayerRepository gamePlayerRepo, GameRepository gameRep, PlayerRepository playerRep, ShipRepository shipRep){

		return(args) -> {
			Player player1 = new Player("Julio");
			Player player2 = new Player("Paul");
			Player player3 = new Player("Robert");

			Game game1 = new Game(dtf.format(now));
			Game game2 = new Game(dtf.format(now.plus(1, ChronoUnit.HOURS)));
			Game game3 = new Game(dtf.format(now.plus(2, ChronoUnit.HOURS)));

            Ship ship1 = new Ship("Cruiser");
			Ship shipToGo = new Ship("HappyShip");
			Ship ship2 = new Ship("Blob");
			Ship ship3 = new Ship("BlackPearl");

			GamePlayer gamePlayer1 = new GamePlayer(game1,player1,ship1);

			GamePlayer gamePlayer2 = new GamePlayer(game2,player2,ship2);
			GamePlayer gamePlayer3 = new GamePlayer(game3,player3,ship3);

			playerRep.save(player1);
			playerRep.save(player2);
			playerRep.save(player3);

			gameRep.save(game1);
			gameRep.save(game2);
			gameRep.save(game3);

			shipRep.save(ship1);
            shipRep.save(shipToGo);
			shipRep.save(ship2);
			shipRep.save(ship3);

			gamePlayer1.addShip(shipToGo);

			gamePlayerRepo.save(gamePlayer1);
			gamePlayerRepo.save(gamePlayer2);
			gamePlayerRepo.save(gamePlayer3);
			//save
//			repository.save(new Game(dtf.format(now)));
//			repository.save(new Game(dtf.format(now.plus(1, ChronoUnit.HOURS))));
//			repository.save(new Game(dtf.format(now.plus(2, ChronoUnit.HOURS))));
		};
	}

}
