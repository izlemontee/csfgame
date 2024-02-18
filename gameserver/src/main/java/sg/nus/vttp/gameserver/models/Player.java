package sg.nus.vttp.gameserver.models;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

public class Player {

    private String sessionId;
    private int playerId;
    
    public Player(String sessionId, int playerId) {
        this.sessionId = sessionId;
        this.playerId = playerId;
    }
    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    public int getPlayerId() {
        return playerId;
    }
    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String toJsonObjectToString(){
        JsonObjectBuilder JOB = Json.createObjectBuilder();
        JOB.add("sessionid",getSessionId())
            .add("playerid",getPlayerId());
        return JOB.build().toString();
    }
    
}
