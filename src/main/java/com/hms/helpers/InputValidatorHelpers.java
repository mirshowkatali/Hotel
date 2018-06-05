package com.hms.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidatorHelpers {

    public static boolean isHtmlSafe(String check) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9_/]*");
        Matcher matcher = pattern.matcher(check);
        if (!matcher.matches()) return true;
        return false;
    }
}
