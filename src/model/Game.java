package model;


import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.util.converter.NumberStringConverter;
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
    private LongProperty score;
    private List<Obstacle> obstacleList;
    private List<Collectable> collectableList;

    public Game(){
        gameLoop = new GameLoop();
        obstacleList = new ArrayList<>();
        collectableList = new ArrayList<>();
        score = new SimpleLongProperty(0);
        initializeSprites();
        addEventHandlers();
        setBindings();
        initialiseObstacles();
    }

    private void setBindings() {
        gameView.getScoreView().textProperty().bindBidirectional(score,new NumberStringConverter());
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
        addObstacles(0,0);
        addObstacles(0,1);
        addObstacles(1,2);
        addObstacles(1,3);
    }



    private void addCollectables(Obstacle o,int index){
        Star s = new Star(this,o);
        ColorSwitch cs = new ColorSwitch(this,o,ColorIterator.nextN(ball.getBallColor(),index));
        for(int i=0;i<2;i++){
            if(gameView.getObstaclePane()[i].getChildren().contains(o.getObstacleView())){
                gameView.getObstaclePane()[i].getChildren().add(s.getStarView());
                gameView.getObstaclePane()[i].getChildren().add(cs.getColorSwitchView());
            }
        }
        collectableList.add(s);
        collectableList.add(cs);
    }



    class GameLoop extends AnimationTimer {
        long startTime = -1;
        @Override
        public void handle(long l) {
            if(startTime==-1){
                startTime = l;
            }
            updatePositions();
            checkCollisions();
        }
    }

    private void addObstacles(int obstaclePaneIndex, int index)
    {
        Random rand = new Random();
        int n = rand.nextInt(5);
        Obstacle o;
        switch (n) {
            case 0 -> {
                o = new DoubleCircleObstacle(0, 0, ColorIterator.nextN(ball.getBallColor(),index+1));
                o.setPos_X(WIDTH / 2 - o.getWidth() / 2);
            }
            case 1 -> {
                o = new TriangleObstacle(0, 0, ColorIterator.nextN(ball.getBallColor(),index+1));
                o.setPos_X(WIDTH / 2 - ((TriangleObstacle) o).getCenterToVertex() + 55);
            }
            case 2 -> {
                o = new RectangularObstacle();
                o.setPos_X(WIDTH / 2 - o.getWidth() / 2);
            }
            case 3 -> {
                o = new CrossObstacle(0,0);
                o.setPos_X(WIDTH/2 -10);
            }
            default -> {
                o = new CircularObstacle(0,0);
                o.setPos_X(WIDTH / 2 - ((CircularObstacle) o).getRadius());
            }
        }
        if(index%2==0){
            o.setPos_Y(600);
        }
        else{
            o.setPos_Y(200);
        }
        gameView.getObstaclePane()[obstaclePaneIndex].getChildren().add(o.getObstacleView());
        addCollectables(o,index+1);
        obstacleList.add(o);
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
                    addObstacles(0,2);
                    addObstacles(0,3);
                }
                if(gameView.getObstaclePane()[1].getLayoutY()>=HEIGHT){
                    //addObstacles(1);
                    gameView.getObstaclePane()[1].setLayoutY(gameView.getObstaclePane()[0].getLayoutY()-HEIGHT);
                    removeObstacles(1);
                    removeCollectables(1);
                    //if(getObstacleList().size()<2)
                    addObstacles(1,2);
                    addObstacles(1,3);

                   //addObstacles(1,0);

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

    private void checkCollisions(){
        for(Obstacle o:obstacleList){
            if(o.isColliding(ball)){
                o.handleCollision();
            }
        }
        Iterator<Collectable> itr = collectableList.iterator();
        while(itr.hasNext()){
            Collectable c = itr.next();
            if(c.isColliding(ball)){
                gameView.getObstaclePane()[0].getChildren().remove(c.getCollectableView());
                gameView.getObstaclePane()[1].getChildren().remove(c.getCollectableView());
                itr.remove();
                c.handleCollision();
            }
        }
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

    public long getScore() {
        return score.get();
    }

    public LongProperty scoreProperty() {
        return score;
    }

    public void setScore(long score) {
        this.score.set(score);
    }
}