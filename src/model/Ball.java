package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import view.BallView;
import view.CrossObstacleView;

import java.io.*;
import java.util.ArrayList;


public class Ball extends Circle implements Collidable, Serializable {
    private transient FloatProperty pos_X;
    private transient FloatProperty pos_Y;
    private final float RADIUS = 12 ;
    private transient BallView ballView;
    private transient Color ballColor;
    private transient FloatProperty speed;
    private transient FloatProperty maxHeight;


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

    }

    private void setBindings() {
        pos_X.bindBidirectional(ballView.layoutXProperty());
        pos_Y.bindBidirectional(ballView.layoutYProperty());
    }



    @Serial
    private void writeObject(ObjectOutputStream ous) throws IOException {
        ous.defaultWriteObject();
        ous.writeFloat(getPos_X());
        ous.writeFloat(getPos_Y());
        ous.writeFloat(getSpeed());
        ous.writeFloat(getMaxHeight());
        ous.writeDouble(ballColor.getRed());
        ous.writeDouble(ballColor.getGreen());
        ous.writeDouble(ballColor.getBlue());
        ous.writeDouble(ballColor.getOpacity());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException,IOException{
        ois.defaultReadObject();
        pos_X = new SimpleFloatProperty(ois.readFloat());
        pos_Y = new SimpleFloatProperty(ois.readFloat());
        speed = new SimpleFloatProperty(ois.readFloat());
        maxHeight = new SimpleFloatProperty(ois.readFloat());
        ballColor = Color.color(ois.readDouble(),ois.readDouble(),ois.readDouble(),ois.readDouble());
        ballView = new BallView(this);
        setBallColor(ballColor);
        setBindings();
    }



    public void goUp(){
        speed.setValue(-8f);
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

    public void goDown(){
        setPos_Y(this.getPos_Y()+speed.getValue());
    }

    public void setBallColor(Color color) {
        this.ballColor = color;
        ballView.setColor(color);
    }


    @Override
    public ArrayList<Shape> getCollidables() {
        ArrayList<Shape> ar = new ArrayList<>();
        ar.add(ballView);
        return ar;
    }

    @Override
    public boolean isColliding(Collidable collidable) {
        ArrayList<Shape> ar = collidable.getCollidables();
        for(Shape shape:ar){
            Shape intersect = Shape.intersect(shape,ballView);
            if(intersect.getBoundsInLocal().getWidth() != -1){
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleCollision() {
        System.out.println("Collision Detected");
    }
}
