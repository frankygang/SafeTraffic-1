package sample.model;

import javafx.beans.property.*;
import sample.util.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Model class for a Vehicle.
 *
 * @author xg
 */
public class Vehicle {


    private final StringProperty vehicleType;
    private final StringProperty company;
    private final IntegerProperty vehicleNum;//系列型号
    private final StringProperty category;
    private final StringProperty depart;
    private final StringProperty vehiclename;
    private final StringProperty vehiclecode;//车牌
    private final StringProperty vehiclesn;//车架识别码
    private final ObjectProperty<LocalDate> entrydate;
    private final StringProperty remark;
    private final StringProperty imagea;
    private final StringProperty imageb;
    private final StringProperty annex;



    /**
     * Default constructor.
     */
    public Vehicle() {
        this(null,null,null,null,null,null,null,null,null,null,null,null,null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param vehicleType
     * @param company
     * @param vehicleNum
     * @param category
     * @param depart
     * @param vehiclename
     * @param vehiclecode
     * @param vehiclesn
     * @param entrydate
     * @param remark
     * @param imagea
     * @param imageb
     * @param annex
     */
    public Vehicle(String vehicleType,String company,Integer vehicleNum,String category,String depart,String vehiclename
            ,String vehiclecode,String vehiclesn,String entrydate,String remark,String imagea,String imageb,String annex) {
        // Some initial dummy data, just for convenient testing.
        this.vehicleType = new SimpleStringProperty("巴士");
        this.company = new SimpleStringProperty("ITSS");
        this.vehicleNum = new SimpleIntegerProperty(000);
        this.category = new SimpleStringProperty("货车");
        this.depart = new SimpleStringProperty("综合管理部");
        this.vehiclename = new SimpleStringProperty("车辆名称");
        this.vehiclecode = new SimpleStringProperty("车牌号");
        this.vehiclesn = new SimpleStringProperty("车辆识别码");
        this.entrydate = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
        this.remark = new SimpleStringProperty("remark");
        this.imagea = new SimpleStringProperty("imagea");
        this.imageb = new SimpleStringProperty("imageb");
        this.annex = new SimpleStringProperty("annex");
    }
    //
//    @XmlJavaTypeAdapter(LocalDateAdapter.class)
//    public LocalDate getBirthday() {
//        return birthday.get();
//    }
    //

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

    public Integer getVehicleNum() {
        return vehicleNum.getValue();
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

    public String getVehiclename() {
        return vehiclename.get();
    }

    public StringProperty vehiclenameProperty() {
        return vehiclename;
    }

    public void setVehiclename(String vehiclename) {
        this.vehiclename.set(vehiclename);
    }

    public String getVehiclecode() {
        return vehiclecode.get();
    }

    public StringProperty vehiclecodeProperty() {
        return vehiclecode;
    }

    public void setVehiclecode(String vehiclecode) {
        this.vehiclecode.set(vehiclecode);
    }

    public String getVehiclesn() {
        return vehiclesn.get();
    }

    public StringProperty vehiclesnProperty() {
        return vehiclesn;
    }

    public void setVehiclesn(String vehiclesn) {
        this.vehiclesn.set(vehiclesn);
    }

    public LocalDate getEntrydate() {
        return entrydate.get();
    }

    public ObjectProperty<LocalDate> entrydateProperty() {
        return entrydate;
    }

    public void setEntrydate(LocalDate entrydate) {
        this.entrydate.set(entrydate);
    }

    public String getRemark() {
        return remark.get();
    }

    public StringProperty remarkProperty() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark.set(remark);
    }

    public String getImagea() {
        return imagea.get();
    }

    public StringProperty imageaProperty() {
        return imagea;
    }

    public void setImagea(String imagea) {
        this.imagea.set(imagea);
    }

    public String getImageb() {
        return imageb.get();
    }

    public StringProperty imagebProperty() {
        return imageb;
    }

    public void setImageb(String imageb) {
        this.imageb.set(imageb);
    }

    public String getAnnex() {
        return annex.get();
    }

    public StringProperty annexProperty() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex.set(annex);
    }
}