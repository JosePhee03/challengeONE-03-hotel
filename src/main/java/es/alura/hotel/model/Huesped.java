package es.alura.hotel.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "huespedes")
public class Huesped {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  private String nombre;
  private String apellido;
  @Column(name = "fecha_de_nacimiento")
  @Temporal(TemporalType.DATE)
  private LocalDate fechaDeNacimiento;
  private String nacionalidad;
  private int telefono;

  @OneToOne
  private Reserva reserva;

  public Huesped() {
  }

  public Huesped(String nombre, String apellido, LocalDate fechaDeNacimiento, String nacionalidad, int telefono,
      Reserva reserva) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.fechaDeNacimiento = fechaDeNacimiento;
    this.nacionalidad = nacionalidad;
    this.telefono = telefono;
    this.reserva = reserva;
  }

  public int getId() {
    return id;
  }

  public Reserva getReserva() {
    return reserva;
  }

  public void setReserva(Reserva reserva) {
    this.reserva = reserva;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public LocalDate getFechaDeNacimiento() {
    return fechaDeNacimiento;
  }

  public String getNacionalidad() {
    return nacionalidad;
  }

  public int getTelefono() {
    return telefono;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
    this.fechaDeNacimiento = fechaDeNacimiento;
  }

  public void setNacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }

  public void setTelefono(int telefono) {
    this.telefono = telefono;
  }
}
