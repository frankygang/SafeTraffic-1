package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.controlsfx.dialog.Dialogs;
import sample.MainApp;
import sample.model.Vehicle;

public class VehicleOverviewController {
    @FXML
    private TableView<Vehicle> vehicleTable;
    @FXML
    private TableColumn<Vehicle, String> vehicleTypeColumn;
    @FXML
    private TableColumn<Vehicle, String> companyColumn;
    @FXML
    private TableColumn<Vehicle, String> vehicleNumColumn;
    @FXML
    private TableColumn<Vehicle, String> categoryColumn;
    @FXML
    private TableColumn<Vehicle, String> departColumn;

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
        //
        // Clear person details.
        showVehicleDetails(null);

        // Listen for selection changes and show the person details when changed.
        vehicleTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showVehicleDetails(newValue));

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
    private void showVehicleDetails(Vehicle vehicle) {
        if (vehicle != null) {
            // Fill the labels with info from the person object.
            vehicleTypeLabel.setText(vehicle.getVehicleType());
            companyLabel.setText(vehicle.getCompany());
            vehicleNumLabel.setText(vehicle.getVehicleNum().toString());
            categoryLabel.setText(vehicle.getCategory());
            departLabel.setText(vehicle.getDepart());

            // TODO: We need a way to convert the birthday into a String!
            //birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            // Person is null, remove all the text.
            vehicleTypeLabel.setText("");
            companyLabel.setText("");
            vehicleNumLabel.setText("");
            categoryLabel.setText("");
            departLabel.setText("");
//            birthdayLabel.setText("");
        }

    }
    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleDeleteVehicle() {
        int selectedIndex = vehicleTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >=0) {
            vehicleTable.getItems().remove(selectedIndex);
        }else{
            // Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Person Selected")
                    .message("Please select a person in the table.")
                    .showWarning();
        }
    }
}
