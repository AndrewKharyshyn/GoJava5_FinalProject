package FinalProjectCore;

import java.util.List;

public class Hotel {
    private int hotelId;
    private String hotelName;
    private String city;
    private List<Room> rooms;

    public Hotel(int hotelId, String hotelName, String city, List<Room> rooms) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.city = city;
        this.rooms = rooms;
    }

        @Override
    public String toString() {
        return "Hotel{" +
                "hotelId=" + hotelId +
                ", hotelName='" + hotelName + '\'' +
                ", city='" + city + '\'' +
                ", rooms=" + rooms +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hotel hotel = (Hotel) o;

        if (hotelId != hotel.hotelId) return false;
        if (!hotelName.equals(hotel.hotelName)) return false;
        if (!city.equals(hotel.city)) return false;
        return rooms.equals(hotel.rooms);

    }

    @Override
    public int hashCode() {
        int result = hotelId;
        result = 31 * result + hotelName.hashCode();
        result = 31 * result + city.hashCode();
        result = 31 * result + rooms.hashCode();
        return result;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
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
}
