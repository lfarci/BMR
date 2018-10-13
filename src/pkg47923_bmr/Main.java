package pkg47923_bmr;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pkg47923_bmr.view.ContentPane;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Basal Metabolic Rate Calculator");
        primaryStage.setMinWidth(500);
        primaryStage.setMinHeight(200);

        VBox root = new VBox();
        ContentPane cp = new ContentPane();
        
        root.getChildren().add(cp);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
