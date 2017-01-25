package Behavior;

import java.util.List;

import Objects.*;

public interface AbstractDAO {

    void addUsersToDB();//Adding users to the list

    List<User> getUsers(); //Getting the copy of user list

    Room generateRoom(int hotelID); //Generating random rooms

    void addGeneratedRooms(); //filling the list with random rooms

    void addHotels(); //Adding hotels to the list

    List<Hotel> getHotels();

    void addingNewUser(User user);

    List<Room> allRoomsDB ();

    List<User> getUserList();
}

