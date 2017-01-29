package Logics;

import Behavior.*;
import Objects.*;

import java.util.*;
import java.util.stream.Collectors;

public class Controller {

    AbstractDAO abstractDAOImpl = new AbstractDAOImpl();
    private long userLoggedInID; //To pass current user ID to methods
    private Scanner scanner = new Scanner(System.in);
    private String nameRequest = "\tPlease, enter your name...";
    private String lastNameRequest = "\tPlease, enter your last name...";
    private String blankFieldErr = "\tField is blank or less then 4 symbols. Please, input again...";
    private String goodByeScreen = "\nThank you for using Book Online System!" +
            "\n=======================================";

    //Method used for scanning String input data
    private String getStringInput(String promptMessage, String errorMessage) {
        System.out.println(promptMessage);
        String userInput = scanner.nextLine();
        while (userInput.isEmpty() || userInput.length() < 4) {
            System.out.println(errorMessage);
            userInput = scanner.nextLine();
        }
        return userInput;
    }

    //Entrance to the system at startup
    public void systemEnter() {
        abstractDAOImpl.allRoomsDB();
        abstractDAOImpl.addHotels();
        abstractDAOImpl.addUsersToDB();

        System.out.println("Welcome to the Hotel Online Booking System!" +
                "\n====================================");
        String name = getStringInput(nameRequest, blankFieldErr);
        String lastName = getStringInput(lastNameRequest, blankFieldErr);

        //Checking if user exists in the system DB already
        List<User> users = abstractDAOImpl.getUserDB()
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
            System.out.println("User " + name + " " + lastName + " has been successfully logged in.");

            //Getting the user's ID (will be used for ID-based search)
            userLoggedInID = users
                    .stream()
                    .filter(u -> u.getUserName().equalsIgnoreCase(name) && u.getUserLastName().equalsIgnoreCase(lastName))
                    .findFirst()
                    .get()
                    .getUserId();

            actionSelect(true);
        }
    }

    //Signing up new user (if does not exist yet)
    private void createNewUser() {
        System.out.println("\tUser's sign up system" +
                "\n");

        String name = getStringInput(nameRequest, blankFieldErr);
        String lastName = getStringInput(lastNameRequest, blankFieldErr);

        User newUser = new User(findNewUserID(), name, lastName); //Creating new User

        abstractDAOImpl.addingNewUser(newUser);//Adding new User to the user list
        System.out.println("New user " + name + " " + lastName + " has been registered successfully!" +
                "\n\tNow you can proceed to Room Search System..." +
                "\n");

        userLoggedInID = findNewUserID() - 1;
        actionSelect(true);
    }

    //Used to find the user's max ID in the user list
    private long findNewUserID() {
        return abstractDAOImpl.getUserDB().size() + 1;
    }

    //Menu to select further user's search action
    private void actionSelect(boolean isLoggedIn) {
        logInCheck(isLoggedIn);

        System.out.println("Please, choose the required search type in the menu below:" +
                "\n\t1. Search by hotel name;" +
                "\n\t2. Search hotel by city.");

        String choice = scanner.nextLine();
        while (!choice.equals("1") && !choice.equals("2") || choice.isEmpty()) {
            System.out.println("Please, choose the correct menu! Enter '1' or '2'...");
            choice = scanner.nextLine();
        }

        switch (choice) {
            case "1":
                String hotelName = getStringInput("Please, enter the hotel name...", blankFieldErr);
                findHotelByName(hotelName, isLoggedIn);
                break;
            case "2":
                String cityName = getStringInput("Please, enter the city name...", blankFieldErr);
                findHotelByCity(cityName, isLoggedIn);
                break;
        }
    }

    //Searching the hotel by its name to get rooms in this hotel
    private List<Hotel> findHotelByName(String name, boolean isLoggedIn) {
        logInCheck(isLoggedIn);
        List<Hotel> foundHotels = abstractDAOImpl.getHotels()
                .stream()
                .filter(m -> m.getHotelName().contains(name))
                .collect(Collectors.toList());

        if (foundHotels.isEmpty()) {
            System.out.println("Hotel(s) with name '" + name + "' not found. Please, check the spelling and retry." +
                    "\n");
            actionSelect(true);
        }

        if (!foundHotels.isEmpty()) {
            System.out.println("\tList of hotels available:");
            foundHotels.forEach(c -> System.out.println(starCount(c.getStars()) + " Hotel '" + c.getHotelName() + "' /" + c.getStars() + " stars/(" + c.getCity() + ")"));

            System.out.println("Please, press Enter to show all rooms in chosen hotel(s)...");
            String enterKey = scanner.nextLine();

            List<Room> selectedRooms = new ArrayList<>();
            foundHotels.forEach(c -> selectedRooms.addAll(c.getRooms()));

            System.out.println("Rooms available in hotel(s):");

            selectedRooms.forEach(c -> System.out.println("Room number: " + c.getRoomId() +
                    ", Persons: " + c.getPersons() + ", Original price: " + (int) c.getPrice() +
                    ", Price (including discount " + (int) c.getDiscount() + "%): " +
                    Math.round(c.getPrice() - (c.getPrice() * c.getDiscount() / 100)) +
                    ", Hotel: " + abstractDAOImpl.getHotels().stream()
                    .filter(n -> n.getHotelId() == c.getHotelID())
                    .findFirst().get().getHotelName() +

                    "(" + abstractDAOImpl.getHotels().stream() //Hotel city
                    .filter(n -> n.getHotelId() == c.getHotelID())
                    .findFirst().get().getCity() + ")" +

                    "/" + abstractDAOImpl.getHotels().stream() //Hotel stars
                    .filter(n -> n.getHotelId() == c.getHotelID())
                    .findFirst().get().getStars() + " stars/"
            ));
            System.out.println("");

            variantSelect(selectedRooms, isLoggedIn);
        }
        return foundHotels;
    }

    //Searching hotels by its city
    private List<Hotel> findHotelByCity(String city, boolean isLoggedIn) {
        logInCheck(isLoggedIn);

        List<Hotel> foundHotels = abstractDAOImpl.getHotels()
                .stream()
                .filter(m -> m.getCity().contains(city))
                .collect(Collectors.toList());

        if (foundHotels.isEmpty()) {
            System.out.println("Hotel(s) in '" + city + "' not found. Please, check information and retry.");
            actionSelect(true);
        }

        if (!foundHotels.isEmpty()) {
            System.out.println("List of hotels available in " + city + ":");
            foundHotels.forEach(c -> System.out.println(starCount(c.getStars()) + " Hotel '" + c.getHotelName() + "'" + " /" + c.getStars() + " stars" + "/"));

            System.out.println("Please, press ENTER to show all rooms in city...");
            String enterKey = scanner.nextLine();

            System.out.println("Rooms in " + city + ":\n");

            List<Room> selectedRooms = new ArrayList<>();
            foundHotels.forEach(c -> selectedRooms.addAll(c.getRooms()));

            selectedRooms.forEach(c -> System.out.println("Room number: " + c.getRoomId() +
                    ", Persons: " + c.getPersons() + ", Original price: " + (int) c.getPrice() +
                    ", Price (including discount " + (int) c.getDiscount() + "%): "
                    + Math.round(c.getPrice() - (c.getPrice() * c.getDiscount() / 100)) +
                    ", Hotel: " + abstractDAOImpl.getHotels().stream()
                    .filter(n -> n.getHotelId() == c.getHotelID())
                    .findFirst().get().getHotelName() +

                    "(" + abstractDAOImpl.getHotels().stream() //Hotel city
                    .filter(n -> n.getHotelId() == c.getHotelID())
                    .findFirst().get().getCity() + ")" +

                    " /" + abstractDAOImpl.getHotels().stream()
                    .filter(n -> n.getHotelId() == c.getHotelID())
                    .findFirst().get().getStars() + " stars/"
            ));
            System.out.println("");
            variantSelect(selectedRooms, isLoggedIn);
        }
        return foundHotels;
    }

    //Method used to select if user wants to pass to booking module directly
    private void variantSelect(List<Room> filteredList, boolean isLoggedIn) {
        logInCheck(isLoggedIn);

        System.out.println("Do you want to book the room by number?" +
                "\n\t1. Book the room." +
                "\n\t2. Go to filter rooms by parameters.");
        String input = scanner.nextLine();

        while (!input.equals("1") && !input.equals("2") || input.isEmpty()) {
            System.out.println("Please, choose the correct menu!");
            input = scanner.nextLine();
        }

        switch (input) {
            case "1":
                System.out.println("Enter room number from the list above");
                String roomNumberInput = scanner.nextLine();

                while (!isDigit(roomNumberInput) || roomNumberInput.isEmpty()) {
                    System.out.println("Please, choose the correct room number from the list");
                    roomNumberInput = scanner.nextLine();
                }

                int searchRoomID = Integer.parseInt(roomNumberInput);//variable must be final before anyMatch()

                //Getting Hotel ID from the chosen Room using RoomID
                if (filteredList.stream().anyMatch(roomNo -> roomNo.getRoomId() == searchRoomID)) {
                    long hotelNo = filteredList
                            .stream()
                            .findFirst()
                            .get()
                            .getHotelID();
                    System.out.println("Room " + searchRoomID + " has been chosen. " +
                            "\n\t***Booking...***" +
                            "\n");
                    bookRoom(searchRoomID, userLoggedInID, hotelNo, isLoggedIn);
                }

                if (filteredList.stream().noneMatch(roomNo -> roomNo.getRoomId() == searchRoomID)) {
                    System.out.println("There is no such room number found in the list. Please retry search...");
                    variantSelect(filteredList, isLoggedIn);
                }
                break;
            case "2":
                createParamRequest(isLoggedIn, filteredList);
                break;
        }
    }

    //Booking the selected room
    private void bookRoom(long roomId, long userId, long hotelId, boolean isLoggedIn) {
        logInCheck(isLoggedIn);

        //Getting the current User from user list by user ID
        User registeredUser = abstractDAOImpl
                .getUserDB().stream().filter(c -> c.getUserId() == userId).findAny().get();

        //Setting up current User to selected room (booking room with User object)
        abstractDAOImpl.getRoomDB()
                .stream()
                .forEach(c -> {
                    if (c.getRoomId() == roomId && c.getHotelID() == hotelId) {
                        c.setUserReserved(registeredUser);
                        System.out.println("Booking confirmation:" +
                                "\n\tRoom ID: " + c.getRoomId() +
                                ". Guest registered: " + c.getUserReserved().getUserName() + " " + c.getUserReserved().getUserLastName() +
                                "\n\tPersons: " + c.getPersons() + "; Price due (discounted): " +
                                Math.round(c.getPrice() - (c.getPrice() * c.getDiscount() / 100)) + " (Discount " + (int) c.getDiscount() + "%)" +
                                "\n");
                    }
                });

        System.out.println("Room " + roomId + " has been booked successfully!" +
                "\n\tIf you want to cancel this reservation, press 'C'" +
                "\n\t\tPress 'E' to exit");

        String selection = scanner.nextLine();
        while (!selection.equalsIgnoreCase("C") && !selection.equalsIgnoreCase("E") || selection.isEmpty()) {
            System.out.println("Please, choose the correct action variant!");
            selection = scanner.nextLine();
        }
        switch (selection) {
            case "c":
                cancelReservation(roomId, userId, hotelId, isLoggedIn);
            case "e":
                System.out.println(goodByeScreen);
                System.exit(0);
        }
    }

    //Cancelling reservation of the selected room
    private void cancelReservation(long roomId, long userId, long hotelId, boolean isLoggedIn) {
        logInCheck(isLoggedIn);

        abstractDAOImpl.getRoomDB()
                .stream()
                .forEach(c -> {
                    if (c.getRoomId() == roomId && c.getHotelID() == hotelId && c.getUserReserved().getUserId() == userId) {
                        c.setUserReserved(abstractDAOImpl.generateDefaultUser());
                        System.out.println("\t*** Cancelling reservation... ***" +
                                "\n" +
                                "You have cancelled the reservation of Room " + roomId +
                                goodByeScreen);
                        ;
                    }
                });
        System.exit(0);
    }

    //Creating the Map with parameters, chosen by User
    private void createParamRequest(boolean isLoggedIn, List<Room> roomList) {
        logInCheck(isLoggedIn);
        Map<String, String> paramsMap = new HashMap<>();//Creating the Map with required parameters (K - parameterID, V - parameter value)

        System.out.println("You can filter rooms by parameters here:" +
                "\nPlease, enter the required points where necessary:");

        System.out.println("\n\t1. Number of persons required (max. 4 people available):");
        String persons = scanner.nextLine(); //Number of persons
        while (!persons.equals("1") && !persons.equals("2")
                && !persons.equals("3") && !persons.equals("4") && !persons.isEmpty()) {
            System.out.println("Number is incorrect. Enter again (or leave blank)...");
            persons = scanner.nextLine();
        }
        if (persons.isEmpty()) {
            paramsMap.put("1", "None");
        }
        if (!persons.isEmpty()) {
            paramsMap.put("1", persons);
        }

        System.out.println("\n\t2. Maximum price:");
        String maxPrice = scanner.nextLine(); //Maximum room price
        while (!isDigit(maxPrice)) {
            System.out.println("Only digits are allowed in this field!");
            persons = scanner.nextLine();
        }
        if (maxPrice.isEmpty()) {
            paramsMap.put("2", "None");
        }
        if (!maxPrice.isEmpty()) {
            paramsMap.put("2", maxPrice);
        }

        System.out.println("\n\t3. Additional services in the room needed (Please, enter 'Y' or 'N'):");
        boolean serv = false;
        String addService = scanner.nextLine(); //Additional services
        while (!addService.equalsIgnoreCase("y") && !addService.equalsIgnoreCase("n") && !addService.isEmpty()) {
            System.out.println("Please, enter 'Y' or 'N' (or leave blank)");
            addService = scanner.nextLine();
        }
        if (!addService.isEmpty()) {
            switch (addService) {
                case "y":
                    addService = "true";
                    break;
                case "n":
                    addService = "false";
                    break;
            }
            paramsMap.put("3", addService);
            serv = true;
        }
        findRoomByParams(paramsMap, isLoggedIn, roomList, serv);
    }

    //Parametrized search of the rooms (filtering)
    private List<Room> findRoomByParams(Map<String, String> params, boolean isLoggedIn, List<Room> list, boolean servConfirm) {
        logInCheck(isLoggedIn);
        System.out.println("\t *** Applying filters... ***\n");
        System.out.println(params);
        List<Room> finalRoomList = new ArrayList<>();
        int personsInt = 0;
        double priceInt = 0;

        String persons = params.entrySet()
                .stream()
                .filter(x -> x.getKey().contains("1"))
                .findFirst().get().getValue();

        String price = params.entrySet()
                .stream()
                .filter(x -> x.getKey().contains("2"))
                .findFirst().get().getValue();

        if (!persons.equals("None")) {
            personsInt = Integer.parseInt(persons);
        }
        if (!price.equals("None")) {
            priceInt = Integer.parseInt(price);
        }
        final int personFinal = personsInt;
        final double priceFinal = priceInt;

        //Filters the list if there is no value entered for "Additional service" parameter (left blank)
        if (!servConfirm) {
            finalRoomList = list
                    .stream()
                    .filter(m -> (m.getPersons() >= personFinal || personFinal == 0)
                            & (m.getPrice() <= priceFinal || priceFinal == 0))
                    .collect(Collectors.toList());
        }

        //Filters the list if there is a value entered for "Additional service" parameter
        if (servConfirm) {
            boolean addServ = Boolean.parseBoolean(params.entrySet().stream()
                    .filter(x -> x.getKey().contains("3")).findFirst().get().getValue());
            finalRoomList = list
                    .stream()
                    .filter(m -> (m.getPersons() >= personFinal || personFinal == 0)
                            & (m.getPrice() <= priceFinal || priceFinal == 0)
                            & (m.isHasAdditionalService() == addServ))
                    .collect(Collectors.toList());
        }
        if (finalRoomList.isEmpty()) {
            System.out.println("There are no rooms corresponding to your request!" + goodByeScreen);
            System.exit(0);
        }

        finalRoomList.forEach(c -> System.out.println("Room number: " + c.getRoomId() +
                ", Persons: " + c.getPersons() + ", Original price: " + (int) c.getPrice() +
                ", Price (including discount " + (int) c.getDiscount() + "%): "
                + Math.round(c.getPrice() - (c.getPrice() * c.getDiscount() / 100)) +
                ", Hotel: " + abstractDAOImpl.getHotels().stream()
                .filter(n -> n.getHotelId() == c.getHotelID())
                .findFirst().get().getHotelName() +

                "(" + abstractDAOImpl.getHotels().stream() //Hotel city
                .filter(n -> n.getHotelId() == c.getHotelID())
                .findFirst().get().getCity() + ")" +

                " /" + abstractDAOImpl.getHotels().stream()
                .filter(n -> n.getHotelId() == c.getHotelID())
                .findFirst().get().getStars() + " stars/"
        ));

        System.out.println(finalRoomList);
        variantSelect(finalRoomList, isLoggedIn);
        return finalRoomList;
    }

    //Method checks if user is properly logged in before every action performed in system(using global variable)
    private void logInCheck(boolean isLoggedIn) {

        if (!isLoggedIn) {
            System.out.println("You are not registered in the system." +
                    "\n\tDo you want to continue?" +
                    "\n\tPress 'Y' to register or press 'N' to exit.");
            String input = scanner.nextLine();

            while (!input.equalsIgnoreCase("n") && !input.equalsIgnoreCase("y") || input.isEmpty()) {
                System.out.println("Please, choose the correct variant!");
                input = scanner.nextLine();
            }
            switch (input) {
                case "y":
                    createNewUser();
                    break;
                case "n":
                    System.out.println(goodByeScreen);
                    System.exit(0);
                    break;
            }
        }
    }

    private boolean isDigit(String string) {
        for (char c : string.toCharArray()) {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private String starCount(int count) {
        String starCount = "";
        for (int i = 0; i < count; i++) {
            starCount = starCount + "*";
        }
        return starCount;
    }
}

