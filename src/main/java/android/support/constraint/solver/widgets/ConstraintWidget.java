package android.support.constraint.solver.widgets;

import android.support.constraint.solver.Cache;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintAnchor.Strength;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import java.util.ArrayList;

public class ConstraintWidget {
    public static float DEFAULT_BIAS = 0.5f;
    protected ArrayList<ConstraintAnchor> mAnchors = new ArrayList();
    ConstraintAnchor mBaseline = new ConstraintAnchor(this, Type.BASELINE);
    int mBaselineDistance = 0;
    ConstraintAnchor mBottom = new ConstraintAnchor(this, Type.BOTTOM);
    ConstraintAnchor mCenter = new ConstraintAnchor(this, Type.CENTER);
    ConstraintAnchor mCenterX = new ConstraintAnchor(this, Type.CENTER_X);
    ConstraintAnchor mCenterY = new ConstraintAnchor(this, Type.CENTER_Y);
    private float mCircleConstraintAngle = 0.0f;
    private Object mCompanionWidget;
    private int mContainerItemSkip = 0;
    private String mDebugName = null;
    protected float mDimensionRatio = 0.0f;
    protected int mDimensionRatioSide = -1;
    private int mDrawHeight = 0;
    private int mDrawWidth = 0;
    private int mDrawX = 0;
    private int mDrawY = 0;
    int mHeight = 0;
    float mHorizontalBiasPercent = DEFAULT_BIAS;
    boolean mHorizontalChainFixedPosition;
    int mHorizontalChainStyle = 0;
    ConstraintWidget mHorizontalNextWidget = null;
    public int mHorizontalResolution = -1;
    boolean mHorizontalWrapVisited;
    boolean mIsHeightWrapContent;
    boolean mIsWidthWrapContent;
    ConstraintAnchor mLeft = new ConstraintAnchor(this, Type.LEFT);
    protected ConstraintAnchor[] mListAnchors = new ConstraintAnchor[]{this.mLeft, this.mRight, this.mTop, this.mBottom, this.mBaseline, this.mCenter};
    protected DimensionBehaviour[] mListDimensionBehaviors = new DimensionBehaviour[]{DimensionBehaviour.FIXED, DimensionBehaviour.FIXED};
    protected ConstraintWidget[] mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
    protected ConstraintWidget[] mListNextVisibleWidget = new ConstraintWidget[]{null, null};
    int mMatchConstraintDefaultHeight = 0;
    int mMatchConstraintDefaultWidth = 0;
    int mMatchConstraintMaxHeight = 0;
    int mMatchConstraintMaxWidth = 0;
    int mMatchConstraintMinHeight = 0;
    int mMatchConstraintMinWidth = 0;
    float mMatchConstraintPercentHeight = 1.0f;
    float mMatchConstraintPercentWidth = 1.0f;
    private int[] mMaxDimension = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
    protected int mMinHeight;
    protected int mMinWidth;
    protected int mOffsetX = 0;
    protected int mOffsetY = 0;
    ConstraintWidget mParent = null;
    ResolutionDimension mResolutionHeight;
    ResolutionDimension mResolutionWidth;
    float mResolvedDimensionRatio = 1.0f;
    int mResolvedDimensionRatioSide = -1;
    ConstraintAnchor mRight = new ConstraintAnchor(this, Type.RIGHT);
    ConstraintAnchor mTop = new ConstraintAnchor(this, Type.TOP);
    private String mType = null;
    float mVerticalBiasPercent = DEFAULT_BIAS;
    boolean mVerticalChainFixedPosition;
    int mVerticalChainStyle = 0;
    ConstraintWidget mVerticalNextWidget = null;
    public int mVerticalResolution = -1;
    boolean mVerticalWrapVisited;
    private int mVisibility = 0;
    float[] mWeight = new float[]{0.0f, 0.0f};
    int mWidth = 0;
    private int mWrapHeight;
    private int mWrapWidth;
    protected int mX = 0;
    protected int mY = 0;

    public enum DimensionBehaviour {
        FIXED,
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        MATCH_PARENT
    }

    public void setMaxWidth(int maxWidth) {
        this.mMaxDimension[0] = maxWidth;
    }

    public void setMaxHeight(int maxWidth) {
        this.mMaxDimension[1] = maxWidth;
    }

