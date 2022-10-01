package com.example.astronautgamebackend.GameService.Movers;

import com.example.astronautgamebackend.GameService.GeometricShapes.Point;

public class Evaluator {
    public static double eval(String eqn, int x){
        try {
            double result = 0;
            eqn = eqn.replaceAll("x", "" + x);
            String[] terms = eqn.split("\\+");
            for (int i = 0; i < terms.length; i++){
                double product = 1;
                String[] termSplitted = terms[i].split("\\*");
                for (int j = 0; j < termSplitted.length; j++) {
                    termSplitted[j] = termSplitted[j].replaceAll("\\(", "").replaceAll("\\)", "");
                    int k;
                    for (k = 0; k < termSplitted[j].length(); k++)
                        if(termSplitted[j].charAt(k) == '-') break;
                    if(k == termSplitted[j].length() || k == 0){
                        product = Double.parseDouble(termSplitted[j]);
                        if (Double.isNaN(product)) product = 0;
                        continue;
                    }
                    product *= (Double.parseDouble(termSplitted[j].substring(0, k)) -
                            Double.parseDouble(termSplitted[j].substring(k +1)));
                }
                result += product;
            }
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public static int getDistance(Point p1, Point p2){
        return (int)Math.sqrt((p2.getX() - p1.getX()) * (p2.getX() - p1.getX()) + (p2.getY() - p1.getY()) * (p2.getY() - p1.getY()));
    }
}
