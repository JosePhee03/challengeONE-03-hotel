package es.alura.hotel.util;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class AlertUtil {

  public static boolean signOut() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmación");
    alert.setHeaderText("¿Estás seguro que quieres cerrar la sesión?");
    alert.setContentText("Presiona ACEPTAR para confirmar o CANCELAR para cancelar la acción.");

    Optional<ButtonType> result = alert.showAndWait();
    return result.isPresent() && result.get() == ButtonType.OK;
  }

  public static boolean goOut() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmar");
    alert.setHeaderText("¿Estas seguro que quieres salir?");
    alert.setContentText("Presiona ACEPTAR para confirmar o CANCELAR para cancelar la acción.");

    Optional<ButtonType> result = alert.showAndWait();
    return result.isPresent() && result.get() == ButtonType.OK;
  }

  public static void errorInput() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Campo incompleto");
    alert.setContentText("Presiona ACEPTAR para continuar.");

    alert.showAndWait();
  }

  public static void errorLoading() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Error al cargar el recurso");
    alert.setContentText("Presiona ACEPTAR para continuar.");

    alert.showAndWait();
  }

  public static void successLoading() {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Información");
    alert.setHeaderText("Recurso cargado con éxito");
    alert.setContentText("Presiona ACEPTAR para continuar.");

    alert.showAndWait();
  }

}
