package edu.gonzaga;

/**
 * 2D Coordinate class on using integers.
 * @param x X coordinate.
 * @param y Y coordinate.
 */
public record Coordinate(int x, int y) implements Comparable<Coordinate> {
    /**
     * Compares whether two coordinates are smaller, equal, or larger than each-other.
     * @param other The object to be compared to the current object.
     * @return 0 if the coordinates are equal, 1 if the current coordinate is larger, -1 if the current coordinate
     * is smaller.
     */
    @Override
    public int compareTo(Coordinate other) {
        if ((this.x == other.x()) && (this.y == other.y())) {
            return 0;
        }
        else {
            if ((this.x >= other.x()) && (this.y >= other.y())) {
                return 1;
            }
            else{
                return -1;
            }
        }
    }
}