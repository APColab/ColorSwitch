package view;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import model.ColorSwitch;

public class ColorSwitchView extends ImageView {
    private ColorSwitch colorSwitch;
    private Image image;
    TranslateTransition translateTransition;

    public ColorSwitchView(ColorSwitch c){
        this.colorSwitch = c;
        this.setLayoutX(c.getPos_X());
        this.setLayoutY(c.getPos_Y());
        image = new Image(c.getIMAGE_PATH());
        this.setImage(image);
        this.setFitHeight(colorSwitch.getHeight());
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
