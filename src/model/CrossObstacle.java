package model;

import javafx.beans.property.SimpleFloatProperty;
import view.CircularObstacleView;
import view.CrossObstacleView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

public class CrossObstacle extends Obstacle{

    public CrossObstacle(float pos_x,float pos_y){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        setObstacleView(new CrossObstacleView(this));
        setBindings();
    }



    @Serial
    private void writeObject(ObjectOutputStream ous) throws IOException {
        ous.defaultWriteObject();
        ous.writeFloat(getPos_X());
        ous.writeFloat(getPos_Y());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException,IOException{
        ois.defaultReadObject();
        this.setPos_X(ois.readFloat());
        this.setPos_Y(ois.readFloat());
        setObstacleView(new CrossObstacleView(this));
        setBindings();
    }

    @Override
    public void setBindings() {
        this.pos_XProperty().bindBidirectional(this.getObstacleView().layoutXProperty());
        this.pos_YProperty().bindBidirectional(this.getObstacleView().layoutYProperty());
    }



    @Override
    public void handleCollision() {

    }
}
