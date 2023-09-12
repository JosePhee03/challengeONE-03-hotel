package es.alura.hotel.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import es.alura.hotel.util.AlertUtil;
import es.alura.hotel.util.NavigationScene;

public class HomeController implements Initializable {
  @FXML
  private Button buttonSignOut;
  @FXML
  private Button buttonSearch;
  @FXML
  private Button buttonReserve;

  @FXML
  private Label labelDateNow;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    labelDateNow.setText("Hoy es: " + LocalDate.now().toString());
  }

  public void onButtonReserve() throws IOException {
    NavigationScene.to("reserve.fxml", "Hotel Alura - Reservas");
    NavigationScene.removeScene(buttonReserve);
  }

  public void onButtonSearch() throws IOException {
    NavigationScene.to("search.fxml", "Hotel Alura - Búsqueda");
    NavigationScene.removeScene(buttonSearch);
  }

  public void onButtonSignOut() throws IOException {
    if (AlertUtil.signOut()) {
      NavigationScene.to("login.fxml", "Hotel Alura - Iniciar Sesión");
      NavigationScene.removeScene(buttonSignOut);
    }
  }

}
