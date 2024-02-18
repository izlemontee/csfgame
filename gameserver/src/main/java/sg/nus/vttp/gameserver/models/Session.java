package sg.nus.vttp.gameserver.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

public class Session {

    private String id;
    private int players;
    private List<Player> playersList = new ArrayList<>();

    
    public Session(String id, int players) {
        this.id = id;
        this.players = players;
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
            .add("players",getPlayers());
        return JOB.build().toString();

    }

    
}
