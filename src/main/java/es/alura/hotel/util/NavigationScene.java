package es.alura.hotel.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import es.alura.hotel.view.App;

public class NavigationScene {

  NavigationScene() {
  }

  public static void to(String nameResource, String title) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(nameResource));
    Scene scene = new Scene(fxmlLoader.load());
    Stage stage = new Stage();
    stage.setTitle(title);
    stage.setScene(scene);
    stage.show();
  }

  public static void removeScene(Button anchor) {
    Stage stageLogin = (Stage) anchor.getScene().getWindow();
    stageLogin.close();
  }

  public static void removeScene(Label anchor) {
    Stage stageLogin = (Stage) anchor.getScene().getWindow();
    stageLogin.close();
  }
}
