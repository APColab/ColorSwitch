package model;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;
import view.ColorSwitchView;
import view.StarView;

import java.util.ArrayList;

public class ColorSwitch extends Collectable{

    private ColorSwitchView colorSwitchView;

    private final String IMAGE_PATH = "resources/colorswitch.png";

    public ColorSwitch(Game game){
        this(game,285,0);
    }

    public ColorSwitch(Game game,Obstacle o){
        this(game,285,0);
        this.findPosition(o);
    }

    public ColorSwitch(Game game,float pos_x, float pos_y){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
        this.setGame(game);
        colorSwitchView = new ColorSwitchView(this);
        setbindings();
    }

    public ColorSwitchView getColorSwitchView() {
        return colorSwitchView;
    }

    public String getIMAGE_PATH() {
        return IMAGE_PATH;
    }

    @Override
    public void setbindings() {
        this.pos_XProperty().bindBidirectional(colorSwitchView.layoutXProperty());
        this.pos_YProperty().bindBidirectional(colorSwitchView.layoutYProperty());
    }

    @Override
    public ImageView getCollectableView() {
        return colorSwitchView;
    }

    @Override
    public void findPosition(Obstacle o) {
        this.setPos_Y(o.getPos_Y()-115);
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
            c = c || colorSwitchView.localToScene(colorSwitchView.getBoundsInLocal()).intersects(shape.localToScene(shape.getBoundsInLocal()));
        }
        return c;
    }

    @Override
    public void handleCollision() {

    }
}
