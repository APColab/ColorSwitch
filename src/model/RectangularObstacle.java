package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import view.CrossObstacleView;
import view.RectangularObstacleView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

public class   RectangularObstacle extends Obstacle {
    private transient FloatProperty length;
    private transient FloatProperty breadth;

    public RectangularObstacle(float pos_x, float pos_y){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.length = new SimpleFloatProperty(getHeight());
        this.breadth = new SimpleFloatProperty(getWidth());
        setObstacleView(new RectangularObstacleView(this));
        setBindings();
    }

    public RectangularObstacle(){
        this(200,100);
    }




    @Override
    public void setBindings() {
        this.pos_XProperty().bindBidirectional(this.getObstacleView().layoutXProperty());
        this.pos_YProperty().bindBidirectional(this.getObstacleView().layoutYProperty());
    }

    @Serial
    private void writeObject(ObjectOutputStream ous) throws IOException {
        ous.defaultWriteObject();
        ous.writeFloat(getPos_X());
        ous.writeFloat(getPos_Y());
        ous.writeFloat(getLength());
        ous.writeFloat(getBreadth());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException,IOException{
        ois.defaultReadObject();
        this.setPos_X(ois.readFloat());
        this.setPos_Y(ois.readFloat());
        this.length = new SimpleFloatProperty(ois.readFloat());
        this.length = new SimpleFloatProperty(ois.readFloat());
        setObstacleView(new RectangularObstacleView(this));
        setBindings();
    }


    public float getLength() {
        return length.get();
    }

    public FloatProperty lengthProperty() {
        return length;
    }

    public void setLength(float length) {
        this.length.set(length);
    }

    public float getBreadth() {
        return breadth.get();
    }

    public FloatProperty breadthProperty() {
        return breadth;
    }

    public void setBreadth(float breadth) {
        this.breadth.set(breadth);
    }


    @Override
    public void handleCollision() {

    }
}
