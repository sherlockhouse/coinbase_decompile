package com.mobsandgeeks.saripaar;

import android.text.TextUtils;
import android.view.View;
import android.widget.Checkable;
import android.widget.Spinner;
import android.widget.TextView;

public final class Rules {
    public static Rule<TextView> required(String failureMessage, final boolean trimInput) {
        return new Rule<TextView>(failureMessage) {
            public boolean isValid(TextView textView) {
                return !TextUtils.isEmpty(Rules.getText(textView, trimInput));
            }
        };
    }

    public static Rule<TextView> regex(String failureMessage, final String regex, final boolean trimInput) {
        if (regex != null) {
            return new Rule<TextView>(failureMessage) {
                public boolean isValid(TextView textView) {
                    String text = Rules.getText(textView, trimInput);
                    return text != null ? text.matches(regex) : false;
                }
            };
        }
        throw new IllegalArgumentException("'regex' cannot be null");
    }

    public static Rule<TextView> minLength(String failureMessage, final int minLength, final boolean trimInput) {
        return new Rule<TextView>(failureMessage) {
            public boolean isValid(TextView view) {
                String text = Rules.getText(view, trimInput);
                if (text == null || text.length() < minLength) {
                    return false;
                }
                return true;
            }
        };
    }

    public static Rule<TextView> maxLength(String failureMessage, final int maxLength, final boolean trimInput) {
        return new Rule<TextView>(failureMessage) {
            public boolean isValid(TextView view) {
                String text = Rules.getText(view, trimInput);
                if (text == null || text.length() > maxLength) {
                    return false;
                }
                return true;
            }
        };
    }

    public static Rule<TextView> eq(String failureMessage, final TextView anotherTextView) {
        if (anotherTextView != null) {
            return new Rule<TextView>(failureMessage) {
                public boolean isValid(TextView view) {
                    return view.getText().toString().equals(anotherTextView.getText().toString());
                }
            };
        }
        throw new IllegalArgumentException("'anotherTextView' cannot be null");
    }

    public static Rule<TextView> eq(String failureMessage, String expectedString) {
        return eq(failureMessage, expectedString, false, false);
    }

    public static Rule<TextView> eq(String failureMessage, String expectedString, final boolean ignoreCase, final boolean trimInput) {
        String cleanString;
        if (expectedString == null) {
            cleanString = "";
        } else {
            cleanString = expectedString;
        }
        return new Rule<TextView>(failureMessage) {
            public boolean isValid(TextView textView) {
                String actualString = Rules.getText(textView, trimInput);
                if (actualString != null) {
                    return ignoreCase ? actualString.equalsIgnoreCase(cleanString) : actualString.equals(cleanString);
                } else {
                    return false;
                }
            }
        };
    }

    public static Rule<TextView> eq(String failureMessage, int expectedInt) {
        return eq(failureMessage, (long) expectedInt);
    }

    public static Rule<TextView> gt(String failureMessage, int lesserInt) {
        return gt(failureMessage, (long) lesserInt);
    }

    public static Rule<TextView> lt(String failureMessage, int greaterInt) {
        return lt(failureMessage, (long) greaterInt);
    }

