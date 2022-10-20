package ku.cs.util;

import java.util.ArrayList;

public final class Spliter {
    public static String[] split(String line, String separator) {
        if (separator.length()!=1) return new String[0];

        int j=0;

        String temp = line;
        int count = line.length() - temp.replace(separator.toString(), "").length() + 1;

        String[] strings = new String[count];
        for(int i=0; i<count; i++) {
            strings[i] = "";
            for(;j<line.length(); j++) {
                if (line.charAt(j) == separator.charAt(0)) {
                    j++;
                    break;
                }
                strings[i] += line.charAt(j);
            }
        }
        return strings;
    }
}
