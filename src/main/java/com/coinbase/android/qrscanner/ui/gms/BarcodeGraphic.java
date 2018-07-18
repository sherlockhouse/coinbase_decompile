package com.coinbase.android.qrscanner.ui.gms;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.v4.content.ContextCompat;
import com.coinbase.android.qrscanner.ui.gms.GraphicOverlay.Graphic;
import com.coinbase.qr_scanner.gms.R;
import com.google.android.gms.vision.barcode.Barcode;

public class BarcodeGraphic extends Graphic {
    private volatile Barcode mBarcode;
    private int mId;
    private Paint mRectPaint = new Paint();

    public BarcodeGraphic(GraphicOverlay overlay) {
        super(overlay);
        this.mRectPaint.setColor(ContextCompat.getColor(overlay.getContext().getApplicationContext(), R.color.button_primary_neutral));
        this.mRectPaint.setStyle(Style.STROKE);
        this.mRectPaint.setStrokeWidth(8.0f);
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public Barcode getBarcode() {
        return this.mBarcode;
    }

    public void updateItem(Barcode barcode) {
        this.mBarcode = barcode;
        postInvalidate();
    }

    public void draw(Canvas canvas) {
        Barcode barcode = this.mBarcode;
        if (barcode != null) {
            RectF rect = new RectF(barcode.getBoundingBox());
            rect.left = translateX(rect.left);
            rect.top = translateY(rect.top);
            rect.right = translateX(rect.right);
            rect.bottom = translateY(rect.bottom);
            canvas.drawRect(rect, this.mRectPaint);
        }
    }
}
