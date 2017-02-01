package FinalProjectCore.Logics;
import FinalProjectCore.Behavior.*;
import FinalProjectCore.Objects.*;
import java.util.*;

/**
 * Реализация DAO
 * Главная задача DAO: Постройка моста между реляционной и объектной моделями данных
 */

public class AbstractDAOImpl implements AbstractDAO {
    /**
     * Предопределенные Списки
     * Возможность быстрого доступа к данным
     * Возможность быстрого добавления/удаления обьектов
     */
    //Here are the predefined lists
    private List<User> userList = new ArrayList<>();
    private List<Room> roomsHotel1 = new ArrayList<>();
    private List<Room> roomsHotel2 = new ArrayList<>();
    private List<Room> roomsHotel3 = new ArrayList<>();
    private List<Room> roomsHotel4 = new ArrayList<>();
    private List<Room> roomsHotel5 = new ArrayList<>();
    private List<Hotel> hotels = new ArrayList<>();
    private List<Room> roomsDatabase = new ArrayList<>();


    //Default (virtual) User is added to empty rooms
    // or after reservation is cancelled to prevent NULL
    @Override
    public User generateDefaultUser() {
        User defaultUser = new User(0, "noName", "noLastName");
        return defaultUser;
    }

    /**
     * Добавление полькователя в пустые комнаты, чтобы предотвратить NULL
     * По умолчанию
     */

    //Default (virtual) User is added to empty rooms to prevent NULL

    /**
     * Метод вызывается при добавлении  предопределенного пользователя в списки по 3-м параметрам
     * Уникальный идентификатор пользоватедя
     * Имя пользователя
     * Фамилия пользователя
     */
    //Adding predefined users to the list
    @Override
    public void addUsersToDB() {
        User user1 = new User(1, "Alex", "Melnikov");
        User user2 = new User(2, "Olga", "Safonova");
        User user3 = new User(3, "Oleg", "Petrov");
        User user4 = new User(4, "Pavlo", "Kopernikov");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
    }
    /**
     * Метод вызываеться при добавлении нового пользователя
     * @param user предопределенный пользователь
     */
    //Adding newly created User to existing User list
    @Override
    public void addingNewUser(User user) {
        userList.add(user);
    }
    /**
     * Метод используеться при создании случайных номеров
     * Присваивая уникальный идентификатор
     */
    //Creating the random rooms with predefined and limited parameters
    private int roomId = 0;
    private Random random = new Random();

    /**
     * Метод вызываться при генерации номера в отеле
     * @param hotelID уникальный идентификатор отеля
     * @return Возвращает Номер,цену,порядковый номер,название отеля
     */
    @Override
    public Room generateRoom(int hotelID) {
        double price = random.nextInt(5001);
        return new Room(++roomId, random.nextInt((4 - 1) + 1) + 1,
                price, hotelID,
                random.nextBoolean(),
                random.nextInt(25),
                generateDefaultUser());
    }

    /**
     * Метод выполняет добавление случайных номеров в списки
     */
    //Adding random rooms to the lists
    //Adding random rooms to the lists with loop
    @Override
    public void addGeneratedRooms() {
        for (int i = 0; i < 10; i++) {
            roomsHotel1.add(generateRoom(1));
            roomsHotel2.add(generateRoom(2));
            roomsHotel3.add(generateRoom(3));
            roomsHotel4.add(generateRoom(4));
            roomsHotel5.add(generateRoom(5));
        }
    }

    /**
     * Создание Гостиничных номеров внутри списков
     */
    //Creating hotel objects with lists inside
    Hotel hotel1 = new Hotel(1, "President Hotel", "Kyiv", roomsHotel1, 5);
    Hotel hotel2 = new Hotel(2, "Hyatt", "Odesa", roomsHotel2, 4);
    Hotel hotel3 = new Hotel(3, "Hyatt", "Istanbul", roomsHotel3, 3);
    Hotel hotel4 = new Hotel(4, "Grand Palace", "Beijing", roomsHotel4, 4);
    Hotel hotel5 = new Hotel(5, "Vavilon", "Bombei", roomsHotel5, 4);

    /**
     * Метод вызываеться при обьеденении отелей в 1ин список
     */
    //Merging hotels into one list
    @Override
    public void addHotels() {
        hotels.add(hotel1);
        hotels.add(hotel2);
        hotels.add(hotel3);
        hotels.add(hotel4);
        hotels.add(hotel5);
    }

    /**
     * Копирование списков отелей
     * @return новый список отелей
     */
    //Copying hotel lists
    //Copying hotel lists to mutable list
    @Override
    public List<Hotel> getHotels() {
        ArrayList<Hotel> res = new ArrayList<Hotel>(hotels);
        return res;
    }

    /**
     * Полная база данных для всех сгенерированых номкеров из всех списков
     * @return база данных номеров
     */
    //Full Database for all generated rooms from all lists
    @Override
    public List<Room> allRoomsDB() {
        addGeneratedRooms();
        roomsDatabase.addAll(roomsHotel1);
        roomsDatabase.addAll(roomsHotel2);
        roomsDatabase.addAll(roomsHotel3);
        roomsDatabase.addAll(roomsHotel4);
        roomsDatabase.addAll(roomsHotel5);
        return roomsDatabase;
    }


    //Getter for the all rooms list
    /**
     * Метод вызывающийся при получении списка пользователей
     * @return Список пользователей
     */
    //Getter for the user list

    @Override
    public List<Room> getRoomDB() {
        return roomsDatabase;
    }

    //Getter for full user list
    @Override
    public List<User> getUserDB() {
        return userList;
    }
}

