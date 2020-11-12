package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.MainApp;
import sample.model.Vehicle;

public class VehicleOverviewController {
    @FXML
    private TableView<Vehicle> vehicleTable;
    //
    @FXML
    private TableColumn<Vehicle, String> vehicleTypeColumn;
    @FXML
    private TableColumn<Vehicle, String> companyColumn;

    @FXML
    private TableColumn<Vehicle, String> vehicleNumColumn;//vehicleNum
    @FXML
    private TableColumn<Vehicle, String> categoryColumn;//category
    @FXML
    private TableColumn<Vehicle, String> departColumn;//depart

    @FXML
    private Label vehicleTypeLabel;
    @FXML
    private Label companyLabel;
    @FXML
    private Label vehicleNumLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label departLabel;

    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public VehicleOverviewController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        vehicleTypeColumn.setCellValueFactory(cellData -> cellData.getValue().vehicleTypeProperty());
        companyColumn.setCellValueFactory(cellData -> cellData.getValue().companyProperty());
        vehicleNumColumn.setCellValueFactory(cellData -> cellData.getValue().vehicleNumProperty().asString());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        departColumn.setCellValueFactory(cellData -> cellData.getValue().departProperty());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        vehicleTable.setItems(mainApp.getVehicleData());
    }
}