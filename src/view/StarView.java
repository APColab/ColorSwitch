package view;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.Star;

public class StarView extends ImageView {
    private Star star;
    private Image image;
    TranslateTransition translateTransition;

    public StarView(Star s){
        this.star = s;
        this.setLayoutX(s.getPos_X());
        this.setLayoutY(s.getPos_Y());
        Glow glow = new Glow();
        image = new Image(s.getIMAGE_PATH());
        glow.setLevel(0.3);
        setEffect(glow);
        this.setImage(image);
        this.setFitHeight(star.getHeight());
        this.setPreserveRatio(true);
        this.setCache(true);

        translateTransition = new TranslateTransition(Duration.millis(800),this);
        translateTransition.setInterpolator(Interpolator.EASE_OUT);
        translateTransition.setCycleCount(Animation.INDEFINITE);
        translateTransition.setAutoReverse(true);
        translateTransition.setByY(8);
        translateTransition.play();
    }


}
