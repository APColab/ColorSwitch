package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import view.ObstacleView;

public abstract class Obstacle implements Collidable {
    private FloatProperty pos_X;
    private FloatProperty pos_Y;
    private final float height = 200;
    private final float width = 200;
    private ObstacleView obstacleView;


    public float getPos_X() {
        return pos_X.get();
    }

    public FloatProperty pos_XProperty() {
        return pos_X;
    }

    public void setPos_X(float pos_X) {
        if(this.pos_X==null){
            this.pos_X = new SimpleFloatProperty(pos_X);
        }
        this.pos_X.set(pos_X);
    }

    public float getPos_Y() {
        return pos_Y.get();
    }

    public FloatProperty pos_YProperty() {
        return pos_Y;
    }

    public void setPos_Y(float pos_Y) {
        if(this.pos_Y==null){
            this.pos_Y = new SimpleFloatProperty(pos_Y);
        }
        this.pos_Y.set(pos_Y);
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public ObstacleView getObstacleView() {
        return obstacleView;
    }

    public void setObstacleView(ObstacleView obstacleView) {
        this.obstacleView = obstacleView;
    }

    public abstract void setBindings();
}
