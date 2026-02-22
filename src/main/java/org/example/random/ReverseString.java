package org.example.random;

public class ReverseString {
    public static String reverse(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0, j = chars.length - 1; i < j; i++, j--) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
        }
        return new String(chars);
    }
    public static void main(String[] args) {
        System.out.println(reverse("coala"));
    }
}
