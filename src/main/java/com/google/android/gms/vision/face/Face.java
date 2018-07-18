package com.google.android.gms.vision.face;

import android.graphics.PointF;
import java.util.Arrays;
import java.util.List;

public class Face {
    private int mId;
    private float zziiv;
    private float zziiw;
    private PointF zzkim;
    private float zzkin;
    private float zzkio;
    private List<Landmark> zzkip;
    private float zzkiq;
    private float zzkir;
    private float zzkis;

    public Face(int i, PointF pointF, float f, float f2, float f3, float f4, Landmark[] landmarkArr, float f5, float f6, float f7) {
        this.mId = i;
        this.zzkim = pointF;
        this.zziiv = f;
        this.zziiw = f2;
        this.zzkin = f3;
        this.zzkio = f4;
        this.zzkip = Arrays.asList(landmarkArr);
        if (f5 < 0.0f || f5 > 1.0f) {
            this.zzkiq = -1.0f;
        } else {
            this.zzkiq = f5;
        }
        if (f6 < 0.0f || f6 > 1.0f) {
            this.zzkir = -1.0f;
        } else {
            this.zzkir = f6;
        }
        if (f7 < 0.0f || f7 > 1.0f) {
            this.zzkis = -1.0f;
        } else {
            this.zzkis = f7;
        }
    }

    public int getId() {
        return this.mId;
    }
}
