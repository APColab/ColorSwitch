package view;


import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import model.RectangularObstacle;

public class RectangularObstacleView extends Pane {
    private final RectangularObstacle rectangularObstacle;
    private final RotateTransition rt;

    public RectangularObstacleView(RectangularObstacle ro){
        rectangularObstacle = ro;
        this.setPrefHeight(200);
        this.setPrefWidth(200);
        BorderStroke bs = new BorderStroke(Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW, BorderStrokeStyle.SOLID,BorderStrokeStyle.SOLID,BorderStrokeStyle.SOLID,BorderStrokeStyle.SOLID, CornerRadii.EMPTY,new BorderWidths(10), Insets.EMPTY);
        Border b = new Border(bs);
        this.setBorder(b);

        rt = new RotateTransition(Duration.millis(3000),this);
        rt.setCycleCount(Animation.INDEFINITE);
        rt.setByAngle(360);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }
}
