package com.coinbase.zxing.client.android.camera;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Point;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import com.coinbase.zxing.client.android.PreferencesActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

final class CameraConfigurationManager {
    private static final int MAX_PREVIEW_PIXELS = 921600;
    private static final int MIN_PREVIEW_PIXELS = 150400;
    private static final String TAG = "CameraConfiguration";
    private Point cameraResolution;
    private final Context context;
    private Point screenResolution;

    CameraConfigurationManager(Context context) {
        this.context = context;
    }

    void initFromCameraParameters(Camera camera) {
        Parameters parameters = camera.getParameters();
        Display display = ((WindowManager) this.context.getSystemService("window")).getDefaultDisplay();
        this.screenResolution = new Point(display.getWidth(), display.getHeight());
        Log.i(TAG, "Screen resolution: " + this.screenResolution);
        this.cameraResolution = findBestPreviewSizeValue(parameters, this.screenResolution);
        Log.i(TAG, "Camera resolution: " + this.cameraResolution);
    }

    void setDesiredCameraParameters(Camera camera, boolean safeMode) {
        Parameters parameters = camera.getParameters();
        if (parameters == null) {
            Log.w(TAG, "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i(TAG, "Initial camera parameters: " + parameters.flatten());
        if (safeMode) {
            Log.w(TAG, "In camera config safe mode -- most settings will not be honored");
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.context);
        initializeTorch(parameters, prefs, safeMode);
        String focusMode = null;
        if (prefs.getBoolean(PreferencesActivity.KEY_AUTO_FOCUS, true)) {
            if (safeMode || prefs.getBoolean(PreferencesActivity.KEY_DISABLE_CONTINUOUS_FOCUS, false)) {
                focusMode = findSettableValue(parameters.getSupportedFocusModes(), "auto");
            } else {
                focusMode = findSettableValue(parameters.getSupportedFocusModes(), "continuous-picture", "continuous-video", "auto");
            }
        }
        if (!safeMode && focusMode == null) {
            focusMode = findSettableValue(parameters.getSupportedFocusModes(), "macro", "edof");
        }
        if (focusMode != null) {
            parameters.setFocusMode(focusMode);
        }
        parameters.setPreviewSize(this.cameraResolution.x, this.cameraResolution.y);
        if (this.context.getResources().getConfiguration().orientation == 1) {
            camera.setDisplayOrientation(90);
        }
        camera.setParameters(parameters);
    }

    Point getCameraResolution() {
        return this.cameraResolution;
    }

    Point getScreenResolution() {
        return this.screenResolution;
    }

    void setTorch(Camera camera, boolean newSetting) {
        Parameters parameters = camera.getParameters();
        doSetTorch(parameters, newSetting, false);
        camera.setParameters(parameters);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.context);
        if (prefs.getBoolean(PreferencesActivity.KEY_FRONT_LIGHT, false) != newSetting) {
            Editor editor = prefs.edit();
            editor.putBoolean(PreferencesActivity.KEY_FRONT_LIGHT, newSetting);
            editor.commit();
        }
    }

    private void initializeTorch(Parameters parameters, SharedPreferences prefs, boolean safeMode) {
        doSetTorch(parameters, prefs.getBoolean(PreferencesActivity.KEY_FRONT_LIGHT, false), safeMode);
    }

    private void doSetTorch(Parameters parameters, boolean newSetting, boolean safeMode) {
        String flashMode;
        if (newSetting) {
            flashMode = findSettableValue(parameters.getSupportedFlashModes(), "torch", "on");
        } else {
            flashMode = findSettableValue(parameters.getSupportedFlashModes(), "off");
        }
        if (flashMode != null) {
            parameters.setFlashMode(flashMode);
        }
    }

    private Point findBestPreviewSizeValue(Parameters parameters, Point screenResolution) {
        List<Size> rawSupportedSizes = parameters.getSupportedPreviewSizes();
        if (rawSupportedSizes == null) {
            Log.w(TAG, "Device returned no supported preview sizes; using default");
            Size defaultSize = parameters.getPreviewSize();
            return new Point(defaultSize.width, defaultSize.height);
        }
        List<Size> arrayList = new ArrayList(rawSupportedSizes);
        Collections.sort(arrayList, new Comparator<Size>() {
            public int compare(Size a, Size b) {
                int aPixels = a.height * a.width;
                int bPixels = b.height * b.width;
                if (bPixels < aPixels) {
                    return -1;
                }
                if (bPixels > aPixels) {
                    return 1;
                }
                return 0;
            }
        });
        if (Log.isLoggable(TAG, 4)) {
            StringBuilder previewSizesString = new StringBuilder();
            for (Size supportedPreviewSize : arrayList) {
                previewSizesString.append(supportedPreviewSize.width).append('x').append(supportedPreviewSize.height).append(' ');
            }
            Log.i(TAG, "Supported preview sizes: " + previewSizesString);
        }
        Point bestSize = null;
        float screenAspectRatio = ((float) screenResolution.x) / ((float) screenResolution.y);
        float diff = Float.POSITIVE_INFINITY;
        for (Size supportedPreviewSize2 : arrayList) {
            int realWidth = supportedPreviewSize2.width;
            int realHeight = supportedPreviewSize2.height;
            int pixels = realWidth * realHeight;
            if (pixels >= MIN_PREVIEW_PIXELS && pixels <= MAX_PREVIEW_PIXELS) {
                boolean isCandidatePortrait;
                int maybeFlippedWidth;
                int maybeFlippedHeight;
                if (this.context.getResources().getConfiguration().orientation == 1) {
                    isCandidatePortrait = (realWidth < realHeight ? 1 : 0) ^ (screenResolution.x < screenResolution.y ? 1 : 0);
                } else {
                    isCandidatePortrait = realWidth < realHeight;
                }
                if (isCandidatePortrait) {
                    maybeFlippedWidth = realHeight;
                } else {
                    maybeFlippedWidth = realWidth;
                }
                if (isCandidatePortrait) {
                    maybeFlippedHeight = realWidth;
                } else {
                    maybeFlippedHeight = realHeight;
                }
                if (maybeFlippedWidth == screenResolution.x && maybeFlippedHeight == screenResolution.y) {
                    Point exactPoint = new Point(realWidth, realHeight);
                    Log.i(TAG, "Found preview size exactly matching screen size: " + exactPoint);
                    return exactPoint;
                }
                float newDiff;
                float aspectRatio = ((float) maybeFlippedWidth) / ((float) maybeFlippedHeight);
                if (this.context.getResources().getConfiguration().orientation == 1) {
                    newDiff = (float) Math.abs((screenResolution.y * realHeight) - (screenResolution.x * realWidth));
                } else {
                    newDiff = Math.abs(aspectRatio - screenAspectRatio);
                }
                if (newDiff < diff) {
                    bestSize = new Point(realWidth, realHeight);
                    diff = newDiff;
                }
            }
        }
        if (bestSize == null) {
            defaultSize = parameters.getPreviewSize();
            bestSize = new Point(defaultSize.width, defaultSize.height);
            Log.i(TAG, "No suitable preview sizes, using default: " + bestSize);
        }
        Log.i(TAG, "Found best approximate preview size: " + bestSize);
        return bestSize;
    }

    private static String findSettableValue(Collection<String> supportedValues, String... desiredValues) {
        Log.i(TAG, "Supported values: " + supportedValues);
        String result = null;
        if (supportedValues != null) {
            for (String desiredValue : desiredValues) {
                if (supportedValues.contains(desiredValue)) {
                    result = desiredValue;
                    break;
                }
            }
        }
        Log.i(TAG, "Settable value: " + result);
        return result;
    }
}
