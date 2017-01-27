package Behavior;

import java.util.List;


/**
 * Класс DAO
 * Главная задача DAO: Постройка моста между реляционной и объектной моделями данных
 */
public interface AbstractDAO {
    /**
     * Добавление юзера в списки
     * Реализация метода в классе AbstractDAOImpl
     */
    void addUserTable();//Adding users to the list
import Objects.*;

public interface AbstractDAO {

    void addUsersToDB();//Adding users to the list

    /**
     * Поиск пользователя в списке
     * Реализация метода в классе AbstractDAOImpl
     * @return
     */
    List<User> getUsers(); //Getting the copy of user list

    /**
     * Создание произвального номера
     * Реализация метода в классе AbstractDAOImpl
     * @param hotelID уникальный идентификатор отеля
     * @return
     */

    Room generateRoom(int hotelID); //Generating random rooms


    /**
     * Заполение списка произвольных номеров
     * Реализация метода в классе AbstractDAOImpl
     */

    void addUserRoom(); //filling the list with random rooms

    void addGeneratedRooms(); //filling the list with random rooms


    /**
     * Добавление отеля в списки
     * Реализация метода в классе AbstractDAOImpl
     */
    void addHotels(); //Adding hotels to the list

    /**
     * Поиск отеля в списках
     * @return отель
     */

    List<Hotel> getHotels();

    /**
     * Добавление нового пользователя
     * @param user Пользователь
     */
    void addingNewUser(User user);

    List<Room> allRoomsDB ();

    List<User> getUserList();
}

