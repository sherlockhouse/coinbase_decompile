package kotlin.text;

/* compiled from: StringsJVM.kt */
class StringsKt__StringsJVMKt extends StringsKt__StringNumberConversionsKt {
    public static final boolean equals(String $receiver, String other, boolean ignoreCase) {
        if ($receiver == null) {
            return other == null;
        } else {
            if (ignoreCase) {
                return $receiver.equalsIgnoreCase(other);
            }
            return $receiver.equals(other);
        }
    }
}
