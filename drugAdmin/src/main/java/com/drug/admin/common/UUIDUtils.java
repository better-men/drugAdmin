package com.drug.admin.common;

import java.util.UUID;

public class UUIDUtils {
    public UUIDUtils() {
    }

    public static String uuid() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }
}
