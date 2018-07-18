package com.coinbase.android.widgets;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import com.coinbase.android.MainActivity;
import com.coinbase.android.R;

public class WidgetCommon {
    public static void bindButtons(Context context, RemoteViews rv, int appWidgetId) {
        Intent mainMenu = new Intent(context, MainActivity.class);
        mainMenu.setAction(MainActivity.ACTION_TRANSACTIONS);
        rv.setOnClickPendingIntent(R.id.widget_icon, PendingIntent.getActivity(context, 0, mainMenu, 0));
        Intent scan = new Intent(context, MainActivity.class);
        scan.setAction(MainActivity.ACTION_SCAN);
        rv.setOnClickPendingIntent(R.id.widget_scan, PendingIntent.getActivity(context, 0, scan, 0));
        Intent refresh = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
        refresh.putExtra("appWidgetIds", new int[]{appWidgetId});
        refresh.setPackage(context.getPackageName());
        rv.setOnClickPendingIntent(R.id.widget_refresh, PendingIntent.getBroadcast(context, appWidgetId, refresh, 0));
        Intent transfer = new Intent(context, MainActivity.class);
        transfer.setAction(MainActivity.ACTION_TRANSFER);
        rv.setOnClickPendingIntent(R.id.widget_transfer, PendingIntent.getActivity(context, 0, transfer, 0));
    }
}
