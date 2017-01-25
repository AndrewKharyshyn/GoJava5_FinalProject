package Logics;

import java.util.*;

import Behavior.*;
import Objects.*;

public class AbstractDAOImpl implements AbstractDAO {

    //Here are the predefined lists
    private List<User> userList = new ArrayList<>();
    private List<Room> roomsHotel1 = new ArrayList<>();
    private List<Room> roomsHotel2 = new ArrayList<>();
    private List<Room> roomsHotel3 = new ArrayList<>();
    private List<Hotel> hotels = new ArrayList<>();

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

    //Copying the user list to other list
    @Override
    public List<User> getUsers() {
        ArrayList<User> res = new ArrayList<User>(userList);
        return res;
    }

    //Adding newly created User to existing User list
    @Override
    public void addingNewUser(User user) {
        userList.add(user);
    }

    //Creating the random rooms with predefined and limited parameters
    private int roomId = 0;
    private Random random = new Random();

    @Override
    public Room generateRoom(int hotelID) {
        double price = random.nextInt(5001);
        return new Room(++roomId, random.nextInt(5),
                price, hotelID,
                random.nextBoolean(),
                random.nextInt(26),
                defaultUser);
    }

    //Adding random rooms to the lists with loop
    @Override
    public void addGeneratedRooms() {
        for (int i = 0; i < 10; i++) {
            roomsHotel1.add(generateRoom(1));
            roomsHotel2.add(generateRoom(2));
            roomsHotel3.add(generateRoom(3));
        }
    }

    //Creating hotel objects with lists inside
    Hotel hotel1 = new Hotel(1, "President Hotel", "Kyiv", roomsHotel1);
    Hotel hotel2 = new Hotel(2, "Hyatt", "Odesa", roomsHotel2);
    Hotel hotel3 = new Hotel(3, "Hyatt", "Istanbul", roomsHotel3);

    //Merging hotels into one list
    @Override
    public void addHotels() {
        hotels.add(hotel1);
        hotels.add(hotel2);
        hotels.add(hotel3);
    }

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

