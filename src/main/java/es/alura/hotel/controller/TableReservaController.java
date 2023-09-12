package es.alura.hotel.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import es.alura.hotel.model.Reserva;
import es.alura.hotel.service.ReservaService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class TableReservaController implements Initializable {

  @FXML
  private TableView<Reserva> tableReservas;
  @FXML
  private TableColumn<Reserva, Integer> columnReservaId;
  @FXML
  private TableColumn<Reserva, LocalDate> columnReservaFechaDeEntrada;
  @FXML
  private TableColumn<Reserva, LocalDate> columnReservaFechaDeSalida;
  @FXML
  private TableColumn<Reserva, Double> columnReservaValor;
  @FXML
  private TableColumn<Reserva, String> columnReservaFormaDePago;

  public static ObservableList<Reserva> reservas = FXCollections.observableArrayList();

  public static Boolean isRowReservaSelected = false;

  public static Reserva reservaSelected;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    createReservaTable();
    initSelectEvent();
  }

  private void initSelectEvent() {
    TableViewSelectionModel<Reserva> selectionModel = tableReservas.getSelectionModel();

    selectionModel.selectedItemProperty().addListener(new ChangeListener<Reserva>() {
      @Override
      public void changed(ObservableValue<? extends Reserva> observable, Reserva oldValue, Reserva newValue) {
        if (oldValue != null && newValue == null) {
          isRowReservaSelected = false;
        } else if (newValue != null) {
          isRowReservaSelected = true;
          int rowIndex = tableReservas.getSelectionModel().getSelectedIndex();
          reservaSelected = tableReservas.getItems().get(rowIndex);
        }
      }
    });
  }

  private void onEditFechaDeEntrada(CellEditEvent<Reserva, LocalDate> event) {
    int indexRow = event.getTablePosition().getRow();
    Reserva reservaCollection = reservas.get(indexRow);
    reservaCollection.setFechaDeEntrada(event.getNewValue());
    updateTableReservas(reservaCollection);
  }

  private void onEditFechaDeSalida(CellEditEvent<Reserva, LocalDate> event) {
    int indexRow = event.getTablePosition().getRow();
    Reserva reservaCollection = reservas.get(indexRow);
    reservaCollection.setFechaDeSalida(event.getNewValue());
    updateTableReservas(reservaCollection);
  }

  private void onEditValor(CellEditEvent<Reserva, Double> event) {
    int indexRow = event.getTablePosition().getRow();
    Reserva reservaCollection = reservas.get(indexRow);
    reservaCollection.setValor(event.getNewValue());
    updateTableReservas(reservaCollection);
  }

  private void onEditFormaDePago(CellEditEvent<Reserva, String> event) {
    int indexRow = event.getTablePosition().getRow();
    Reserva reservaCollection = reservas.get(indexRow);
    reservaCollection.setFormaDePago(event.getNewValue());
    updateTableReservas(reservaCollection);
  }

  public void createReservaTable() {
    this.columnReservaId.setCellValueFactory(new PropertyValueFactory<Reserva, Integer>("id"));
    this.columnReservaFechaDeEntrada
        .setCellValueFactory(new PropertyValueFactory<Reserva, LocalDate>("fechaDeEntrada"));

    this.columnReservaFechaDeEntrada.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
    this.columnReservaFechaDeEntrada.setOnEditCommit(this::onEditFechaDeEntrada);

    this.columnReservaFechaDeSalida.setCellValueFactory(new PropertyValueFactory<Reserva, LocalDate>("fechaDeSalida"));

    this.columnReservaFechaDeSalida.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
    this.columnReservaFechaDeSalida.setOnEditCommit(this::onEditFechaDeSalida);

    this.columnReservaValor.setCellValueFactory(new PropertyValueFactory<Reserva, Double>("valor"));

    this.columnReservaValor.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
    this.columnReservaValor.setOnEditCommit(this::onEditValor);

    this.columnReservaFormaDePago.setCellValueFactory(new PropertyValueFactory<Reserva, String>("formaDePago"));

    this.columnReservaFormaDePago.setCellFactory(TextFieldTableCell.forTableColumn());
    this.columnReservaFormaDePago.setOnEditCommit(this::onEditFormaDePago);

    this.tableReservas.setItems(reservas);
  }

  private void updateTableReservas(Reserva reserva) {
    ReservaService.updateReserva(reserva);
  }

  public static void loadTableReservas(String value) {
    ReservaService.searchReservas(value, () -> {
      reservas.clear();
      reservas.addAll(ReservaService.getReservas());
    });
  }

  public static void deleteRowReserva(Runnable action) {
    ReservaService.deleteReserva(reservaSelected, action);
  }
}
