package model;


import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import view.GameView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

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
        initialiseObstacles();
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

    private void initialiseObstacles()
    {
        CircularObstacle c4 = new CircularObstacle();
        c4.setPos_X(WIDTH/2-c4.getRadius());
        c4.setPos_Y(c4.getRadius());
        gameView.getObstaclePane()[0].getChildren().add(c4.getObstacleView());
        Star s4 = new Star(this,c4);
        gameView.getObstaclePane()[0].getChildren().add(s4.getStarView());
        ColorSwitch cs4 = new ColorSwitch(this,c4);
        gameView.getObstaclePane()[0].getChildren().add(cs4.getColorSwitchView());
        this.getObstacleList().add(c4);
        this.getCollectableList().add(s4);
        this.getCollectableList().add(cs4);

        CircularObstacle c5 = new CircularObstacle();
        c4.setPos_X(WIDTH/2-c5.getRadius());
        c4.setPos_Y(c5.getRadius());
        gameView.getObstaclePane()[1].getChildren().add(c5.getObstacleView());
        Star s5 = new Star(this,c5);
        gameView.getObstaclePane()[1].getChildren().add(s5.getStarView());
        ColorSwitch cs5 = new ColorSwitch(this,c5);
        gameView.getObstaclePane()[1].getChildren().add(cs5.getColorSwitchView());
        this.getObstacleList().add(c5);
        this.getCollectableList().add(s5);
        this.getCollectableList().add(cs5);

        getObstacleList().add(c4);
        getObstacleList().add(c5);
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

    private void addObstacles(int index)
    {
        Random rand = new Random();
        int n = rand.nextInt(6);
        switch(n)
        {
            case 0: //double c
                DoubleCircleObstacle c = new DoubleCircleObstacle(0,0, Color.BLUE);
                c.setPos_X(WIDTH / 2-c.getWidth()/2);
                c.setPos_Y(c.getHeight());
                gameView.getObstaclePane()[index].getChildren().add(c.getObstacleView());
                Star s = new Star(this,c);
                gameView.getObstaclePane()[index].getChildren().add(s.getStarView());
                ColorSwitch cs = new ColorSwitch(this,c);
                gameView.getObstaclePane()[index].getChildren().add(cs.getColorSwitchView());
                getObstacleList().add(c);
                getCollectableList().add(s);
                getCollectableList().add(cs);
                break;

            case 1:
                TriangleObstacle c1 = new TriangleObstacle(0,0, Color.GREEN);
                c1.setPos_X(WIDTH / 2-c1.getCenterToVertex()+30);
                c1.setPos_Y(3*c1.getCenterToVertex());
                gameView.getObstaclePane()[index].getChildren().add(c1.getObstacleView());
                Star s1 = new Star(this,c1);
                gameView.getObstaclePane()[index].getChildren().add(s1.getStarView());
                ColorSwitch cs1 = new ColorSwitch(this,c1);
                gameView.getObstaclePane()[index].getChildren().add(cs1.getColorSwitchView());
                getObstacleList().add(c1);
                getCollectableList().add(s1);
                getCollectableList().add(cs1);
                break;

            case 2:
                RectangularObstacle c2 = new RectangularObstacle();
                c2.setPos_X(WIDTH / 2-c2.getWidth()/2);
                c2.setPos_Y(c2.getHeight());
                gameView.getObstaclePane()[index].getChildren().add(c2.getObstacleView());
                Star s2 = new Star(this,c2);
                gameView.getObstaclePane()[index].getChildren().add(s2.getStarView());
                ColorSwitch cs2 = new ColorSwitch(this,c2);
                gameView.getObstaclePane()[index].getChildren().add(cs2.getColorSwitchView());
                getObstacleList().add(c2);
                getCollectableList().add(s2);
                getCollectableList().add(cs2);
                break;

            case 3:
                CrossObstacle c3 = new CrossObstacle(WIDTH/2-10, HEIGHT/2);
                gameView.getObstaclePane()[index].getChildren().add(c3.getObstacleView());
                Star s3 = new Star(this,c3);
                gameView.getObstaclePane()[index].getChildren().add(s3.getStarView());
                ColorSwitch cs3 = new ColorSwitch(this,c3);
                gameView.getObstaclePane()[index].getChildren().add(cs3.getColorSwitchView());
                getObstacleList().add(c3);
                getCollectableList().add(s3);
                getCollectableList().add(cs3);
                break;

            default:
                CircularObstacle c4 = new CircularObstacle();
                c4.setPos_X(WIDTH/2-c4.getRadius());
                c4.setPos_Y(c4.getRadius());
                gameView.getObstaclePane()[index].getChildren().add(c4.getObstacleView());
                Star s4 = new Star(this,c4);
                gameView.getObstaclePane()[index].getChildren().add(s4.getStarView());
                ColorSwitch cs4 = new ColorSwitch(this,c4);
                gameView.getObstaclePane()[index].getChildren().add(cs4.getColorSwitchView());
                this.getObstacleList().add(c4);
                this.getCollectableList().add(s4);
                this.getCollectableList().add(cs4);
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
                    //if(getObstacleList().size()<2)
                        addObstacles(0);
                }
                if(gameView.getObstaclePane()[1].getLayoutY()>=HEIGHT){
                    addObstacles(1);
                    gameView.getObstaclePane()[1].setLayoutY(gameView.getObstaclePane()[0].getLayoutY()-HEIGHT);
                    removeObstacles(1);
                    removeCollectables(1);
                    //if(getObstacleList().size()<2)
                        addObstacles(1);

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