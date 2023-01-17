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
            Player player4 = new Player("Ronaldo");

			Game game1 = new Game(dtf.format(now));
			Game game2 = new Game(dtf.format(now.plus(1, ChronoUnit.HOURS)));
			Game game3 = new Game(dtf.format(now.plus(2, ChronoUnit.HOURS)));

			GamePlayer gamePlayer1 = new GamePlayer(game1,player1);
			GamePlayer gamePlayer4 = new GamePlayer(game1,player4);
			GamePlayer gamePlayer2 = new GamePlayer(game2,player2);
			GamePlayer gamePlayer3 = new GamePlayer(game3,player3);

			playerRep.save(player1);
			playerRep.save(player2);
			playerRep.save(player3);
			playerRep.save(player4);

			gameRep.save(game1);
			gameRep.save(game2);
			gameRep.save(game3);

			gamePlayerRepo.save(gamePlayer1);
			gamePlayerRepo.save(gamePlayer4);
			gamePlayerRepo.save(gamePlayer2);
			gamePlayerRepo.save(gamePlayer3);

			List<String> locationShipCruiser = new ArrayList<>();
			locationShipCruiser.add("D8");
			locationShipCruiser.add("E8");
			locationShipCruiser.add("F8");


			List<String> locationShipHappy = new ArrayList<>();
			locationShipHappy.add("E1");
			locationShipHappy.add("F1");

			List<String> locationShipHappy2 = new ArrayList<>();
			locationShipHappy2.add("E2");
			locationShipHappy2.add("E3");

			List<String> locationJackShip = new ArrayList<>();
			locationJackShip.add("B4");
			locationJackShip.add("B5");
			locationJackShip.add("B6");
			locationJackShip.add("B7");

			List<String> locationBraveShip = new ArrayList<>();
			locationBraveShip.add("F4");
			locationBraveShip.add("F5");
			locationBraveShip.add("F6");
			locationBraveShip.add("F7");


			Ship shipCruiser = new Ship("Cruiser", gamePlayer1, locationShipCruiser);
			Ship shipHappy = new Ship("Happy", gamePlayer1, locationShipHappy);
			Ship shipHappy2 = new Ship("Happy", gamePlayer1, locationShipHappy2);
			Ship shipThree = new Ship("Jack Sparrow", gamePlayer1, locationJackShip);

			Ship shipJack = new Ship("Jack Sparrow", gamePlayer4, locationJackShip);
			Ship shipCaptain = new Ship("Brave", gamePlayer4, locationBraveShip);

			Ship shipToTest = new Ship("Cruiser", gamePlayer2, locationShipCruiser);

			Ship shipToTest2 = new Ship("Cruiser", gamePlayer3, locationShipCruiser);

			shipRep.save(shipCruiser);
			shipRep.save(shipHappy);
			shipRep.save(shipHappy2);
			shipRep.save(shipThree);

			shipRep.save(shipJack);
			shipRep.save(shipCaptain);

			shipRep.save(shipToTest);
			shipRep.save(shipToTest2);





			//save
//			repository.save(new Game(dtf.format(now)));
//			repository.save(new Game(dtf.format(now.plus(1, ChronoUnit.HOURS))));
//			repository.save(new Game(dtf.format(now.plus(2, ChronoUnit.HOURS))));
		};
	}

}
