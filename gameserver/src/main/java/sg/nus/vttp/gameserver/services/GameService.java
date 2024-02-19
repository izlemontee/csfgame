package sg.nus.vttp.gameserver.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;
import sg.nus.vttp.gameserver.models.Player;
import sg.nus.vttp.gameserver.models.Session;

@Service
public class GameService {

    private Map<String,Session> sessions = new HashMap<>();

    public String initialiseNewGame(){
        String id = UUID.randomUUID().toString().substring(0,8);
        int players = 0;
        if(sessions.containsKey(id)){
            initialiseNewGame();
        }
        Session session = new Session(id, players);
        Player player = new Player(id, players);
        String sessionString = session.toJsonObjectToString();
        sessions.put(id,session);
        return sessionString;
    }

    public String getSession(String id){
        Session session = sessions.get(id);
        return session.toJsonObjectToString();
    }

    public String addNewPlayer(String sessionId){
        Session session = sessions.get(sessionId);
        int playerNumber = session.getPlayers();
        Player player = new Player(sessionId, playerNumber);
        session.addNewPlayer(player);
        return player.toJsonObjectToString();

    }

    public boolean idExists(String id){
        return (sessions.containsKey(id));
    }

    public String playerWaitingRoom(String id){
        Session session = sessions.get(id);
        boolean gameStarted = session.isGameStarted();
        JsonObjectBuilder JOB = Json.createObjectBuilder();
        JOB.add("gamestarted",gameStarted);
        return JOB.build().toString();
    }

    public void startGame(String id){
        Session session = sessions.get(id);
        session.setGameStarted(true);
        System.out.println("game started");
        sessions.replace(id, session);
    }
    
}
