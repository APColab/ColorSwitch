package model;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.util.Duration;

public class CircularObstacle extends Obstacle{
    private float radius;
    private float lineWidth;
    private final Arc[] arcs;
    private RotateTransition rotator;
    public CircularObstacle(float pos_x, float pos_y, float r, float w){
        this.radius = r;
        this.lineWidth = w;

        arcs = new Arc[4];
        for(int i =0;i<4;i++){
            arcs[i] = new Arc(this.radius,this.radius,this.radius,this.radius,i*90,90);
            arcs[i].setType(ArcType.OPEN);
            arcs[i].setStrokeWidth(this.lineWidth);
            arcs[i].setFill(Color.TRANSPARENT);
        }

        arcs[0].setStroke(Color.RED);
        arcs[1].setStroke(Color.BLUE);
        arcs[2].setStroke(Color.GREEN);
        arcs[3].setStroke(Color.YELLOW);

        this.setPrefHeight(2*this.radius);
        this.setPrefWidth(2*this.radius);
        this.setLayoutX(pos_x);
        this.setLayoutY(pos_y);
        this.getChildren().addAll(arcs);

        rotator = new RotateTransition(Duration.millis(2500),this);
        rotator.setInterpolator(Interpolator.LINEAR);
        rotator.setByAngle(360);
        rotator.setCycleCount(Animation.INDEFINITE);
        rotator.play();
    }

    public CircularObstacle() {
        this(200,100,60.0f,10.0f);

    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        updatePosition();
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(float width) {
        this.lineWidth = width;
        updatePosition();
    }


    public void updatePosition(){
        for(int i=0;i<4;i++){
            arcs[i].setCenterX(this.radius);
            arcs[i].setCenterY(this.radius);
            arcs[i].setRadiusX(this.radius);
            arcs[i].setRadiusY(this.radius);
            arcs[i].setStrokeWidth(this.lineWidth);
        }
        this.setPrefWidth(2*this.radius);
        this.setPrefHeight(2*this.radius);
    }
}
