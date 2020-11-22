import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.util.Duration;
import menupages.MainMenu;
import menupages.MainMenuView;
import model.Ball;
import model.CircularObstacle;
import model.ColorSwitch;
import model.Game;
import view.CircularObstacleView;
import view.GameView;

public class Test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage testStage){
        testStage = new Stage();
        Pane testPane = new Pane();
        Scene testScene = new Scene(testPane,600,800);
        CircularObstacle c = new CircularObstacle();
        c.setPos_X(400);
        c.setRadius(100);
        Ball b = new Ball();
        b.setPos_X(90);
        b.setPos_Y(70);
        testStage.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()== KeyCode.SPACE){
                    b.jump();
                }
            }
        });
        ColorSwitch cs = new ColorSwitch(c);
        testPane.getChildren().add(c.getCircularObstacleView());
        testPane.getChildren().add(b.getBallView());
        testPane.getChildren().add(cs.getColorSwitchView());
        testStage.setScene(testScene);
        //testStage.show();
        //Game g = new Game();
        //g.gameView.getGameStage().show();
         MainMenu mm = new MainMenu();;
    }
}
