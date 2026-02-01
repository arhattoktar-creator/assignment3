package service.interfaces;

public interface Validatable {

    default boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    static void printError(String msg) {
        System.out.println(msg);
    }
}
