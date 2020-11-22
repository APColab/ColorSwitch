package model;

import javafx.animation.*;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import view.BallView;


public class Ball extends Circle implements Collidable {
    private FloatProperty pos_X;
    private FloatProperty pos_Y;
    private final float RADIUS = 20 ;
    private BallView ballView;
    private Color ballColor;
    private AnimationTimer gravity;
    private FloatProperty speed;
    private FloatProperty maxHeight;


    public Ball(){
        this(100,200);
    }

    public Ball(float pos_X, float pos_Y) {
        this.pos_X = new SimpleFloatProperty(pos_X);
        this.pos_Y = new SimpleFloatProperty(pos_Y);
        ballView = new BallView(this);
        ballColor = Color.RED;
        maxHeight = new SimpleFloatProperty(400);
        speed = new SimpleFloatProperty(0);
        setBindings();
        gravity = new Gravity();
    }

    private void setBindings() {
        pos_X.bindBidirectional(ballView.layoutXProperty());
        pos_Y.bindBidirectional(ballView.layoutYProperty());
    }

    class Gravity extends AnimationTimer{
        long startTime = -1;
        @Override
        public void handle(long l) {
            if(startTime==-1){
                startTime = l;
            }
            speed.setValue(speed.getValue()+0.4f);
            if(getPos_Y()<=maxHeight.getValue()){
                if(speed.getValue()<0){

                }
                else{
                    setPos_Y(getPos_Y() + speed.getValue());
                }
            }
            else{
                setPos_Y(getPos_Y()+speed.getValue());
            }

        }
    }

    public void jump(){
        speed.setValue(-10f);
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

    public FloatProperty pos_XProperty() {
        return pos_X;
    }

    public FloatProperty pos_YProperty() {
        return pos_Y;
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

    public AnimationTimer getGravity() {
        return gravity;
    }

    public float getSpeed() {
        return speed.get();
    }

    public FloatProperty speedProperty() {
        return speed;
    }

    public float getMaxHeight() {
        return maxHeight.get();
    }

    public FloatProperty maxHeightProperty() {
        return maxHeight;
    }

    public void setBallColor(Color color) {
        this.ballColor = color;
        ballView.setColor(color);
    }
}
