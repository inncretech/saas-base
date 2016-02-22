package com.inncretech.identity.utils;

public class MapperUtils {

    public static void checkArgument(Object object, Object_Type type) {
        if (object == null) {
            String message = null;
            if (type == Object_Type.DESTINATION) {
                message = "Destination object is null.";
            } else if (type == Object_Type.SOURCE) {
                message = "Source object is null.";
            } else {
                throw new RuntimeException("Invalid object type passed.");
            }
            throw new IllegalArgumentException(message);
        }
    }

    public enum Object_Type {
        SOURCE, DESTINATION;
    }
}
