package es.alura.hotel.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

import es.alura.hotel.util.NavigationScene;

public class LoginController {

  @FXML
  private Button buttonLogin;

  @FXML
  private TextField inputTextField;

  @FXML
  private PasswordField inputPasswordField;

  @FXML
  public void onButtonLogin() throws IOException {
    if (validateInputs()) {
      NavigationScene.to("home.fxml", "Hotel Alura - Inicio");
      NavigationScene.removeScene(buttonLogin);
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Usuario incorrecto");
      alert.setContentText("Presiona ACEPTAR para continuar.");
      alert.showAndWait();
    }
  }

  private boolean validateInputs() {
    return inputTextField.getText().equals("admin") && inputPasswordField.getText().equals("admin");
  }
}