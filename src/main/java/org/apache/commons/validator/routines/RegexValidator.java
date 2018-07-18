package org.apache.commons.validator.routines;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexValidator implements Serializable {
    private final Pattern[] patterns;

    public RegexValidator(String regex) {
        this(regex, true);
    }

    public RegexValidator(String regex, boolean caseSensitive) {
        this(new String[]{regex}, caseSensitive);
    }

    public RegexValidator(String[] regexs) {
        this(regexs, true);
    }

    public RegexValidator(String[] regexs, boolean caseSensitive) {
        if (regexs == null || regexs.length == 0) {
            throw new IllegalArgumentException("Regular expressions are missing");
        }
        this.patterns = new Pattern[regexs.length];
        int flags = caseSensitive ? 0 : 2;
        int i = 0;
        while (i < regexs.length) {
            if (regexs[i] == null || regexs[i].length() == 0) {
                throw new IllegalArgumentException("Regular expression[" + i + "] is missing");
            }
            this.patterns[i] = Pattern.compile(regexs[i], flags);
            i++;
        }
    }

    public String validate(String value) {
        if (value == null) {
            return null;
        }
        for (Pattern matcher : this.patterns) {
            Matcher matcher2 = matcher.matcher(value);
            if (matcher2.matches()) {
                int count = matcher2.groupCount();
                if (count == 1) {
                    return matcher2.group(1);
                }
                StringBuilder buffer = new StringBuilder();
                for (int j = 0; j < count; j++) {
                    String component = matcher2.group(j + 1);
                    if (component != null) {
                        buffer.append(component);
                    }
                }
                return buffer.toString();
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("RegexValidator{");
        for (int i = 0; i < this.patterns.length; i++) {
            if (i > 0) {
                buffer.append(",");
            }
            buffer.append(this.patterns[i].pattern());
        }
        buffer.append("}");
        return buffer.toString();
    }
}
