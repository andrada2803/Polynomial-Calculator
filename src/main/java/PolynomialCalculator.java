import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PolynomialCalculator extends Application {

    public static void main(String[] args){

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("/Calculator.fxml"));
        Parent root = myLoader.load();
        primaryStage.setTitle("Polynomial Calculator");
        Scene scene = new Scene(root, 600, 570);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

