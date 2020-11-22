package menupages;

import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class PauseMenuView extends SubScene
{

    public PauseMenuView(PauseMenu pausem) {
        super(new AnchorPane(), 500, 200);
        {
            prefWidth(500);
            prefHeight(200);

            BackgroundImage bgimg = new BackgroundImage(new Image("/menupages/resources/pausebg.png", 500, 200, false, true),
                    BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

            AnchorPane pausepane = (AnchorPane)this.getRoot();
            pausepane.setBackground(new Background((bgimg)));

            setLayoutX(1024);
            setLayoutY(180);
        }
    }

    public void menuTransition()
    {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.4));
        translateTransition.setNode(this);
        translateTransition.setToX(-600);
        translateTransition.play();
    }
}
