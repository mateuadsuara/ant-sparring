package world;

import parsers.Ant;
import parsers.Location;

public interface World {
   Ant spawn();
   Ant move(Ant ant, Location location);
}
