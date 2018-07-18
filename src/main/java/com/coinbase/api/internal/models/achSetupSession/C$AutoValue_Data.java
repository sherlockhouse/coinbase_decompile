package com.coinbase.api.internal.models.achSetupSession;

import com.coinbase.api.internal.models.achSetupSession.mfa.Mfa;
import com.coinbase.v2.models.account.Data;
import com.google.gson.annotations.SerializedName;
import java.util.List;

abstract class C$AutoValue_Data extends Data {
    private final List<Data> accounts;
    private final String id;
    private final String institution;
    private final Mfa mfa;
    private final String status;

    C$AutoValue_Data(String id, String status, String institution, List<Data> accounts, Mfa mfa) {
        this.id = id;
        this.status = status;
        this.institution = institution;
        this.accounts = accounts;
        this.mfa = mfa;
    }

    @SerializedName("id")
    public String getId() {
        return this.id;
    }

    @SerializedName("status")
    public String getStatus() {
        return this.status;
    }

    @SerializedName("institution")
    public String getInstitution() {
        return this.institution;
    }

    @SerializedName("accounts")
    public List<Data> getAccounts() {
        return this.accounts;
    }

    @SerializedName("mfa")
    public Mfa getMfa() {
        return this.mfa;
    }

    public String toString() {
        return "Data{id=" + this.id + ", status=" + this.status + ", institution=" + this.institution + ", accounts=" + this.accounts + ", mfa=" + this.mfa + "}";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Data)) {
            return false;
        }
        Data that = (Data) o;
        if (this.id != null) {
            if (this.id.equals(that.getId())) {
            }
            return false;
        }
        if (this.status != null) {
            if (this.status.equals(that.getStatus())) {
            }
            return false;
        }
        if (this.institution != null) {
            if (this.institution.equals(that.getInstitution())) {
            }
            return false;
        }
        if (this.accounts != null) {
            if (this.accounts.equals(that.getAccounts())) {
            }
            return false;
        }
        if (this.mfa == null) {
            if (that.getMfa() == null) {
                return true;
            }
        } else if (this.mfa.equals(that.getMfa())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((1 * 1000003) ^ (this.id == null ? 0 : this.id.hashCode())) * 1000003) ^ (this.status == null ? 0 : this.status.hashCode())) * 1000003) ^ (this.institution == null ? 0 : this.institution.hashCode())) * 1000003) ^ (this.accounts == null ? 0 : this.accounts.hashCode())) * 1000003;
        if (this.mfa != null) {
            i = this.mfa.hashCode();
        }
        return h ^ i;
    }
}
