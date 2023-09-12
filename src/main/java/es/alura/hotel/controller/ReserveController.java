package es.alura.hotel.controller;

import es.alura.hotel.model.FormaDePago;
import es.alura.hotel.model.Reserva;
import es.alura.hotel.service.ReservaService;
import es.alura.hotel.util.AlertUtil;
import es.alura.hotel.util.NavigationScene;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class ReserveController implements Initializable {
  @FXML
  private ComboBox<String> inputFormaDePago;
  @FXML
  private TextField inputValor;
  @FXML
  private DatePicker inputFechaDeSalida;
  @FXML
  private DatePicker inputFechaDeEntrada;
  @FXML
  private Button buttonBack;
  @FXML
  private Button buttonNext;

  private Double initialValor = 300.00;

  private double reservaValor = initialValor;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    inputFormaDePago.getItems().addAll(FormaDePago.getAll());
    inputValor.setText("$ " + reservaValor);

    inputFechaDeEntrada.setOnAction(event -> dateDifference());
    inputFechaDeSalida.setOnAction((event) -> dateDifference());
  }

  private void dateDifference() {
    LocalDate date1 = inputFechaDeEntrada.getValue();
    LocalDate date2 = inputFechaDeSalida.getValue();
    if (date1 != null & date2 != null) {
      long dateDaysDifference = ChronoUnit.DAYS.between(date1, date2);
      reservaValor = (dateDaysDifference + 1) * initialValor;
      inputValor.setText("$ " + reservaValor);
    }
  }

  @FXML
  public void onButtonNext() throws IOException {
    if (!checkInputValues())
      AlertUtil.errorInput();
    else {

      Reserva reserva = new Reserva();

      reserva.setFechaDeEntrada(inputFechaDeEntrada.getValue());
      reserva.setFechaDeSalida(inputFechaDeSalida.getValue());
      reserva.setFormaDePago(inputFormaDePago.getValue());
      reserva.setValor(reservaValor);

      ReservaService.setReserva(reserva);
      ReservaService.RegistrarReserva(() -> {
        try {
          NavigationScene.to("register.fxml", "Hotel Alura - Registro");
          NavigationScene.removeScene(buttonNext);
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
  }

  public void onButtonBack() throws IOException {
    if (AlertUtil.goOut()) {
      NavigationScene.to("home.fxml", "Hotel Alura - Inicio");
      NavigationScene.removeScene(buttonBack);
    }
  }

  private boolean checkInputValues() {
    return inputFechaDeEntrada.getValue() != null && inputFechaDeSalida.getValue() != null
        && inputFormaDePago.getValue() != null;
  }
}
