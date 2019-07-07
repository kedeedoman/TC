package com.example.tc.data;

import java.util.HashMap;
import java.util.Map;

public final class DataUtils {

    public static String stringOfCharAt10xIndex(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 9; i < str.length(); i += 10) {
            stringBuilder.append(str.charAt(i));
        }
        return stringBuilder.toString();
    }

    public static String stringOfCharAt0thIndex(String str) {
        return String.valueOf(str.charAt(0));
    }

    public static String wordFrequency(String str) {
        String[] words = str.split("\\s+");
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            if (wordCount.get(word) != null) {
                wordCount.put(word, wordCount.get(word) + 1);
            }
            else {
                wordCount.put(word, 1);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            stringBuilder.append(entry.getKey());
            stringBuilder.append(" : ");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