    public boolean isSpreadWidth() {
        return this.mMatchConstraintDefaultWidth == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinWidth == 0 && this.mMatchConstraintMaxWidth == 0 && this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public boolean isSpreadHeight() {
        return this.mMatchConstraintDefaultHeight == 0 && this.mDimensionRatio == 0.0f && this.mMatchConstraintMinHeight == 0 && this.mMatchConstraintMaxHeight == 0 && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mCircleConstraintAngle = 0.0f;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        this.mHorizontalBiasPercent = DEFAULT_BIAS;
        this.mVerticalBiasPercent = DEFAULT_BIAS;
        this.mListDimensionBehaviors[0] = DimensionBehaviour.FIXED;
        this.mListDimensionBehaviors[1] = DimensionBehaviour.FIXED;
        this.mCompanionWidget = null;
        this.mContainerItemSkip = 0;
        this.mVisibility = 0;
        this.mType = null;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        this.mWeight[0] = 0.0f;
        this.mWeight[1] = 0.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mMaxDimension[0] = Integer.MAX_VALUE;
        this.mMaxDimension[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Integer.MAX_VALUE;
        this.mMatchConstraintMaxHeight = Integer.MAX_VALUE;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        if (this.mResolutionWidth != null) {
            this.mResolutionWidth.reset();
        }
        if (this.mResolutionHeight != null) {
            this.mResolutionHeight.reset();
        }
    }

    public void resetResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().reset();
        }
    }

