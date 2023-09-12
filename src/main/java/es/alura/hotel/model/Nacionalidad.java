package es.alura.hotel.model;

public enum Nacionalidad {
  ARGENTINA("Argentina"), BRASIL("Brasil"), CHILE("Chile"), COLOMBIA("Colombia"), ESPANA("España"),
  ESTADOS_UNIDOS("Estados Unidos"), MEXICO("México"), PERU("Perú");

  private final String nacionalidad;

  Nacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }

  public String get() {
    return nacionalidad;
  }

  public static String[] getAll() {
    Nacionalidad[] values = Nacionalidad.values();
    String[] arr = new String[values.length];

    for (int i = 0; i < values.length; i++) {
      arr[i] = values[i].get();
    }

    return arr;
  }
}
