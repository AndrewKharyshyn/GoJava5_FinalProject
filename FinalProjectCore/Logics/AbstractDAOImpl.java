package FinalProjectCore.Logics;

import FinalProjectCore.Objects.*;
import FinalProjectCore.Behavior.*;

import java.util.*;

public class AbstractDAOImpl implements AbstractDAO {

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
        return new Room(++roomId, random.nextInt((4 - 1) + 1) + 1,
                price, hotelID,
                random.nextBoolean(),
                random.nextInt(25),
                generateDefaultUser());
    }

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

    //Creating hotel objects with lists inside
    Hotel hotel1 = new Hotel(1, "President Hotel", "Kyiv", roomsHotel1, 5);
    Hotel hotel2 = new Hotel(2, "Hyatt", "Odesa", roomsHotel2, 4);
    Hotel hotel3 = new Hotel(3, "Hyatt", "Istanbul", roomsHotel3, 3);
    Hotel hotel4 = new Hotel(4, "Grand Palace", "Beijing", roomsHotel4, 4);
    Hotel hotel5 = new Hotel(5, "Vavilon", "Bombei", roomsHotel5, 4);

    //Merging hotels into one list
    @Override
    public void addHotels() {
        hotels.add(hotel1);
        hotels.add(hotel2);
        hotels.add(hotel3);
        hotels.add(hotel4);
        hotels.add(hotel5);
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
        roomsDatabase.addAll(roomsHotel1);
        roomsDatabase.addAll(roomsHotel2);
        roomsDatabase.addAll(roomsHotel3);
        roomsDatabase.addAll(roomsHotel4);
        roomsDatabase.addAll(roomsHotel5);
        return roomsDatabase;
    }

    //Getter for the all rooms list
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

