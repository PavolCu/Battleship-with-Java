package battleship;

public class Coordinate implements Comparable<Coordinate> {
    private int row;
    private int col;

    public Coordinate( int row, int col){
        this.row = row;
        this.col = col;
    }

    public static int calculateDistance (Coordinate start, Coordinate end){
        if (start.getRow() == end.getRow()) {
            return Math.abs(start.getCol() - end.getCol());
        } else if (start.getCol() == end.getCol()) {
            return Math.abs(start.getRow() - end.getRow());
        } else {
            return -1;
        }
    }

    public int getRow () {
        return row;
    }

    public int getCol () {
        return col;
    }
    @Override
    public int compareTo (Coordinate other){
        if (this.row != other.row) {
            return this.row - other.row;
        } else {
            return this.col - other.col;
        }
    }
}
