package io.hashimati.util;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexUtil {
    private static String variableRegex = "\\{([^}]*)\\}";
    public static List<String> getPathVariables(String url) {
        return Pattern.compile(variableRegex)
                .matcher(url)
                .results()
                .map(matchResult -> matchResult.group(1))
                .collect(Collectors.toList());
    }
}
