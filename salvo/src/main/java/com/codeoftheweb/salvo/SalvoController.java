package com.codeoftheweb.salvo;

import com.codeoftheweb.salvo.FirebaseRepositories.FirebaseGameRepository;
import com.codeoftheweb.salvo.firebaseUtilities.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SalvoController {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private GamePlayerRepository gamePlayerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SalvoRepository salvoRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private PlayerDetailsService playerDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GameService gameService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.badRequest().body("User is already logged in");
        }
        try {
                UserDetails userDetails = playerDetailsService.loadUserByUsername(loginRequest.getUsername());
                if (!passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
                }
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                Long playerId = ((PlayerDetailsService.CustomUserDetails) userDetails).playerId;

                request.getSession(true).setAttribute("playerId", playerId);

                SecurityContextHolder.getContext().setAuthentication(token);
                return ResponseEntity.ok(playerId);
            } catch (AuthenticationException e) {
                return ResponseEntity.badRequest().body("Invalid credentials");
            }
        }


    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpServletRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "User is not logged in"));
        }
        if (authentication != null && authentication.isAuthenticated()) {
            SecurityContextHolder.clearContext();
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            Map<String, String> response = new HashMap<>();
            response.put("message", "Logged out successfully");
            return ResponseEntity.ok().body(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "You must be logged in to perform this action"));
        }
    }


    @PostMapping("/joinGame")
    public ResponseEntity<String> joinGame(@RequestParam("gameid") Long gameId, @RequestBody List<List<String>> gameData, HttpServletRequest request) {
        try {
            List<String> happyShip = new ArrayList<>();
            List<String> cruiserShip = new ArrayList<>();
            List<String> jackShip = new ArrayList<>();

            HttpSession session = request.getSession();
            String ID = "playerId";
            Long playerId = (Long) session.getAttribute(ID);

            Game game = gameRepository.findById(gameId).orElse(null);
            Player player = playerRepository.findById(playerId).orElse(null);

            GamePlayer gamePlayer = new GamePlayer(game,player);


            gamePlayerRepository.save(gamePlayer);

            for (int i = 0; i < gameData.size(); i++) {
                List<String> sublist = gameData.get(i);
                int length = sublist.size();
                switch (length) {
                    case 2:
                        happyShip.addAll(sublist);
                        break;
                    case 3:
                        cruiserShip.addAll(sublist);
                        break;
                    case 4:
                        jackShip.addAll(sublist);
                        break;
                    default:
                }
            }


            Ship shipCruiser = new Ship("Cruiser", gamePlayer, cruiserShip);
            Ship shipHappy = new Ship("Happy", gamePlayer, happyShip);
            Ship shipJack = new Ship("Jack", gamePlayer, jackShip);

            shipRepository.save(shipCruiser);
            shipRepository.save(shipHappy);
            shipRepository.save(shipJack);


            return ResponseEntity.status(HttpStatus.CREATED).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to join game.");
        }
    }




    @PostMapping("/createGameShips")
    public ResponseEntity<String> createGameShips(@RequestBody List<List<String>> gameData, HttpServletRequest request) {
        try {

            List<String> happyShip = new ArrayList<>();
            List<String> cruiserShip = new ArrayList<>();
            List<String> jackShip = new ArrayList<>();

            HttpSession session = request.getSession();
            String ID = "playerId";
            Long playerId = (Long) session.getAttribute(ID);

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd --- HH:mm:ss");
            String gameCreated = now.format(formatter);

            Game game = new Game(gameCreated);
            Player player = playerRepository.findById(playerId).orElse(null);
            
            GamePlayer gamePlayer = new GamePlayer(game,player);

            gameService.saveToBoth(game);
            gamePlayerRepository.save(gamePlayer);


            for (int i = 0; i < gameData.size(); i++) {
                List<String> sublist = gameData.get(i);
                int length = sublist.size();
                switch (length) {
                    case 2:
                        happyShip.addAll(sublist);
                        break;
                    case 3:
                        cruiserShip.addAll(sublist);
                        break;
                    case 4:
                        jackShip.addAll(sublist);
                        break;
                    default:
                }
            }


            Ship shipCruiser = new Ship("Cruiser", gamePlayer, cruiserShip);
            Ship shipHappy = new Ship("Happy", gamePlayer, happyShip);
            Ship shipJack = new Ship("Jack", gamePlayer, jackShip);

            shipRepository.save(shipCruiser);
            shipRepository.save(shipHappy);
            shipRepository.save(shipJack);

            // use gameId, happyShip, cruiserShip, and jackShip to create the game
            return ResponseEntity.status(HttpStatus.CREATED).body("OK");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to join game.");
        }
    }

    @PostMapping("/createShots/{gameplayerID}")
    public ResponseEntity<?> createShots(@RequestBody List<String> shots, @PathVariable Long gameplayerID) {
        try {
            List<String> locations = new ArrayList<>(shots);

            GamePlayer gamePlayer = gamePlayerRepository.findById(gameplayerID).orElse(null);
            if (gamePlayer == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game player not found.");
            }

            Integer previousRoundNumber = gamePlayer.getSalvos().stream()
                    .map(Salvo::getRoundNumber)
                    .max(Comparator.naturalOrder())
                    .orElse(0);

            if (previousRoundNumber == 0) {

                Game game = gamePlayer.getGames();
                if (game == null) {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game not found.");
                }

                Long enemyGamePlayerId = game.getGamePlayers().stream()
                        .map(GamePlayer::getGamePlayerId)
                        .filter(gpId -> !gpId.equals(gameplayerID))
                        .findFirst()
                        .orElse(null);

                if (enemyGamePlayerId != null) {
                    GamePlayer enemyGamePlayer = gamePlayerRepository.findById(enemyGamePlayerId).orElse(null);
                    if (enemyGamePlayer != null) {
                        Salvo latestEnemySalvo = enemyGamePlayer.getSalvos().stream()
                                .max(Comparator.comparing(Salvo::getRoundNumber))
                                .orElse(null);
                        if (latestEnemySalvo != null) {
                            latestEnemySalvo.setRoundNumber(0);
                        }

                        List<String> enemyShipLocations = enemyGamePlayer.getShips().stream()
                                .flatMap(ship -> ship.getShipLocation().stream()
                                        .map(loc -> ship.getShipType() + "-" + loc))
                                .collect(Collectors.toList());

                        Map<String, String> matchedLocations = locations.stream()
                                .flatMap(location -> enemyShipLocations.stream()
                                        .filter(esl -> esl.split("-")[1].equals(location))
                                        .map(esl -> new AbstractMap.SimpleEntry<>(location, esl.split("-")[0].substring(0, 1))))
                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                        locations = locations.stream()
                                .map(location -> location + (matchedLocations.containsKey(location) ? matchedLocations.get(location) + "T" : "F"))
                                .collect(Collectors.toList());

                        List<String> enemyLocations = enemyShipLocations.stream()
                                .map(esl -> esl.split("-")[1]) // Get the location part
                                .collect(Collectors.toList());


                        Salvo roundGP = new Salvo(gamePlayer, locations, 1);
                        salvoRepository.save(roundGP);

                        List<Salvo> salvos = new ArrayList<>(gamePlayer.getSalvos());
                        salvos.add(roundGP);

                        List<String> allSalvoLocations = salvos.stream()
                                .flatMap(salvo -> salvo.getSalvoLocation().stream())
                                .map(location -> location.contains("10") ? location.substring(0, 3) : location.substring(0, 2))
                                .collect(Collectors.toList());

                        allSalvoLocations.addAll(locations);

                        if (allSalvoLocations.containsAll(enemyLocations)) {
                            Score score;
                            try {
                                score = gamePlayer.getPlayers().getScoresPlayer().iterator().next();
                                score.setScore(score.getScore() + 1);
                            } catch (NoSuchElementException e) {
                                score = new Score(1, gamePlayer.getPlayers(), gamePlayer.getGames());
                            }
                            scoreRepository.save(score);
                            return ResponseEntity.status(HttpStatus.OK).body("GAME OVER");
                        }
                        return ResponseEntity.status(HttpStatus.CREATED).body(matchedLocations);
                    }
                }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Enemy game player not found.");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("You already played. Wait for the other player.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to shoot.");
        }
    }





    @PostMapping("/game/{gameId}/players")
    ResponseEntity<Object> joinGame(@PathVariable Long gameId, @RequestBody Map<String, Long> requestBody) {
        try {
            // Get the current user

            Long playerId = requestBody.get("playerId");
            Player player = playerRepository.findById(playerId).orElse(null);
            if (player == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You must be logged in to join a game.");
            }
            // Get the game with the given ID
            Game game = gameRepository.findById(gameId).orElse(null);
            if (game == null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No such game.");
            }
            // Check if the game has only one player
            if (game.getGamePlayers().size() >= 2) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Game is full.");
            }
            // Create and save a new game player
            GamePlayer gamePlayer = new GamePlayer(game, player);
            gamePlayerRepository.save(gamePlayer);

            Long shipsAreThere = null;
            if (gamePlayer.getShips() != null) {
                shipsAreThere = Long.parseLong("Yes");;
            }

            // Send a Created response with the new game player ID and game ID for some front end fun :D
            Map<String, Long> responseMap = new HashMap<>();
            responseMap.put("playerID", player.getUserId());
            responseMap.put("gameID", gameId);
            responseMap.put("gamePlayerID", gamePlayer.getGamePlayerId());
            responseMap.put("ships", shipsAreThere);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to join game.");
        }
    }




    @GetMapping("/games")
    public List<Object> getGames() {
        return gameRepository.findAll().stream().map(this::getIndividualGameData).collect(Collectors.toList());
    }

    @RequestMapping("/game_view/{GPId}")
    public Map<String, Object> getGamePlayerByIds(@PathVariable Long GPId){
        return getGameViewData(GPId);
    }


    @RequestMapping("/leaderboard")
    public Map<String, Object> getPlayerLeaders(){
        List<Score> Scores = scoreRepository.findAll();
        Map<String, Object> mapping = new LinkedHashMap<>();
        // I create a Stream from Scores list then we can call sorted method to sort the scores ( It let us process elements in functional way )
        // Comparator takes a F"unction" that will take a !! KEY !! for comparising and returns new Comparator that comapres objects based on the extracted KEY...
        // .reversed() because descending order.
        // .thenComparing takes secondary ! KEY ! when the two object have the same value for the primary key
        // and DONE ! :-}

        List<Score> sortedScores = Scores.stream()
                .sorted(Comparator.comparing(Score::getScore).reversed()
                        .thenComparing(element -> element.getPlayerScore().getUserName()))
                .collect(Collectors.toList());

        mapping.put("Scores", sortedScores.stream().map(Score::getScore));
        mapping.put("PlayersByScore", sortedScores.stream()
                .map(element -> element.getPlayerScore().getUserName()));
        return mapping;
    }


    public Map<String, Object> getGameViewData(Long GPId){
        Map<String, Object> oneGamePlayer = new LinkedHashMap<>();
        GamePlayer gamePlayer = gamePlayerRepository.findById(GPId).get();
        oneGamePlayer.put("gameId", gamePlayer.getGames().getGameId());
        oneGamePlayer.put("gamePlayerId", gamePlayer.getGamePlayerId());
        oneGamePlayer.put("created", gamePlayer.getGames().getGameCreated());
        oneGamePlayer.put("gamePlayers", getGamePlayersData(gamePlayer.getGames().getGamePlayers(), GPId));
        oneGamePlayer.put("ships", getShipsData(gamePlayer.getShips()));

        List<Salvo> salvos = new ArrayList<>(gamePlayer.getSalvos());
        List<String> allSalvoLocations = salvos.stream()
                .flatMap(salvo -> salvo.getSalvoLocation().stream())
                .collect(Collectors.toList());
        oneGamePlayer.put("salvoes", allSalvoLocations);
        oneGamePlayer.put("round", salvos.stream().map(Salvo::getRoundNumber));
        return oneGamePlayer;
    }




    public List<Object> getGamePlayersData(Set<GamePlayer> gamePlayers, Long GPId){
        return gamePlayers.stream().map(gamePlayer -> getIndividualGamePlayerData(gamePlayer, GPId)).collect(Collectors.toList());
    }

    public Map<String, Object> getIndividualGamePlayerData(GamePlayer gamePlayer, Long GPId){
        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("Id", gamePlayer.getPlayers().getUserId());
        mapping.put("Player", gamePlayer.getPlayers().getUserName());
        mapping.put("Score", gamePlayer.getPlayers().getScoresPlayer().stream().map(Score::getScore));
        if(gamePlayer.getPlayers().getUserId() != GPId){
            mapping.put("enemySalvoes", gamePlayer.getSalvos().stream().flatMap(salvo -> salvo.getSalvoLocation().stream()));
        }
        return mapping;
    }


    public List<Object> getShipsData(Set<Ship> ships){
        return ships.stream().map(this::getIndividualShipData).collect(Collectors.toList());
    }

    public Map<String, Object> getIndividualShipData(Ship ship){
        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("type", ship.getShipType());
        mapping.put("locations", ship.getShipLocation());
        return mapping;
    }
   public Map<String, Object> getIndividualGameData(Game gameEach){
        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("id", gameEach.getGameId());
        mapping.put("created", gameEach.getGameCreated());
        mapping.put("gamePlayers", getGamePlayersInAGameData(gameEach.getGamePlayers()));   // now i have [{ .... }]
       return
                mapping;
   }

    public List<Object> getGamePlayersInAGameData(Set<GamePlayer> mapGame){
        return mapGame
                .stream().map(this::getIndividualGamePlayerInAGameData).collect(Collectors.toList());
    }
    public Map<String, Object> getIndividualGamePlayerInAGameData(GamePlayer gamePlayerEach){
        Map<String, Object> mapping = new LinkedHashMap<>();
        mapping.put("id", gamePlayerEach.getGamePlayerId());
        mapping.put("players",  getPlayerData(gamePlayerEach.getPlayers()));
        mapping.put("shipsPlaced", gamePlayerEach.getShips());
        return mapping;
    }

public Map<String, Object>  getPlayerData(Player player){
    Map<String, Object> mapping = new LinkedHashMap<>();
    mapping.put("id", player.getUserId());
    mapping.put("userName", player.getUserName());
    return
            mapping;
}
}
