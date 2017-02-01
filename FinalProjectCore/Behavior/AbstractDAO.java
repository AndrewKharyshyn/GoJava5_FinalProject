package FinalProjectCore.Behavior;

import FinalProjectCore.Objects.*;

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

    void addUsersToDB();//Adding users to the list

    /**
     * Создание произвольного номера
     * Реализация метода в классе AbstractDAOImpl
     *
     * @param hotelID уникальный идентификатор отеля
     * @return
     */

    Room generateRoom(int hotelID); //Generating random rooms

    /**
     * Генерация поизвольный номеров
     */
    void addGeneratedRooms(); //filling the list with random rooms

    /**
     * Добавление отеля в списки
     * Реализация метода в классе AbstractDAOImpl
     */
    void addHotels(); //Adding hotels to the list

    /**
     * Поиск отеля в списках
     *
     * @return отель
     */
    List<Hotel> getHotels();

    /**
     * Добавление нового пользователя
     *
     * @param user Пользователь
     */
    void addingNewUser(User user);

    List<Room> allRoomsDB();

    List<Room> getRoomDB();//Get rooms from Database

    User generateDefaultUser();

    List<User> getUserDB();

}

