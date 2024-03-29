package com.example.astronautgamebackend.GameService.GeometricShapes;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void setCoordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
    public int getX() {return x;}
    public int getY() {return y;}

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }
}

