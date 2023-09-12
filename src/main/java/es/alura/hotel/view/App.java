package es.alura.hotel.view;

import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

import es.alura.hotel.util.NavigationScene;

public class App extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    NavigationScene.to("login.fxml", "Hotel Alura - Ingresar");
  }

  public static void main(String[] args) {
    launch();
  }
}