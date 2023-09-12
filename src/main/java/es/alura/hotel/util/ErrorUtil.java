package es.alura.hotel.util;

public class ErrorUtil extends RuntimeException {

  public ErrorUtil() {
  }

  public ErrorUtil(String errorMensajeString) {
    super(errorMensajeString);
  }

}
