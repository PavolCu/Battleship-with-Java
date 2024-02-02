package battleship;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private int length;
    private List<Coordinate> coordinates;

    public Ship(int length) {
        this.length = length;
        this.coordinates = new ArrayList<>();
    }

    public void addCoordinate(Coordinate coordinate) {
        coordinates.add(coordinate);
    }

    public int getLength() {
        return length;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }
}
