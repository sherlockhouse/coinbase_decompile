package com.coinbase.zxing.client.android;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.coinbase.zxing.client.android.camera.CameraManager;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.List;

public final class ViewfinderView extends View {
    private static final long ANIMATION_DELAY = 80;
    private static final int CURRENT_POINT_OPACITY = 160;
    private static final int MAX_RESULT_POINTS = 20;
    private static final int POINT_SIZE = 6;
    private static final int[] SCANNER_ALPHA = new int[]{0, 64, 128, 192, 255, 192, 128, 64};
    private CameraManager cameraManager;
    private final int g2;
    private boolean hideScanner = false;
    private final int laserColor;
    private List<ResultPoint> lastPossibleResultPoints;
    private final int maskColor;
    private final int o;
    private final Paint paint = new Paint(1);
    private List<ResultPoint> possibleResultPoints;
    private Bitmap resultBitmap;
    private final int resultColor;
    private final int resultPointColor;
    private final int reticuleColor;
    private int scannerAlpha;
    private final int t;

    public ViewfinderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Resources resources = getResources();
        this.maskColor = resources.getColor(R.color.barcode_viewfinder_mask);
        this.resultColor = resources.getColor(R.color.barcode_result_view);
        this.laserColor = resources.getColor(R.color.barcode_viewfinder_laser);
        this.reticuleColor = resources.getColor(R.color.barcode_viewfinder_reticule);
        this.resultPointColor = resources.getColor(R.color.barcode_possible_result_points);
        this.t = (int) resources.getDimension(R.dimen.line_thickness);
        this.g2 = (int) resources.getDimension(R.dimen.gap_div_2);
        this.o = (int) resources.getDimension(R.dimen.offset_from_rect);
        this.scannerAlpha = 0;
        this.possibleResultPoints = new ArrayList(5);
        this.lastPossibleResultPoints = null;
    }

    public void setCameraManager(CameraManager cameraManager) {
        this.cameraManager = cameraManager;
    }

    public void onDraw(Canvas canvas) {
        if (this.cameraManager != null) {
            this.cameraManager.setFramingViewSize(new Point(getWidth(), getHeight()));
            Rect frame = this.cameraManager.getFramingRect();
            if (frame != null) {
                int width = canvas.getWidth();
                int height = canvas.getHeight();
                this.paint.setColor(this.resultBitmap != null ? this.resultColor : this.maskColor);
                if (this.hideScanner) {
                    canvas.drawRect(0.0f, 0.0f, (float) width, (float) height, this.paint);
                } else {
                    canvas.drawRect(0.0f, 0.0f, (float) width, (float) frame.top, this.paint);
                    canvas.drawRect(0.0f, (float) frame.top, (float) frame.left, (float) (frame.bottom + 1), this.paint);
                    canvas.drawRect((float) (frame.right + 1), (float) frame.top, (float) width, (float) (frame.bottom + 1), this.paint);
                    canvas.drawRect(0.0f, (float) (frame.bottom + 1), (float) width, (float) height, this.paint);
                    this.paint.setColor(this.reticuleColor);
                    canvas.drawRect((float) ((frame.left - this.t) - this.o), (float) ((frame.top - this.t) - this.o), (float) (((frame.right + frame.left) / 2) - this.g2), (float) (frame.top - this.o), this.paint);
                    canvas.drawRect((float) (((frame.right + frame.left) / 2) + this.g2), (float) ((frame.top - this.t) - this.o), (float) ((frame.right + this.t) + this.o), (float) (frame.top - this.o), this.paint);
                    canvas.drawRect((float) ((frame.left - this.t) - this.o), (float) (frame.bottom + this.o), (float) (((frame.right + frame.left) / 2) - this.g2), (float) ((frame.bottom + this.t) + this.o), this.paint);
                    canvas.drawRect((float) (((frame.right + frame.left) / 2) + this.g2), (float) (frame.bottom + this.o), (float) ((frame.right + this.t) + this.o), (float) ((frame.bottom + this.t) + this.o), this.paint);
                    canvas.drawRect((float) ((frame.left - this.t) - this.o), (float) (frame.top - this.o), (float) (frame.left - this.o), (float) (((frame.bottom + frame.top) / 2) - this.g2), this.paint);
                    canvas.drawRect((float) (frame.right + this.o), (float) (frame.top - this.o), (float) ((frame.right + this.t) + this.o), (float) (((frame.bottom + frame.top) / 2) - this.g2), this.paint);
                    canvas.drawRect((float) ((frame.left - this.t) - this.o), (float) (((frame.bottom + frame.top) / 2) + this.g2), (float) (frame.left - this.o), (float) (frame.bottom + this.o), this.paint);
                    canvas.drawRect((float) (frame.right + this.o), (float) (((frame.bottom + frame.top) / 2) + this.g2), (float) ((frame.right + this.t) + this.o), (float) (frame.bottom + this.o), this.paint);
                }
                if (this.resultBitmap != null) {
                    this.paint.setAlpha(CURRENT_POINT_OPACITY);
                    canvas.drawBitmap(this.resultBitmap, null, frame, this.paint);
                    return;
                }
                if (!this.hideScanner) {
                    this.paint.setColor(this.laserColor);
                    this.paint.setAlpha(SCANNER_ALPHA[this.scannerAlpha]);
                    this.scannerAlpha = (this.scannerAlpha + 1) % SCANNER_ALPHA.length;
                    int middle = (frame.height() / 2) + frame.top;
                    canvas.drawRect((float) (frame.left + 2), (float) (middle - 1), (float) (frame.right - 1), (float) (middle + 2), this.paint);
                    Rect previewFrame = this.cameraManager.getFramingRectInPreview();
                    float scaleX = ((float) frame.width()) / ((float) previewFrame.width());
                    float scaleY = ((float) frame.height()) / ((float) previewFrame.height());
                    List<ResultPoint> currentPossible = this.possibleResultPoints;
                    List<ResultPoint> currentLast = this.lastPossibleResultPoints;
                    int frameLeft = frame.left;
                    int frameTop = frame.top;
                    if (currentPossible.isEmpty()) {
                        this.lastPossibleResultPoints = null;
                    } else {
                        this.possibleResultPoints = new ArrayList(5);
                        this.lastPossibleResultPoints = currentPossible;
                        this.paint.setAlpha(CURRENT_POINT_OPACITY);
                        this.paint.setColor(this.resultPointColor);
                        synchronized (currentPossible) {
                            for (ResultPoint point : currentPossible) {
                                canvas.drawCircle((float) (((int) (point.getX() * scaleX)) + frameLeft), (float) (((int) (point.getY() * scaleY)) + frameTop), 6.0f, this.paint);
                            }
                        }
                    }
                    if (currentLast != null) {
                        this.paint.setAlpha(80);
                        this.paint.setColor(this.resultPointColor);
                        synchronized (currentLast) {
                            for (ResultPoint point2 : currentLast) {
                                canvas.drawCircle((float) (((int) (point2.getX() * scaleX)) + frameLeft), (float) (((int) (point2.getY() * scaleY)) + frameTop), 3.0f, this.paint);
                            }
                        }
                    }
                }
                postInvalidateDelayed(ANIMATION_DELAY, ((frame.left - 6) - this.t) - this.o, ((frame.top - 6) - this.t) - this.o, ((frame.right + 6) + this.t) + this.o, ((frame.bottom + 6) + this.t) + this.o);
            }
        }
    }

    public void hideScanner(boolean hide) {
        this.hideScanner = hide;
    }

    public void drawViewfinder() {
        Bitmap resultBitmap = this.resultBitmap;
        this.resultBitmap = null;
        if (resultBitmap != null) {
            resultBitmap.recycle();
        }
        invalidate();
    }

    public void drawResultBitmap(Bitmap barcode) {
        this.resultBitmap = barcode;
        invalidate();
    }

    public void addPossibleResultPoint(ResultPoint point) {
        List<ResultPoint> points = this.possibleResultPoints;
        synchronized (points) {
            points.add(point);
            int size = points.size();
            if (size > 20) {
                points.subList(0, size - 10).clear();
            }
        }
    }
}
