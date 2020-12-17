package model;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import view.DoubleCircleObstacleView;
import view.TriangleObstacleView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;

public class TriangleObstacle extends Obstacle{

    private transient FloatProperty centerToVertex;
    private transient Color triangleColor;

    public TriangleObstacle(float pos_x, float pos_y, Color c){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.centerToVertex = new SimpleFloatProperty(this.getHeight()*0.707f);
        this.triangleColor = c;
        setObstacleView(new TriangleObstacleView(this,c));
        setBindings();
    }

    public float getCenterToVertex() {
        return centerToVertex.get();
    }

    public FloatProperty centerToVertexProperty() {
        return centerToVertex;
    }

    public void setCenterToVertex(float centerToVertex) {
        this.centerToVertex.set(centerToVertex);
    }

    @Serial
    private void writeObject(ObjectOutputStream ous) throws IOException {
        ous.defaultWriteObject();
        ous.writeFloat(getPos_X());
        ous.writeFloat(getPos_Y());
        ous.writeFloat(getCenterToVertex());
        ous.writeDouble(triangleColor.getRed());
        ous.writeDouble(triangleColor.getGreen());
        ous.writeDouble(triangleColor.getBlue());
        ous.writeDouble(triangleColor.getOpacity());
        ous.writeDouble(getObstacleView().getTransition().getCurrentTime().toMillis());
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException,IOException{
        ois.defaultReadObject();
        this.setPos_X(ois.readFloat());
        this.setPos_Y(ois.readFloat());
        this.centerToVertex = new SimpleFloatProperty(ois.readFloat());
        this.triangleColor = Color.color(ois.readDouble(),ois.readDouble(),ois.readDouble(),ois.readDouble());
        setObstacleView(new TriangleObstacleView(this,triangleColor));
        setBindings();
        getObstacleView().getTransition().jumpTo(Duration.millis(ois.readDouble()));
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
