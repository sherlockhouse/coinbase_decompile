package com.google.android.gms.appinvite;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import java.util.ArrayList;

public class PreviewActivity extends Activity {
    private final View zza(Context context, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(context).inflate(bundle.getInt("com.google.android.gms.appinvite.LAYOUT_RES_ID"), viewGroup, false);
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("com.google.android.gms.appinvite.VIEWS");
        if (parcelableArrayList != null) {
            parcelableArrayList = parcelableArrayList;
            int size = parcelableArrayList.size();
            int i = 0;
            while (i < size) {
                int i2 = i + 1;
                Bundle bundle2 = (Bundle) parcelableArrayList.get(i);
                View findViewById = inflate.findViewById(bundle2.getInt("View_id"));
                for (String str : bundle2.keySet()) {
                    Object obj = -1;
                    switch (str.hashCode()) {
                        case -1829808865:
                            if (str.equals("View_minHeight")) {
                                obj = 1;
                                break;
                            }
                            break;
                        case -499175494:
                            if (str.equals("TextView_text")) {
                                obj = 3;
                                break;
                            }
                            break;
                        case -111184848:
                            if (str.equals("WebView_data")) {
                                obj = 6;
                                break;
                            }
                            break;
                        case 573559753:
                            if (str.equals("TextView_textColor")) {
                                obj = 4;
                                break;
                            }
                            break;
                        case 966644059:
                            if (str.equals("View_backgroundColor")) {
                                obj = null;
                                break;
                            }
                            break;
                        case 1729346977:
                            if (str.equals("TextView_isTitle")) {
                                obj = 5;
                                break;
                            }
                            break;
                        case 1920443715:
                            if (str.equals("View_onClickListener")) {
                                obj = 2;
                                break;
                            }
                            break;
                    }
                    switch (obj) {
                        case null:
                            findViewById.setBackgroundColor(bundle2.getInt(str));
                            break;
                        case 1:
                            findViewById.setMinimumHeight(bundle2.getInt(str));
                            break;
                        case 2:
                            String string = bundle2.getString(str);
                            Object obj2 = -1;
                            switch (string.hashCode()) {
                                case 94756344:
                                    if (string.equals("close")) {
                                        obj2 = null;
                                        break;
                                    }
                                    break;
                            }
                            switch (obj2) {
                                case null:
                                    findViewById.setOnClickListener(new zzb(this));
                                    break;
                                default:
                                    break;
                            }
                        case 3:
                            if (!(findViewById instanceof TextView)) {
                                break;
                            }
                            ((TextView) findViewById).setText(bundle2.getCharSequence(str));
                            break;
                        case 4:
                            if (!(findViewById instanceof TextView)) {
                                break;
                            }
                            ((TextView) findViewById).setTextColor(bundle2.getInt(str));
                            break;
                        case 5:
                            if ((findViewById instanceof TextView) && bundle2.getBoolean(str)) {
                                setTitle(((TextView) findViewById).getText());
                                break;
                            }
                        case 6:
                            if (!(findViewById instanceof ViewGroup)) {
                                break;
                            }
                            View webView = new WebView(this);
                            webView.loadData(bundle2.getString(str), "text/html; charset=utf-8", "UTF-8");
                            ((ViewGroup) findViewById).addView(webView, new LayoutParams(-1, -1));
                            break;
                        default:
                            break;
                    }
                }
                i = i2;
            }
        }
        return inflate;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getCallingActivity() == null || !"com.google.android.gms".equals(getCallingActivity().getPackageName())) {
            finish();
            return;
        }
        try {
            Context createPackageContext = createPackageContext("com.google.android.gms", 0);
            Bundle extras = getIntent().getExtras();
            View zza = zza(createPackageContext, null, extras);
            if (zza == null) {
                finish();
                return;
            }
            TabHost tabHost = (TabHost) zza.findViewById(16908306);
            TabWidget tabWidget = (TabWidget) zza.findViewById(16908307);
            ArrayList parcelableArrayList = extras.getParcelableArrayList("com.google.android.gms.appinvite.TABS");
            if (!(tabHost == null || tabWidget == null || parcelableArrayList == null)) {
                tabHost.setup();
                parcelableArrayList = parcelableArrayList;
                int size = parcelableArrayList.size();
                int i = 0;
                while (i < size) {
                    Object obj = parcelableArrayList.get(i);
                    i++;
                    Bundle bundle2 = (Bundle) obj;
                    TabSpec newTabSpec = tabHost.newTabSpec(bundle2.getString("tabTag"));
                    newTabSpec.setContent(bundle2.getInt("tabContentId"));
                    newTabSpec.setIndicator(zza(createPackageContext, tabWidget, bundle2));
                    tabHost.addTab(newTabSpec);
                }
            }
            setContentView(zza);
        } catch (NameNotFoundException e) {
            finish();
        }
    }
}
