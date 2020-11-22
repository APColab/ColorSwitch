package view;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.Star;

public class StarView extends ImageView {
    private Star star;
    private Image image;
    RotateTransition rotateTransition;

    public StarView(Star s){
        this.star = s;
        this.setLayoutX(s.getPos_X());
        this.setLayoutY(s.getPos_Y());
        image = new Image(s.getIMAGE_PATH());
        this.setImage(image);
        this.setFitHeight(30);
        this.setPreserveRatio(true);
        this.setCache(true);

        rotateTransition = new RotateTransition(Duration.millis(800),this);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.setCycleCount(Animation.INDEFINITE);
        rotateTransition.setByAngle(360);
        rotateTransition.play();
    }


}
