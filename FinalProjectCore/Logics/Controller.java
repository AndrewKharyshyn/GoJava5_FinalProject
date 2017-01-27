package Logics;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Класс Контроллер
 * Предназначен для непосредственной обработки запросов от клиента и возвращения результатов.
 */
=======
import Behavior.*;
import Objects.*;


public class Controller {

    AbstractDAO abstractDAOImpl = new AbstractDAOImpl();

    private long userLoggedInID; //To pass current user ID to methods
    private Scanner scanner = new Scanner(System.in);
    private String nameRequest = "\tPlease, enter your name...";
    private String lastNameRequest = "\tPlease, enter your last name...";
    private String blankFieldErr = "\tField is blank or less then 4 symbols. Please, input again...";

    /**
     * Метож вызывается при вводе данных для сканирования
     * @param promptMessage строка ввода
     * @param errorMessage сообщение об ошибочном вводе
     * @return вводные данные
     */
    //Method used for scanning input data
    private String getUserInput(String promptMessage, String errorMessage) {
        System.out.println(promptMessage);
        String userInput = scanner.nextLine();
        while (userInput.isEmpty() || userInput.length() < 4) {
            System.out.println(errorMessage);
            userInput = scanner.nextLine();
        }
        return userInput;
    }


    /**
     * Вход в систему
     * Для входа в систему нужно ввести данные пользователя (Имя,Фамилия)
     */
    //Entrance to the system
    void systemEnter() {
        abstractDAOImpl.addUserRoom();

    //Entrance to the system at startup
    public void systemEnter() {
        abstractDAOImpl.allRoomsDB();

        abstractDAOImpl.addHotels();
        abstractDAOImpl.addUsersToDB();

        System.out.println("Welcome to the Hotel Online Booking System!" +
                "\n====================================");

        String s1 = getUserInput("\tPlease, enter your name...", "\tField is blank or less then 4 symbols. Please, input again...");
        String s2 = getUserInput("\tPlease, enter your last name...", "\tField is blank or less then 4 symbols. Please, input again...");
/**
 * Проверка наличия данного пользователя в системе
 * Если пользователь не найден,предлагается регистрация для дальнейшего пользования сервисом
 * Если пользователь найден в списках, производится его автовизация
 */
        //Checking if user exists in the system

        String name = getUserInput(nameRequest, blankFieldErr);
        String lastName = getUserInput(lastNameRequest, blankFieldErr);

        //Checking if user exists in the system DB already

        List<User> users = abstractDAOImpl.getUsers()
                .stream()
                .filter(u -> u.getUserName().equalsIgnoreCase(name) && u.getUserLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());

        if (users.isEmpty()) {
            System.out.println("User does not exist. Please, register your account to enable search" +
                    "\n\tRedirecting to the registration server..." +
                    "\n\t====================================");
            createNewUser();
        }
        if (!users.isEmpty()) {
            System.out.println("User " + name + " " + lastName + " has been logged in.");
            userLoggedInID = users.stream().findFirst().get().getUserId();
            actionSelect(true);
        }
    }


    /**
     * Метод вызыватся при добавлени нового пользователя
     * Если пользователь не был найден в списках
     */
    //Signing up new user (if does not exists yet)
    void newUser() {

    //Signing up new user (if does not exist yet)
    private void createNewUser() {

        System.out.println("\tUser's sign up system" +
                "\n");

        String name = getUserInput(nameRequest, blankFieldErr);
        String lastName = getUserInput(lastNameRequest, blankFieldErr);

        User newUser = new User(findNewUserID(), name, lastName); //Creating new User

        abstractDAOImpl.addingNewUser(newUser);//Adding new User to the user list
        System.out.println("New user " + name + " " + lastName + " has been registered successfully!" +
                "\n\tNow you can proceed to Room Search System..." +
                "\n");
        userLoggedInID = findNewUserID();
        actionSelect(true);
    }

    /**
     * Метод вызываться при присваивании новому пользователю уникального идентификатора
     * @return никальный идентификатор
     */
    //Used to find the user's max ID in the user list
    private long findNewUserID() {
        return abstractDAOImpl.getUsers().size() + 1;
    }

    /**
     *Меню для дальнейшых действий пользователя
     * Выбор нужного типа поиска (Название отеля, по городу, поиск номера по параметрам)
     * Поиск номеров
     * Поиск отелей
     * @param isLoggedIn Автовизированый пользователь
     */
    //Menu to select further user's search action
    private void actionSelect(boolean isLoggedIn) {

        System.out.println("Please, choose the required search type in the menu below:" +
                "\n\t1. Search by hotel name;" +
                "\n\t2. Search hotel by city.");

        String input = scanner.nextLine();
        while (input.isEmpty() || !input.equalsIgnoreCase("1") || !input.equalsIgnoreCase("2")) {
            System.out.println("Please, choose the correct menu!");
            input = scanner.nextLine();
        }

        switch (input) {
            case "1":
                String hotelName = getUserInput("Please, enter the hotel name...", blankFieldErr);
                findHotelByName(hotelName);
                break;
            case "2":
                String cityName = getUserInput("Please, enter the city name...", blankFieldErr);
                findHotelByCity(cityName);
                break;
        }
    }

<<<<<<< HEAD
    /**
     * Поиск отеля по его названию
     * @param name название отлея
     * @return результат поиска
     */
    //Searching the hotel by its name
    List<Hotel> findHotelByName(String name) {
=======
    //Searching the hotel by its name to get rooms in this hotel
    private List<Hotel> findHotelByName(String name) {
>>>>>>> 8b391db7c2e3f8c81e9e8e249800f8956c40a0b3
        List<Hotel> foundHotels = abstractDAOImpl.getHotels()
                .stream()
                .filter(m -> m.getHotelName().contains(name))
                .collect(Collectors.toList());

        if (foundHotels.isEmpty()) {
            System.out.println("Hotels with name '" + name + "' not found. Please, check the spelling and retry.");
            actionSelect(true);
        }

        if (!foundHotels.isEmpty()) {
            System.out.println("List of hotels available:");
            foundHotels.forEach(c -> System.out.println("Hotel '" + c.getHotelName() + "'" + " /" + c.getCity() + "/"));

            System.out.println("Please, press Enter to show rooms in chosen hotel(s)...");
            String enterKey = scanner.nextLine();

            List<Room> selectedRooms = new ArrayList<>();
            foundHotels.forEach(c -> selectedRooms
                    .addAll(c.getRooms()));

            System.out.println("Rooms available in hotel(s):");
            selectedRooms.forEach(c -> System.out.println("Room number: " + c.getRoomId() +
                    ", Persons: " + c.getPersons() + ", Original price: " + (int) c.getPrice() +
                    ", Price (including discount " + (int) c.getDiscount() + "%): " +
                    Math.round(c.getPrice() - (c.getPrice() * c.getDiscount() / 100)) +
                    ", Hotel: " + c.getHotelID()));

            variantSelect(selectedRooms);
        }
        return foundHotels;
    }

    /**
     * Поиск отеля по городу
     * @param city город
     * @return реззультат
     */
    //Searching hotels by its city
    private List<Hotel> findHotelByCity(String city) {
        List<Hotel> foundHotels = abstractDAOImpl.getHotels()
                .stream()
                .filter(m -> m.getCity().contains(city))
                .collect(Collectors.toList());

        if (foundHotels.isEmpty()) {
            System.out.println("Hotels in '" + city + "' not found. Please, check information and retry.");
            actionSelect(true);
        }

        if (!foundHotels.isEmpty()) {
            System.out.println("List of hotels available in " + city + ":");
            foundHotels.forEach(c -> System.out.println("\tHotel '" + c.getHotelName() + "'"));

            System.out.println("Please, press ENTER to show all rooms in city...");
            String enterKey = scanner.nextLine();

            System.out.println("Rooms in " + city + ":");

            List<Room> selectedRooms = new ArrayList<>();
            foundHotels.forEach(c -> selectedRooms.addAll(c.getRooms()));

            selectedRooms.forEach(c -> System.out.println("Room number: " + c.getRoomId() +
                    ", Persons: " + c.getPersons() + ", Original price: " + (int) c.getPrice() +
                    ", Price (including discount " + (int) c.getDiscount() + "%): "
                    + Math.round(c.getPrice() - (c.getPrice() * c.getDiscount() / 100)) +
                    ", Hotel: " + c.getHotelID()));
            variantSelect(selectedRooms);

            //Map<String, String> byCityParam = new HashMap<>();
            //byCityParam.put("byCity", );
        }
        return foundHotels;
    }

<<<<<<< HEAD
    /**
     * Бронирование номера в отлее
     * @param roomId уникальный идентификатор номера отеля
     * @param userId уникальный идентификатор пользователя
     * @param hotelId уникальный идентификатор отеля
     */
    //Booking selected room
    void bookRoom(long roomId, long userId, long hotelId) {
=======
    //Method used to select if user wants to pass to booking module directly
    private void variantSelect(List<Room> filteredList) {
>>>>>>> 8b391db7c2e3f8c81e9e8e249800f8956c40a0b3

        System.out.println("Do you want to book the room by number?" +
                "\n\t1. Book the room." +
                "\n\t2. Go to search by parameters.");
        String input = scanner.nextLine();

<<<<<<< HEAD
    /**
     * Отмена бронирования номера в отеле
     * @param roomId уникальный идентификатор номера отеля
     * @param userId уникальный идентификатор пользователя
     * @param hotelId уникальный идентификатор отеля
     */
    //Cancelling reservation of the selected room
    void cancelReservation(long roomId, long userId, long hotelId) {
=======
        while (input.isEmpty() || !input.equalsIgnoreCase("1") || !input.equalsIgnoreCase("2")) {
            System.out.println("Please, choose the correct menu!");
            input = scanner.nextLine();
        }
>>>>>>> 8b391db7c2e3f8c81e9e8e249800f8956c40a0b3

        switch (input) {
            case "1":
                System.out.println("Enter room number from the list above");
                String roomNumberInput = scanner.nextLine();

<<<<<<< HEAD
    /**
     * Поиск номера по городам или отелям
     * @param params параметры требуемого номера
     * @return результат поиска
     */
    //Searching rooms by cities or hotels
    List<Room> findRoom(Map<String, String> params) {
        Collection<Hotel> hotels = abstractDAOImpl.getHotels();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            switch (entry.getKey()) {
                case "city":
                    hotels
                            .stream()
                            .filter(h -> h.getCity().equals(entry.getValue()))
                            .collect(Collectors.toList());
                    break;
                case "hotel":
                    hotels
=======
                while (roomNumberInput.isEmpty()) {
                    System.out.println("Please, choose the correct room number from the list");
                    roomNumberInput = scanner.nextLine();

                }

                int searchRoomID = Integer.parseInt(roomNumberInput);//variable must be final before anyMatch()

                //Getting Hotel ID from the chosen Room using RoomID
                if (filteredList.stream().anyMatch(roomNo -> roomNo.getRoomId() == searchRoomID)) {
                    long hotelNo = filteredList
>>>>>>> 8b391db7c2e3f8c81e9e8e249800f8956c40a0b3
                            .stream()
                            .findFirst()
                            .get()
                            .getHotelID();
                    System.out.println("Room " + searchRoomID + " has been chosen. Booking...");
                    bookRoom(searchRoomID, userLoggedInID, hotelNo);
                }

                if (filteredList.stream().noneMatch(roomNo -> roomNo.getRoomId() == searchRoomID)) {
                    System.out.println("There is no such room number in the list.");
                }
                break;
            case "2":
                //   findRoomByParams();
                break;
        }
    }

    //Booking the selected room
    private void bookRoom(long roomId, long userId, long hotelId) {

        //Getting the current User from user list by user ID
        User registeredUser = abstractDAOImpl.getUserList().get(abstractDAOImpl.getUserList().size());

        //Setting up current User to selected room (booking room)
        abstractDAOImpl.allRoomsDB().forEach(c -> {
            if (c.getRoomId() == roomId && c.getHotelID() == hotelId) {
                if (c.getUserReserved().getUserId() == userId) {
                    c.setUserReserved(registeredUser);
                }
            }
        });

        System.out.println("Room " + roomId + "has been booked successfully!" +
                "\n\tIf you want to cancel this reservation, press 'C'" +
                "\n\t\tPress 'E' to exit");

        String selection = scanner.nextLine();
        while (selection.isEmpty() || !selection.equalsIgnoreCase("C") || !selection.equalsIgnoreCase("E")) {
            System.out.println("Please, choose the correct action variant");
            selection = scanner.nextLine();
        }

        switch (selection) {
            case "C":
                cancelReservation(roomId, userId, hotelId);
            case "E":
                System.out.println("Thank you for using Book Online System!" +
                        "=========================================");
                System.exit(0);
        }
    }

    //Cancelling reservation of the selected room
    private void cancelReservation(long roomId, long userId, long hotelId) {


        System.out.println("You have cancelled the reservation of room " + roomId +
                "\nThank you for using Book Online System!" +
                "=========================================");
        System.exit(0);
    }

    /**
     * Посик по параметрам
     * Кол-во чел-к
     * Цена
     * Дополнительные услуги в номере
     * @param roomList Список номеров
     * @return результат поиска
     */
    //Parametrized search of the rooms
    private Map<String, String> findRoomByParams(List<Room> roomList) {
        String persons = scanner.nextLine(); //persons
        String price = scanner.nextLine();//price
        String service = scanner.nextLine();//services

        System.out.println("You can filter rooms by parameters here:" +
                "\nPlease, enter the required points where necessary:"
                + "\n\t1. Number of persons."
                + "\n\t2. Price."
                + "\n\t3. Additional services in the room (Please, enter 'Y' or 'N')");

        int s1Num = Integer.parseInt(persons);
        int s2Num = Integer.parseInt(price);

        switch (service) {
            case "Y":
                boolean s3Bool = Boolean.parseBoolean("true");
                break;
            case "N":
                s3Bool = Boolean.parseBoolean("false");
                break;
            default:
                //
        }

        List<Room> finalRes = roomList
                .stream()
                .filter(m -> m.getPersons() <= s1Num || s1Num == 0
                        && m.getPrice() <= s2Num
                        && m.isHasAdditionalService())
                .collect(Collectors.toList());

        return null;
    }

<<<<<<< HEAD
    /**
     * Метод проверяет автовизацию пользователя
     * @param isLoggedIn
     */
    void logInCheck(boolean isLoggedIn) {
=======
    //Method checks if user is properly logged in before every action performed (using global variable)
    private void logInCheck(boolean isLoggedIn) {

>>>>>>> 8b391db7c2e3f8c81e9e8e249800f8956c40a0b3
        if (!isLoggedIn) {
            System.out.println("User not registered in the system." +
                    "\n\tDo you want to continue?" +
                    "\n\tPress 'Y' to register or press 'N' to exit.");
            String input = scanner.nextLine();

            while (input.isEmpty() || !input.equalsIgnoreCase("N") || !input.equalsIgnoreCase("Y")) {
                System.out.println("Please, choose the correct variant!");
                input = scanner.nextLine();
            }

            switch (input) {
                case "Y":
                    createNewUser();
                    break;
                case "N":
                    System.out.println("Thank you for using Book Online System!");
                    System.exit(0);
                    break;
            }
        }
    }
}

