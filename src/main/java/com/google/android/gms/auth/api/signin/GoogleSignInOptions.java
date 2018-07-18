package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.internal.zzn;
import com.google.android.gms.auth.api.signin.internal.zzo;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbp;
import com.google.android.gms.internal.zzbck;
import com.google.android.gms.internal.zzbcn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleSignInOptions extends zzbck implements Optional, ReflectedParcelable {
    public static final Creator<GoogleSignInOptions> CREATOR = new zzd();
    public static final GoogleSignInOptions DEFAULT_GAMES_SIGN_IN = new Builder().requestScopes(SCOPE_GAMES, new Scope[0]).build();
    public static final GoogleSignInOptions DEFAULT_SIGN_IN = new Builder().requestId().requestProfile().build();
    private static Scope SCOPE_GAMES = new Scope("https://www.googleapis.com/auth/games");
    private static Comparator<Scope> zzecc = new zzc();
    public static final Scope zzecd = new Scope("profile");
    public static final Scope zzece = new Scope("email");
    public static final Scope zzecf = new Scope("openid");
    private int versionCode;
    private Account zzduz;
    private boolean zzeap;
    private String zzeaq;
    private final ArrayList<Scope> zzecg;
    private final boolean zzech;
    private final boolean zzeci;
    private String zzecj;
    private ArrayList<zzn> zzeck;
    private Map<Integer, zzn> zzecl;

    public static final class Builder {
        private Account zzduz;
        private boolean zzeap;
        private String zzeaq;
        private boolean zzech;
        private boolean zzeci;
        private String zzecj;
        private Set<Scope> zzecm = new HashSet();
        private Map<Integer, zzn> zzecn = new HashMap();

        public Builder(GoogleSignInOptions googleSignInOptions) {
            zzbp.zzu(googleSignInOptions);
            this.zzecm = new HashSet(googleSignInOptions.zzecg);
            this.zzech = googleSignInOptions.zzech;
            this.zzeci = googleSignInOptions.zzeci;
            this.zzeap = googleSignInOptions.zzeap;
            this.zzeaq = googleSignInOptions.zzeaq;
            this.zzduz = googleSignInOptions.zzduz;
            this.zzecj = googleSignInOptions.zzecj;
            this.zzecn = GoogleSignInOptions.zzu(googleSignInOptions.zzeck);
        }

        public final GoogleSignInOptions build() {
            if (this.zzeap && (this.zzduz == null || !this.zzecm.isEmpty())) {
                requestId();
            }
            return new GoogleSignInOptions(new ArrayList(this.zzecm), this.zzduz, this.zzeap, this.zzech, this.zzeci, this.zzeaq, this.zzecj, this.zzecn);
        }

        public final Builder requestId() {
            this.zzecm.add(GoogleSignInOptions.zzecf);
            return this;
        }

        public final Builder requestProfile() {
            this.zzecm.add(GoogleSignInOptions.zzecd);
            return this;
        }

        public final Builder requestScopes(Scope scope, Scope... scopeArr) {
            this.zzecm.add(scope);
            this.zzecm.addAll(Arrays.asList(scopeArr));
            return this;
        }
    }

    GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, ArrayList<zzn> arrayList2) {
        this(i, (ArrayList) arrayList, account, z, z2, z3, str, str2, zzu(arrayList2));
    }

    private GoogleSignInOptions(int i, ArrayList<Scope> arrayList, Account account, boolean z, boolean z2, boolean z3, String str, String str2, Map<Integer, zzn> map) {
        this.versionCode = i;
        this.zzecg = arrayList;
        this.zzduz = account;
        this.zzeap = z;
        this.zzech = z2;
        this.zzeci = z3;
        this.zzeaq = str;
        this.zzecj = str2;
        this.zzeck = new ArrayList(map.values());
        this.zzecl = map;
    }

    private final JSONObject toJsonObject() {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Collections.sort(this.zzecg, zzecc);
            ArrayList arrayList = this.zzecg;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                jSONArray.put(((Scope) obj).zzaft());
            }
            jSONObject.put("scopes", jSONArray);
            if (this.zzduz != null) {
                jSONObject.put("accountName", this.zzduz.name);
            }
            jSONObject.put("idTokenRequested", this.zzeap);
            jSONObject.put("forceCodeForRefreshToken", this.zzeci);
            jSONObject.put("serverAuthRequested", this.zzech);
            if (!TextUtils.isEmpty(this.zzeaq)) {
                jSONObject.put("serverClientId", this.zzeaq);
            }
            if (!TextUtils.isEmpty(this.zzecj)) {
                jSONObject.put("hostedDomain", this.zzecj);
            }
            return jSONObject;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static GoogleSignInOptions zzen(String str) throws JSONException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(str);
        Collection hashSet = new HashSet();
        JSONArray jSONArray = jSONObject.getJSONArray("scopes");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            hashSet.add(new Scope(jSONArray.getString(i)));
        }
        Object optString = jSONObject.optString("accountName", null);
        return new GoogleSignInOptions(3, new ArrayList(hashSet), !TextUtils.isEmpty(optString) ? new Account(optString, "com.google") : null, jSONObject.getBoolean("idTokenRequested"), jSONObject.getBoolean("serverAuthRequested"), jSONObject.getBoolean("forceCodeForRefreshToken"), jSONObject.optString("serverClientId", null), jSONObject.optString("hostedDomain", null), new HashMap());
    }

    private static Map<Integer, zzn> zzu(List<zzn> list) {
        Map<Integer, zzn> hashMap = new HashMap();
        if (list == null) {
            return hashMap;
        }
        for (zzn com_google_android_gms_auth_api_signin_internal_zzn : list) {
            hashMap.put(Integer.valueOf(com_google_android_gms_auth_api_signin_internal_zzn.getType()), com_google_android_gms_auth_api_signin_internal_zzn);
        }
        return hashMap;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        try {
            GoogleSignInOptions googleSignInOptions = (GoogleSignInOptions) obj;
            if (this.zzeck.size() > 0 || googleSignInOptions.zzeck.size() > 0 || this.zzecg.size() != googleSignInOptions.zzaag().size() || !this.zzecg.containsAll(googleSignInOptions.zzaag())) {
                return false;
            }
            if (this.zzduz == null) {
                if (googleSignInOptions.zzduz != null) {
                    return false;
                }
            } else if (!this.zzduz.equals(googleSignInOptions.zzduz)) {
                return false;
            }
            if (TextUtils.isEmpty(this.zzeaq)) {
                if (!TextUtils.isEmpty(googleSignInOptions.zzeaq)) {
                    return false;
                }
            } else if (!this.zzeaq.equals(googleSignInOptions.zzeaq)) {
                return false;
            }
            return this.zzeci == googleSignInOptions.zzeci && this.zzeap == googleSignInOptions.zzeap && this.zzech == googleSignInOptions.zzech;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode() {
        List arrayList = new ArrayList();
        ArrayList arrayList2 = this.zzecg;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            arrayList.add(((Scope) obj).zzaft());
        }
        Collections.sort(arrayList);
        return new zzo().zzo(arrayList).zzo(this.zzduz).zzo(this.zzeaq).zzaq(this.zzeci).zzaq(this.zzeap).zzaq(this.zzech).zzaao();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbcn.zze(parcel);
        zzbcn.zzc(parcel, 1, this.versionCode);
        zzbcn.zzc(parcel, 2, zzaag(), false);
        zzbcn.zza(parcel, 3, this.zzduz, i, false);
        zzbcn.zza(parcel, 4, this.zzeap);
        zzbcn.zza(parcel, 5, this.zzech);
        zzbcn.zza(parcel, 6, this.zzeci);
        zzbcn.zza(parcel, 7, this.zzeaq, false);
        zzbcn.zza(parcel, 8, this.zzecj, false);
        zzbcn.zzc(parcel, 9, this.zzeck, false);
        zzbcn.zzai(parcel, zze);
    }

    public final ArrayList<Scope> zzaag() {
        return new ArrayList(this.zzecg);
    }

    public final String zzaai() {
        return toJsonObject().toString();
    }
}
