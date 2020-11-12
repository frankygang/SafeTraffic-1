package sample.model;
import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Vehicle.
 *
 * @author xg
 */
public class Vehicle {


    private final StringProperty vehicleType;
    private final StringProperty company;
    private final IntegerProperty vehicleNum;
    private final StringProperty category;
    private final StringProperty depart;


    /**
     * Default constructor.
     */
    public Vehicle() {
        this(null, null,null, null,null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param vehicleType
     * @param company
     * @param vehicleNum
     * @param category
     * @param depart
     */
    public Vehicle(String vehicleType,String company,Integer vehicleNum,String category,String depart) {
        // Some initial dummy data, just for convenient testing.
        this.vehicleType = new SimpleStringProperty(vehicleType);
        this.company = new SimpleStringProperty(company);
        this.vehicleNum = new SimpleIntegerProperty(vehicleNum);
        this.category = new SimpleStringProperty(category);
        this.depart = new SimpleStringProperty(depart);
    }

    public String getVehicleType() {
        return vehicleType.get();
    }

    public StringProperty vehicleTypeProperty() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType.set(vehicleType);
    }

    public String getCompany() {
        return company.get();
    }

    public StringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public int getVehicleNum() {
        return vehicleNum.get();
    }

    public IntegerProperty vehicleNumProperty() {
        return vehicleNum;
    }

    public void setVehicleNum(int vehicleNum) {
        this.vehicleNum.set(vehicleNum);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getDepart() {
        return depart.get();
    }

    public StringProperty departProperty() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart.set(depart);
    }
}