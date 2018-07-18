package au.com.bytecode.opencsv;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader implements Closeable {
    private BufferedReader br;
    private final char escape;
    private boolean hasNext;
    private boolean linesSkiped;
    private final char quotechar;
    private final char separator;
    private int skipLines;

    public CSVReader(Reader reader) {
        this(reader, ',');
    }

    public CSVReader(Reader reader, char separator) {
        this(reader, separator, '\"', '\\');
    }

    public CSVReader(Reader reader, char separator, char quotechar, char escape) {
        this(reader, separator, quotechar, escape, 0);
    }

    public CSVReader(Reader reader, char separator, char quotechar, char escape, int line) {
        this.hasNext = true;
        this.br = new BufferedReader(reader);
        this.separator = separator;
        this.quotechar = quotechar;
        this.escape = escape;
        this.skipLines = line;
    }

    public String[] readNext() throws IOException {
        return this.hasNext ? parseLine(getNextLine()) : null;
    }

    private String getNextLine() throws IOException {
        if (!this.linesSkiped) {
            for (int i = 0; i < this.skipLines; i++) {
                this.br.readLine();
            }
            this.linesSkiped = true;
        }
        String nextLine = this.br.readLine();
        if (nextLine == null) {
            this.hasNext = false;
        }
        return this.hasNext ? nextLine : null;
    }

    private String[] parseLine(String nextLine) throws IOException {
        if (nextLine == null) {
            return null;
        }
        List<String> tokensOnThisLine = new ArrayList();
        StringBuilder sb = new StringBuilder(64);
        boolean inQuotes = false;
        do {
            if (inQuotes) {
                sb.append("\n");
                nextLine = getNextLine();
                if (nextLine == null) {
                    break;
                }
            }
            int i = 0;
            while (i < nextLine.length()) {
                char c = nextLine.charAt(i);
                if (c == this.escape) {
                    if (isEscapable(nextLine, inQuotes, i)) {
                        sb.append(nextLine.charAt(i + 1));
                        i++;
                    } else {
                        i++;
                    }
                } else if (c == this.quotechar) {
                    if (isEscapedQuote(nextLine, inQuotes, i)) {
                        sb.append(nextLine.charAt(i + 1));
                        i++;
                    } else {
                        inQuotes = !inQuotes;
                        if (i > 2 && nextLine.charAt(i - 1) != this.separator && nextLine.length() > i + 1 && nextLine.charAt(i + 1) != this.separator) {
                            sb.append(c);
                        }
                    }
                } else if (c != this.separator || inQuotes) {
                    sb.append(c);
                } else {
                    tokensOnThisLine.add(sb.toString());
                    sb = new StringBuilder(64);
                }
                i++;
            }
        } while (inQuotes);
        tokensOnThisLine.add(sb.toString());
        return (String[]) tokensOnThisLine.toArray(new String[0]);
    }

    private boolean isEscapedQuote(String nextLine, boolean inQuotes, int i) {
        return inQuotes && nextLine.length() > i + 1 && nextLine.charAt(i + 1) == this.quotechar;
    }

    private boolean isEscapable(String nextLine, boolean inQuotes, int i) {
        return inQuotes && nextLine.length() > i + 1 && (nextLine.charAt(i + 1) == this.quotechar || nextLine.charAt(i + 1) == this.escape);
    }

    public void close() throws IOException {
        this.br.close();
    }
}
