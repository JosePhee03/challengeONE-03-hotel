package es.alura.hotel.controller;

import java.net.URL;
import java.util.ResourceBundle;

import es.alura.hotel.util.NotificationUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class TapPaneController implements Initializable {

  @FXML
  private TabPane tabPane;
  @FXML
  private Tab tabHuesped;
  @FXML
  private Tab tabReserva;

  private static Boolean isReserva;
  private static Boolean isHuesped;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
  }

  @FXML
  public void tapSelectTableHuesped() {
    if (tabHuesped.isSelected()) {
      isReserva = false;
      isHuesped = true;
      TableHuespedController.loadTableHuespedes(SearchController.searchQuery);
    }
  }

  @FXML
  public void tapSelectTableReserva() {
    if (tabReserva.isSelected()) {
      TableReservaController.loadTableReservas(SearchController.searchQuery);
      isReserva = true;
      isHuesped = false;
    }
  }

  public static void isTapSelected() {
    if (isReserva) {
      TableReservaController.loadTableReservas(SearchController.searchQuery);
    } else if (isHuesped) {
      TableHuespedController.loadTableHuespedes(SearchController.searchQuery);
    }
  }

  public static void isDeleteRowSelected() {
    if (TableHuespedController.isRowHuespedSelected) {
      TableHuespedController.deleteRowHuesped(() -> {
        TableHuespedController.loadTableHuespedes(SearchController.searchQuery);
      });
    } else if (TableReservaController.isRowReservaSelected) {
      TableReservaController.deleteRowReserva(() -> {
        TableReservaController.loadTableReservas(SearchController.searchQuery);
      });
    } else {
      NotificationUtil.showErrorNotification("Debes seleccionar una fila primero");
    }
  }

}
