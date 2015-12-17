import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
import parsers.Ant;
import parsers.Location;
import world.ServerWorld;
import world.World;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;

public class Main {
    static class CollectFood implements Runnable {
        private Ant ant;
        private World world;
        private Location destination;

        public CollectFood(World world, Ant ant, Location destination) {
            this.ant = ant;
            this.world = world;
            this.destination = destination;
        }

        @Override
        public void run() {
            while (true) {
                ant = world.move(ant, destination);
                ant = world.move(ant, new Location(0, 0));
            }
        }
    }

    public static void main(String[] args) {
        runN(5, new Location(-4, 4));
    }

    public static void runN(int amount, Location location) {
        ServerWorld serverWorld = new ServerWorld("mateu_rabea_priya", "172.30.249.28:8888");
        //ServerWorld serverWorld = new ServerWorld("ant" + random(), "172.30.249.39:8888");

        ExecutorService executor = Executors.newCachedThreadPool();

        for (int i = 0; i < amount; i++) {
            executor.execute(new CollectFood(serverWorld, serverWorld.spawn(), location));
        }
    }

    private static void spiral(ServerWorld serverWorld, Ant ant) {
        List<Location> locations = Arrays.asList(
                new Location(0, 1),
                new Location(1, 1),
                new Location(1, 0),
                new Location(1, -1),
                new Location(0, -1),
                new Location(-1, -1),
                new Location(-1, 0),
                new Location(-1, 1),

                new Location(-1, 2),
                new Location(0, 2),
                new Location(1, 2),
                new Location(2, 2),
                new Location(2, 1),
                new Location(2, 0),
                new Location(2,-1)
        );
        for (Location location: locations) {
            ant = serverWorld.move(ant, location);
        }
    }

    private static String random() {
        return Double.toString(Math.random());
    }
}
