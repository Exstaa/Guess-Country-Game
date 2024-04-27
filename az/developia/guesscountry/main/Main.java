package az.developia.guesscountry.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	public static Stage mainStage;

	@Override
	public void start(Stage stage) {
		try {
			AnchorPane pane = FXMLLoader
					.load(getClass().getClassLoader().getResource("az/developia/guesscountry/view/index.fxml"));
			Scene scene = new Scene(pane);
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Guess a country");
			stage.show();
			mainStage = stage;
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}
}
