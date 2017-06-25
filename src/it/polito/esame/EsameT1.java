package it.polito.esame;

import java.io.IOException;

import it.polito.esame.bean.Model;
import it.polito.esame.gui.ParoleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EsameT1 extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
		FXMLLoader loader = new FXMLLoader( this.getClass().getResource("gui/paroleT1.fxml")) ;
		loader.load() ;
		ParoleController controller = loader.getController();
		Model model = new Model();
		controller.setModel(model);
		
		Parent root = loader.getRoot() ;
		Scene scene = new Scene(root) ;
		primaryStage.setScene(scene) ;
		primaryStage.show() ;
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
