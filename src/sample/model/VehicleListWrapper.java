package sample.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 *
 * @author xg
 */
@XmlRootElement(name = "vehicles")
public class VehicleListWrapper {

    private List<Vehicle> vehicles;

    @XmlElement(name = "vehicle")
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> persons) {
        this.vehicles = persons;
    }
}