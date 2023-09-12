package es.alura.hotel.util;

import org.controlsfx.control.Notifications;

import javafx.geometry.Pos;

public class NotificationUtil {

  public static void showLoadingNotification() {
    Notifications.create().title("Cargando").text("Cargando recursos").position(Pos.BASELINE_LEFT)
        .hideAfter(javafx.util.Duration.seconds(3)).showInformation();
    ;
  }

  public static void showSynchronizationNotification() {
    Notifications.create().title("Cargando").text("Sincronizando datos").position(Pos.BASELINE_LEFT)
        .hideAfter(javafx.util.Duration.seconds(3)).showInformation();
    ;
  }

  public static void showSuccessNotification(String message) {
    Notifications.create().title("Éxito").text(message).position(Pos.CENTER).hideAfter(javafx.util.Duration.INDEFINITE)
        .showConfirm();
  }

  public static void showErrorNotification(String message) {
    Notifications.create().title("Error").text(message).position(Pos.CENTER).hideAfter(javafx.util.Duration.INDEFINITE)
        .showError();
  }

}
