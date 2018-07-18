package hotchemi.android.rate;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.View;

final class DialogManager {
    static Dialog create(final Context context, final DialogOptions options) {
        Builder builder = Utils.getDialogBuilder(context);
        builder.setMessage(options.getMessageText(context));
        if (options.shouldShowTitle()) {
            builder.setTitle(options.getTitleText(context));
        }
        builder.setCancelable(options.getCancelable());
        View view = options.getView();
        if (view != null) {
            builder.setView(view);
        }
        final OnClickButtonListener listener = options.getListener();
        builder.setPositiveButton(options.getPositiveText(context), new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(options.getStoreType() == StoreType.GOOGLEPLAY ? IntentHelper.createIntentForGooglePlay(context) : IntentHelper.createIntentForAmazonAppstore(context));
                PreferenceHelper.setAgreeShowDialog(context, false);
                if (listener != null) {
                    listener.onClickButton(which);
                }
            }
        });
        if (options.shouldShowNeutralButton()) {
            builder.setNeutralButton(options.getNeutralText(context), new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    PreferenceHelper.setRemindInterval(context);
                    if (listener != null) {
                        listener.onClickButton(which);
                    }
                }
            });
        }
        if (options.shouldShowNegativeButton()) {
            builder.setNegativeButton(options.getNegativeText(context), new OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    PreferenceHelper.setAgreeShowDialog(context, false);
                    if (listener != null) {
                        listener.onClickButton(which);
                    }
                }
            });
        }
        return builder.create();
    }
}
