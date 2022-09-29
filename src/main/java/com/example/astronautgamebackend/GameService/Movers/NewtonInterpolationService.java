package com.example.astronautgamebackend.GameService.Movers;

import com.example.astronautgamebackend.GameService.GeometricShapes.Point;

import java.util.List;

public class NewtonInterpolationService {
    public static String generateEqn(List<Point> points){
        double[][] matrix = new double[points.size()][points.size() + 1];
        for (int i = 0; i < points.size(); i++) {
            matrix[i][0] = points.get(i).getX();
            matrix[i][1] = points.get(i).getY();
        }
        for (int i = 1; i < points.size(); i++)
            for (int j = 0; j < points.size() - i; j++)
                matrix[i + j][i + 1] = (matrix[i + j][i] - matrix[i + j - 1][i]) / (matrix[i + j][0] - matrix[j][0]);
        String eqn = "";
        for (int i = 0; i < points.size(); i++) {
            eqn += "(" + matrix[i][i + 1] + ")";
            for (int j = i; j > 0 ; j--) eqn += "*(x-" + matrix[j - 1][0] + ")";
            if (i < points.size() - 1) eqn += "+";
        }
        return eqn;
    }
}
