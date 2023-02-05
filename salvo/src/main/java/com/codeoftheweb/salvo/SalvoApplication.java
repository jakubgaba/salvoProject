package com.codeoftheweb.salvo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;


@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);
	}
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd --- HH:mm:ss");
	LocalDateTime now = LocalDateTime.now();
	@Bean
	public CommandLineRunner initData(GamePlayerRepository gamePlayerRepo, GameRepository gameRep, PlayerRepository playerRep, ShipRepository shipRep, SalvoRepository salvoRep){

		return(args) -> {
			Player player1 = new Player("Julio");
			Player player2 = new Player("Paul");
			Player player3 = new Player("Robert");
            Player player4 = new Player("Ronaldo");

			Game game1 = new Game(dtf.format(now));
			Game game2 = new Game(dtf.format(now.plus(1, ChronoUnit.HOURS)));
			Game game3 = new Game(dtf.format(now.plus(2, ChronoUnit.HOURS)));

			GamePlayer gamePlayer1 = new GamePlayer(game1,player1);
			GamePlayer gamePlayer2 = new GamePlayer(game1,player2);
			GamePlayer gamePlayer3 = new GamePlayer(game3,player3);
			GamePlayer gamePlayer4 = new GamePlayer(game2,player4);

			playerRep.save(player1);
			playerRep.save(player2);
			playerRep.save(player3);
			playerRep.save(player4);

			gameRep.save(game1);
			gameRep.save(game2);
			gameRep.save(game3);

			gamePlayerRepo.save(gamePlayer1);
			gamePlayerRepo.save(gamePlayer2);
			gamePlayerRepo.save(gamePlayer3);
			gamePlayerRepo.save(gamePlayer4);

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

            List<String> firstShotGP1 = new ArrayList<>();
			firstShotGP1.add("F4");
			firstShotGP1.add("F5");

			List<String> firstShotGP2 = new ArrayList<>();
			firstShotGP2.add("E8");
			firstShotGP2.add("F8");

			List<String> secondShotGP1 = new ArrayList<>();
			secondShotGP1.add("B1");
			secondShotGP1.add("B2");

			List<String> secondShotGP2 = new ArrayList<>();
			secondShotGP2.add("H1");
			secondShotGP2.add("H2");

			List<String> thirdShotGP1 = new ArrayList<>();
			thirdShotGP1.add("H6");
			thirdShotGP1.add("H9");

			List<String> thirdShotGP2 = new ArrayList<>();
			thirdShotGP2.add("D8");
			thirdShotGP2.add("C8");

			Map<Integer, String> GP1rounds = new HashMap<Integer, String>();
			GP1rounds.put(1,firstShotGP1.toString());
			GP1rounds.put(2,secondShotGP1.toString());
			GP1rounds.put(3,thirdShotGP1.toString());

			Map<Integer, String> GP2rounds = new HashMap<Integer, String>();
			GP2rounds.put(1,firstShotGP2.toString());
			GP2rounds.put(2,secondShotGP2.toString());
			GP2rounds.put(3,thirdShotGP2.toString());

			Ship shipCruiser = new Ship("Cruiser", gamePlayer1, locationShipCruiser);
			Ship shipHappy = new Ship("Happy", gamePlayer1, locationShipHappy);
			Ship shipHappy2 = new Ship("Happy", gamePlayer1, locationShipHappy2);
			Ship shipThree = new Ship("Jack Sparrow", gamePlayer1, locationJackShip);
			Ship shipJack = new Ship("Jack Sparrow", gamePlayer2, locationJackShip);
			Ship shipCaptain = new Ship("Brave", gamePlayer2, locationBraveShip);
			Ship shipToTest = new Ship("Cruiser", gamePlayer4, locationShipCruiser);
			Ship shipToTest2 = new Ship("Cruiser", gamePlayer3, locationShipCruiser);

         Salvo RoundGP1 = new Salvo(gamePlayer1, GP1rounds);
		 Salvo RoundGP2 = new Salvo(gamePlayer2, GP2rounds);
		 Salvo RoundGP3 = new Salvo(gamePlayer3, GP2rounds);
		 Salvo RoundGP4 = new Salvo(gamePlayer4, GP2rounds);

			shipRep.save(shipCruiser);
			shipRep.save(shipHappy);
			shipRep.save(shipHappy2);
			shipRep.save(shipThree);
			shipRep.save(shipJack);
			shipRep.save(shipCaptain);
			shipRep.save(shipToTest);
			shipRep.save(shipToTest2);


            salvoRep.save(RoundGP1);
			salvoRep.save(RoundGP2);
			salvoRep.save(RoundGP3);
			salvoRep.save(RoundGP4);
		};
	}

}
