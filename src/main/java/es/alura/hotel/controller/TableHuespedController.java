package es.alura.hotel.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import es.alura.hotel.model.Huesped;
import es.alura.hotel.model.Reserva;
import es.alura.hotel.service.HuespedService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

public class TableHuespedController implements Initializable {

  @FXML
  private TableView<Huesped> tableHuespedes;
  @FXML
  private TableColumn<Huesped, Integer> columnHuespedId;
  @FXML
  private TableColumn<Huesped, String> columnHuespedNombre;
  @FXML
  private TableColumn<Huesped, String> columnHuespedApellido;
  @FXML
  private TableColumn<Huesped, LocalDate> columnHuespedFechaDeNacimiento;
  @FXML
  private TableColumn<Huesped, String> columnHuespedNacionalidad;
  @FXML
  private TableColumn<Huesped, Integer> columnHuespedTelefono;
  @FXML
  private TableColumn<Huesped, Integer> columnHuespedIdReserva;

  @FXML
  public static ObservableList<Huesped> huespedes = FXCollections.observableArrayList();

  public static Boolean isRowHuespedSelected = false;

  public static Huesped huespedSelected;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    createHuespedTable();
    initSelectEvent();
  }

  private void initSelectEvent() {
    TableViewSelectionModel<Huesped> selectionModel = tableHuespedes.getSelectionModel();

    selectionModel.selectedItemProperty().addListener(new ChangeListener<Huesped>() {
      @Override
      public void changed(ObservableValue<? extends Huesped> observable, Huesped oldValue, Huesped newValue) {
        if (oldValue != null && newValue == null) {
          isRowHuespedSelected = false;
        } else if (newValue != null) {
          isRowHuespedSelected = true;
          int rowIndex = tableHuespedes.getSelectionModel().getSelectedIndex();
          huespedSelected = tableHuespedes.getItems().get(rowIndex);
        }
      }
    });
  }

  private void onEditNombre(CellEditEvent<Huesped, String> event) {
    int indexRow = event.getTablePosition().getRow();
    Huesped huespedCollection = huespedes.get(indexRow);
    huespedCollection.setNombre(event.getNewValue());
    updateTableHuespedes(huespedCollection);
  }

  private void onEditApellido(CellEditEvent<Huesped, String> event) {
    int indexRow = event.getTablePosition().getRow();
    Huesped huespedCollection = huespedes.get(indexRow);
    huespedCollection.setApellido(event.getNewValue());
    updateTableHuespedes(huespedCollection);
  }

  private void onEditFechaDeNacimiento(CellEditEvent<Huesped, LocalDate> event) {
    int indexRow = event.getTablePosition().getRow();
    Huesped huespedCollection = huespedes.get(indexRow);
    huespedCollection.setFechaDeNacimiento(event.getNewValue());
    updateTableHuespedes(huespedCollection);
  }

  private void onEditNacionalidad(CellEditEvent<Huesped, String> event) {
    int indexRow = event.getTablePosition().getRow();
    Huesped huespedCollection = huespedes.get(indexRow);
    huespedCollection.setNacionalidad(event.getNewValue());
    updateTableHuespedes(huespedCollection);
  }

  private void onEditTelefono(CellEditEvent<Huesped, Integer> event) {
    int indexRow = event.getTablePosition().getRow();
    Huesped huespedCollection = huespedes.get(indexRow);
    huespedCollection.setTelefono(event.getNewValue());
    updateTableHuespedes(huespedCollection);
  }

  public void createHuespedTable() {
    this.columnHuespedId.setCellValueFactory(new PropertyValueFactory<Huesped, Integer>("id"));
    this.columnHuespedNombre.setCellValueFactory(new PropertyValueFactory<Huesped, String>("nombre"));

    columnHuespedNombre.setCellFactory(TextFieldTableCell.forTableColumn());
    columnHuespedNombre.setOnEditCommit(this::onEditNombre);

    this.columnHuespedApellido.setCellValueFactory(new PropertyValueFactory<Huesped, String>("apellido"));

    columnHuespedApellido.setCellFactory(TextFieldTableCell.forTableColumn());
    columnHuespedApellido.setOnEditCommit(this::onEditApellido);

    this.columnHuespedFechaDeNacimiento
        .setCellValueFactory(new PropertyValueFactory<Huesped, LocalDate>("fechaDeNacimiento"));

    columnHuespedFechaDeNacimiento.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
    columnHuespedFechaDeNacimiento.setOnEditCommit(this::onEditFechaDeNacimiento);

    this.columnHuespedNacionalidad.setCellValueFactory(new PropertyValueFactory<Huesped, String>("nacionalidad"));

    columnHuespedNacionalidad.setCellFactory(TextFieldTableCell.forTableColumn());
    columnHuespedNacionalidad.setOnEditCommit(this::onEditNacionalidad);

    this.columnHuespedTelefono.setCellValueFactory(new PropertyValueFactory<Huesped, Integer>("telefono"));

    columnHuespedTelefono.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    columnHuespedTelefono.setOnEditCommit(this::onEditTelefono);

    this.columnHuespedIdReserva
        .setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Huesped, Integer>, ObservableValue<Integer>>() {
          @Override
          public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Huesped, Integer> param) {
            Reserva reserva = param.getValue().getReserva();
            return new SimpleObjectProperty<>(reserva.getId());
          }
        });

    this.tableHuespedes.setItems(huespedes);
  }

  private void updateTableHuespedes(Huesped huesped) {
    HuespedService.updateHuesped(huesped);
  }

  public static void loadTableHuespedes(String value) {
    HuespedService.searchHuespedes(value, () -> {
      huespedes.clear();
      huespedes.addAll(HuespedService.getHuespedes());
    });
  }

  public static void deleteRowHuesped(Runnable action) {
    HuespedService.deleteHuesped(huespedSelected, action);
  }
}
