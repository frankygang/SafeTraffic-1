package sample;

import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.VehicleOverviewController;
import sample.model.Vehicle;

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
        vehicleData.add(new Vehicle("巴士", "IRNC", 33, "BUS", "综合管理部"));
        vehicleData.add(new Vehicle("矿车", "ITSS", 33, "BUS", "综合管理部"));
        vehicleData.add(new Vehicle("通勤车", "ITSS", 88, "BUS", "综合管理部"));
        vehicleData.add(new Vehicle("矿车", "IRNC", 33, "BUS", "综合管理部"));
        vehicleData.add(new Vehicle("矿车", "GCNS", 88, "BUS", "综合管理部"));
        vehicleData.add(new Vehicle("通勤车", "ITSS", 33, "BUS", "综合管理部"));
        vehicleData.add(new Vehicle("小汽车", "IRNC", 33, "BUS", "综合管理部"));
        vehicleData.add(new Vehicle("小汽车", "IRNC", 33, "BUS", "综合管理部"));
        vehicleData.add(new Vehicle("巴士", "IRNC", 33, "BUS", "综合管理部"));
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

        initRootLayout();

        showVehicleOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
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