package front_end.regex;

import java.util.regex.Pattern;

public class RegexManager {
    private static String checkNull(String txt) {
        if (null == txt) {
            txt = "";
        }
        return txt;
    }

    public static boolean verify_number(String txt) {
        if (txt.equals("")) return false;
        txt = checkNull(txt);
        String contact_number_regex = "^[0-9]*$";
        return Pattern.compile(contact_number_regex).matcher(txt).matches();
    }
}
