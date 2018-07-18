package com.google.android.gms.appinvite;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbp;

public final class AppInviteInvitation {
    private static final String[] zzdwr = new String[]{"jpg", "jpeg", "png"};

    public static final class IntentBuilder {
        private final Intent mIntent = new Intent("com.google.android.gms.appinvite.ACTION_APP_INVITE");
        private String zzdws;
        private String zzdwt;

        public IntentBuilder(CharSequence charSequence) {
            zzbp.zzu(charSequence);
            this.mIntent.putExtra("com.google.android.gms.appinvite.TITLE", charSequence);
            this.mIntent.setPackage("com.google.android.gms");
        }

        public final Intent build() {
            if (!TextUtils.isEmpty(this.zzdws)) {
                zzbp.zzh(this.zzdwt, "Email html content must be set when email subject is set.");
                zzbp.zzb(this.mIntent.getData() == null, (Object) "Custom image must not be set when email html content is set.");
                zzbp.zzb(TextUtils.isEmpty(this.mIntent.getCharSequenceExtra("com.google.android.gms.appinvite.BUTTON_TEXT")), (Object) "Call to action text must not be set when email html content is set.");
                this.mIntent.putExtra("com.google.android.gms.appinvite.EMAIL_SUBJECT", this.zzdws);
                this.mIntent.putExtra("com.google.android.gms.appinvite.EMAIL_CONTENT", this.zzdwt);
            } else if (!TextUtils.isEmpty(this.zzdwt)) {
                throw new IllegalArgumentException("Email subject must be set when email html content is set.");
            }
            return this.mIntent;
        }

        public final IntentBuilder setDeepLink(Uri uri) {
            if (uri != null) {
                this.mIntent.putExtra("com.google.android.gms.appinvite.DEEP_LINK_URL", uri);
            } else {
                this.mIntent.removeExtra("com.google.android.gms.appinvite.DEEP_LINK_URL");
            }
            return this;
        }

        public final IntentBuilder setMessage(CharSequence charSequence) {
            if (charSequence == null || charSequence.length() <= 100) {
                this.mIntent.putExtra("com.google.android.gms.appinvite.MESSAGE", charSequence);
                return this;
            }
            throw new IllegalArgumentException(String.format("Message must be %d chars or less.", new Object[]{Integer.valueOf(100)}));
        }
    }
}
