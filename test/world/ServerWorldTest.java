package world;

import org.junit.Before;
import org.junit.Test;
import parsers.Ant;
import parsers.Location;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class ServerWorldTest {
    private ServerWorld serverWorld;

    @Before
    public void setUp() {
        serverWorld = new ServerWorld("test" + random(), "172.30.249.39:8888");
    }

    @Test
    public void spawnOneAnt() {
        Ant ant = serverWorld.spawn();
        assertThat(ant.id, is(notNullValue()));
        assertThat(ant.location.x, is(0L));
        assertThat(ant.location.y, is(0L));
    }

    @Test
    public void spawnTwoAnts() {
        Ant first = serverWorld.spawn();
        Ant second = serverWorld.spawn();

        assertThat(second.id, is(not(equals(first.id))));
        assertThat(second.id, is(notNullValue()));
        assertThat(second.location.x, is(0L));
        assertThat(second.location.y, is(0L));
    }

    @Test
    public void doesNotMoveAnAnt() {
        Ant spawnedAnt = serverWorld.spawn();
        Ant ant = serverWorld.move(spawnedAnt, spawnedAnt.location);
        assertThat(ant.location.x, is(spawnedAnt.location.x));
        assertThat(ant.location.y, is(spawnedAnt.location.y));
    }

    @Test
    public void movesAnAnt() {
        Ant ant = serverWorld.move(serverWorld.spawn(), new Location(1, -2));
        assertThat(ant.location.x, is(1L));
        assertThat(ant.location.y, is(-2L));
    }

    @Test
    public void goesBackHome() {
        Ant ant = serverWorld.move(serverWorld.spawn(), new Location(1, 0));
        Ant antAtHome = serverWorld.move(ant, new Location(0, 0));
        assertThat(antAtHome.location.x, is(0L));
        assertThat(antAtHome.location.y, is(0L));
    }

    private String random() {
        return Double.toString(Math.random());
    }
}
