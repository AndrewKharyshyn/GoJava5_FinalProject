package Objects;
/**
 * Класс Отелей
 */
import java.util.List;
/**
 * Данный отель с  параметрами :
 * @param hotelId Уникальный идентификатор отеля
 * @param hotelName Название Отеля
 * @param city Город в котором находиться отель
 * @param rooms комнаты
 */
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
                '}';
    }
    /**
     * Метод вызываеться при сравнивании содержимого обьектов (отелей)
     * @param o данный отель
     * @return эквивалентность обьектов
     */
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
    /**
     *Метод вызываеться при сравнении отелей
     * @return результат
     */
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
