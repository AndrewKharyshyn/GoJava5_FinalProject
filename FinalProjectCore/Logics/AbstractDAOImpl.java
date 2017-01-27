package Logics;

import java.util.*;


/**
 * Реализация DAO
 * Главная задача DAO: Постройка моста между реляционной и объектной моделями данных
 */
=======
import Behavior.*;
import Objects.*;

public class AbstractDAOImpl implements AbstractDAO {
    /**
     * Предопределенные Списки
     * Возможность быстрого доступа к данным
     * Возможность быстрого добавления/даления обьектов
     */
    //Here are the predefined lists
    private List<User> userList = new ArrayList<>();
    private List<Room> roomsHotel1 = new ArrayList<>();
    private List<Room> roomsHotel2 = new ArrayList<>();
    private List<Room> roomsHotel3 = new ArrayList<>();
    private List<Hotel> hotels = new ArrayList<>();


    /**
     * Метод вызывается при добавлении  предопределенного пользователя в списки по 3ом параметрам
     * Уникальный идентификатор пользоватедя
     * Имя пользователя
     * Фамилия пользователя
     */

    //Default (virtual) User is added to empty rooms to prevent NULL
    User defaultUser = new User(0, "noName", "noLastName");

    //Adding predefined users to the list
    @Override
    public void addUsersToDB() {
        User user1 = new User(1, "Alex", "Melnikov");
        User user2 = new User(2, "Olga", "Safonova");
        User user3 = new User(3, "Oleg", "Petrov");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
    }

    /**
     * Метод вызываеться при копировании пользователя во все остальные списки
     * @return
     */
    //Copying the user list to other list
    @Override
    public List<User> getUsers() {
        ArrayList<User> res = new ArrayList<User>(userList);
        return res;
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
    //Creating the random rooms
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
        return new Room(++roomId, random.nextInt(5),
                price, hotelID,
                random.nextBoolean(),
                random.nextInt(26),
                defaultUser);
    }

    //Room room1 = new Room(1, 2, 520, true, 0, null);
//    Room room2 = new Room(2, 1, 360, 1, true, 5, null);
//    Room room3 = new Room(3, 4, 1250, 1, false, 3, null);
//    Room room4 = new Room(4, 1, 400, 1, false, 3, null);
//    Room room5 = new Room(5, 2, 500, 1, true, 10, null);
//    Room room6 = new Room(6, 1, 1100, 1, true, 15, null);
//    Room room7 = new Room(7, 1, 150, 1, false, 0, null);
//    Room room8 = new Room(8, 2, 1500, 1, true, 2, null);
//    Room room9 = new Room(9, 2, 1000, 1, true, 0, null);
//    Room room10 = new Room(10, 3, 2500, 1, true, 0, null);
//
//    Room room11 = new Room(11, 3, 750, 2, true, 10, null);
//    Room room12 = new Room(12, 2, 600, 2, false, 7, null);
//    Room room13 = new Room(13, 4, 1250, 2, false, 15, null);
//    Room room14 = new Room(14, 1, 800, 2, false, 5, null);
//    Room room15 = new Room(15, 3, 2500, 2, true, 0, null);
//    Room room16 = new Room(16, 1, 850, 2, true, 0, null);
//    Room room17 = new Room(17, 1, 550, 2, false, 5, null);
//    Room room18 = new Room(18, 4, 1500, 2, false, 20, null);
//    Room room19 = new Room(19, 2, 1000, 2, true, 0, null);
//    Room room20 = new Room(20, 2, 2500, 2, true, 0, null);

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
        }
    }

    /**
     * Создание Гостиничных номеров внутри списков
     */
    //Creating hotel objects with lists inside
    Hotel hotel1 = new Hotel(1, "President Hotel", "Kyiv", roomsHotel1);
    Hotel hotel2 = new Hotel(2, "Hyatt", "Odesa", roomsHotel2);
    Hotel hotel3 = new Hotel(3, "Hyatt", "Istanbul", roomsHotel3);

    /**
     * Метод вызываеться при обьеденении отелей в 1ин список
     */
    //Merging hotels into one list
    @Override
    public void addHotels() {
        hotels.add(hotel1);
        hotels.add(hotel2);
        hotels.add(hotel3);
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

    //Full Database for all generated rooms from all lists
    @Override
    public List<Room> allRoomsDB() {
        addGeneratedRooms();
        List<Room> roomsDatabase = new ArrayList<>();
        roomsDatabase.addAll(roomsHotel1);
        roomsDatabase.addAll(roomsHotel2);
        roomsDatabase.addAll(roomsHotel3);
        return roomsDatabase;
    }

    //Getter for the user list
    @Override
    public List<User> getUserList() {
        return userList;
    }

}

