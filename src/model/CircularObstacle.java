package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import view.CircularObstacleView;
import view.CrossObstacleView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.ArrayList;

public class CircularObstacle extends Obstacle{
    private transient FloatProperty radius;

    public CircularObstacle(float pos_x, float pos_y){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.radius = new SimpleFloatProperty(getHeight()/2);
        setObstacleView(new CircularObstacleView(this));
        setBindings();
    }

    public FloatProperty radiusProperty() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius.set(radius);
    }

    public CircularObstacle() {
        this(200,100);
    }

    public float getRadius() {
        return radius.getValue();
    }


    @Override
    public void setBindings() {
        this.pos_XProperty().bindBidirectional(this.getObstacleView().layoutXProperty());
        this.pos_YProperty().bindBidirectional(this.getObstacleView().layoutYProperty());
        for(int i=0;i<4;i++){
            ((Arc)getObstacleView().getShapeList().get(i)).centerXProperty().bind(radius);
            ((Arc)getObstacleView().getShapeList().get(i)).centerYProperty().bind(radius);
            ((Arc)getObstacleView().getShapeList().get(i)).radiusXProperty().bind(radius);
            ((Arc)getObstacleView().getShapeList().get(i)).radiusYProperty().bind(radius);
        }
        this.getObstacleView().prefHeightProperty().bind(radius.multiply(2.0f));
        this.getObstacleView().prefWidthProperty().bind(radius.multiply(2.0f));
    }




    @Serial
    private void writeObject(ObjectOutputStream ous) throws IOException {
        ous.defaultWriteObject();
        ous.writeFloat(getPos_X());
        ous.writeFloat(getPos_Y());
        ous.writeFloat(getRadius());
        ous.writeDouble(getObstacleView().getTransition().getCurrentTime().toMillis());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException,IOException{
        ois.defaultReadObject();
        this.setPos_X(ois.readFloat());
        this.setPos_Y(ois.readFloat());
        this.radius = new SimpleFloatProperty(ois.readFloat());
        setObstacleView(new CircularObstacleView(this));
        setBindings();
        getObstacleView().getTransition().jumpTo(Duration.millis(ois.readDouble()));
    }


    @Override
    public void handleCollision() {
        System.out.println("Colliding with circular obstacle");
    }
}
