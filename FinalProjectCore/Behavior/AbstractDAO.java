package FinalProjectCore.Behavior;

import FinalProjectCore.Objects.*;

import java.util.List;

public interface AbstractDAO {

    void addUsersToDB();//Adding users to the list

    Room generateRoom(int hotelID); //Generating random rooms

    void addGeneratedRooms(); //filling the list with random rooms

    void addHotels(); //Adding hotels to the list

    List<Hotel> getHotels();

    void addingNewUser(User user);

    List<Room> allRoomsDB();

    List<Room> getRoomDB();//Get rooms from Database

    User generateDefaultUser();

    List<User> getUserDB();

}

