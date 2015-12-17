package parsers;

import us.bpsm.edn.Keyword;
import us.bpsm.edn.parser.Parsers;

import java.util.*;

import static us.bpsm.edn.Keyword.newKeyword;
import static us.bpsm.edn.parser.Parsers.defaultConfiguration;

public class AntParser {
    public Ant parse(String response) {
        Map<?, ?> stat = parseStat(response);
        return new Ant(getId(stat), hasFood(stat), getLocation(stat), getDetectedFood(stat));
    }

    private List<Location> getDetectedFood(Map<?, ?> stat) {
        List<Location> detectedFood = new ArrayList<>();
        Map<?, ?> surroundings = (Map<?, ?>) stat.get(newKeyword("surroundings"));
        for (Map.Entry<?, ?> direction : surroundings.entrySet()) {
            List<Map> contentsOfDirection = (List<Map>) direction.getValue();
            detectedFood.addAll(foodInDirection(contentsOfDirection));
        }
        return detectedFood;
    }

    private List<Location> foodInDirection(List<Map> contentsOfDirection) {
        List<Location> detectedFood = new ArrayList<>();
        for (Map neighbour : contentsOfDirection) {
            Keyword typeOfNeighbour = (Keyword) neighbour.get(newKeyword("type"));
            if (typeOfNeighbour.equals(newKeyword("food"))) {
                Location foodCoordinate = getLocation(neighbour);
                detectedFood.add(foodCoordinate);
            }
        }
        return detectedFood;
    }

    private Map<?, ?> parseStat(String response) {
        Map<?, ?> map = (Map<?, ?>) parseEdn(response);
        return (Map<?, ?>) map.get(newKeyword("stat"));
    }

    private String getId(Map<?, ?> stat) {
        return (String) stat.get(newKeyword("id"));
    }

    private Boolean hasFood(Map<?, ?> stat) {
        return (Boolean) stat.get(newKeyword("got-food"));
    }

    private Boolean isAnAnt(Map<?, ?> surroundings) {
        return (Boolean) surroundings.get(newKeyword("type"));
    }

    private Location getLocation(Map<?, ?> locatedObject) {
        List<Long> responseLocation = (List<Long>) locatedObject.get(newKeyword("location"));
        return new Location(responseLocation.get(1), responseLocation.get(0));
    }

    private Object parseEdn(String response) {
        return Parsers.newParser(defaultConfiguration()).nextValue(Parsers.newParseable(response));
    }
}