    public void updateResolutionNodes() {
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i].getResolutionNode().update();
        }
    }

    public void analyze(int optimizationLevel) {
        Optimizer.analyze(optimizationLevel, this);
    }

    public void resolve() {
    }

    public ResolutionDimension getResolutionWidth() {
        if (this.mResolutionWidth == null) {
            this.mResolutionWidth = new ResolutionDimension();
        }
        return this.mResolutionWidth;
    }

    public ResolutionDimension getResolutionHeight() {
        if (this.mResolutionHeight == null) {
            this.mResolutionHeight = new ResolutionDimension();
        }
        return this.mResolutionHeight;
    }

    public ConstraintWidget() {
        addAnchors();
    }

    public void resetSolverVariables(Cache cache) {
        this.mLeft.resetSolverVariable(cache);
        this.mTop.resetSolverVariable(cache);
        this.mRight.resetSolverVariable(cache);
        this.mBottom.resetSolverVariable(cache);
        this.mBaseline.resetSolverVariable(cache);
        this.mCenter.resetSolverVariable(cache);
        this.mCenterX.resetSolverVariable(cache);
        this.mCenterY.resetSolverVariable(cache);
    }

    private void addAnchors() {
        this.mAnchors.add(this.mLeft);
        this.mAnchors.add(this.mTop);
        this.mAnchors.add(this.mRight);
        this.mAnchors.add(this.mBottom);
        this.mAnchors.add(this.mCenterX);
        this.mAnchors.add(this.mCenterY);
        this.mAnchors.add(this.mCenter);
        this.mAnchors.add(this.mBaseline);
    }

    public ConstraintWidget getParent() {
        return this.mParent;
    }

    public void setParent(ConstraintWidget widget) {
        this.mParent = widget;
    }

    public void setWidthWrapContent(boolean widthWrapContent) {
        this.mIsWidthWrapContent = widthWrapContent;
    }

    public void setHeightWrapContent(boolean heightWrapContent) {
        this.mIsHeightWrapContent = heightWrapContent;
    }

    public void connectCircularConstraint(ConstraintWidget target, float angle, int radius) {
        immediateConnect(Type.CENTER, target, Type.CENTER, radius, 0);
        this.mCircleConstraintAngle = angle;
    }

    public void setVisibility(int visibility) {
        this.mVisibility = visibility;
    }

    public int getVisibility() {
        return this.mVisibility;
    }

    public String getDebugName() {
        return this.mDebugName;
    }

    public void setDebugName(String name) {
        this.mDebugName = name;
    }

    public String toString() {
        return (this.mType != null ? "type: " + this.mType + " " : "") + (this.mDebugName != null ? "id: " + this.mDebugName + " " : "") + "(" + this.mX + ", " + this.mY + ") - (" + this.mWidth + " x " + this.mHeight + ") wrap: (" + this.mWrapWidth + " x " + this.mWrapHeight + ")";
    }

    public int getX() {
        return this.mX;
    }

    public int getY() {
        return this.mY;
    }

    public int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public int getWrapWidth() {
        return this.mWrapWidth;
    }

    public int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public int getWrapHeight() {
        return this.mWrapHeight;
    }

    public int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    protected int getRootX() {
        return this.mX + this.mOffsetX;
    }

    protected int getRootY() {
        return this.mY + this.mOffsetY;
    }

    public int getRight() {
        return getX() + this.mWidth;
    }

    public int getBottom() {
        return getY() + this.mHeight;
    }

    public float getHorizontalBiasPercent() {
        return this.mHorizontalBiasPercent;
    }

    public boolean hasBaseline() {
        return this.mBaselineDistance > 0;
    }

    public int getBaselineDistance() {
        return this.mBaselineDistance;
    }

    public Object getCompanionWidget() {
        return this.mCompanionWidget;
    }

    public ArrayList<ConstraintAnchor> getAnchors() {
        return this.mAnchors;
    }

    public void setX(int x) {
        this.mX = x;
    }

    public void setY(int y) {
        this.mY = y;
    }

    public void setOrigin(int x, int y) {
        this.mX = x;
        this.mY = y;
    }

    public void setOffset(int x, int y) {
        this.mOffsetX = x;
        this.mOffsetY = y;
    }

    public void updateDrawPosition() {
        int left = this.mX;
        int top = this.mY;
        int right = this.mX + this.mWidth;
        int bottom = this.mY + this.mHeight;
        this.mDrawX = left;
        this.mDrawY = top;
        this.mDrawWidth = right - left;
        this.mDrawHeight = bottom - top;
    }

    public void setWidth(int w) {
        this.mWidth = w;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHeight(int h) {
        this.mHeight = h;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setHorizontalMatchStyle(int horizontalMatchStyle, int min, int max, float percent) {
        this.mMatchConstraintDefaultWidth = horizontalMatchStyle;
        this.mMatchConstraintMinWidth = min;
        this.mMatchConstraintMaxWidth = max;
        this.mMatchConstraintPercentWidth = percent;
        if (percent < 1.0f && this.mMatchConstraintDefaultWidth == 0) {
            this.mMatchConstraintDefaultWidth = 2;
        }
    }

    public void setVerticalMatchStyle(int verticalMatchStyle, int min, int max, float percent) {
        this.mMatchConstraintDefaultHeight = verticalMatchStyle;
        this.mMatchConstraintMinHeight = min;
        this.mMatchConstraintMaxHeight = max;
        this.mMatchConstraintPercentHeight = percent;
        if (percent < 1.0f && this.mMatchConstraintDefaultHeight == 0) {
            this.mMatchConstraintDefaultHeight = 2;
        }
    }

    public void setDimensionRatio(String ratio) {
        if (ratio == null || ratio.length() == 0) {
            this.mDimensionRatio = 0.0f;
            return;
        }
        int dimensionRatioSide = -1;
        float dimensionRatio = 0.0f;
        int len = ratio.length();
        int commaIndex = ratio.indexOf(44);
        if (commaIndex <= 0 || commaIndex >= len - 1) {
            commaIndex = 0;
        } else {
            String dimension = ratio.substring(0, commaIndex);
            if (dimension.equalsIgnoreCase("W")) {
                dimensionRatioSide = 0;
            } else if (dimension.equalsIgnoreCase("H")) {
                dimensionRatioSide = 1;
            }
            commaIndex++;
        }
        int colonIndex = ratio.indexOf(58);
        if (colonIndex < 0 || colonIndex >= len - 1) {
            String r = ratio.substring(commaIndex);
            if (r.length() > 0) {
                try {
                    dimensionRatio = Float.parseFloat(r);
                } catch (NumberFormatException e) {
                }
            }
        } else {
            String nominator = ratio.substring(commaIndex, colonIndex);
            String denominator = ratio.substring(colonIndex + 1);
            if (nominator.length() > 0 && denominator.length() > 0) {
                try {
                    float nominatorValue = Float.parseFloat(nominator);
                    float denominatorValue = Float.parseFloat(denominator);
                    if (nominatorValue > 0.0f && denominatorValue > 0.0f) {
                        dimensionRatio = dimensionRatioSide == 1 ? Math.abs(denominatorValue / nominatorValue) : Math.abs(nominatorValue / denominatorValue);
                    }
                } catch (NumberFormatException e2) {
                }
            }
        }
        if (dimensionRatio > 0.0f) {
            this.mDimensionRatio = dimensionRatio;
            this.mDimensionRatioSide = dimensionRatioSide;
        }
    }

    public void setHorizontalBiasPercent(float horizontalBiasPercent) {
        this.mHorizontalBiasPercent = horizontalBiasPercent;
    }

    public void setVerticalBiasPercent(float verticalBiasPercent) {
        this.mVerticalBiasPercent = verticalBiasPercent;
    }

    public void setMinWidth(int w) {
        if (w < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = w;
        }
    }

    public void setMinHeight(int h) {
        if (h < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = h;
        }
    }

    public void setWrapWidth(int w) {
        this.mWrapWidth = w;
    }

    public void setWrapHeight(int h) {
        this.mWrapHeight = h;
    }

    public void setFrame(int left, int top, int right, int bottom) {
        int w = right - left;
        int h = bottom - top;
        this.mX = left;
        this.mY = top;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED && w < this.mWidth) {
            w = this.mWidth;
        }
        if (this.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED && h < this.mHeight) {
            h = this.mHeight;
        }
        this.mWidth = w;
        this.mHeight = h;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setHorizontalDimension(int left, int right) {
        this.mX = left;
        this.mWidth = right - left;
        if (this.mWidth < this.mMinWidth) {
            this.mWidth = this.mMinWidth;
        }
    }

    public void setVerticalDimension(int top, int bottom) {
        this.mY = top;
        this.mHeight = bottom - top;
        if (this.mHeight < this.mMinHeight) {
            this.mHeight = this.mMinHeight;
        }
    }

    public void setBaselineDistance(int baseline) {
        this.mBaselineDistance = baseline;
    }

    public void setCompanionWidget(Object companion) {
        this.mCompanionWidget = companion;
    }

    public void setHorizontalWeight(float horizontalWeight) {
        this.mWeight[0] = horizontalWeight;
    }

    public void setVerticalWeight(float verticalWeight) {
        this.mWeight[1] = verticalWeight;
    }

    public void setHorizontalChainStyle(int horizontalChainStyle) {
        this.mHorizontalChainStyle = horizontalChainStyle;
    }

    public void setVerticalChainStyle(int verticalChainStyle) {
        this.mVerticalChainStyle = verticalChainStyle;
    }

    public boolean allowedInBarrier() {
        return this.mVisibility != 8;
    }

    public void immediateConnect(Type startType, ConstraintWidget target, Type endType, int margin, int goneMargin) {
        getAnchor(startType).connect(target.getAnchor(endType), margin, goneMargin, Strength.STRONG, 0, true);
    }

    public void resetAnchors() {
        ConstraintWidget parent = getParent();
        if (parent == null || !(parent instanceof ConstraintWidgetContainer) || !((ConstraintWidgetContainer) getParent()).handlesInternalConstraints()) {
            int mAnchorsSize = this.mAnchors.size();
            for (int i = 0; i < mAnchorsSize; i++) {
                ((ConstraintAnchor) this.mAnchors.get(i)).reset();
            }
        }
    }

    public ConstraintAnchor getAnchor(Type anchorType) {
        switch (anchorType) {
            case LEFT:
                return this.mLeft;
            case TOP:
                return this.mTop;
            case RIGHT:
                return this.mRight;
            case BOTTOM:
                return this.mBottom;
            case BASELINE:
                return this.mBaseline;
            case CENTER:
                return this.mCenter;
            case CENTER_X:
                return this.mCenterX;
            case CENTER_Y:
                return this.mCenterY;
            case NONE:
                return null;
            default:
                throw new AssertionError(anchorType.name());
        }
    }

    public DimensionBehaviour getHorizontalDimensionBehaviour() {
        return this.mListDimensionBehaviors[0];
    }

    public DimensionBehaviour getVerticalDimensionBehaviour() {
        return this.mListDimensionBehaviors[1];
    }

    public void setHorizontalDimensionBehaviour(DimensionBehaviour behaviour) {
        this.mListDimensionBehaviors[0] = behaviour;
        if (behaviour == DimensionBehaviour.WRAP_CONTENT) {
            setWidth(this.mWrapWidth);
        }
    }

    public void setVerticalDimensionBehaviour(DimensionBehaviour behaviour) {
        this.mListDimensionBehaviors[1] = behaviour;
        if (behaviour == DimensionBehaviour.WRAP_CONTENT) {
            setHeight(this.mWrapHeight);
        }
    }

    public void addToSolver(LinearSystem system) {
        SolverVariable left = system.createObjectVariable(this.mLeft);
        SolverVariable right = system.createObjectVariable(this.mRight);
        SolverVariable top = system.createObjectVariable(this.mTop);
        SolverVariable bottom = system.createObjectVariable(this.mBottom);
        SolverVariable baseline = system.createObjectVariable(this.mBaseline);
        boolean inHorizontalChain = false;
        boolean inVerticalChain = false;
        boolean horizontalParentWrapContent = false;
        boolean verticalParentWrapContent = false;
        if (this.mParent != null) {
            horizontalParentWrapContent = this.mParent != null ? this.mParent.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT : false;
            verticalParentWrapContent = this.mParent != null ? this.mParent.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT : false;
            if ((this.mLeft.mTarget != null && this.mLeft.mTarget.mTarget == this.mLeft) || (this.mRight.mTarget != null && this.mRight.mTarget.mTarget == this.mRight)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 0);
                inHorizontalChain = true;
            }
            if ((this.mTop.mTarget != null && this.mTop.mTarget.mTarget == this.mTop) || (this.mBottom.mTarget != null && this.mBottom.mTarget.mTarget == this.mBottom)) {
                ((ConstraintWidgetContainer) this.mParent).addChain(this, 1);
                inVerticalChain = true;
            }
            if (horizontalParentWrapContent && this.mVisibility != 8 && this.mLeft.mTarget == null && this.mRight.mTarget == null) {
                system.addGreaterThan(system.createObjectVariable(this.mParent.mRight), right, 0, 1);
            }
            if (verticalParentWrapContent && this.mVisibility != 8 && this.mTop.mTarget == null && this.mBottom.mTarget == null && this.mBaseline == null) {
                system.addGreaterThan(system.createObjectVariable(this.mParent.mBottom), bottom, 0, 1);
            }
        }
        int width = this.mWidth;
        if (width < this.mMinWidth) {
            width = this.mMinWidth;
        }
        int height = this.mHeight;
        if (height < this.mMinHeight) {
            height = this.mMinHeight;
        }
        boolean horizontalDimensionFixed = this.mListDimensionBehaviors[0] != DimensionBehaviour.MATCH_CONSTRAINT;
        boolean verticalDimensionFixed = this.mListDimensionBehaviors[1] != DimensionBehaviour.MATCH_CONSTRAINT;
        boolean useRatio = false;
        this.mResolvedDimensionRatioSide = this.mDimensionRatioSide;
        this.mResolvedDimensionRatio = this.mDimensionRatio;
        if (this.mDimensionRatio > 0.0f && this.mVisibility != 8) {
            useRatio = true;
            if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
                setupDimensionRatio(horizontalParentWrapContent, verticalParentWrapContent, horizontalDimensionFixed, verticalDimensionFixed);
            } else if (this.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT) {
                this.mResolvedDimensionRatioSide = 0;
                width = (int) (this.mResolvedDimensionRatio * ((float) this.mHeight));
            } else if (this.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
                height = (int) (this.mResolvedDimensionRatio * ((float) this.mWidth));
            }
        }
        boolean useHorizontalRatio = useRatio && (this.mResolvedDimensionRatioSide == 0 || this.mResolvedDimensionRatioSide == -1);
        boolean wrapContent = this.mListDimensionBehaviors[0] == DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
        boolean applyPosition = true;
        if (this.mCenter.isConnected()) {
            applyPosition = false;
        }
        if (this.mHorizontalResolution != 2) {
            applyConstraints(system, horizontalParentWrapContent, this.mParent != null ? system.createObjectVariable(this.mParent.mLeft) : null, this.mParent != null ? system.createObjectVariable(this.mParent.mRight) : null, this.mListDimensionBehaviors[0], wrapContent, this.mLeft, this.mRight, this.mX, width, this.mMinWidth, this.mMaxDimension[0], this.mHorizontalBiasPercent, useHorizontalRatio, inHorizontalChain, this.mMatchConstraintDefaultWidth, this.mMatchConstraintMinWidth, this.mMatchConstraintMaxWidth, this.mMatchConstraintPercentWidth, applyPosition);
        }
        if (this.mVerticalResolution != 2) {
            wrapContent = this.mListDimensionBehaviors[1] == DimensionBehaviour.WRAP_CONTENT && (this instanceof ConstraintWidgetContainer);
            boolean useVerticalRatio = useRatio && (this.mResolvedDimensionRatioSide == 1 || this.mResolvedDimensionRatioSide == -1);
            if (this.mBaselineDistance > 0) {
                if (this.mBaseline.getResolutionNode().state == 1) {
                    this.mBaseline.getResolutionNode().addResolvedValue(system);
                } else {
                    system.addEquality(baseline, top, getBaselineDistance(), 6);
                    if (this.mBaseline.mTarget != null) {
                        system.addEquality(baseline, system.createObjectVariable(this.mBaseline.mTarget), 0, 6);
                        applyPosition = false;
                    }
                }
            }
            applyConstraints(system, verticalParentWrapContent, this.mParent != null ? system.createObjectVariable(this.mParent.mTop) : null, this.mParent != null ? system.createObjectVariable(this.mParent.mBottom) : null, this.mListDimensionBehaviors[1], wrapContent, this.mTop, this.mBottom, this.mY, height, this.mMinHeight, this.mMaxDimension[1], this.mVerticalBiasPercent, useVerticalRatio, inVerticalChain, this.mMatchConstraintDefaultHeight, this.mMatchConstraintMinHeight, this.mMatchConstraintMaxHeight, this.mMatchConstraintPercentHeight, applyPosition);
            if (useRatio) {
                if (this.mResolvedDimensionRatioSide == 1) {
                    system.addRatio(bottom, top, right, left, this.mResolvedDimensionRatio, 6);
                } else {
                    system.addRatio(right, left, bottom, top, this.mResolvedDimensionRatio, 6);
                }
            }
            if (this.mCenter.isConnected()) {
                system.addCenterPoint(this, this.mCenter.getTarget().getOwner(), (float) Math.toRadians((double) (this.mCircleConstraintAngle + 90.0f)), this.mCenter.getMargin());
            }
        }
    }

    public void setupDimensionRatio(boolean hparentWrapContent, boolean vparentWrapContent, boolean horizontalDimensionFixed, boolean verticalDimensionFixed) {
        if (this.mMatchConstraintDefaultWidth == 0) {
            this.mMatchConstraintDefaultWidth = 3;
        }
        if (this.mMatchConstraintDefaultHeight == 0) {
            this.mMatchConstraintDefaultHeight = 3;
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (horizontalDimensionFixed && !verticalDimensionFixed) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!horizontalDimensionFixed && verticalDimensionFixed) {
                this.mResolvedDimensionRatioSide = 1;
                if (this.mDimensionRatioSide == -1) {
                    this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                }
            }
        }
        if (this.mResolvedDimensionRatioSide == 0 && (!this.mTop.isConnected() || !this.mBottom.isConnected())) {
            this.mResolvedDimensionRatioSide = 1;
        } else if (this.mResolvedDimensionRatioSide == 1 && !(this.mLeft.isConnected() && this.mRight.isConnected())) {
            this.mResolvedDimensionRatioSide = 0;
        }
        if (this.mResolvedDimensionRatioSide == -1 && !(this.mTop.isConnected() && this.mBottom.isConnected() && this.mLeft.isConnected() && this.mRight.isConnected())) {
            if (this.mTop.isConnected() && this.mBottom.isConnected()) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (this.mLeft.isConnected() && this.mRight.isConnected()) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide == -1) {
            if (hparentWrapContent && !vparentWrapContent) {
                this.mResolvedDimensionRatioSide = 0;
            } else if (!hparentWrapContent && vparentWrapContent) {
                this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
                this.mResolvedDimensionRatioSide = 1;
            }
        }
        if (this.mResolvedDimensionRatioSide != -1) {
            return;
        }
        if (this.mMatchConstraintMinWidth > 0 && this.mMatchConstraintMinHeight == 0) {
            this.mResolvedDimensionRatioSide = 0;
        } else if (this.mMatchConstraintMinWidth != 0 || this.mMatchConstraintMinHeight <= 0) {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        } else {
            this.mResolvedDimensionRatio = 1.0f / this.mResolvedDimensionRatio;
            this.mResolvedDimensionRatioSide = 1;
        }
    }

    private void applyConstraints(LinearSystem system, boolean parentWrapContent, SolverVariable parentMin, SolverVariable parentMax, DimensionBehaviour dimensionBehaviour, boolean wrapContent, ConstraintAnchor beginAnchor, ConstraintAnchor endAnchor, int beginPosition, int dimension, int minDimension, int maxDimension, float bias, boolean useRatio, boolean inChain, int matchConstraintDefault, int matchMinDimension, int matchMaxDimension, float matchPercentDimension, boolean applyPosition) {
        SolverVariable begin = system.createObjectVariable(beginAnchor);
        SolverVariable end = system.createObjectVariable(endAnchor);
        SolverVariable beginTarget = system.createObjectVariable(beginAnchor.getTarget());
        SolverVariable endTarget = system.createObjectVariable(endAnchor.getTarget());
        if (system.graphOptimizer && beginAnchor.getResolutionNode().state == 1 && endAnchor.getResolutionNode().state == 1) {
            if (LinearSystem.getMetrics() != null) {
                Metrics metrics = LinearSystem.getMetrics();
                metrics.resolvedWidgets++;
            }
            beginAnchor.getResolutionNode().addResolvedValue(system);
            endAnchor.getResolutionNode().addResolvedValue(system);
            if (!inChain && parentWrapContent) {
                system.addGreaterThan(parentMax, end, 0, 6);
                return;
            }
            return;
        }
        if (LinearSystem.getMetrics() != null) {
            metrics = LinearSystem.getMetrics();
            metrics.nonresolvedWidgets++;
        }
        boolean isBeginConnected = beginAnchor.isConnected();
        boolean isEndConnected = endAnchor.isConnected();
        boolean isCenterConnected = this.mCenter.isConnected();
        boolean variableSize = false;
        int numConnections = 0;
        if (isBeginConnected) {
            numConnections = 0 + 1;
        }
        if (isEndConnected) {
            numConnections++;
        }
        if (isCenterConnected) {
            numConnections++;
        }
        if (useRatio) {
            matchConstraintDefault = 3;
        }
        switch (dimensionBehaviour) {
            case FIXED:
                variableSize = false;
                break;
            case WRAP_CONTENT:
                variableSize = false;
                break;
            case MATCH_PARENT:
                variableSize = false;
                break;
            case MATCH_CONSTRAINT:
                variableSize = true;
                break;
        }
        if (this.mVisibility == 8) {
            dimension = 0;
            variableSize = false;
        }
        if (applyPosition) {
            if (!isBeginConnected && !isEndConnected && !isCenterConnected) {
                system.addEquality(begin, beginPosition);
            } else if (isBeginConnected && !isEndConnected) {
                system.addEquality(begin, beginTarget, beginAnchor.getMargin(), 6);
            }
        }
        if (variableSize) {
            if (matchMinDimension == -2) {
                matchMinDimension = dimension;
            }
            if (matchMaxDimension == -2) {
                matchMaxDimension = dimension;
            }
            if (matchMinDimension > 0) {
                if (parentWrapContent) {
                    system.addGreaterThan(end, begin, matchMinDimension, 6);
                } else {
                    system.addGreaterThan(end, begin, matchMinDimension, 6);
                }
                dimension = Math.max(dimension, matchMinDimension);
            }
            if (matchMaxDimension > 0) {
                if (parentWrapContent) {
                    system.addLowerThan(end, begin, matchMaxDimension, 1);
                } else {
                    system.addLowerThan(end, begin, matchMaxDimension, 6);
                }
                dimension = Math.min(dimension, matchMaxDimension);
            }
            if (matchConstraintDefault == 1) {
                if (parentWrapContent) {
                    system.addEquality(end, begin, dimension, 6);
                } else if (inChain) {
                    system.addEquality(end, begin, dimension, 4);
                } else {
                    system.addEquality(end, begin, dimension, 1);
                }
            } else if (matchConstraintDefault == 2) {
                SolverVariable percentBegin;
                SolverVariable percentEnd;
                if (beginAnchor.getType() == Type.TOP || beginAnchor.getType() == Type.BOTTOM) {
                    percentBegin = system.createObjectVariable(this.mParent.getAnchor(Type.TOP));
                    percentEnd = system.createObjectVariable(this.mParent.getAnchor(Type.BOTTOM));
                } else {
                    percentBegin = system.createObjectVariable(this.mParent.getAnchor(Type.LEFT));
                    percentEnd = system.createObjectVariable(this.mParent.getAnchor(Type.RIGHT));
                }
                system.addConstraint(system.createRow().createRowDimensionRatio(end, begin, percentEnd, percentBegin, matchPercentDimension));
                variableSize = false;
            }
            if (!(!variableSize || numConnections == 2 || useRatio)) {
                variableSize = false;
                int d = Math.max(matchMinDimension, dimension);
                if (matchMaxDimension > 0) {
                    d = Math.min(matchMaxDimension, d);
                }
                system.addEquality(end, begin, d, 6);
            }
        } else if (wrapContent) {
            system.addEquality(end, begin, 0, 3);
            if (minDimension > 0) {
                system.addGreaterThan(end, begin, minDimension, 6);
            }
            if (maxDimension < Integer.MAX_VALUE) {
                system.addLowerThan(end, begin, maxDimension, 6);
            }
        } else {
            system.addEquality(end, begin, dimension, 6);
        }
        if (applyPosition && !inChain) {
            if (isBeginConnected || isEndConnected || isCenterConnected) {
                if (!isBeginConnected || isEndConnected) {
                    if (!isBeginConnected && isEndConnected) {
                        system.addEquality(end, endTarget, -endAnchor.getMargin(), 6);
                        if (parentWrapContent) {
                            system.addGreaterThan(begin, parentMin, 0, 5);
                        }
                    } else if (isBeginConnected && isEndConnected) {
                        boolean applyBoundsCheck = false;
                        boolean applyCentering = false;
                        int centeringStrength = 5;
                        if (variableSize) {
                            if (parentWrapContent && minDimension == 0) {
                                system.addGreaterThan(end, begin, 0, 6);
                            }
                            int strength;
                            if (matchConstraintDefault == 0) {
                                strength = 6;
                                if (matchMaxDimension > 0 || matchMinDimension > 0) {
                                    strength = 4;
                                    applyBoundsCheck = true;
                                }
                                system.addEquality(begin, beginTarget, beginAnchor.getMargin(), strength);
                                system.addEquality(end, endTarget, -endAnchor.getMargin(), strength);
                                if (matchMaxDimension > 0 || matchMinDimension > 0) {
                                    applyCentering = true;
                                }
                            } else if (matchConstraintDefault == 1) {
                                applyCentering = true;
                                applyBoundsCheck = true;
                                centeringStrength = 6;
                            } else if (matchConstraintDefault == 3) {
                                applyCentering = true;
                                applyBoundsCheck = true;
                                strength = 4;
                                if (!useRatio) {
                                    strength = 6;
                                }
                                system.addEquality(begin, beginTarget, beginAnchor.getMargin(), strength);
                                system.addEquality(end, endTarget, -endAnchor.getMargin(), strength);
                            }
                        } else {
                            applyCentering = true;
                            if (parentWrapContent) {
                                system.addGreaterThan(begin, beginTarget, beginAnchor.getMargin(), 5);
                                system.addLowerThan(end, endTarget, -endAnchor.getMargin(), 5);
                            }
                        }
                        if (applyCentering) {
                            system.addCentering(begin, beginTarget, beginAnchor.getMargin(), bias, endTarget, end, endAnchor.getMargin(), centeringStrength);
                        }
                        if (applyBoundsCheck) {
                            system.addGreaterThan(begin, beginTarget, beginAnchor.getMargin(), 6);
                            system.addLowerThan(end, endTarget, -endAnchor.getMargin(), 6);
                        }
                        if (parentWrapContent) {
                            system.addGreaterThan(begin, parentMin, 0, 6);
                        }
                    }
                } else if (parentWrapContent) {
                    system.addGreaterThan(parentMax, end, 0, 5);
                }
            } else if (parentWrapContent) {
                system.addGreaterThan(parentMax, end, 0, 5);
            }
            if (parentWrapContent) {
                system.addGreaterThan(parentMax, end, 0, 6);
            }
        } else if (numConnections < 2 && parentWrapContent) {
            system.addGreaterThan(begin, parentMin, 0, 6);
            system.addGreaterThan(parentMax, end, 0, 6);
        }
    }

    public void updateFromSolver(LinearSystem system) {
        setFrame(system.getObjectVariableValue(this.mLeft), system.getObjectVariableValue(this.mTop), system.getObjectVariableValue(this.mRight), system.getObjectVariableValue(this.mBottom));
    }
}
