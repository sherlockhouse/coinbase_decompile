package com.coinbase.zxing.client.android;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.coinbase.zxing.client.android.Intents.Scan;
import com.coinbase.zxing.client.android.camera.CameraManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public final class CaptureActivity extends Activity implements Callback {
    public static final String EXPLAIN_TEXT = "CaptureActivity_Explanation_Text";
    public static final String QR_BITMAP = "CaptureActivity_QR_Code_Bitmap";
    public static final String QR_TEXT = "CaptureActivity_QR_Code_Text";
    private static final String TAG = CaptureActivity.class.getSimpleName();
    private View addressView;
    private BeepManager beepManager;
    private CameraManager cameraManager;
    private String characterSet;
    private Button copyAddressBtn;
    private Collection<BarcodeFormat> decodeFormats;
    private TextView explanation;
    private String explanationText;
    private CaptureActivityHandler handler;
    private boolean hasSurface;
    private InactivityTimer inactivityTimer;
    private ImageView qrCode;
    private TextView qrText;
    private Bitmap qr_image;
    private String qr_text;
    private Result savedResultToShow;
    private ScanTabsWidget scanTabsWidget;
    private IntentSource source;
    private TextView statusView;
    private ViewfinderView viewfinderView;

    ViewfinderView getViewfinderView() {
        return this.viewfinderView;
    }

    public Handler getHandler() {
        return this.handler;
    }

    CameraManager getCameraManager() {
        return this.cameraManager;
    }

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getWindow().addFlags(128);
        setContentView(R.layout.zxing_capture);
        if (findViewById(R.id.this_is_landscape_large) == null) {
            boolean isLargeLandscape = false;
        }
        this.hasSurface = false;
        this.inactivityTimer = new InactivityTimer(this);
        this.beepManager = new BeepManager(this);
        this.scanTabsWidget = (ScanTabsWidget) findViewById(R.id.scan_widget);
        this.explanationText = getIntent().getStringExtra(EXPLAIN_TEXT);
        this.qr_text = getIntent().getStringExtra(QR_TEXT);
        byte[] byte_array = getIntent().getByteArrayExtra(QR_BITMAP);
        if (byte_array != null) {
            this.qr_image = BitmapFactory.decodeByteArray(byte_array, 0, byte_array.length);
        }
        if (this.qr_text == null) {
            this.scanTabsWidget.setVisibility(4);
        }
        this.addressView = findViewById(R.id.my_address);
        this.qrCode = (ImageView) findViewById(R.id.qrcode);
        this.qrCode.setImageBitmap(this.qr_image);
        this.qrText = (TextView) findViewById(R.id.qrcode_text);
        this.qrText.setText(this.qr_text);
        this.explanation = (TextView) findViewById(R.id.explaination);
        if (this.explanationText != null && this.explanationText.length() > 0) {
            this.explanation.setText(this.explanationText);
            this.explanation.setVisibility(0);
        }
        this.copyAddressBtn = (Button) findViewById(R.id.copy_address);
        this.copyAddressBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                CaptureActivity.this.copyAddress();
            }
        });
        this.scanTabsWidget.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                boolean isAddress = ((ScanTabsWidget) v).getTabSelected();
                if (!isAddress) {
                    CaptureActivity.this.addressView.setVisibility(4);
                    CaptureActivity.this.statusView.setVisibility(0);
                } else if (CaptureActivity.this.qr_text != null) {
                    CaptureActivity.this.addressView.setVisibility(0);
                    CaptureActivity.this.statusView.setVisibility(4);
                    int h = CaptureActivity.this.addressView.getHeight();
                    LayoutParams lllp = CaptureActivity.this.qrCode.getLayoutParams();
                    if (CaptureActivity.this.addressView.getWidth() > h) {
                        lllp.width = CaptureActivity.this.addressView.getHeight() / 2;
                    } else {
                        lllp.height = CaptureActivity.this.addressView.getHeight() / 2;
                    }
                    CaptureActivity.this.qrCode.setLayoutParams(lllp);
                    CaptureActivity.this.qrCode.requestLayout();
                } else {
                    return;
                }
                CaptureActivity.this.getViewfinderView().hideScanner(isAddress);
            }
        });
    }

    public void finish() {
        super.finish();
    }

    protected void onResume() {
        super.onResume();
        this.cameraManager = new CameraManager(getApplication());
        this.viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
        this.viewfinderView.setCameraManager(this.cameraManager);
        this.statusView = (TextView) findViewById(R.id.status_view);
        this.handler = null;
        resetStatusView();
        SurfaceHolder surfaceHolder = ((SurfaceView) findViewById(R.id.preview_view)).getHolder();
        if (this.hasSurface) {
            initCamera(surfaceHolder);
        } else {
            surfaceHolder.addCallback(this);
            surfaceHolder.setType(3);
        }
        this.beepManager.updatePrefs();
        this.inactivityTimer.onResume();
        Intent intent = getIntent();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        setRequestedOrientation(getRequestedOrientation());
        this.source = IntentSource.NONE;
        this.decodeFormats = null;
        this.characterSet = null;
        if (intent != null) {
            String action = intent.getAction();
            String dataString = intent.getDataString();
            if (Scan.ACTION.equals(action)) {
                this.source = IntentSource.NATIVE_APP_INTENT;
                this.decodeFormats = DecodeFormatManager.parseDecodeFormats(intent);
                if (intent.hasExtra(Scan.WIDTH) && intent.hasExtra(Scan.HEIGHT)) {
                    int width = intent.getIntExtra(Scan.WIDTH, 0);
                    int height = intent.getIntExtra(Scan.HEIGHT, 0);
                    if (width > 0 && height > 0) {
                        this.cameraManager.setManualFramingRect(width, height);
                    }
                }
                String customPromptMessage = intent.getStringExtra(Scan.PROMPT_MESSAGE);
                if (customPromptMessage != null) {
                    this.statusView.setText(customPromptMessage);
                }
            }
            this.characterSet = intent.getStringExtra(Scan.CHARACTER_SET);
        }
    }

    protected void onPause() {
        if (this.handler != null) {
            this.handler.quitSynchronously();
            this.handler = null;
        }
        this.inactivityTimer.onPause();
        this.cameraManager.closeDriver();
        if (!this.hasSurface) {
            ((SurfaceView) findViewById(R.id.preview_view)).getHolder().removeCallback(this);
        }
        super.onPause();
    }

    protected void onDestroy() {
        this.inactivityTimer.shutdown();
        super.onDestroy();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case 4:
                setResult(0);
                finish();
                break;
            case 24:
                this.cameraManager.setTorch(true);
                return true;
            case 25:
                this.cameraManager.setTorch(false);
                return true;
            case 27:
            case 80:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        if (holder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!this.hasSurface) {
            this.hasSurface = true;
            initCamera(holder);
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        this.hasSurface = false;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    public void handleDecode(Result rawResult, Bitmap barcode) {
        this.inactivityTimer.onActivity();
        if (barcode != null) {
            this.beepManager.playBeepSoundAndVibrate();
            drawResultPoints(barcode, rawResult);
        }
        handleDecodeExternally(rawResult, barcode);
    }

    private void drawResultPoints(Bitmap barcode, Result rawResult) {
        int i = 0;
        ResultPoint[] points = rawResult.getResultPoints();
        if (points != null && points.length > 0) {
            Canvas canvas = new Canvas(barcode);
            Paint paint = new Paint();
            paint.setColor(getResources().getColor(R.color.barcode_result_points));
            if (points.length == 2) {
                paint.setStrokeWidth(4.0f);
                drawLine(canvas, paint, points[0], points[1]);
            } else if (points.length == 4 && (rawResult.getBarcodeFormat() == BarcodeFormat.UPC_A || rawResult.getBarcodeFormat() == BarcodeFormat.EAN_13)) {
                drawLine(canvas, paint, points[0], points[1]);
                drawLine(canvas, paint, points[2], points[3]);
            } else {
                paint.setStrokeWidth(10.0f);
                int length = points.length;
                while (i < length) {
                    ResultPoint point = points[i];
                    canvas.drawPoint(point.getX(), point.getY(), paint);
                    i++;
                }
            }
        }
    }

    private static void drawLine(Canvas canvas, Paint paint, ResultPoint a, ResultPoint b) {
        canvas.drawLine(a.getX(), a.getY(), b.getX(), b.getY(), paint);
    }

    private void handleDecodeExternally(Result rawResult, Bitmap barcode) {
        Intent intent = new Intent(getIntent().getAction());
        intent.addFlags(524288);
        intent.putExtra(Scan.RESULT, rawResult.toString());
        intent.putExtra(Scan.RESULT_FORMAT, rawResult.getBarcodeFormat().toString());
        byte[] rawBytes = rawResult.getRawBytes();
        if (rawBytes != null && rawBytes.length > 0) {
            intent.putExtra(Scan.RESULT_BYTES, rawBytes);
        }
        Map<ResultMetadataType, ?> metadata = rawResult.getResultMetadata();
        if (metadata != null) {
            if (metadata.containsKey(ResultMetadataType.UPC_EAN_EXTENSION)) {
                intent.putExtra(Scan.RESULT_UPC_EAN_EXTENSION, metadata.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
            }
            Integer orientation = (Integer) metadata.get(ResultMetadataType.ORIENTATION);
            if (orientation != null) {
                intent.putExtra(Scan.RESULT_ORIENTATION, orientation.intValue());
            }
            String ecLevel = (String) metadata.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
            if (ecLevel != null) {
                intent.putExtra(Scan.RESULT_ERROR_CORRECTION_LEVEL, ecLevel);
            }
            Iterable<byte[]> byteSegments = (Iterable) metadata.get(ResultMetadataType.BYTE_SEGMENTS);
            if (byteSegments != null) {
                int i = 0;
                for (byte[] byteSegment : byteSegments) {
                    intent.putExtra(Scan.RESULT_BYTE_SEGMENTS_PREFIX + i, byteSegment);
                    i++;
                }
            }
        }
        setResult(-1, intent);
        finish();
    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        } else if (this.cameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
        } else {
            try {
                this.cameraManager.openDriver(this, surfaceHolder);
                if (this.handler == null) {
                    this.handler = new CaptureActivityHandler(this, this.decodeFormats, this.characterSet, this.cameraManager);
                }
                decodeOrStoreSavedBitmap(null, null);
            } catch (IOException ioe) {
                Log.w(TAG, ioe);
                displayFrameworkBugMessageAndExit();
            } catch (RuntimeException e) {
                Log.w(TAG, "Unexpected error initializing camera", e);
                displayFrameworkBugMessageAndExit();
            }
        }
    }

    private void decodeOrStoreSavedBitmap(Bitmap bitmap, Result result) {
        if (this.handler == null) {
            this.savedResultToShow = result;
            return;
        }
        if (result != null) {
            this.savedResultToShow = result;
        }
        if (this.savedResultToShow != null) {
            this.handler.sendMessage(Message.obtain(this.handler, R.id.decode_succeeded, this.savedResultToShow));
        }
        this.savedResultToShow = null;
    }

    private void displayFrameworkBugMessageAndExit() {
        Builder builder = new Builder(this);
        builder.setTitle(getString(R.string.app_name));
        builder.setMessage("There was a camera error.");
        builder.setPositiveButton(17039370, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    public void restartPreviewAfterDelay(long delayMS) {
        if (this.handler != null) {
            this.handler.sendEmptyMessageDelayed(R.id.restart_preview, delayMS);
        }
        resetStatusView();
    }

    private void resetStatusView() {
        this.statusView.setVisibility(0);
        this.viewfinderView.setVisibility(0);
    }

    public void drawViewfinder() {
        this.viewfinderView.drawViewfinder();
    }

    private void copyAddress() {
        ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Coinbase", this.qr_text));
        Toast.makeText(this, getString(R.string.addresses_copied), 0).show();
    }
}
