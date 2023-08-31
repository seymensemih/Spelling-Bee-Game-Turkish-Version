package midtermproject.visualmidterm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("menudesign.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("SpellingBee Game");
        stage.setScene(scene);
        stage.show();
    }

}
