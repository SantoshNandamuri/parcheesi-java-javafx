package parcheesi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ParcheesiApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ParcheesiApplication.class.getResource("parcheesi-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 630);
        stage.setTitle("Parcheesi");
        stage.setScene(scene);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch();
    }
}