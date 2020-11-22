package model;


import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import view.GameView;

public class Game{
    private Ball ball;
    private GameView gameView;
    private final float HEIGHT = 800;
    private final float WIDTH = 600;
    private boolean isGameStarted;

    public Game(){
        initializeSprites();
        setBindings();
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
                    ball.getGravity().start();
                }
                ball.jump();
            }
        });
    }

    private void setBindings(){
        ball.speedProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                if(ball.getPos_Y()<=ball.getMaxHeight()+5){
                    if((float)t1<=0f){
                        gameView.getObstaclePane()[0].setLayoutY(gameView.getObstaclePane()[0].getLayoutY()-(float)t1);
                        gameView.getObstaclePane()[1].setLayoutY(gameView.getObstaclePane()[1].getLayoutY()-(float)t1);
                    }
                    if(gameView.getObstaclePane()[0].getLayoutY()>=HEIGHT){
                        gameView.getObstaclePane()[0].setLayoutY(gameView.getObstaclePane()[1].getLayoutY()-HEIGHT);
                    }
                    if(gameView.getObstaclePane()[1].getLayoutY()>=HEIGHT){
                        gameView.getObstaclePane()[1].setLayoutY(gameView.getObstaclePane()[0].getLayoutY()-HEIGHT);
                    }
                }
            }
        });
    }

    private float test(){
        System.out.println("here");
        return 30;
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
}