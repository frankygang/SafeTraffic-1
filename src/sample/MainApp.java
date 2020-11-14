package sample;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.dialog.Dialogs;
import sample.controller.EntrydateStatisticsController;
import sample.controller.RootLayoutController;
import sample.controller.VehicleEditDialogController;
import sample.controller.VehicleOverviewController;
import sample.model.Vehicle;
import sample.model.VehicleListWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    // ... AFTER THE OTHER VARIABLES ...
    /**
     * The data as an observable list of Vehicles.
     */
    private ObservableList<Vehicle> vehicleData = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
        vehicleData.add(new Vehicle("车辆类别", "公司名称", 001, "车辆类型", "所属部门","车辆名称","车辆型号","识别代码","19990101","备注信息","图片1","图片2","附件"));
        vehicleData.add(new Vehicle("车辆类别", "公司名称", 001, "车辆类型", "所属部门","车辆名称","车辆型号","识别代码","19990101","备注信息","图片1","图片2","附件"));
        vehicleData.add(new Vehicle("车辆类别", "公司名称", 001, "车辆类型", "所属部门","车辆名称","车辆型号","识别代码","19990101","备注信息","图片1","图片2","附件"));
        vehicleData.add(new Vehicle("车辆类别", "公司名称", 001, "车辆类型", "所属部门","车辆名称","车辆型号","识别代码","19990101","备注信息","图片1","图片2","附件"));
        vehicleData.add(new Vehicle("车辆类别", "公司名称", 001, "车辆类型", "所属部门","车辆名称","车辆型号","识别代码","19990101","备注信息","图片1","图片2","附件"));
        vehicleData.add(new Vehicle("车辆类别", "公司名称", 001, "车辆类型", "所属部门","车辆名称","车辆型号","识别代码","19990101","备注信息","图片1","图片2","附件"));


    }

    /**
     * Returns the data as an observable list of Vehicles.
     *
     * @return
     */
    public ObservableList<Vehicle> getVehicleData() {
        return vehicleData;
    }

    // ... THE REST OF THE CLASS ...

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.primaryStage.getIcons().add(new Image("file:resources/images/address_book_32.png"));

        initRootLayout();

        showVehicleOverview();
    }

    /**
     * Initializes the root layout and tries to load the last opened
     * person file.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class
                    .getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Try to load last opened person file.
        File file = getVehicleFilePath();
        if (file != null) {
            loadVehicleDataFromFile(file);
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showVehicleOverview() {
        try {
            // Load   overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/VehicleOverview.fxml"));
            AnchorPane vehicleOverview = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setCenter(vehicleOverview);

            // Give the controller access to the main app.
            VehicleOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param vehicle the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showVehicleEditDialog(Vehicle vehicle) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/VehicleEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Vehicle");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the vehicle into the controller.
            VehicleEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVehicle(vehicle);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    /**
     * Returns the vehicle file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     *
     * @return
     */
    public File getVehicleFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     *
     * @param file the file or null to remove the path
     */
    public void setVehicleFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }
    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     *
     * @param file
     */
    public void loadVehicleDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(VehicleListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            VehicleListWrapper wrapper = (VehicleListWrapper) um.unmarshal(file);

            vehicleData.clear();
            vehicleData.addAll(wrapper.getVehicles());

            // Save the file path to the registry.
            setVehicleFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Dialogs.create()
                    .title("Error")
                    .masthead("Could not load data from file:\n" + file.getPath())
                    .showException(e);
        }
    }

    /**
     * Saves the current person data to the specified file.
     *
     * @param file
     */
    public void saveVehicleDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(VehicleListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our Vehicle data.
            VehicleListWrapper wrapper = new VehicleListWrapper();
            wrapper.setVehicles(vehicleData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setVehicleFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Dialogs.create().title("Error")
                    .masthead("Could not save data to file:\n" + file.getPath())
                    .showException(e);
        }
    }
    /**
     * Opens a dialog to show birthday statistics.
     */
    public void showEntrydateStatistics() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EntrydateStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("图表");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the vehicles into the controller.
            EntrydateStatisticsController controller = loader.getController();
            controller.setVehicleData(vehicleData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}