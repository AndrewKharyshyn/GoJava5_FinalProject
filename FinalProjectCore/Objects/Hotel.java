package Objects;

import java.util.List;

public class Hotel {
    private int hotelId;
    private String hotelName;
    private String city;
    private List<Room> rooms;
    private int stars;

    public Hotel(int hotelId, String hotelName, String city, List<Room> rooms, int stars) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.city = city;
        this.rooms = rooms;
        this.stars = stars;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", city='" + city + '\'' +
                ", rooms=" + rooms +
                ", stars=" + stars +
                '}';
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
