package Backend.API.Database;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class TwitterDB implements Serializable{
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "country")
    private String country;

    private String timestamps;

    @Column(unique = true)
    private String location;

    public TwitterDB(){
    }
    public TwitterDB(String country, String location,String timestamps){
        this.country = country;
        this.location = location;
        this.timestamps = timestamps;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimestamps() {
        return timestamps;
    }

    public void setTimestamps(String timestamps) {
        this.timestamps = timestamps;
    }

    @Override
    public String toString() {
        return "TwitterDB{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", timestamps='" + timestamps + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
