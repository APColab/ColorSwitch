package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import view.BallView;


public class Ball extends Circle implements Collidable {
    private FloatProperty pos_X;
    private FloatProperty pos_Y;
    private final float RADIUS = 50 ;
    private BallView ballView;
    private Color ballColor;

    public Ball(){
        this(100,200);
    }

    public Ball(float pos_X, float pos_Y) {
        this.pos_X = new SimpleFloatProperty(pos_X);
        this.pos_Y = new SimpleFloatProperty(pos_Y);
        ballView = new BallView(this);
        ballColor = Color.RED;
        setBindings();
    }

    private void setBindings() {
        pos_X.bindBidirectional(ballView.centerXProperty());
        pos_Y.bindBidirectional(ballView.centerYProperty());
    }

    public float getPos_X() {
        return pos_X.getValue();
    }

    public void setPos_X(float pos_X) {
        this.pos_X.setValue(pos_X);
    }

    public float getPos_Y() {
        return pos_Y.getValue();
    }

    public void setPos_Y(float pos_Y) {
        this.pos_Y.setValue(pos_Y);
    }

    public float getRADIUS() {
        return RADIUS;
    }

    public BallView getBallView() {
        return ballView;
    }

    public Color getBallColor() {
        return ballColor;
    }

    public void setBallColor(Color color) {
        this.ballColor = color;
        ballView.setColor(color);
    }
}
