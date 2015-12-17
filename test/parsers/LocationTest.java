package parsers;

import org.junit.Test;
import parsers.Location;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class LocationTest {
    @Test
    public void samePlace() {
        assertThat(directions(new Location(0, 0), new Location(0, 0)), is(Arrays.asList(
        )));
    }

    @Test
    public void oneToTheRight() {
        assertThat(directions(new Location(0, 0), new Location(0, 1)), is(Arrays.asList(
                "e"
        )));
    }

    @Test
    public void twoToTheRight() {
        assertThat(directions(new Location(0, 0), new Location(0, 2)), is(Arrays.asList(
                "e", "e"
        )));
    }

    @Test
    public void oneToTheWest() {
        assertThat(directions(new Location(0, 0), new Location(0, -1)), is(Arrays.asList(
                "w"
        )));
    }

    @Test
    public void twoToTheWest() {
        assertThat(directions(new Location(0, 0), new Location(0, -2)), is(Arrays.asList(
                "w", "w"
        )));
    }

    @Test
    public void oneToTheSouth() {
        assertThat(directions(new Location(0, 0), new Location(1, 0)), is(Arrays.asList(
                "s"
        )));
    }

    @Test
    public void twoToTheSouth() {
        assertThat(directions(new Location(0, 0), new Location(2, 0)), is(Arrays.asList(
                "s", "s"
        )));
    }

    @Test
    public void oneToTheNorth() {
        assertThat(directions(new Location(0, 0), new Location(-1, 0)), is(Arrays.asList(
                "n"
        )));
    }

    @Test
    public void twoToTheNorth() {
        assertThat(directions(new Location(0, 0), new Location(-2, 0)), is(Arrays.asList(
                "n", "n"
        )));
    }

    @Test
    public void oneToTheSouthEast() {
        assertThat(directions(new Location(0, 0), new Location(1, 1)), is(Arrays.asList(
                "se"
        )));
    }

    @Test
    public void oneToTheSouthWest() {
        assertThat(directions(new Location(0, 0), new Location(1, -1)), is(Arrays.asList(
                "sw"
        )));
    }

    @Test
    public void oneToTheNorthEast() {
        assertThat(directions(new Location(0, 0), new Location(-1, 1)), is(Arrays.asList(
                "ne"
        )));
    }

    @Test
    public void oneToTheNorthWest() {
        assertThat(directions(new Location(0, 0), new Location(-1, -1)), is(Arrays.asList(
                "nw"
        )));
    }

    @Test
    public void toAComplexLocation() {
        assertThat(directions(new Location(0, 0), new Location(1, 2)), is(Arrays.asList(
                "e", "se"
        )));
    }

    private List<String> directions(Location origin, Location destination) {
        return origin.directionsTo(destination);
    }
}
