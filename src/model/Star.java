package model;

import view.StarView;

public class Star extends Collectable{

    private StarView starView;

    private final String IMAGE_PATH = "resources/star.png";

    public Star(Obstacle o){
        this(300,0);
        this.findPosition(o);
    }

    public Star(float pos_x, float pos_y){
        this.setPos_X(pos_x);
        this.setPos_Y(pos_y);
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
        this.setPos_Y(o.getPos_Y());
    }
}
