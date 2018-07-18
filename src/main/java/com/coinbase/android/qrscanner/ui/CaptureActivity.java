package com.coinbase.android.qrscanner.ui;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.coinbase.android.qrscanner.internal.CaptureActivityPresenter;
import com.coinbase.android.qrscanner.internal.CaptureActivityScreen;
import com.coinbase.android.qrscanner.ui.gms.GraphicOverlay;
import com.coinbase.zxing.client.android.FinishListener;
import com.coinbase.zxing.client.android.Intents.Scan;
import com.coinbase.zxing.client.android.R;
import com.coinbase.zxing.client.android.ScanTabsWidget;

public final class CaptureActivity extends Activity implements CaptureActivityScreen {
    private View mAddressView;
    private CaptureActivityPresenter mCaptureActivityPresenter;
    private GraphicOverlay mGraphicOverlay;
    private ImageView mQrCode;
    private Bitmap mQrImage;
    private String mQrText;
    private TextView mStatusView;
    private View mTorchParent;
    private ImageButton mTorchView;

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        this.mCaptureActivityPresenter = new CaptureActivityPresenter(this);
        this.mCaptureActivityPresenter.onCreate();
        getWindow().addFlags(128);
        setContentView(R.layout.zxing_capture);
        this.mGraphicOverlay = new GraphicOverlay(this, null);
        this.mGraphicOverlay.setLayoutParams(new LayoutParams(-1, -1));
        this.mTorchParent = getLayoutInflater().inflate(com.coinbase.qr_scanner.gms.R.layout.torch_image, null);
        FrameLayout parent = (FrameLayout) findViewById(R.id.parent_layout);
        parent.addView(this.mGraphicOverlay);
        parent.addView(this.mTorchParent);
        this.mTorchView = (ImageButton) this.mTorchParent.findViewById(com.coinbase.qr_scanner.gms.R.id.flashlight_button);
        this.mTorchView.getDrawable().setAlpha(155);
        this.mTorchView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                CaptureActivity.this.mCaptureActivityPresenter.toggleTorch();
            }
        });
        ScanTabsWidget scanTabsWidget = (ScanTabsWidget) findViewById(R.id.scan_widget);
        String explanationText = getIntent().getStringExtra(com.coinbase.zxing.client.android.CaptureActivity.EXPLAIN_TEXT);
        this.mQrText = getIntent().getStringExtra(com.coinbase.zxing.client.android.CaptureActivity.QR_TEXT);
        byte[] byte_array = getIntent().getByteArrayExtra(com.coinbase.zxing.client.android.CaptureActivity.QR_BITMAP);
        if (byte_array != null) {
            this.mQrImage = BitmapFactory.decodeByteArray(byte_array, 0, byte_array.length);
        }
        if (this.mQrText == null) {
            scanTabsWidget.setVisibility(4);
        }
        this.mAddressView = findViewById(R.id.my_address);
        this.mQrCode = (ImageView) findViewById(R.id.qrcode);
        this.mQrCode.setImageBitmap(this.mQrImage);
        ((TextView) findViewById(R.id.qrcode_text)).setText(this.mQrText);
        TextView explanation = (TextView) findViewById(R.id.explaination);
        if (explanationText != null && explanationText.length() > 0) {
            explanation.setText(explanationText);
            explanation.setVisibility(0);
        }
        ((Button) findViewById(R.id.copy_address)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                CaptureActivity.this.copyAddress();
            }
        });
        scanTabsWidget.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (!((ScanTabsWidget) v).getTabSelected()) {
                    CaptureActivity.this.mAddressView.setVisibility(4);
                    CaptureActivity.this.mStatusView.setVisibility(0);
                    CaptureActivity.this.mTorchParent.setVisibility(0);
                } else if (CaptureActivity.this.mQrText != null) {
                    CaptureActivity.this.mAddressView.setVisibility(0);
                    CaptureActivity.this.mStatusView.setVisibility(4);
                    int h = CaptureActivity.this.mAddressView.getHeight();
                    ViewGroup.LayoutParams lllp = CaptureActivity.this.mQrCode.getLayoutParams();
                    if (CaptureActivity.this.mAddressView.getWidth() > h) {
                        lllp.width = CaptureActivity.this.mAddressView.getHeight() / 2;
                    } else {
                        lllp.height = CaptureActivity.this.mAddressView.getHeight() / 2;
                    }
                    CaptureActivity.this.mQrCode.setLayoutParams(lllp);
                    CaptureActivity.this.mQrCode.requestLayout();
                    CaptureActivity.this.mTorchParent.setVisibility(4);
                }
            }
        });
    }

    protected void onResume() {
        super.onResume();
        this.mStatusView = (TextView) findViewById(R.id.status_view);
        this.mStatusView.setText(com.coinbase.qr_scanner.gms.R.string.barcode_scan_messsage_gms);
        resetStatusView();
        this.mCaptureActivityPresenter.onResume((SurfaceView) findViewById(R.id.preview_view), this.mGraphicOverlay);
        Intent intent = getIntent();
        if (intent != null) {
            if (Scan.ACTION.equals(intent.getAction())) {
                String customPromptMessage = intent.getStringExtra(Scan.PROMPT_MESSAGE);
                if (customPromptMessage != null) {
                    this.mStatusView.setText(customPromptMessage);
                }
            }
        }
    }

    protected void onPause() {
        this.mCaptureActivityPresenter.onPause();
        super.onPause();
    }

    protected void onDestroy() {
        this.mCaptureActivityPresenter.onDestroy();
        super.onDestroy();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 4:
                setResult(0);
                finish();
                break;
            case 24:
            case 25:
            case 27:
            case 80:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void displayFrameworkBugMessageAndExit() {
        Builder builder = new Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage(getString(com.coinbase.qr_scanner.gms.R.string.camera_error));
        builder.setPositiveButton(17039370, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    public void displayCameraBugMessageAndContinue() {
        Toast.makeText(this, getString(com.coinbase.qr_scanner.gms.R.string.camera_error), 0).show();
    }

    private void resetStatusView() {
        this.mStatusView.setVisibility(0);
    }

    private void copyAddress() {
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Coinbase", this.mQrText));
        Toast.makeText(this, getString(R.string.addresses_copied), 0).show();
    }

    public void hideTorch() {
        this.mTorchView.setVisibility(8);
    }

    public void setTorchOn() {
        this.mTorchView.getDrawable().setAlpha(150);
    }

    public void setTorchOff() {
        this.mTorchView.getDrawable().setAlpha(255);
    }
}
