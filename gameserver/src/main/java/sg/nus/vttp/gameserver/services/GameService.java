package sg.nus.vttp.gameserver.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

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
        String sessionString = session.toJsonObjectToString();
        sessions.put(id,session);
        return sessionString;
    }

    public String getSession(String id){
        Session session = sessions.get(id);
        return session.toJsonObjectToString();
    }

    public void addNewPlayer(String sessionId){
        Session session = sessions.get(sessionId);
        int playerNumber = session.getPlayers();
        Player player = new Player(sessionId, playerNumber);
        session.addNewPlayer(player);

    }
    
}
