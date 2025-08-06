package com.errday.kafkahelper.adapter.out.kafka.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KafkaFieldUtils {

    private KafkaFieldUtils() {
    }

    public static Map<String, String> getNonNullFields(Object obj) {
        Map<String, String> result = new HashMap<>();

        if (obj == null) return result;

        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value != null) {
                    result.put(dotCase(field.getName()), String.valueOf(value));
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    public static String dotCaseToCamelCase(String dotCase) {
        String[] parts = dotCase.split("\\.");
        StringBuilder camelCaseString = new StringBuilder(parts[0]);

        for (int i = 1; i < parts.length; i++) {
            if (!parts[i].isEmpty()) {
                camelCaseString.append(Character.toUpperCase(parts[i].charAt(0)));
                if (parts[i].length() > 1) {
                    camelCaseString.append(parts[i].substring(1));
                }
            }
        }
        return camelCaseString.toString();
    }

    private static String dotCase(String camelCase) {
        Pattern pattern = Pattern.compile("([a-z])([A-Z]+)");
        Matcher matcher = pattern.matcher(camelCase);
        StringBuilder result = new StringBuilder();

        while (matcher.find()) {
            String replacement = matcher.group(1) + "." + matcher.group(2).toLowerCase();
            matcher.appendReplacement(result, replacement);
        }
        matcher.appendTail(result);

        return result.toString();
    }

}
