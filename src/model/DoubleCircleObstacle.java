package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import view.CrossObstacleView;
import view.DoubleCircleObstacleView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

public class DoubleCircleObstacle extends Obstacle{
    private CircularObstacle outer;
    private transient FloatProperty innerRadius;
    private transient Color doubleCircleColor;

    public DoubleCircleObstacle(float pos_x, float pos_y, Color color){
        this.outer = new CircularObstacle(pos_x,pos_y);
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.doubleCircleColor = color;
        this.innerRadius = new SimpleFloatProperty(outer.getRadius()-15);
        setObstacleView(new DoubleCircleObstacleView(this, color));
        setBindings();
    }

    public CircularObstacle getOuter() {
        return outer;
    }

    public float getInnerRadius() {
        return innerRadius.get();
    }

    public FloatProperty innerRadiusProperty() {
        return innerRadius;
    }

    public void setInnerRadius(float innerRadius) {
        this.innerRadius.set(innerRadius);
    }

    @Serial
    private void writeObject(ObjectOutputStream ous) throws IOException {
        ous.defaultWriteObject();
        ous.writeFloat(getPos_X());
        ous.writeFloat(getPos_Y());
        ous.writeFloat(getInnerRadius());
        ous.writeDouble(doubleCircleColor.getRed());
        ous.writeDouble(doubleCircleColor.getGreen());
        ous.writeDouble(doubleCircleColor.getBlue());
        ous.writeDouble(doubleCircleColor.getOpacity());
        ous.writeDouble(getObstacleView().getTransition().getCurrentTime().toMillis());
        ous.writeDouble(outer.getObstacleView().getTransition().getCurrentTime().toMillis());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException,IOException{
        ois.defaultReadObject();
        this.setPos_X(ois.readFloat());
        this.setPos_Y(ois.readFloat());
        this.innerRadius = new SimpleFloatProperty(ois.readFloat());
        this.doubleCircleColor = Color.color(ois.readDouble(),ois.readDouble(),ois.readDouble(),ois.readDouble());
        setObstacleView(new DoubleCircleObstacleView(this,doubleCircleColor));
        setBindings();
        getObstacleView().getTransition().jumpTo(Duration.millis(ois.readDouble()));
        outer.getObstacleView().getTransition().jumpTo(Duration.millis(ois.readDouble()));
    }

    @Override
    public void setBindings() {
        this.pos_XProperty().bindBidirectional(this.getObstacleView().layoutXProperty());
        this.pos_YProperty().bindBidirectional(this.getObstacleView().layoutYProperty());
    }

    @Override
    public boolean isColliding(Collidable collidable) {
        return super.isColliding(collidable) || outer.isColliding(collidable);
    }

    @Override
    public void handleCollision() {

    }
}
