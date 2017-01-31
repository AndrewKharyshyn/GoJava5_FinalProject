package com.booking;


import java.util.List;

import com.booking.model.Hotel;
import com.booking.model.Room;
import com.booking.model.User;


public interface AbstractDAO {

    void addUserTable();//Adding users to the list

    List<User> getUsers(); //Getting the copy of user list

    Room generateRoom(int hotelID); //Generating random rooms

    void addUserRoom(); //filling the list with random rooms

    void addHotels(); //Adding hotels to the list

    List<Hotel> getHotels();

    void addingNewUser(User user);
}


