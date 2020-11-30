package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem newCorretor;
	@FXML
	private MenuItem verEscala;
	
	
	public void onItemMenuNewCorretorAction() {
		loadView("/gui/CadCorretor.fxml");
	}

	public void onitemMenuVerEscala() {
		Alerts.showAlert("IO Exception", "Error TESTE!!!!!!!", "ErroTeste", AlertType.ERROR);
	}
	
	private synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox = loader.load();

			Scene mainScene = Main.getMainScene();
			
			VBox vBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = vBox.getChildren().get(0);
			vBox.getChildren().clear();
			vBox.getChildren().add(mainMenu);
			vBox.getChildren().addAll(newVbox.getChildren());
			
			
		} catch (IOException e) {
			
			Alerts.showAlert("IO Exception", "Error loading page", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
	}
	
	
}
