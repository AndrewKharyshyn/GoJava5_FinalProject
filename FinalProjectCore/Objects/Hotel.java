package FinalProjectCore.Objects;

import java.util.List;
/**
 * Класс Отелей
 */
public class Hotel {
    private int hotelId;
    private String hotelName;
    private String city;
    private List<Room> rooms;
    private int stars;

    public Hotel(int hotelId, String hotelName, String city, List<Room> rooms, int stars) {

    /**
     * Данный отель с  параметрами :
     * @param hotelId Уникальный идентификатор отеля
     * @param hotelName Название Отеля
     * @param city Город в котором находиться отель
     * @param rooms комнаты
     */

        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.city = city;
        this.rooms = rooms;
        this.stars = stars;
    }

    /**
     * Метод для корректного вывода информации об отеле
     * @return параметры отеля
     */
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
