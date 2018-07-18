package org.joda.money;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DefaultCurrencyUnitDataProvider extends CurrencyUnitDataProvider {
    private static final Pattern REGEX_LINE = Pattern.compile("([A-Z]{3}),(-1|[0-9]{1,3}),(-1|[0-9]),([A-Z]*)#?.*");

    DefaultCurrencyUnitDataProvider() {
    }

    protected void registerCurrencies() throws Exception {
        loadCurrenciesFromFile("/org/joda/money/MoneyData.csv", true);
        loadCurrenciesFromFile("/org/joda/money/MoneyDataExtension.csv", false);
    }

    private void loadCurrenciesFromFile(String fileName, boolean isNecessary) throws Exception {
        Exception resultEx;
        InputStream in = null;
        try {
            in = getClass().getResourceAsStream(fileName);
            if (in == null && isNecessary) {
                throw new FileNotFoundException("Data file " + fileName + " not found");
            } else if (in != null || isNecessary) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    Matcher matcher = REGEX_LINE.matcher(line);
                    if (matcher.matches()) {
                        List<String> countryCodes = new ArrayList();
                        String codeStr = matcher.group(4);
                        String currencyCode = matcher.group(1);
                        if (codeStr.length() % 2 != 1) {
                            for (int i = 0; i < codeStr.length(); i += 2) {
                                countryCodes.add(codeStr.substring(i, i + 2));
                            }
                            registerCurrency(currencyCode, Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)), countryCodes);
                        }
                    }
                }
                if (in == null) {
                    return;
                }
                if (null != null) {
                    try {
                        in.close();
                        return;
                    } catch (IOException e) {
                        throw null;
                    }
                }
                in.close();
            } else if (in == null) {
            } else {
                if (null != null) {
                    try {
                        in.close();
                        return;
                    } catch (IOException e2) {
                        throw null;
                    }
                }
                in.close();
            }
        } catch (Exception ex) {
            resultEx = ex;
            throw ex;
        } catch (Throwable th) {
            if (in != null) {
                if (resultEx != null) {
                    try {
                        in.close();
                    } catch (IOException e3) {
                        throw resultEx;
                    }
                }
                in.close();
            }
        }
    }
}
