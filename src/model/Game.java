package model;


import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import view.GameView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game{
    private Ball ball;
    private GameView gameView;
    private final float HEIGHT = 800;
    private final float WIDTH = 600;
    private boolean isGameStarted;
    private GameLoop gameLoop;
    private List<Obstacle> obstacleList;
    private List<Collectable> collectableList;

    public Game(){
        gameLoop = new GameLoop();
        obstacleList = new ArrayList<>();
        collectableList = new ArrayList<>();
        initializeSprites();
        addEventHandlers();
    }

    public GameView getGameView() {
        return gameView;
    }

    private void addEventHandlers() {
        gameView.getGameStage().addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(!isGameStarted){
                    isGameStarted = true;
                    gameLoop.start();
                }
                ball.goUp();
            }
        });
    }



    class GameLoop extends AnimationTimer {
        long startTime = -1;
        @Override
        public void handle(long l) {
            if(startTime==-1){
                startTime = l;
            }
            updatePositions();
        }
    }


    private void removeObstacles(int index){
        Iterator<Obstacle> itr = obstacleList.iterator();
        while (itr.hasNext()){
            Obstacle o = itr.next();
            if(gameView.getObstaclePane()[index].getChildren().contains(o.getObstacleView())){
                gameView.getObstaclePane()[index].getChildren().remove(o.getObstacleView());
                itr.remove();
            }
        }
    }

    private void removeCollectables(int index){
        Iterator<Collectable> itr = collectableList.iterator();
        while (itr.hasNext()){
            Collectable o = itr.next();
            if(gameView.getObstaclePane()[index].getChildren().contains(o.getCollectableView())){
                gameView.getObstaclePane()[index].getChildren().remove(o.getCollectableView());
                itr.remove();
            }
        }
    }

    private void updatePositions(){
        ball.speedProperty().setValue(ball.getSpeed()+0.4f);
        if(ball.getPos_Y()<=ball.getMaxHeight()){
            if(ball.getSpeed()>0){
                ball.goDown();
            }else{
                gameView.getObstaclePane()[0].setLayoutY(gameView.getObstaclePane()[0].getLayoutY()-ball.getSpeed());
                gameView.getObstaclePane()[1].setLayoutY(gameView.getObstaclePane()[1].getLayoutY()-ball.getSpeed());
                if(gameView.getObstaclePane()[0].getLayoutY()>=HEIGHT){
                    gameView.getObstaclePane()[0].setLayoutY(gameView.getObstaclePane()[1].getLayoutY()-HEIGHT);
                    removeObstacles(0);
                    removeCollectables(0);
                }
                if(gameView.getObstaclePane()[1].getLayoutY()>=HEIGHT){
                    gameView.getObstaclePane()[1].setLayoutY(gameView.getObstaclePane()[0].getLayoutY()-HEIGHT);
                    removeObstacles(1);
                    removeCollectables(1);
                }
            }
        }else{
            ball.goDown();
        }
    }
    private void initializeSprites(){
        isGameStarted = false;
        this.ball = new Ball();
        ball.setPos_X(WIDTH/2);
        ball.setPos_Y(HEIGHT-3*ball.getRADIUS());
        gameView = new GameView(this);
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public List<Obstacle> getObstacleList() {
        return obstacleList;
    }

    public List<Collectable> getCollectableList() {
        return collectableList;
    }
}