package parsers;

import java.util.*;

public class Location {
    public Long x;
    public Long y;

    public Location(long x, long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (!x.equals(location.x)) return false;
        return y.equals(location.y);

    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + y.hashCode();
        return result;
    }

    public List<String> directionsTo(Location destination) {
        Step step = directionTo(destination);
        if (step == null) {
            return new ArrayList<>();
        } else {
            List<String> followingSteps = step.afterStepping.directionsTo(destination);
            followingSteps.add(step.direction);
            return followingSteps;
        }
    }

    public class Step {
        public String direction;
        public Location afterStepping;

        public Step(String direction, Location afterStepping) {
            this.direction = direction;
            this.afterStepping = afterStepping;
        }
    }

    private Step directionTo(Location destination) {
        if (destination.x < x && destination.y > y) {
            return new Step("ne", new Location(x -1, y +1));
        }
        if (destination.x < x && destination.y < y) {
            return new Step("nw", new Location(x -1, y -1));
        }
        if (destination.x > x && destination.y > y) {
            return new Step("se", new Location(x +1, y +1));
        }
        if (destination.x > x && destination.y < y) {
            return new Step("sw", new Location(x +1, y -1));
        }
        if (destination.y > y) {
            return new Step("e", new Location(x, y +1));
        }
        if (destination.y < y) {
            return new Step("w", new Location(x, y -1));
        }
        if (destination.x > x) {
            return new Step("s", new Location(x +1, y));
        }
        if (destination.x < x) {
            return new Step("n", new Location(x -1, y));
        }
        return null;
    }
}
