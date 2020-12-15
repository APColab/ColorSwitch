package model;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import view.StarView;

import java.util.ArrayList;

public class Star extends Collectable{

    private StarView starView;

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

    }
}