    public static Rule<TextView> eq(String failureMessage, final long expectedLong) {
        return new Rule<TextView>(failureMessage) {
            public boolean isValid(TextView textView) {
                String actualLong = Rules.getText(textView, true);
                if (actualLong == null) {
                    return false;
                }
                if (!actualLong.matches("\\d+")) {
                    return false;
                }
                if (Long.parseLong(actualLong) == expectedLong) {
                    return true;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> gt(String failureMessage, final long lesserLong) {
        return new Rule<TextView>(failureMessage) {
            public boolean isValid(TextView textView) {
                String actualLong = Rules.getText(textView, true);
                if (actualLong == null) {
                    return false;
                }
                if (!actualLong.matches("\\d+")) {
                    return false;
                }
                if (Long.parseLong(actualLong) > lesserLong) {
                    return true;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> lt(String failureMessage, final long greaterLong) {
        return new Rule<TextView>(failureMessage) {
            public boolean isValid(TextView textView) {
                String actualLong = Rules.getText(textView, true);
                if (actualLong == null) {
                    return false;
                }
                if (!actualLong.matches("\\d+")) {
                    return false;
                }
                if (Long.parseLong(actualLong) < greaterLong) {
                    return true;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> eq(String failureMessage, final float expectedFloat) {
        return new Rule<TextView>(failureMessage) {
            public boolean isValid(TextView view) {
                String actualFloat = Rules.getText(view, true);
                if (actualFloat == null) {
                    return false;
                }
                if (!actualFloat.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                    return false;
                }
                if (Float.parseFloat(actualFloat) == expectedFloat) {
                    return true;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> gt(String failureMessage, final float lesserFloat) {
        return new Rule<TextView>(failureMessage) {
            public boolean isValid(TextView view) {
                String actualFloat = Rules.getText(view, true);
                if (actualFloat == null) {
                    return false;
                }
                if (!actualFloat.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                    return false;
                }
                if (Float.parseFloat(actualFloat) > lesserFloat) {
                    return true;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> lt(String failureMessage, final float greaterFloat) {
        return new Rule<TextView>(failureMessage) {
            public boolean isValid(TextView view) {
                String actualFloat = Rules.getText(view, true);
                if (actualFloat == null) {
                    return false;
                }
                if (!actualFloat.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                    return false;
                }
                if (Float.parseFloat(actualFloat) < greaterFloat) {
                    return true;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> eq(String failureMessage, final double expectedDouble) {
        return new Rule<TextView>(failureMessage) {
            public boolean isValid(TextView view) {
                String actualDouble = Rules.getText(view, true);
                if (actualDouble == null) {
                    return false;
                }
                if (!actualDouble.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                    return false;
                }
                if (Double.parseDouble(actualDouble) == expectedDouble) {
                    return true;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> gt(String failureMessage, final double lesserDouble) {
        return new Rule<TextView>(failureMessage) {
            public boolean isValid(TextView view) {
                String actualDouble = Rules.getText(view, true);
                if (actualDouble == null) {
                    return false;
                }
                if (!actualDouble.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                    return false;
                }
                if (Double.parseDouble(actualDouble) > lesserDouble) {
                    return true;
                }
                return false;
            }
        };
    }

    public static Rule<TextView> lt(String failureMessage, final double greaterDouble) {
        return new Rule<TextView>(failureMessage) {
            public boolean isValid(TextView view) {
                String actualDouble = Rules.getText(view, true);
                if (actualDouble == null) {
                    return false;
                }
                if (!actualDouble.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
                    return false;
                }
                if (Double.parseDouble(actualDouble) < greaterDouble) {
                    return true;
                }
                return false;
            }
        };
    }

    public static Rule<Checkable> checked(String failureMessage, final boolean checked) {
        return new Rule<Checkable>(failureMessage) {
            public boolean isValid(Checkable view) {
                return view.isChecked() == checked;
            }
        };
    }

    public static Rule<Spinner> spinnerNotEq(String failureMessage, final int selection) {
        return new Rule<Spinner>(failureMessage) {
            public boolean isValid(Spinner spinner) {
                return spinner.getSelectedItemPosition() != selection;
            }
        };
    }

    public static Rule<View> and(String failureMessage, final Rule<?>... rules) {
        return new Rule<View>(failureMessage) {
            public boolean isValid(View view) {
                boolean valid = true;
                for (Rule rule : rules) {
                    if (rule != null) {
                        valid &= rule.isValid(view);
                    }
                    if (!valid) {
                        break;
                    }
                }
                return valid;
            }
        };
    }

    public static Rule<View> or(String failureMessage, final Rule<?>... rules) {
        return new Rule<View>(failureMessage) {
            public boolean isValid(View view) {
                boolean valid = false;
                for (Rule rule : rules) {
                    if (rule != null) {
                        valid |= rule.isValid(view);
                    }
                    if (valid) {
                        break;
                    }
                }
                return valid;
            }
        };
    }

    private static String getText(TextView textView, boolean trim) {
        CharSequence text = null;
        if (textView != null) {
            text = textView.getText();
            if (trim) {
                text = text.toString().trim();
            }
        }
        return text != null ? text.toString() : null;
    }
}
