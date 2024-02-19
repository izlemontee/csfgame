package sg.nus.vttp.gameserver.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

public class Session {

    private String id;
    private int players;
    private List<Player> playersList = new ArrayList<>();
    private boolean gameStarted = false;

    
    public boolean isGameStarted() {
        return gameStarted;
    }
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }
    public Session(String id, int players) {
        this.id = id;
        this.players = players;
        this.gameStarted = false;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getPlayers() {
        return players;
    }
    public void setPlayers(int players) {
        this.players = players;
    }

    public void addNewPlayer(Player player){
        playersList.add(player);
        setPlayers(playersList.size());
    }

    public String toJsonObjectToString(){
        JsonObjectBuilder JOB = Json.createObjectBuilder();
        JOB.add("id",getId())
            .add("players",getPlayers())
            .add("gameStarted",isGameStarted());
        return JOB.build().toString();
    }

    
}
