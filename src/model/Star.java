package model;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import view.CrossObstacleView;
import view.StarView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.ArrayList;

public class Star extends Collectable{

    private transient StarView starView;

    private final String IMAGE_PATH = "resources/star.png";

    public Star(Game game){
        this(game,285,0);
    }

    public Star(Game game,Obstacle o){
        this(game,285,0);
        this.findPosition(o);
    }

    public Star(Game game,float pos_x, float pos_y){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.setGame(game);
        starView = new StarView(this);
        setbindings();
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
        starView = new StarView(this);
        setbindings();
    }


    public String getIMAGE_PATH() {
        return IMAGE_PATH;
    }

    public StarView getStarView() {
        return starView;
    }

    @Override
    public void setbindings() {
        this.pos_XProperty().bindBidirectional(starView.layoutXProperty());
        this.pos_YProperty().bindBidirectional(starView.layoutYProperty());
    }

    @Override
    public ImageView getCollectableView() {
        return starView;
    }

    @Override
    public void findPosition(Obstacle o) {
        this.setPos_Y(o.getPos_Y()+85);
    }

    @Override
    public ArrayList<Shape> getCollidables() {
        return null;
    }

    @Override
    public boolean isColliding(Collidable collidable) {
        ArrayList<Shape> shapeList = collidable.getCollidables();
        boolean c = false;
        for(Shape shape:shapeList){
            c = c || starView.localToScene(starView.getBoundsInLocal()).intersects(shape.localToScene(shape.getBoundsInLocal()));
        }
        return c;
    }

    @Override
    public void handleCollision() {
        getGame().setScore(getGame().getScore()+1);
    }
}
