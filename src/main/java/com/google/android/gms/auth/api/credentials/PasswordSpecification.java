package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public final class PasswordSpecification extends zzbck implements ReflectedParcelable {
    public static final Creator<PasswordSpecification> CREATOR = new zzi();
    public static final PasswordSpecification zzeax = new zza().zzi(12, 16).zzek("abcdefghijkmnopqrstxyzABCDEFGHJKLMNPQRSTXY3456789").zze("abcdefghijkmnopqrstxyz", 1).zze("ABCDEFGHJKLMNPQRSTXY", 1).zze("3456789", 1).zzaaa();
    private static PasswordSpecification zzeay = new zza().zzi(12, 16).zzek("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890").zze("abcdefghijklmnopqrstuvwxyz", 1).zze("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1).zze("1234567890", 1).zzaaa();
    private final Random zzbds;
    private String zzeaz;
    private List<String> zzeba;
    private List<Integer> zzebb;
    private int zzebc;
    private int zzebd;
    private final int[] zzebe;

    public static class zza {
        private final List<String> zzeba = new ArrayList();
        private final List<Integer> zzebb = new ArrayList();
        private int zzebc = 12;
        private int zzebd = 16;
        private final TreeSet<Character> zzebf = new TreeSet();

        private static TreeSet<Character> zzo(String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                throw new zzb(String.valueOf(str2).concat(" cannot be null or empty"));
            }
            TreeSet<Character> treeSet = new TreeSet();
            for (char c : str.toCharArray()) {
                if (PasswordSpecification.zzc(c, 32, 126)) {
                    throw new zzb(String.valueOf(str2).concat(" must only contain ASCII printable characters"));
                }
                treeSet.add(Character.valueOf(c));
            }
            return treeSet;
        }

        public final PasswordSpecification zzaaa() {
            if (this.zzebf.isEmpty()) {
                throw new zzb("no allowed characters specified");
            }
            int i = 0;
            for (Integer intValue : this.zzebb) {
                i = intValue.intValue() + i;
            }
            if (i > this.zzebd) {
                throw new zzb("required character count cannot be greater than the max password size");
            }
            boolean[] zArr = new boolean[95];
            for (String toCharArray : this.zzeba) {
                for (char c : toCharArray.toCharArray()) {
                    if (zArr[c - 32]) {
                        throw new zzb("character " + c + " occurs in more than one required character set");
                    }
                    zArr[c - 32] = true;
                }
            }
            return new PasswordSpecification(PasswordSpecification.zzb(this.zzebf), this.zzeba, this.zzebb, this.zzebc, this.zzebd);
        }

        public final zza zze(String str, int i) {
            this.zzeba.add(PasswordSpecification.zzb(zzo(str, "requiredChars")));
            this.zzebb.add(Integer.valueOf(1));
            return this;
        }

        public final zza zzek(String str) {
            this.zzebf.addAll(zzo(str, "allowedChars"));
            return this;
        }

        public final zza zzi(int i, int i2) {
            this.zzebc = 12;
            this.zzebd = 16;
            return this;
        }
    }

    public static class zzb extends Error {
        public zzb(String str) {
            super(str);
        }
    }

    PasswordSpecification(String str, List<String> list, List<Integer> list2, int i, int i2) {
        this.zzeaz = str;
        this.zzeba = Collections.unmodifiableList(list);
        this.zzebb = Collections.unmodifiableList(list2);
        this.zzebc = i;
        this.zzebd = i2;
        int[] iArr = new int[95];
        Arrays.fill(iArr, -1);
        int i3 = 0;
        for (String toCharArray : this.zzeba) {
            for (char c : toCharArray.toCharArray()) {
                iArr[c - 32] = i3;
            }
            i3++;
        }
        this.zzebe = iArr;
        this.zzbds = new SecureRandom();
    }

    private static String zzb(Collection<Character> collection) {
        char[] cArr = new char[collection.size()];
        int i = 0;
        for (Character charValue : collection) {
            int i2 = i + 1;
            cArr[i] = charValue.charValue();
            i = i2;
        }
        return new String(cArr);
    }

    private static boolean zzc(int i, int i2, int i3) {
        return i < 32 || i > 126;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zza(parcel, 1, this.zzeaz, false);
        zzbcn.zzb(parcel, 2, this.zzeba, false);
        zzbcn.zza(parcel, 3, this.zzebb, false);
        zzbcn.zzc(parcel, 4, this.zzebc);
        zzbcn.zzc(parcel, 5, this.zzebd);
        zzbcn.zzai(parcel, zze);
    }
}
