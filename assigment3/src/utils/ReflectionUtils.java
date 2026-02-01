package utils;

public class ReflectionUtils {

    public static void printClassInfo(Object obj) {
        Class<?> c = obj.getClass();
        System.out.println("🔍 Reflection: class name = " + c.getName());
    }
}
