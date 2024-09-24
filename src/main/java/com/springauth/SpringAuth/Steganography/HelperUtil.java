package com.springauth.SpringAuth.Steganography;

public class HelperUtil {

    // Method to remove partial repetition from a string
    public static String removePartialRepetition(String s) {
        int n = s.length();
        for (int l = 1; l <= n / 2; l++) {
            if (n % l == 0 || (n % l != 0 && s.substring(0, n % l).equals(s.substring(n - (n % l))))) {
                String candidate = s.substring(0, l);
                boolean match = true;
                for (int i = 0; i < n - n % l; i += l) {
                    if (!s.substring(i, i + l).equals(candidate)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return s.substring(0, n - n % l);
                }
            }
        }
        return s;
    }

    // Method to find the original substring from a concatenated string
    public static String findOriginalSubstring(String s) {
        // Concatenate the string with itself
        String doubled = s + s;
        // Find the first occurrence of the original string starting from the second character
        int firstOccurrence = doubled.indexOf(s, 1);
        if (firstOccurrence != -1) {
            // Extract the smallest repeating substring
            String possibleSubstring = s.substring(0, firstOccurrence);
            // Check if repeating this substring forms the original string
            if (s.equals(repeatSubstring(possibleSubstring, s.length() / possibleSubstring.length()))) {
                return possibleSubstring;
            }
        }
        return s;
    }

    private static String repeatSubstring(String str, int times) {
        StringBuilder sb = new StringBuilder(str.length() * times);
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    // Main method for testing
//    public static void main(String[] args) {
//        String repeatedString = "Oc7gDd9oWfj7jMsg/Y7Hfg4GtpuWEwPKlD5GH24MmRqb8b4aka/dY0+/I2xXew1LFpwBPI+nvQXrCzlej4U5XQ==Oc7gDd9oWfj7jMsg/Y7Hfg4GtpuWEwPKlD5GH24MmRqb8b4aka/dY0+/I2xXew1LFpwBPI+nvQXrCzlej4U5XQ==Oc7gDd9oWfj7jMsg/Y7Hfg4GtpuWEwPKlD5GH24MmRqb8b4aka/dY0+/I2xXew1LFpwBPI+nvQXrCzlej4U5XQ==Oc7gDd9oWfj7jMsg/Y7Hfg4GtpuWEwPKlD5GH24MmRqb8b4aka/dY0+/I2xXew1LFpwBPI+nvQXrCzlej4U5XQ==Oc7gDd9oWfj7jMsg/Y7Hfg4GtpuWEwPKlD5GH24MmRqb8b4aka/dY0+/I2xXew1LFpwBPI+nvQXrCzlej4U5XQ==Oc7gDd9oWfj7jMsg/Y7Hfg4GtpuWEwPKlD5GH24MmRqb8b4aka/dY0+/I2xXew1LFpwBPI+nvQXrCzlej4U5XQ==Oc7gDd9oWfj7jMsg/Y7Hfg4GtpuWEwPKlD5GH24MmRqb8b4aka/dY0+/I2xXew1LFpwBPI+nvQXrCzlej4U5XQ==Oc7gDd9oWfj7jMsg/Y7Hfg4GtpuWEwPKlD5GH24MmRqb8b4aka/dY0+/I2xXew1LFpwBPI+nvQXrCzlej4U5XQ==Oc7gDd9oWfj7jMsg/Y7Hfg4GtpuWEwPKlD5GH24MmRqb8b4aka/dY0+/I2xXew1LFpwBPI+nvQXrCzlej4U5XQ==Oc7gDd9oWfj7jMsg/Y7Hfg4GtpuWEwPKlD5GH24MmRqb8b4aka/dY0+/I2xXew1LFpwBPI+nvQXrCzlej4U5XQ==";
//        String originalSubstring = findOriginalSubstring(repeatedString);
//        System.out.println("Original Substring: " + originalSubstring); // Expected output: the original substring
//
//        String partialString = "abcabcab";
//        String cleanedString = removePartialRepetition(partialString);
//        System.out.println("Cleaned String: " + cleanedString); // Expected output: "abcabc"
//    }
}
