import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.util.Duration;
import view.CircularObstacleView;

public class Test extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage testStage){
        testStage = new Stage();
        AnchorPane testPane = new AnchorPane();
        Scene testScene = new Scene(testPane,600,800);
        CircularObstacleView c = new CircularObstacleView();
        testPane.getChildren().add(c);
        testStage.setScene(testScene);
        testStage.show();
    }
}
