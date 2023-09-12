package es.alura.hotel.model;

public enum FormaDePago {

  CREDITO("Tarjeta de crédito"), DEBITO("Tarjeta de débito"), EFECTIVO("Dinero en efectivo");

  private final String formaDePago;

  FormaDePago(String formaDePago) {
    this.formaDePago = formaDePago;
  }

  public String get() {
    return formaDePago;
  }

  public static String[] getAll() {
    FormaDePago[] values = FormaDePago.values();
    String[] arr = new String[values.length];

    for (int i = 0; i < values.length; i++) {
      arr[i] = values[i].get();
    }

    return arr;
  }

}
