package model;


import javafx.animation.AnimationTimer;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import javafx.util.Duration;
import menupages.*;

import javafx.util.converter.NumberStringConverter;

import view.CircularObstacleView;
import view.GameView;
import view.ObstacleView;

import java.io.*;
import java.util.*;

public class Game implements Serializable {
    private Ball ball;
    private transient GameView gameView;
    private final float HEIGHT = 800;
    private final float WIDTH = 600;
    private transient GameLoop gameLoop;
    private transient LongProperty score;
    private transient GameState GAME_STATE;
    private List<Obstacle> obstacleList;
    private List<Collectable> collectableList;
    private transient CollectedStars collectedStars;
    private int numberOfRevivals;
    private UUID gameID;

    public Game(){
        gameLoop = new GameLoop();
        obstacleList = new ArrayList<>();
        collectableList = new ArrayList<>();
        score = new SimpleLongProperty(0);
        GAME_STATE = GameState.GAME_NOTSTARTED;
        collectedStars = new CollectedStars();
        this.numberOfRevivals = 0;
        gameID = UUID.randomUUID();
        initializeSprites();
        addEventHandlers();
        setBindings();
        initialiseObstacles();
    }


    @Serial
    private void writeObject(ObjectOutputStream ous) throws IOException {
        ous.defaultWriteObject();
        ous.writeLong(score.getValue());
        HashMap<Obstacle,Integer> hashMap = new HashMap<>();
        HashMap<Collectable,Integer> hashMapC = new HashMap<>();
        for(Obstacle o:obstacleList){
            if(gameView.getObstaclePane()[0].getChildren().contains(o.getObstacleView())){
                hashMap.put(o,0);
            }else{
                hashMap.put(o,1);
            }
        }
        for(Collectable c:collectableList){
            if(gameView.getObstaclePane()[0].getChildren().contains(c.getCollectableView())){
                hashMapC.put(c,0);
            }else{
                hashMapC.put(c,1);
            }
        }
        ous.writeObject(hashMap);
        ous.writeObject(hashMapC);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException,IOException{
        ois.defaultReadObject();
        gameLoop = new GameLoop();
        score = new SimpleLongProperty(ois.readLong());
        GAME_STATE = GameState.GAME_NOTSTARTED;
        collectedStars = new CollectedStars();
        gameView = new GameView(this);
        addEventHandlers();
        setBindings();
        ball.setPos_Y(HEIGHT-3*ball.getRADIUS());
        HashMap<Obstacle,Integer> hashMap = (HashMap<Obstacle, Integer>) ois.readObject();
        for(Obstacle o:obstacleList){
            gameView.getObstaclePane()[hashMap.get(o)].getChildren().add(o.getObstacleView());
        }
        HashMap<Collectable,Integer> hashMapC = (HashMap<Collectable, Integer>) ois.readObject();
        for(Collectable c:collectableList){
            gameView.getObstaclePane()[hashMapC.get(c)].getChildren().add(c.getCollectableView());
        }
    }



    public void exitGame() throws IOException {
        File folder = new File("./src/savedGames");
        ArrayList<File> saveGameList = new ArrayList<>(Arrays.asList(folder.listFiles()));
        for(File saveGame:saveGameList){
            if(saveGame.getName().equals(gameID.toString())){
                saveGame.delete();
            }
        }
        collectedStars.setTotalStars(collectedStars.getTotalStars()+getScore());
        collectedStars.saveStars();
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
                if(keyEvent.getCode()== KeyCode.SPACE) {
                    if (GAME_STATE==GameState.GAME_NOTSTARTED) {
                        GAME_STATE = GameState.GAME_RUNNING;
                        gameLoop.start();
                    }
                    ball.goUp();
                }else if(keyEvent.getCode()==KeyCode.S){
                    try {
                        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./game.sav"));
                        sav(oos);
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void sav(ObjectOutputStream oos){
        try {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initialiseObstacles()
    {
        addObstacles(0,0,false);
        addObstacles(0,1,false);
        addObstacles(1,2,false);
        addObstacles(1,3,false);
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

    private void addObstacles(int obstaclePaneIndex, int index,boolean flag)
    {
        Random rand = new Random();
        int n = rand.nextInt(5);
        Obstacle o;
        switch (n) {
            case 0 -> {
                o = new DoubleCircleObstacle(0, 0, ColorIterator.nextN(ball.getBallColor(),index));
                o.setPos_X(WIDTH / 2 - o.getWidth() / 2);
            }
            case 1 -> {
                o = new TriangleObstacle(0, 0, ColorIterator.nextN(ball.getBallColor(),index));
                o.setPos_X(WIDTH / 2 - ((TriangleObstacle) o).getCenterToVertex() + 55);
            }
            case 2 -> {
                o = new RectangularObstacle();
                o.setPos_X(WIDTH / 2 - o.getWidth() / 2);
            }
            case 3 -> {
                o = new CrossObstacle(0,0);
                o.setPos_X(WIDTH/2 -15);
            }
            default -> {
                o = new CircularObstacle(0,0);
                o.setPos_X(WIDTH / 2 - ((CircularObstacle) o).getRadius());
            }
        }
        if (!flag) {
            if (index % 2 == 0) {
                o.setPos_Y(600);
            } else {
                o.setPos_Y(200);
            }
        }else{
            if (index % 2 != 0) {
                o.setPos_Y(600);
            } else {
                o.setPos_Y(200);
            }
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
                    addObstacles(0,1,true);
                    addObstacles(0,2,true);
                }
                if(gameView.getObstaclePane()[1].getLayoutY()>=HEIGHT){
                    //addObstacles(1);
                    gameView.getObstaclePane()[1].setLayoutY(gameView.getObstaclePane()[0].getLayoutY()-HEIGHT);
                    removeObstacles(1);
                    removeCollectables(1);
                    //if(getObstacleList().size()<2)
                    addObstacles(1,1,true);
                    addObstacles(1,2,true);

                }
            }
        }else{
            ball.goDown();
        }
    }

    private void initializeSprites(){
        this.ball = new Ball();
        ball.setPos_X(WIDTH/2);
        ball.setPos_Y(HEIGHT-3*ball.getRADIUS());
        gameView = new GameView(this);
    }

    private void checkCollisions(){
        for(Obstacle o:obstacleList){
            if(o.isColliding(ball)){
                setGAME_STATE(GameState.GAME_OVER);
                gameLoop.stop();
                OnCollisionMenu onc = new OnCollisionMenu(this);
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

        if(getBall().getPos_Y()>=HEIGHT)
        {
            setGAME_STATE(GameState.GAME_OVER);
            gameLoop.stop();
            OnCollisionMenu onc = new OnCollisionMenu(this);
        }
    }


    public void pause() {
        PauseMenu p = new PauseMenu(this);
        p.setMenu(p);
        gameLoop.stop();
        Iterator<Obstacle> iter = obstacleList.listIterator();
        while(iter.hasNext())
        {
            Obstacle o = iter.next();
            o.getObstacleView().getTransition().pause();
            if(o.getClass().getName().equals("model.DoubleCircleObstacle"))
            {
                ((DoubleCircleObstacle)(o)).getOuter().getObstacleView().getTransition().pause();
            }
        }
        setGAME_STATE(GameState.GAME_PAUSED);
    }

    public void resumeGame()
    {
        for(int i=0;i<500000000;i++);
        getGameLoop().start();
        setGAME_STATE(GameState.GAME_RUNNING);
        Iterator<Obstacle> iter = obstacleList.listIterator();
        while(iter.hasNext())
        {
            (iter.next()).getObstacleView().getTransition().play();
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

    public GameState getGAME_STATE() {
        return GAME_STATE;
    }

    public void setGAME_STATE(GameState GAME_STATE) {
        this.GAME_STATE = GAME_STATE;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    public CollectedStars getCollectedStars() {
        return collectedStars;
    }

    public UUID getGameID() {
        return gameID;
    }

    public int getNumberOfRevivals() {
        return numberOfRevivals;
    }

    public void setNumberOfRevivals(int numberOfRevivals) {
        this.numberOfRevivals = numberOfRevivals;
    }

    public float getHEIGHT() {
        return HEIGHT;
    }

    public float getWIDTH() {
        return WIDTH;
    }
}