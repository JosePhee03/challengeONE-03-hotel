package es.alura.hotel.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reservas")
public class Reserva {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "fecha_de_entrada")
  private LocalDate fechaDeEntrada;
  @Column(name = "fecha_de_salida")
  private LocalDate fechaDeSalida;
  private Double valor;
  @Column(name = "forma_de_pago")
  private String formaDePago;

  public Reserva() {
  }

  public Reserva(LocalDate fechaDeEntrada, LocalDate fechaDeSalida, Double valor, String formaDePago) {
    this.fechaDeEntrada = fechaDeEntrada;
    this.fechaDeSalida = fechaDeSalida;
    this.valor = valor;
    this.formaDePago = formaDePago;
  }

  public int getId() {
    return id;
  }

  public LocalDate getFechaDeEntrada() {
    return fechaDeEntrada;
  }

  public LocalDate getFechaDeSalida() {
    return fechaDeSalida;
  }

  public String getFormaDePago() {
    return formaDePago;
  }

  public Double getValor() {
    return valor;
  }

  public void setFechaDeEntrada(LocalDate fechaDeEntrada) {
    this.fechaDeEntrada = fechaDeEntrada;
  }

  public void setFechaDeSalida(LocalDate fechaDeSalida) {
    this.fechaDeSalida = fechaDeSalida;
  }

  public void setFormaDePago(String formaDePago) {
    this.formaDePago = formaDePago;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }

}
