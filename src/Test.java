import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.application.Application;
import model.Ball;
import model.CircularObstacle;
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
        Ball b = new Ball(100,100);
        testPane.getChildren().add(c);
        testStage.setScene(testScene);
        testStage.show();
    }
}
