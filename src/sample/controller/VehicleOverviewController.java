package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.dialog.Dialogs;
import sample.MainApp;
import sample.model.Vehicle;
import sample.util.DateUtil;

public class VehicleOverviewController {
    @FXML
    private ImageView imageaview;
    @FXML
    private ImageView imagebview;
    //
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
    private TableColumn<Vehicle, String> vehiclenameColumn;
    @FXML
    private TableColumn<Vehicle, String> vehiclecodeColumn;
    @FXML
    private TableColumn<Vehicle, String> vehiclesnColumn;
    @FXML
    private TableColumn<Vehicle, String> entrydateColumn;
    @FXML
    private TableColumn<Vehicle, String> remarkColumn;
    @FXML
    private TableColumn<Vehicle, String> imageaColumn;
    @FXML
    private TableColumn<Vehicle, String> imagebColumn;
    @FXML
    private TableColumn<Vehicle, String> annexColumn;


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
    @FXML
    private Label vehiclenameLabel;
    @FXML
    private Label vehiclecodeLabel;
    @FXML
    private Label vehiclesnLabel;
    @FXML
    private Label entrydateLabel;
    @FXML
    private Label remarkLabel;
    @FXML
    private Label imageaLabel;
    @FXML
    private Label imagebLabel;
    @FXML
    private Label annexLabel;


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
        // Initialize the table with the columns用列初始化车辆表.
        vehicleTypeColumn.setCellValueFactory(cellData -> cellData.getValue().vehicleTypeProperty());
        companyColumn.setCellValueFactory(cellData -> cellData.getValue().companyProperty());
        vehicleNumColumn.setCellValueFactory(cellData -> cellData.getValue().vehicleNumProperty().asString());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        departColumn.setCellValueFactory(cellData -> cellData.getValue().departProperty());
        vehiclenameColumn.setCellValueFactory(cellData -> cellData.getValue().vehiclenameProperty());
        vehiclecodeColumn.setCellValueFactory(cellData -> cellData.getValue().vehiclecodeProperty());
        vehiclesnColumn.setCellValueFactory(cellData -> cellData.getValue().vehiclesnProperty());
        entrydateColumn.setCellValueFactory(cellData -> cellData.getValue().entrydateProperty().asString());
        remarkColumn.setCellValueFactory(cellData -> cellData.getValue().remarkProperty());
        imageaColumn.setCellValueFactory(cellData -> cellData.getValue().imageaProperty());
        imagebColumn.setCellValueFactory(cellData -> cellData.getValue().imagebProperty());
        annexColumn.setCellValueFactory(cellData -> cellData.getValue().annexProperty());
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
            vehicleNumLabel.setText(Integer.toString(vehicle.getVehicleNum()));
            categoryLabel.setText(vehicle.getCategory());
            departLabel.setText(vehicle.getDepart());
            vehiclenameLabel.setText(vehicle.getVehiclename());
            vehiclecodeLabel.setText(vehicle.getVehiclecode());
            vehiclesnLabel.setText(vehicle.getVehiclesn());
            entrydateLabel.setText(vehicle.getEntrydate().toString());
            remarkLabel.setText(vehicle.getRemark());
//            imageaLabel.setText(vehicle.getImagea());
            try {
                Image imagea = new Image(vehicle.getImagea());
                imageaview.setImage(imagea);
            } catch (Exception e) {
                imageaview.setImage(new Image("/sample/image/default-user-icon.png"));
            }
//            imagebLabel.setText(vehicle.getImageb());
            try {
                Image imageb = new Image(vehicle.getImageb());
                 imagebview.setImage(imageb);
            } catch (Exception e) {
            imagebview.setImage(new Image("/sample/image/default-user-icon.png"));
            }
            annexLabel.setText(vehicle.getAnnex());

            // TODO: We need a way to convert the birthday into a String!
            entrydateLabel.setText(DateUtil.format(vehicle.getEntrydate()));
        } else {
            // Person is null, remove all the text.
            vehicleTypeLabel.setText("");
            companyLabel.setText("");
            vehicleNumLabel.setText("");
            categoryLabel.setText("");
            departLabel.setText("");
//            birthdayLabel.setText("");
            vehiclenameLabel.setText("");
            vehiclecodeLabel.setText("");
            vehiclesnLabel.setText("");
            entrydateLabel.setText("");
            remarkLabel.setText("");
            imageaLabel.setText("");
            imagebLabel.setText("");
            annexLabel.setText("");
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
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewVehicle() {
        Vehicle tempVehicle = new Vehicle();
        boolean okClicked = mainApp.showVehicleEditDialog(tempVehicle);
        if (okClicked) {
            mainApp.getVehicleData().add(tempVehicle);
//            mainApp.getVehicleData().
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditVehicle() {
        Vehicle selectedVehicle =vehicleTable.getSelectionModel().getSelectedItem();
        if (selectedVehicle != null) {
            boolean okClicked = mainApp.showVehicleEditDialog(selectedVehicle);
            if (okClicked) {
                showVehicleDetails(selectedVehicle);
            }

        } else {
            // Nothing selected.
            Dialogs.create()
                    .title("No Selection")
                    .masthead("No Vehicle Selected")
                    .message("Please select a Vehicle in the table.")
                    .showWarning();
        }
    }
}
