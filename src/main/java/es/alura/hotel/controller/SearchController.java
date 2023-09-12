package es.alura.hotel.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import es.alura.hotel.util.NavigationScene;

public class SearchController implements Initializable {

  public static String searchQuery = "";
  @FXML
  private Button buttonBack;

  @FXML
  private TextField inputSearch;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  @FXML
  public void onButtonBack() throws IOException {
    NavigationScene.to("home.fxml", "Hotel Alura - Inicio");
    NavigationScene.removeScene(buttonBack);
  }

  @FXML
  public void onButtonSearch() {
    SearchController.searchQuery = inputSearch.getText();
    TapPaneController.isTapSelected();
  }

  @FXML
  public void onButtonDelete() {
    TapPaneController.isDeleteRowSelected();
  }

}
