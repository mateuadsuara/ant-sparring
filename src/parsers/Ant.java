package parsers;

import java.util.List;

public class Ant {
    public String id;
    public Location location;
    public boolean hasFood;
    public List<Location> detectedFood;

    public Ant(String id, boolean hasFood, Location location, List<Location> detectedFood) {
        this.id = id;
        this.hasFood = hasFood;
        this.location = location;
        this.detectedFood = detectedFood;
    }
}
