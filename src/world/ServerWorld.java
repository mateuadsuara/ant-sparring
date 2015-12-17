package world;

import http.Http;
import parsers.Ant;
import parsers.JoinParser;
import parsers.Location;
import parsers.AntParser;

import java.util.List;

public class ServerWorld implements World {
    private String name;
    private String server;
    private String nestId;

    public ServerWorld(String name, String server) {
        this.name = name;
        this.server = server;
        this.nestId = join();
    }

    @Override
    public Ant spawn() {
        Ant ant = spawn(nestId);
        return ant;
    }

    private Ant spawn(String nestId) {
        String response = request("http://" +  server + "/" + nestId + "/spawn");
        Ant ant = new AntParser().parse(response);
        return ant;
    }

    private String join() {
        String response = request("http://" + server + "/join/" + name);
        String id = new JoinParser().parse(response);
        return id;
    }

    private String request(String uri) {
        return new Http().sendGETRequest(uri);
    }

    @Override
    public Ant move(Ant ant, Location destination) {
        List<String> directions = ant.location.directionsTo(destination);
        if (directions.isEmpty()) return ant;

        String response = "";
        for (String direction : directions) {
            response = request("http://" + server + "/" + ant.id + "/go/" + direction);
        }
        return new AntParser().parse(response);
    }

    public void look(Ant ant) {
        String res = request("http://" + server + "/" + ant.id + "/look");
        System.out.println(res);
    }
}
