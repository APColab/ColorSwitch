package model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import view.CircularObstacleView;
import view.CrossObstacleView;
import view.CustomObstacleView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.ArrayList;

public class CustomObstacle extends Obstacle{

    private transient ArrayList<Line> lines;

    public CustomObstacle(float pos_x, float pos_y,ArrayList<Line> lines){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.lines = new ArrayList<>();
        this.lines.addAll(lines);
        setObstacleView(new CustomObstacleView(this,lines));
        setBindings();
    }


    @Serial
    private void writeObject(ObjectOutputStream ous) throws IOException {
        ous.defaultWriteObject();
        ous.writeFloat(getPos_X());
        ous.writeFloat(getPos_Y());
        for(Line l:lines){
            ous.writeDouble(l.getStartX());
            ous.writeDouble(l.getStartY());
            ous.writeDouble(l.getEndX());
            ous.writeDouble(l.getEndY());
        }
        ous.writeDouble(getObstacleView().getTransition().getCurrentTime().toMillis());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException,IOException{
        ois.defaultReadObject();
        this.setPos_X(ois.readFloat());
        this.setPos_Y(ois.readFloat());
        lines = new ArrayList<>();
        for(int i=0;i<4;i++){
            Line line = new Line(ois.readDouble(),ois.readDouble(),ois.readDouble(),ois.readDouble());
            switch(i){
                case 0->{
                    line.setStroke(Color.RED);
                }
                case 1->{
                    line.setStroke(Color.GREEN);
                }
                case 2->{
                    line.setStroke(Color.BLUE);
                }
                case 3->{
                    line.setStroke(Color.YELLOW);
                }
            }
            lines.add(line);
        }
        setObstacleView(new CustomObstacleView(this,lines));
        setBindings();
        getObstacleView().getTransition().jumpTo(Duration.millis(ois.readDouble()));
    }

    @Override
    public void handleCollision() {

    }

    @Override
    public void setBindings() {
        this.pos_XProperty().bindBidirectional(this.getObstacleView().layoutXProperty());
        this.pos_YProperty().bindBidirectional(this.getObstacleView().layoutYProperty());
    }
}
