package front_end.regex;

import java.util.regex.Pattern;

public class RegexManager {

    public static boolean verify_number(String txt) {
        if (null == txt || txt.equals("")) return false;
        String regex = "^[0-9]*$";
        return Pattern.compile(regex).matcher(txt).matches();
    }

    public static boolean verify_amount (String txt) {
        if (null == txt || txt.equals("")) return false;
        String regex = "^\\d+(\\.(\\d{2}))?$";
        return Pattern.compile(regex).matcher(txt).matches();
    }
}
