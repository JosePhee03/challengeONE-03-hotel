package es.alura.hotel.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import es.alura.hotel.model.Huesped;
import es.alura.hotel.model.Nacionalidad;
import es.alura.hotel.service.HuespedService;
import es.alura.hotel.service.ReservaService;
import es.alura.hotel.util.AlertUtil;
import es.alura.hotel.util.NavigationScene;

public class RegisterController implements Initializable {
  @FXML
  private Button buttonSave;
  @FXML
  private Button buttonBack;
  @FXML
  private TextField inputNombre;
  @FXML
  private TextField inputApellido;
  @FXML
  private DatePicker inputFechaDeNacimiento;
  @FXML
  private ComboBox<String> inputNacionalidad;
  @FXML
  private TextField inputTelefono;
  @FXML
  private TextField inputReservaId;

  @Override
  public void initialize(URL arg0, ResourceBundle arg1) {
    inputNacionalidad.getItems().addAll(Nacionalidad.getAll());
    inputReservaId.setText(String.valueOf(ReservaService.getReservaId()));
  }

  @FXML
  public void onButtonBack() throws IOException {
    if (AlertUtil.goOut()) {
      NavigationScene.to("reserve.fxml", "Hotel Alura - Reservas");
      NavigationScene.removeScene(buttonBack);
    }
  }

  @FXML
  public void onButtonSave() throws IOException {
    if (!checkInputValues())
      AlertUtil.errorInput();
    else {

      Huesped huesped = new Huesped();

      huesped.setNombre(inputNombre.getText());
      huesped.setApellido(inputApellido.getText());
      huesped.setNacionalidad(inputNacionalidad.getValue());
      huesped.setFechaDeNacimiento(inputFechaDeNacimiento.getValue());
      huesped.setTelefono(Integer.valueOf(inputTelefono.getText()));
      huesped.setReserva(ReservaService.getReserva());

      HuespedService.setHuesped(huesped);
      HuespedService.RegistrarHuesped(() -> {
        try {
          NavigationScene.to("home.fxml", "Hotel Alura - Inicio");
          NavigationScene.removeScene(buttonSave);
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
  }

  private boolean checkInputValues() {
    return inputNombre.getText() != null && inputApellido.getText() != null && inputFechaDeNacimiento.getValue() != null
        && inputNacionalidad.getValue() != null && inputTelefono.getText() != null && inputReservaId.getText() != null;
  }
}
