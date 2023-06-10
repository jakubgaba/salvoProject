package com.codeoftheweb.salvo;


import com.codeoftheweb.salvo.firebaseUtilities.*;
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
	public CommandLineRunner initData(GamePlayerService gamePlayerRepo, GameService gameService, PlayerService playerRep, ShipService shipRep, SalvoService salvoRep, ScoreService scoreRep){

		return(args) -> {
			Player player1 = new Player("Julio", "123","ROLE_PLAYER");

			Player player2 = new Player("Paul","123","ROLE_PLAYER");

			Player player3 = new Player("Robert","123","ROLE_PLAYER");

            Player player4 = new Player("Ronaldo","123","ROLE_PLAYER");
			Player player5 = new Player("Jakub","123","ROLE_PLAYER");
			Player player6 = new Player("Igor","123","ROLE_PLAYER");
			Player player7 = new Player("Yura","123","ROLE_PLAYER");
			Player player8 = new Player("Josef","123","ROLE_PLAYER");


			Game game1 = new Game(dtf.format(now));
			Game game2 = new Game(dtf.format(now.plus(1, ChronoUnit.HOURS)));
			Game game3 = new Game(dtf.format(now.plus(2, ChronoUnit.HOURS)));
			Game game4 = new Game(dtf.format(now.plus(3, ChronoUnit.HOURS)));


			GamePlayer gamePlayer1 = new GamePlayer(game1,player1);
			GamePlayer gamePlayer2 = new GamePlayer(game1,player2);
			GamePlayer gamePlayer3 = new GamePlayer(game2,player3);
			GamePlayer gamePlayer4 = new GamePlayer(game2,player4);
			GamePlayer gamePlayer5 = new GamePlayer(game3,player5);
			GamePlayer gamePlayer6 = new GamePlayer(game3,player6);
			GamePlayer gamePlayer7 = new GamePlayer(game4,player1);
			playerRep.saveToBoth(player1);
			playerRep.saveToBoth(player2);
			playerRep.saveToBoth(player3);
			playerRep.saveToBoth(player4);
			playerRep.saveToBoth(player5);
			playerRep.saveToBoth(player6);
			playerRep.saveToBoth(player7);
			playerRep.saveToBoth(player8);

			gameService.saveToBoth(game1);
			gameService.saveToBoth(game2);
			gameService.saveToBoth(game3);
			gameService.saveToBoth(game4);


			gamePlayerRepo.saveToBoth(gamePlayer1);
			gamePlayerRepo.saveToBoth(gamePlayer2);
			gamePlayerRepo.saveToBoth(gamePlayer3);
			gamePlayerRepo.saveToBoth(gamePlayer4);
			gamePlayerRepo.saveToBoth(gamePlayer5);
			gamePlayerRepo.saveToBoth(gamePlayer6);
			gamePlayerRepo.saveToBoth(gamePlayer7);



			List<String> locationShipCruiser = new ArrayList<>();
			locationShipCruiser.add("D8");
			locationShipCruiser.add("E8");
			locationShipCruiser.add("F8");

			List<String> locationShipCruiserV2 = new ArrayList<>();
			locationShipCruiserV2.add("A4");
			locationShipCruiserV2.add("B4");
			locationShipCruiserV2.add("C4");

			List<String> locationShipCruiserV3 = new ArrayList<>();
			locationShipCruiserV3.add("G3");
			locationShipCruiserV3.add("G4");
			locationShipCruiserV3.add("G5");

			List<String> locationShipCruiserV4 = new ArrayList<>();
			locationShipCruiserV4.add("G4");
			locationShipCruiserV4.add("G5");
			locationShipCruiserV4.add("G6");

			List<String> locationShipCruiserV5 = new ArrayList<>();
			locationShipCruiserV5.add("C5");
			locationShipCruiserV5.add("C6");
			locationShipCruiserV5.add("C7");

			List<String> locationShipCruiserV6 = new ArrayList<>();
			locationShipCruiserV6.add("B6");
			locationShipCruiserV6.add("C6");
			locationShipCruiserV6.add("D6");

			List<String> locationShipCruiserV7 = new ArrayList<>();
			locationShipCruiserV7.add("H1");
			locationShipCruiserV7.add("H2");
			locationShipCruiserV7.add("H3");


			List<String> locationShipHappy = new ArrayList<>();
			locationShipHappy.add("E1");
			locationShipHappy.add("F1");

			List<String> locationShipHappyV2 = new ArrayList<>();
			locationShipHappyV2.add("G3");
			locationShipHappyV2.add("H3");

			List<String> locationShipHappyV3 = new ArrayList<>();
			locationShipHappyV3.add("H5");
			locationShipHappyV3.add("I5");

			List<String> locationShipHappyV4 = new ArrayList<>();
			locationShipHappyV4.add("A1");
			locationShipHappyV4.add("B1");

			List<String> locationShipHappyV5 = new ArrayList<>();
			locationShipHappyV5.add("H3");
			locationShipHappyV5.add("H4");

			List<String> locationShipHappyV6 = new ArrayList<>();
			locationShipHappyV6.add("J4");
			locationShipHappyV6.add("J5");

			List<String> locationShipHappyV7 = new ArrayList<>();
			locationShipHappyV7.add("F5");
			locationShipHappyV7.add("G5");







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
			List<String> secondShotGP1 = new ArrayList<>();
			secondShotGP1.add("B1");
			secondShotGP1.add("B2");
			List<String> thirdShotGP1 = new ArrayList<>();
			thirdShotGP1.add("H6");
			thirdShotGP1.add("H9");
			List<String> fourthShotGP1 = new ArrayList<>();
			fourthShotGP1.add("F6");
			fourthShotGP1.add("F7");
			List<String> fifthShotGP1 = new ArrayList<>();
			fifthShotGP1.add("B4");
			fifthShotGP1.add("B5");
			List<String> sixthShotGP1 = new ArrayList<>();
			sixthShotGP1.add("B6");
			sixthShotGP1.add("B7");

			List<String> firstShotGP2 = new ArrayList<>();
			firstShotGP2.add("E8");
			firstShotGP2.add("F8");
			List<String> secondShotGP2 = new ArrayList<>();
			secondShotGP2.add("H1");
			secondShotGP2.add("H2");
			List<String> thirdShotGP2 = new ArrayList<>();
			thirdShotGP2.add("D8");
			thirdShotGP2.add("C8");
			List<String> fourthShotGP2 = new ArrayList<>();
			fourthShotGP2.add("F6");
			fourthShotGP2.add("F7");
			List<String> fifthShotGP2 = new ArrayList<>();
			fifthShotGP2.add("B4");
			fifthShotGP2.add("B5");
			List<String> sixthShotGP2 = new ArrayList<>();
			sixthShotGP2.add("B6");
			sixthShotGP2.add("B7");

			List<String> firstShotGP3 = new ArrayList<>();
			firstShotGP3.add("A9");
			firstShotGP3.add("A9");
			List<String> secondShotGP3 = new ArrayList<>();
			secondShotGP3.add("B1");
			secondShotGP3.add("B2");
			List<String> thirdShotGP3 = new ArrayList<>();
			thirdShotGP3.add("G4");
			thirdShotGP3.add("G4");


			List<String> firstShotGP4 = new ArrayList<>();
			firstShotGP4.add("C5");
			firstShotGP4.add("C2");
			List<String> secondShotGP4 = new ArrayList<>();
			secondShotGP4.add("B2");
			secondShotGP4.add("B4");
			List<String> thirdShotGP4 = new ArrayList<>();
			thirdShotGP4.add("H4");
			thirdShotGP4.add("H3");

			List<String> firstShotGP5 = new ArrayList<>();
			firstShotGP5.add("F1");
			firstShotGP5.add("F3");
			List<String> secondShotGP5 = new ArrayList<>();
			secondShotGP5.add("G4");
			secondShotGP5.add("D3");
			List<String> thirdShotGP5 = new ArrayList<>();
			thirdShotGP5.add("E2");
			thirdShotGP5.add("E2");

			List<String> firstShotGP6 = new ArrayList<>();
			firstShotGP6.add("G3");
			firstShotGP6.add("G4");
			List<String> secondShotGP6 = new ArrayList<>();
			secondShotGP6.add("C2");
			secondShotGP6.add("C3");
			List<String> thirdShotGP6 = new ArrayList<>();
			thirdShotGP6.add("J3");
			thirdShotGP6.add("J4");

			List<String> firstShotGP7 = new ArrayList<>();
			firstShotGP7.add("I3");
			firstShotGP7.add("I2");
			List<String> secondShotGP7 = new ArrayList<>();
			secondShotGP7.add("I6");
			secondShotGP7.add("I1");
			List<String> thirdShotGP7 = new ArrayList<>();
			thirdShotGP7.add("H5");
			thirdShotGP7.add("H4");

			List<String> firstShotGP8 = new ArrayList<>();
			firstShotGP8.add("A1");
			firstShotGP8.add("A4");
			List<String> secondShotGP8 = new ArrayList<>();
			secondShotGP8.add("B3");
			secondShotGP8.add("B4");
			List<String> thirdShotGP8 = new ArrayList<>();
			thirdShotGP8.add("F4");
			thirdShotGP8.add("F1");

			List<String> GP1rounds = new ArrayList<>();
			GP1rounds.add(firstShotGP1.toString());
			GP1rounds.add(secondShotGP1.toString());
			GP1rounds.add(thirdShotGP1.toString());
			GP1rounds.add(fourthShotGP1.toString());
			GP1rounds.add(fifthShotGP1.toString());
			GP1rounds.add(sixthShotGP1.toString());

			List<String> GP2rounds = new ArrayList<>();
			GP2rounds.add(firstShotGP2.toString());
			GP2rounds.add(secondShotGP2.toString());
			GP2rounds.add(thirdShotGP2.toString());
			GP2rounds.add(fourthShotGP2.toString());
			GP2rounds.add(fifthShotGP2.toString());
			GP2rounds.add(sixthShotGP2.toString());

			List<String> GP3rounds = new ArrayList<>();
			GP3rounds.add(firstShotGP3.toString());
			GP3rounds.add(secondShotGP3.toString());
			GP3rounds.add(thirdShotGP3.toString());

			List<String> GP4rounds = new ArrayList<>();
			GP4rounds.add(firstShotGP4.toString());
			GP4rounds.add(secondShotGP4.toString());
			GP4rounds.add(thirdShotGP4.toString());

			List<String> GP5rounds = new ArrayList<>();
			GP5rounds.add(firstShotGP5.toString());
			GP5rounds.add(secondShotGP5.toString());
			GP5rounds.add(thirdShotGP5.toString());

			List<String> GP6rounds = new ArrayList<>();
			GP6rounds.add(firstShotGP6.toString());
			GP6rounds.add(secondShotGP6.toString());
			GP6rounds.add(thirdShotGP6.toString());

			List<String> GP7rounds = new ArrayList<>();
			GP7rounds.add(firstShotGP7.toString());
			GP7rounds.add(secondShotGP7.toString());
			GP7rounds.add(thirdShotGP7.toString());

			List<String> GP8rounds = new ArrayList<>();
			GP8rounds.add(firstShotGP8.toString());
			GP8rounds.add(secondShotGP8.toString());
			GP8rounds.add(thirdShotGP8.toString());

			Ship shipCruiser = new Ship("Cruiser", gamePlayer1, locationShipCruiser);
			Ship shipHappy = new Ship("Happy", gamePlayer1, locationShipHappy);
			Ship shipHappy2 = new Ship("Happy", gamePlayer1, locationShipHappy2);
			Ship shipThree = new Ship("Jack Sparrow", gamePlayer1, locationJackShip);
			Ship shipJack = new Ship("Jack Sparrow", gamePlayer2, locationJackShip);
			Ship shipCaptain = new Ship("Brave", gamePlayer2, locationBraveShip);
			Ship shipHappyV2 = new Ship("HappyV2", gamePlayer3, locationShipHappyV2);
			Ship shipCruiserV2 = new Ship("CruiserV2", gamePlayer3, locationShipCruiserV2);
			Ship shipHappyV3 = new Ship("HappyV3", gamePlayer4, locationShipHappyV3);
			Ship shipCruiserV3 = new Ship("CruiserV3", gamePlayer4, locationShipCruiserV3);
			Ship shipHappyV4 = new Ship("HappyV4", gamePlayer5, locationShipHappyV4);
			Ship shipCruiserV4 = new Ship("CruiserV4", gamePlayer5, locationShipCruiserV4);
			Ship shipHappyV5 = new Ship("HappyV5", gamePlayer6, locationShipHappyV5);
			Ship shipCruiserV5 = new Ship("CruiserV5", gamePlayer6, locationShipCruiserV5);
			Ship shipHappyV6 = new Ship("HappyV6", gamePlayer7, locationShipHappyV6);
			Ship shipCruiserV6 = new Ship("CruiserV6", gamePlayer7, locationShipCruiserV6);



         Salvo RoundGP1 = new Salvo(gamePlayer1, GP1rounds, 1);
		 Salvo RoundGP2 = new Salvo(gamePlayer2, GP2rounds,1);
		 Salvo RoundGP3 = new Salvo(gamePlayer3, GP3rounds,1);
		 Salvo RoundGP4 = new Salvo(gamePlayer4, GP4rounds,1);
		 Salvo RoundGP5 = new Salvo(gamePlayer5, GP5rounds,1);
		 Salvo RoundGP6 = new Salvo(gamePlayer6, GP6rounds,1);
		 Salvo RoundGP7 = new Salvo(gamePlayer7, GP7rounds,1);


		 Score scoreGP1 = new Score(1, player1, game1);
		 Score scoreGP2 = new Score(0, player2, game1);
		 Score scoreGP3 = new Score(0, player3, game2);
		 Score scoreGP4 = new Score(1, player4, game2);
		 Score scoreGP5 = new Score(2, player5, game3);
		 Score scoreGP6 = new Score(0, player6, game3);
		 Score scoreGP7 = new Score(1, player7, game4);
		 Score scoreGP8 = new Score(0, player8, game4);


			shipRep.saveToBoth(shipCruiser);
			shipRep.saveToBoth(shipHappy);
			shipRep.saveToBoth(shipHappy2);
			shipRep.saveToBoth(shipThree);
			shipRep.saveToBoth(shipJack);
			shipRep.saveToBoth(shipCaptain);
            shipRep.saveToBoth(shipHappyV2);
            shipRep.saveToBoth(shipCruiserV2);
			shipRep.saveToBoth(shipCruiserV3);
			shipRep.saveToBoth(shipCruiserV4);
			shipRep.saveToBoth(shipCruiserV5);
			shipRep.saveToBoth(shipCruiserV6);
			shipRep.saveToBoth(shipCruiserV2);

			shipRep.saveToBoth(shipHappyV3);
			shipRep.saveToBoth(shipHappyV4);
			shipRep.saveToBoth(shipHappyV5);
			shipRep.saveToBoth(shipHappyV6);


            salvoRep.saveToBoth(RoundGP1);
			salvoRep.saveToBoth(RoundGP2);
			salvoRep.saveToBoth(RoundGP3);
			salvoRep.saveToBoth(RoundGP4);
			salvoRep.saveToBoth(RoundGP5);
			salvoRep.saveToBoth(RoundGP6);
			salvoRep.saveToBoth(RoundGP7);


			scoreRep.saveToBoth(scoreGP1);
			scoreRep.saveToBoth(scoreGP2);
			scoreRep.saveToBoth(scoreGP3);
			scoreRep.saveToBoth(scoreGP4);
			scoreRep.saveToBoth(scoreGP5);
			scoreRep.saveToBoth(scoreGP6);
			scoreRep.saveToBoth(scoreGP7);

		};

	}


}
