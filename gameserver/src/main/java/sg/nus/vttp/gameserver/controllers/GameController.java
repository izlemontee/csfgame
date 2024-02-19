package sg.nus.vttp.gameserver.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.vttp.gameserver.services.GameService;

@RestController
@RequestMapping(path = "/game")
@CrossOrigin
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping(path = "/initialise")
    public ResponseEntity<String> initialiseGame(){
        String body = gameService.initialiseNewGame();
        ResponseEntity<String> response = ResponseEntity.status(201).contentType(MediaType.APPLICATION_JSON)
                                            .body(body);
        return response;
    }
    
    @GetMapping(path = "/waitingroom")
    public ResponseEntity<String> getWaitingRoom(@RequestParam String id){
        String responseBody = gameService.getSession(id);
        ResponseEntity<String> response = ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON)
                                            .body(responseBody);
        return response;
    }

    @GetMapping(path = "/startgame")
    public ResponseEntity<String> startGame(@RequestParam String id){
        gameService.startGame(id);
        ResponseEntity<String> response = ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON)
        .body("{}");
        return response;

    }

    @GetMapping(path = "/joingame")
    public ResponseEntity<String> addNewPlayer(@RequestParam String id){
        gameService.addNewPlayer(id);
        ResponseEntity<String> response = ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON)
        .body("{}}");
        return response;
    }

    @GetMapping(path = "/playerenter")
    public ResponseEntity<String> playerEnterRoom(@RequestParam String id){
        
        if(gameService.idExists(id)){
            String body = gameService.addNewPlayer(id);
           ResponseEntity<String> response = ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(body);
           return response; 
        }
        ResponseEntity<String> response = ResponseEntity.status(404).contentType(MediaType.APPLICATION_JSON).body("{}");
        return response;
    }

    @GetMapping(path = "/playerwaiting")
    public ResponseEntity<String> playerInWaitingRoom(@RequestParam String id){
        String body = gameService.playerWaitingRoom(id);
        ResponseEntity<String> response = ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON).body(body);
        return response;
    }

}
