package sample.controller;



import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

import sample.model.Vehicle;
//import sample.util.DateUtil;
import sample.model.Vehicle;
import sample.util.DateUtil;

/**
 * Dialog to edit details of a person.
 *
 * @author Marco Jakob
 */
public class VehicleEditDialogController {

    @FXML
    private TextField vehicleTypeField;
    @FXML
    private TextField companyField;
    @FXML
    private TextField vehicleNumField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField departField;
    @FXML
    private TextField vehiclenameField;
    @FXML
    private TextField vehiclecodeField;
    @FXML
    private TextField vehiclesnField;
    @FXML
    private TextField entrydateField;
    @FXML
    private TextField remarkField;
    @FXML
    private TextField imageaField;
    @FXML
    private TextField imagebField;
    @FXML
    private TextField annexField;
    //
    private Stage dialogStage;
    private Vehicle vehicle;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets the person to be edited in the dialog.
     *
     * @param vehicle
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;

        vehicleTypeField.setText(vehicle.getVehicleType());
        companyField.setText(vehicle.getCompany());
        vehicleNumField.setText(Integer.toString(vehicle.getVehicleNum()));
        categoryField.setText(vehicle.getCategory());
        departField.setText(vehicle.getDepart());
        vehiclenameField.setText(vehicle.getVehiclename());
        vehiclecodeField.setText(vehicle.getVehiclecode());
        vehiclesnField.setText(vehicle.getVehiclesn());
        entrydateField.setPromptText("yyyy.mm.dd");
//      birthdayField.setPromptText("dd.mm.yyyy");
        remarkField.setText(vehicle.getRemark());
        imageaField.setText(vehicle.getImagea());
        imagebField.setText(vehicle.getImageb());
        annexField.setText(vehicle.getAnnex());

    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            vehicle.setVehicleType(vehicleTypeField.getText());
            vehicle.setCompany(companyField.getText());
            vehicle.setVehicleNum(Integer.parseInt(vehicleNumField.getText()));
            vehicle.setCategory(categoryField.getText());
            vehicle.setDepart(departField.getText());
            vehicle.setVehiclename(vehiclenameField.getText());
            vehicle.setVehiclecode(vehiclecodeField.getText());
            vehicle.setVehiclesn(vehiclesnField.getText());
            vehicle.setEntrydate(DateUtil.parse(entrydateField.getText()));
            vehicle.setRemark(remarkField.getText());
            vehicle.setImagea(imageaField.getText());
            vehicle.setImageb(imagebField.getText());
            vehicle.setAnnex(annexField.getText());



            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (vehicleTypeField.getText() == null || vehicleTypeField.getText().length() == 0) {
            errorMessage += "No valid  vehicleTypeField!\n";
        }
        if (companyField.getText() == null || companyField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (vehicleNumField.getText() == null || vehicleNumField.getText().length() == 0) {
            errorMessage += "No valid vehicleNumField!\n";
        }

        if (categoryField.getText() == null || categoryField.getText().length() == 0) {
            errorMessage += "No valid categoryField !\n";
        } else {
            // try to parse the postal code into an int.
            try {
                Integer.parseInt(vehicleNumField.getText());
            } catch (NumberFormatException e) {
                errorMessage += "No valid postal code (must be an integer)!\n";
            }
        }

        if (departField.getText() == null || departField.getText().length() == 0) {
            errorMessage += "No valid departField!\n";
        }

        if (entrydateField.getText() == null || entrydateField.getText().length() == 0) {
            errorMessage += "No valid entrydate!\n";
        } else {
            if (!DateUtil.validDate(entrydateField.getText())) {
                errorMessage += "No valid entrydate. Use the format dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Dialogs.create()
                    .title("Invalid Fields")
                    .masthead("Please correct invalid fields")
                    .message(errorMessage)
                    .showError();
            return false;
        }
    }
}