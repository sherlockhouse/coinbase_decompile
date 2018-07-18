package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInAccount extends zzbck implements ReflectedParcelable {
    public static final Creator<GoogleSignInAccount> CREATOR = new zzb();
    private static zzd zzebv = zzh.zzalc();
    private static Comparator<Scope> zzecc = new zza();
    private int versionCode;
    private String zzbsx;
    private List<Scope> zzdxx;
    private String zzeaf;
    private String zzeag;
    private String zzeaw;
    private String zzebw;
    private String zzebx;
    private Uri zzeby;
    private String zzebz;
    private long zzeca;
    private String zzecb;

    GoogleSignInAccount(int i, String str, String str2, String str3, String str4, Uri uri, String str5, long j, String str6, List<Scope> list, String str7, String str8) {
        this.versionCode = i;
        this.zzbsx = str;
        this.zzeaw = str2;
        this.zzebw = str3;
        this.zzebx = str4;
        this.zzeby = uri;
        this.zzebz = str5;
        this.zzeca = j;
        this.zzecb = str6;
        this.zzdxx = list;
        this.zzeaf = str7;
        this.zzeag = str8;
    }

    private final JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (getId() != null) {
                jSONObject.put("id", getId());
            }
            if (getIdToken() != null) {
                jSONObject.put("tokenId", getIdToken());
            }
            if (getEmail() != null) {
                jSONObject.put("email", getEmail());
            }
            if (getDisplayName() != null) {
                jSONObject.put("displayName", getDisplayName());
            }
            if (getGivenName() != null) {
                jSONObject.put("givenName", getGivenName());
            }
            if (getFamilyName() != null) {
                jSONObject.put("familyName", getFamilyName());
            }
            if (getPhotoUrl() != null) {
                jSONObject.put("photoUrl", getPhotoUrl().toString());
            }
            if (getServerAuthCode() != null) {
                jSONObject.put("serverAuthCode", getServerAuthCode());
            }
            jSONObject.put("expirationTime", this.zzeca);
            jSONObject.put("obfuscatedIdentifier", this.zzecb);
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zzdxx, zzecc);
            for (Scope zzaft : this.zzdxx) {
                jSONArray.put(zzaft.zzaft());
            }
            jSONObject.put("grantedScopes", jSONArray);
            return jSONObject;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static GoogleSignInAccount zzem(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Uri uri = null;
        Object optString = jSONObject.optString("photoUrl", null);
        if (!TextUtils.isEmpty(optString)) {
            uri = Uri.parse(optString);
        }
        long parseLong = Long.parseLong(jSONObject.getString("expirationTime"));
        Set hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("grantedScopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        String optString2 = jSONObject.optString("id");
        String optString3 = jSONObject.optString("tokenId", null);
        String optString4 = jSONObject.optString("email", null);
        String optString5 = jSONObject.optString("displayName", null);
        String optString6 = jSONObject.optString("givenName", null);
        String optString7 = jSONObject.optString("familyName", null);
        Long valueOf = Long.valueOf(parseLong);
        GoogleSignInAccount googleSignInAccount = new GoogleSignInAccount(3, optString2, optString3, optString4, optString5, uri, null, (valueOf == null ? Long.valueOf(zzebv.currentTimeMillis() / 1000) : valueOf).longValue(), zzbp.zzgg(jSONObject.getString("obfuscatedIdentifier")), new ArrayList((Collection) zzbp.zzu(hashSet)), optString6, optString7);
        googleSignInAccount.zzebz = jSONObject.optString("serverAuthCode", null);
        return googleSignInAccount;
    }

    public boolean equals(Object obj) {
        return !(obj instanceof GoogleSignInAccount) ? false : ((GoogleSignInAccount) obj).toJsonObject().toString().equals(toJsonObject().toString());
    }

    public Account getAccount() {
        return this.zzebw == null ? null : new Account(this.zzebw, "com.google");
    }

    public String getDisplayName() {
        return this.zzebx;
    }

    public String getEmail() {
        return this.zzebw;
    }

    public String getFamilyName() {
        return this.zzeag;
    }

    public String getGivenName() {
        return this.zzeaf;
    }

    public Set<Scope> getGrantedScopes() {
        return new HashSet(this.zzdxx);
    }

    public String getId() {
        return this.zzbsx;
    }

    public String getIdToken() {
        return this.zzeaw;
    }

    public Uri getPhotoUrl() {
        return this.zzeby;
    }

    public String getServerAuthCode() {
        return this.zzebz;
    }

    public int hashCode() {
        return toJsonObject().toString().hashCode();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.versionCode);
        zzbcn.zza(parcel, 2, getId(), false);
        zzbcn.zza(parcel, 3, getIdToken(), false);
        zzbcn.zza(parcel, 4, getEmail(), false);
        zzbcn.zza(parcel, 5, getDisplayName(), false);
        zzbcn.zza(parcel, 6, getPhotoUrl(), i, false);
        zzbcn.zza(parcel, 7, getServerAuthCode(), false);
        zzbcn.zza(parcel, 8, this.zzeca);
        zzbcn.zza(parcel, 9, this.zzecb, false);
        zzbcn.zzc(parcel, 10, this.zzdxx, false);
        zzbcn.zza(parcel, 11, getGivenName(), false);
        zzbcn.zza(parcel, 12, getFamilyName(), false);
        zzbcn.zzai(parcel, zze);
    }

    public final String zzaae() {
        return this.zzecb;
    }

    public final String zzaaf() {
        JSONObject toJsonObject = toJsonObject();
        toJsonObject.remove("serverAuthCode");
        return toJsonObject.toString();
    }
}
