package FinalProjectCore;

import FinalProjectCore.Logics.*;

public class Main {
    public static void main(String[] args) {
        try {
            controller.systemEnter();
        } catch (NullPointerException e) {
            System.out.println("Null pointer exception :)");
        }
    }
}
