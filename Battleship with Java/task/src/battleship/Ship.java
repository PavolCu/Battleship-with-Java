package battleship;

import java.util.ArrayList;
import java.util.List;

public class Ship {

    private String name;
    private int length;
    private List<Coordinate> coordinates;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.coordinates = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }


    public void addCoordinate(Coordinate coordinate) {
        coordinates.add(coordinate);
    }

    public boolean placeShip(Board board, Coordinate start, Coordinate end) {
        int distance = Coordinate.calculateDistance(start, end) + 1;
        if(distance != this.length) {
            return false;
        }

        if(!board.isPlacementValid(start, end, this.length)) {
            return false;
        }

        if(start.getRow() == end.getRow()) {
            if(board.placeHorizontally(this, start, end)) {
                for(int i = Math.min(start.getCol(), end.getCol()); i <= Math.max(start.getCol(), end.getCol()); i++) {
                    this.addCoordinate(new Coordinate(start.getRow(), i));
                }
                return true;
            }
        } else {
            if(board.placeVertically(this, start, end)) {
                for(int i = Math.min(start.getRow(), end.getRow()); i <= Math.max(start.getRow(), end.getRow()); i++) {
                    this.addCoordinate(new Coordinate(i, start.getCol()));
                }
                return true;
            }
        }

        return false;
    }
}
