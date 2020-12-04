package com.epam.util;

public class IdGenerator {
    private static long currentId = 0;

    public static long getCurrentBookId() {
        return currentId++;
    }
}
