package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
            Player playervs1 = new Player("Ronaldo");

			Game game1 = new Game(dtf.format(now));
			Game game2 = new Game(dtf.format(now.plus(1, ChronoUnit.HOURS)));
			Game game3 = new Game(dtf.format(now.plus(2, ChronoUnit.HOURS)));

			GamePlayer gamePlayer1 = new GamePlayer(game1,player1);
			GamePlayer gamePlayervs1 = new GamePlayer(game1,playervs1);
			GamePlayer gamePlayer2 = new GamePlayer(game2,player2);
			GamePlayer gamePlayer3 = new GamePlayer(game3,player3);

			playerRep.save(player1);
			playerRep.save(player2);
			playerRep.save(player3);
			playerRep.save(playervs1);

			gameRep.save(game1);
			gameRep.save(game2);
			gameRep.save(game3);

			gamePlayerRepo.save(gamePlayer1);
			gamePlayerRepo.save(gamePlayervs1);
			gamePlayerRepo.save(gamePlayer2);
			gamePlayerRepo.save(gamePlayer3);

			List<String> locationShip1 = new ArrayList<>();
			locationShip1.add("H2");
			locationShip1.add("H3");
			locationShip1.add("H4");
			List<String> locationShipToGo = new ArrayList<>();
			locationShipToGo.add("E1");
			locationShipToGo.add("F1");
			locationShipToGo.add("G1");
			List<String> locationJackShip = new ArrayList<>();
			locationJackShip.add("B4");
			locationJackShip.add("B5");

			Ship ship1 = new Ship("Cruiser", gamePlayer1, locationShip1);
			Ship shipToGo = new Ship("Happy Ship", gamePlayer1, locationShipToGo);
			Ship jackShip = new Ship("Jack Sparrow ship", gamePlayer1, locationJackShip);
			Ship ship2 = new Ship("BadShip", gamePlayer2, locationShip1);
			Ship shipToGo2 = new Ship("JackSparrowShip", gamePlayer2, locationShipToGo);

			shipRep.save(ship1);
			shipRep.save(shipToGo);
			shipRep.save(ship2);
			shipRep.save(shipToGo2);
			shipRep.save(jackShip);





			//save
//			repository.save(new Game(dtf.format(now)));
//			repository.save(new Game(dtf.format(now.plus(1, ChronoUnit.HOURS))));
//			repository.save(new Game(dtf.format(now.plus(2, ChronoUnit.HOURS))));
		};
	}

}
