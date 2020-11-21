package view;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import model.Ball;

public class BallView extends Circle {
    private Ball ball;

    public BallView(Ball b){
        this.ball = b;
        this.setLayoutX(b.getPos_X());
        this.setLayoutY(b.getPos_Y());
        this.setColor(Color.RED);
        this.setRadius(ball.getRADIUS());

    }

    public void setColor(Color color) {
        Stop[] stops = new Stop[]{
                new Stop(0.0,Color.WHITE),
                new Stop(0.7,color)
        };
        this.setFill(new RadialGradient(360,0.0,0.5,0.5,0.7,true, CycleMethod.NO_CYCLE,stops));
    }
}
