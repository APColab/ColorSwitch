package model;


import view.GameView;

import java.util.ArrayList;
import java.util.HashMap;

public class Game{
    private ArrayList<Obstacle> obstacleList;
    private GameView gameView;
    private Ball ball;

    private final float HEIGHT = 800;
    private final float WIDTH = 600;

    public Game(){
        gameView = new GameView(this);
        obstacleList = new ArrayList<>();
        ball = new Ball(WIDTH/2,HEIGHT-2*ball.getRADIUS());
    }

    public void addObstacle(){
        obstacleList.add(new CircularObstacle());
    }


    public ArrayList<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public Ball getBall() {
        return ball;
    }
}