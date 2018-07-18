package android.support.graphics.drawable;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Build.VERSION;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.graphics.PathParser;
import android.support.v4.graphics.PathParser.PathDataNode;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.InflateException;
import com.coinbase.android.utils.CryptoUri;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatorInflaterCompat {

    private static class PathDataEvaluator implements TypeEvaluator<PathDataNode[]> {
        private PathDataNode[] mNodeArray;

        private PathDataEvaluator() {
        }

        public PathDataNode[] evaluate(float fraction, PathDataNode[] startPathData, PathDataNode[] endPathData) {
            if (PathParser.canMorph(startPathData, endPathData)) {
                if (this.mNodeArray == null || !PathParser.canMorph(this.mNodeArray, startPathData)) {
                    this.mNodeArray = PathParser.deepCopyNodes(startPathData);
                }
                for (int i = 0; i < startPathData.length; i++) {
                    this.mNodeArray[i].interpolatePathDataNode(startPathData[i], endPathData[i], fraction);
                }
                return this.mNodeArray;
            }
            throw new IllegalArgumentException("Can't interpolate between two incompatible pathData");
        }
    }

    public static Animator loadAnimator(Context context, int id) throws NotFoundException {
        if (VERSION.SDK_INT >= 24) {
            return AnimatorInflater.loadAnimator(context, id);
        }
        return loadAnimator(context, context.getResources(), context.getTheme(), id);
    }

    public static Animator loadAnimator(Context context, Resources resources, Theme theme, int id) throws NotFoundException {
        return loadAnimator(context, resources, theme, id, 1.0f);
    }

    public static Animator loadAnimator(Context context, Resources resources, Theme theme, int id, float pathErrorScale) throws NotFoundException {
        NotFoundException rnf;
        XmlResourceParser xmlResourceParser = null;
        try {
            xmlResourceParser = resources.getAnimation(id);
            Animator animator = createAnimatorFromXml(context, resources, theme, xmlResourceParser, pathErrorScale);
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
            return animator;
        } catch (XmlPullParserException ex) {
            rnf = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
            rnf.initCause(ex);
            throw rnf;
        } catch (IOException ex2) {
            rnf = new NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(id));
            rnf.initCause(ex2);
            throw rnf;
        } catch (Throwable th) {
            if (xmlResourceParser != null) {
                xmlResourceParser.close();
            }
        }
    }

    private static PropertyValuesHolder getPVH(TypedArray styledAttributes, int valueType, int valueFromId, int valueToId, String propertyName) {
        TypedValue tvFrom = styledAttributes.peekValue(valueFromId);
        boolean hasFrom = tvFrom != null;
        int fromType = hasFrom ? tvFrom.type : 0;
        TypedValue tvTo = styledAttributes.peekValue(valueToId);
        boolean hasTo = tvTo != null;
        int toType = hasTo ? tvTo.type : 0;
        if (valueType == 4) {
            if ((hasFrom && isColorType(fromType)) || (hasTo && isColorType(toType))) {
                valueType = 3;
            } else {
                valueType = 0;
            }
        }
        boolean getFloats = valueType == 0;
        PropertyValuesHolder returnValue = null;
        TypeEvaluator evaluator;
        if (valueType == 2) {
            String fromString = styledAttributes.getString(valueFromId);
            String toString = styledAttributes.getString(valueToId);
            PathDataNode[] nodesFrom = PathParser.createNodesFromPathData(fromString);
            PathDataNode[] nodesTo = PathParser.createNodesFromPathData(toString);
            if (nodesFrom == null && nodesTo == null) {
                return null;
            }
            if (nodesFrom != null) {
                evaluator = new PathDataEvaluator();
                if (nodesTo == null) {
                    return PropertyValuesHolder.ofObject(propertyName, evaluator, new Object[]{nodesFrom});
                } else if (PathParser.canMorph(nodesFrom, nodesTo)) {
                    return PropertyValuesHolder.ofObject(propertyName, evaluator, new Object[]{nodesFrom, nodesTo});
                } else {
                    throw new InflateException(" Can't morph from " + fromString + " to " + toString);
                }
            } else if (nodesTo == null) {
                return null;
            } else {
                return PropertyValuesHolder.ofObject(propertyName, new PathDataEvaluator(), new Object[]{nodesTo});
            }
        }
        evaluator = null;
        if (valueType == 3) {
            evaluator = ArgbEvaluator.getInstance();
        }
        if (getFloats) {
            float valueTo;
            if (hasFrom) {
                float valueFrom;
                if (fromType == 5) {
                    valueFrom = styledAttributes.getDimension(valueFromId, 0.0f);
                } else {
                    valueFrom = styledAttributes.getFloat(valueFromId, 0.0f);
                }
                if (hasTo) {
                    if (toType == 5) {
                        valueTo = styledAttributes.getDimension(valueToId, 0.0f);
                    } else {
                        valueTo = styledAttributes.getFloat(valueToId, 0.0f);
                    }
                    returnValue = PropertyValuesHolder.ofFloat(propertyName, new float[]{valueFrom, valueTo});
                } else {
                    returnValue = PropertyValuesHolder.ofFloat(propertyName, new float[]{valueFrom});
                }
            } else {
                if (toType == 5) {
                    valueTo = styledAttributes.getDimension(valueToId, 0.0f);
                } else {
                    valueTo = styledAttributes.getFloat(valueToId, 0.0f);
                }
                returnValue = PropertyValuesHolder.ofFloat(propertyName, new float[]{valueTo});
            }
        } else if (hasFrom) {
            int valueFrom2;
            if (fromType == 5) {
                valueFrom2 = (int) styledAttributes.getDimension(valueFromId, 0.0f);
            } else if (isColorType(fromType)) {
                valueFrom2 = styledAttributes.getColor(valueFromId, 0);
            } else {
                valueFrom2 = styledAttributes.getInt(valueFromId, 0);
            }
            if (hasTo) {
                if (toType == 5) {
                    valueTo = (int) styledAttributes.getDimension(valueToId, 0.0f);
                } else if (isColorType(toType)) {
                    valueTo = styledAttributes.getColor(valueToId, 0);
                } else {
                    valueTo = styledAttributes.getInt(valueToId, 0);
                }
                returnValue = PropertyValuesHolder.ofInt(propertyName, new int[]{valueFrom2, valueTo});
            } else {
                returnValue = PropertyValuesHolder.ofInt(propertyName, new int[]{valueFrom2});
            }
        } else if (hasTo) {
            if (toType == 5) {
                valueTo = (int) styledAttributes.getDimension(valueToId, 0.0f);
            } else if (isColorType(toType)) {
                valueTo = styledAttributes.getColor(valueToId, 0);
            } else {
                valueTo = styledAttributes.getInt(valueToId, 0);
            }
            returnValue = PropertyValuesHolder.ofInt(propertyName, new int[]{valueTo});
        }
        if (returnValue == null || evaluator == null) {
            return returnValue;
        }
        returnValue.setEvaluator(evaluator);
        return returnValue;
    }

    private static void parseAnimatorFromTypeArray(ValueAnimator anim, TypedArray arrayAnimator, TypedArray arrayObjectAnimator, float pixelSize, XmlPullParser parser) {
        long duration = (long) TypedArrayUtils.getNamedInt(arrayAnimator, parser, "duration", 1, 300);
        long startDelay = (long) TypedArrayUtils.getNamedInt(arrayAnimator, parser, "startOffset", 2, 0);
        int valueType = TypedArrayUtils.getNamedInt(arrayAnimator, parser, "valueType", 7, 4);
        if (TypedArrayUtils.hasAttribute(parser, "valueFrom") && TypedArrayUtils.hasAttribute(parser, "valueTo")) {
            if (valueType == 4) {
                valueType = inferValueTypeFromValues(arrayAnimator, 5, 6);
            }
            if (getPVH(arrayAnimator, valueType, 5, 6, "") != null) {
                anim.setValues(new PropertyValuesHolder[]{getPVH(arrayAnimator, valueType, 5, 6, "")});
            }
        }
        anim.setDuration(duration);
        anim.setStartDelay(startDelay);
        anim.setRepeatCount(TypedArrayUtils.getNamedInt(arrayAnimator, parser, "repeatCount", 3, 0));
        anim.setRepeatMode(TypedArrayUtils.getNamedInt(arrayAnimator, parser, "repeatMode", 4, 1));
        if (arrayObjectAnimator != null) {
            setupObjectAnimator(anim, arrayObjectAnimator, valueType, pixelSize, parser);
        }
    }

    private static void setupObjectAnimator(ValueAnimator anim, TypedArray arrayObjectAnimator, int valueType, float pixelSize, XmlPullParser parser) {
        ObjectAnimator oa = (ObjectAnimator) anim;
        String pathData = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "pathData", 1);
        if (pathData != null) {
            String propertyXName = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyXName", 2);
            String propertyYName = TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyYName", 3);
            if (valueType == 2 || valueType == 4) {
            }
            if (propertyXName == null && propertyYName == null) {
                throw new InflateException(arrayObjectAnimator.getPositionDescription() + " propertyXName or propertyYName is needed for PathData");
            }
            setupPathMotion(PathParser.createPathFromPathData(pathData), oa, 0.5f * pixelSize, propertyXName, propertyYName);
            return;
        }
        oa.setPropertyName(TypedArrayUtils.getNamedString(arrayObjectAnimator, parser, "propertyName", 0));
    }

    private static void setupPathMotion(Path path, ObjectAnimator oa, float precision, String propertyXName, String propertyYName) {
        PathMeasure measureForTotalLength = new PathMeasure(path, false);
        float totalLength = 0.0f;
        ArrayList<Float> contourLengths = new ArrayList();
        contourLengths.add(Float.valueOf(0.0f));
        do {
            totalLength += measureForTotalLength.getLength();
            contourLengths.add(Float.valueOf(totalLength));
        } while (measureForTotalLength.nextContour());
        PathMeasure pathMeasure = new PathMeasure(path, false);
        int numPoints = Math.min(100, ((int) (totalLength / precision)) + 1);
        float[] mX = new float[numPoints];
        float[] mY = new float[numPoints];
        float[] position = new float[2];
        int contourIndex = 0;
        float step = totalLength / ((float) (numPoints - 1));
        float currentDistance = 0.0f;
        for (int i = 0; i < numPoints; i++) {
            pathMeasure.getPosTan(currentDistance, position, null);
            pathMeasure.getPosTan(currentDistance, position, null);
            mX[i] = position[0];
            mY[i] = position[1];
            currentDistance += step;
            if (contourIndex + 1 < contourLengths.size() && currentDistance > ((Float) contourLengths.get(contourIndex + 1)).floatValue()) {
                currentDistance -= ((Float) contourLengths.get(contourIndex + 1)).floatValue();
                contourIndex++;
                pathMeasure.nextContour();
            }
        }
        PropertyValuesHolder x = null;
        PropertyValuesHolder y = null;
        if (propertyXName != null) {
            x = PropertyValuesHolder.ofFloat(propertyXName, mX);
        }
        if (propertyYName != null) {
            y = PropertyValuesHolder.ofFloat(propertyYName, mY);
        }
        if (x == null) {
            oa.setValues(new PropertyValuesHolder[]{y});
        } else if (y == null) {
            oa.setValues(new PropertyValuesHolder[]{x});
        } else {
            oa.setValues(new PropertyValuesHolder[]{x, y});
        }
    }

    private static Animator createAnimatorFromXml(Context context, Resources res, Theme theme, XmlPullParser parser, float pixelSize) throws XmlPullParserException, IOException {
        return createAnimatorFromXml(context, res, theme, parser, Xml.asAttributeSet(parser), null, 0, pixelSize);
    }

    private static Animator createAnimatorFromXml(Context context, Resources res, Theme theme, XmlPullParser parser, AttributeSet attrs, AnimatorSet parent, int sequenceOrdering, float pixelSize) throws XmlPullParserException, IOException {
        Animator anim = null;
        ArrayList<Animator> childAnims = null;
        int depth = parser.getDepth();
        while (true) {
            int type = parser.next();
            if ((type != 3 || parser.getDepth() > depth) && type != 1) {
                if (type == 2) {
                    String name = parser.getName();
                    boolean gotValues = false;
                    if (name.equals("objectAnimator")) {
                        anim = loadObjectAnimator(context, res, theme, attrs, pixelSize, parser);
                    } else {
                        if (name.equals("animator")) {
                            anim = loadAnimator(context, res, theme, attrs, null, pixelSize, parser);
                        } else {
                            if (name.equals("set")) {
                                anim = new AnimatorSet();
                                TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_ANIMATOR_SET);
                                Context context2 = context;
                                Resources resources = res;
                                Theme theme2 = theme;
                                XmlPullParser xmlPullParser = parser;
                                AttributeSet attributeSet = attrs;
                                createAnimatorFromXml(context2, resources, theme2, xmlPullParser, attributeSet, (AnimatorSet) anim, TypedArrayUtils.getNamedInt(a, parser, "ordering", 0, 0), pixelSize);
                                a.recycle();
                            } else {
                                if (name.equals("propertyValuesHolder")) {
                                    PropertyValuesHolder[] values = loadValues(context, res, theme, parser, Xml.asAttributeSet(parser));
                                    if (!(values == null || anim == null || !(anim instanceof ValueAnimator))) {
                                        ((ValueAnimator) anim).setValues(values);
                                    }
                                    gotValues = true;
                                } else {
                                    throw new RuntimeException("Unknown animator name: " + parser.getName());
                                }
                            }
                        }
                    }
                    if (!(parent == null || gotValues)) {
                        if (childAnims == null) {
                            childAnims = new ArrayList();
                        }
                        childAnims.add(anim);
                    }
                }
            }
        }
        if (!(parent == null || childAnims == null)) {
            Animator[] animsArray = new Animator[childAnims.size()];
            int index = 0;
            Iterator it = childAnims.iterator();
            while (it.hasNext()) {
                int index2 = index + 1;
                animsArray[index] = (Animator) it.next();
                index = index2;
            }
            if (sequenceOrdering == 0) {
                parent.playTogether(animsArray);
            } else {
                parent.playSequentially(animsArray);
            }
        }
        return anim;
    }

    private static PropertyValuesHolder[] loadValues(Context context, Resources res, Theme theme, XmlPullParser parser, AttributeSet attrs) throws XmlPullParserException, IOException {
        PropertyValuesHolder[] valuesArray;
        ArrayList<PropertyValuesHolder> values = null;
        while (true) {
            int type = parser.getEventType();
            if (type == 3 || type == 1) {
                valuesArray = null;
            } else if (type != 2) {
                parser.next();
            } else {
                if (parser.getName().equals("propertyValuesHolder")) {
                    TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_PROPERTY_VALUES_HOLDER);
                    String propertyName = TypedArrayUtils.getNamedString(a, parser, "propertyName", 3);
                    int valueType = TypedArrayUtils.getNamedInt(a, parser, "valueType", 2, 4);
                    PropertyValuesHolder pvh = loadPvh(context, res, theme, parser, propertyName, valueType);
                    if (pvh == null) {
                        pvh = getPVH(a, valueType, 0, 1, propertyName);
                    }
                    if (pvh != null) {
                        if (values == null) {
                            values = new ArrayList();
                        }
                        values.add(pvh);
                    }
                    a.recycle();
                }
                parser.next();
            }
        }
        valuesArray = null;
        if (values != null) {
            int count = values.size();
            valuesArray = new PropertyValuesHolder[count];
            for (int i = 0; i < count; i++) {
                valuesArray[i] = (PropertyValuesHolder) values.get(i);
            }
        }
        return valuesArray;
    }

    private static int inferValueTypeOfKeyframe(Resources res, Theme theme, AttributeSet attrs, XmlPullParser parser) {
        int valueType;
        boolean hasValue = false;
        TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_KEYFRAME);
        TypedValue keyframeValue = TypedArrayUtils.peekNamedValue(a, parser, CryptoUri.VALUE, 0);
        if (keyframeValue != null) {
            hasValue = true;
        }
        if (hasValue && isColorType(keyframeValue.type)) {
            valueType = 3;
        } else {
            valueType = 0;
        }
        a.recycle();
        return valueType;
    }

    private static int inferValueTypeFromValues(TypedArray styledAttributes, int valueFromId, int valueToId) {
        boolean hasFrom;
        boolean hasTo = true;
        TypedValue tvFrom = styledAttributes.peekValue(valueFromId);
        if (tvFrom != null) {
            hasFrom = true;
        } else {
            hasFrom = false;
        }
        int fromType;
        if (hasFrom) {
            fromType = tvFrom.type;
        } else {
            fromType = 0;
        }
        TypedValue tvTo = styledAttributes.peekValue(valueToId);
        if (tvTo == null) {
            hasTo = false;
        }
        int toType;
        if (hasTo) {
            toType = tvTo.type;
        } else {
            toType = 0;
        }
        if ((hasFrom && isColorType(fromType)) || (hasTo && isColorType(toType))) {
            return 3;
        }
        return 0;
    }

    private static PropertyValuesHolder loadPvh(Context context, Resources res, Theme theme, XmlPullParser parser, String propertyName, int valueType) throws XmlPullParserException, IOException {
        Keyframe keyframe;
        Keyframe lastKeyframe;
        float startFraction;
        PropertyValuesHolder value = null;
        ArrayList<Keyframe> keyframes = null;
        while (true) {
            int count;
            Keyframe firstKeyframe;
            float endFraction;
            Keyframe[] keyframeArray;
            int i;
            int endIndex;
            int j;
            int type = parser.next();
            if (type != 3 && type != 1) {
                if (parser.getName().equals("keyframe")) {
                    if (valueType == 4) {
                        valueType = inferValueTypeOfKeyframe(res, theme, Xml.asAttributeSet(parser), parser);
                    }
                    keyframe = loadKeyframe(context, res, theme, Xml.asAttributeSet(parser), valueType, parser);
                    if (keyframe != null) {
                        if (keyframes == null) {
                            keyframes = new ArrayList();
                        }
                        keyframes.add(keyframe);
                    }
                    parser.next();
                }
            } else if (keyframes != null) {
                count = keyframes.size();
                if (count > 0) {
                    firstKeyframe = (Keyframe) keyframes.get(0);
                    lastKeyframe = (Keyframe) keyframes.get(count - 1);
                    endFraction = lastKeyframe.getFraction();
                    if (endFraction < 1.0f) {
                        if (endFraction >= 0.0f) {
                            lastKeyframe.setFraction(1.0f);
                        } else {
                            keyframes.add(keyframes.size(), createNewKeyframe(lastKeyframe, 1.0f));
                            count++;
                        }
                    }
                    startFraction = firstKeyframe.getFraction();
                    if (startFraction != 0.0f) {
                        if (startFraction >= 0.0f) {
                            firstKeyframe.setFraction(0.0f);
                        } else {
                            keyframes.add(0, createNewKeyframe(firstKeyframe, 0.0f));
                            count++;
                        }
                    }
                    keyframeArray = new Keyframe[count];
                    keyframes.toArray(keyframeArray);
                    for (i = 0; i < count; i++) {
                        keyframe = keyframeArray[i];
                        if (keyframe.getFraction() >= 0.0f) {
                            if (i == 0) {
                                keyframe.setFraction(0.0f);
                            } else if (i != count - 1) {
                                keyframe.setFraction(1.0f);
                            } else {
                                int startIndex = i;
                                endIndex = i;
                                j = startIndex + 1;
                                while (j < count - 1 && keyframeArray[j].getFraction() < 0.0f) {
                                    endIndex = j;
                                    j++;
                                }
                                distributeKeyframes(keyframeArray, keyframeArray[endIndex + 1].getFraction() - keyframeArray[startIndex - 1].getFraction(), startIndex, endIndex);
                            }
                        }
                    }
                    value = PropertyValuesHolder.ofKeyframe(propertyName, keyframeArray);
                    if (valueType == 3) {
                        value.setEvaluator(ArgbEvaluator.getInstance());
                    }
                }
            }
        }
        if (keyframes != null) {
            count = keyframes.size();
            if (count > 0) {
                firstKeyframe = (Keyframe) keyframes.get(0);
                lastKeyframe = (Keyframe) keyframes.get(count - 1);
                endFraction = lastKeyframe.getFraction();
                if (endFraction < 1.0f) {
                    if (endFraction >= 0.0f) {
                        keyframes.add(keyframes.size(), createNewKeyframe(lastKeyframe, 1.0f));
                        count++;
                    } else {
                        lastKeyframe.setFraction(1.0f);
                    }
                }
                startFraction = firstKeyframe.getFraction();
                if (startFraction != 0.0f) {
                    if (startFraction >= 0.0f) {
                        keyframes.add(0, createNewKeyframe(firstKeyframe, 0.0f));
                        count++;
                    } else {
                        firstKeyframe.setFraction(0.0f);
                    }
                }
                keyframeArray = new Keyframe[count];
                keyframes.toArray(keyframeArray);
                for (i = 0; i < count; i++) {
                    keyframe = keyframeArray[i];
                    if (keyframe.getFraction() >= 0.0f) {
                        if (i == 0) {
                            keyframe.setFraction(0.0f);
                        } else if (i != count - 1) {
                            int startIndex2 = i;
                            endIndex = i;
                            j = startIndex2 + 1;
                            while (j < count - 1) {
                                endIndex = j;
                                j++;
                            }
                            distributeKeyframes(keyframeArray, keyframeArray[endIndex + 1].getFraction() - keyframeArray[startIndex2 - 1].getFraction(), startIndex2, endIndex);
                        } else {
                            keyframe.setFraction(1.0f);
                        }
                    }
                }
                value = PropertyValuesHolder.ofKeyframe(propertyName, keyframeArray);
                if (valueType == 3) {
                    value.setEvaluator(ArgbEvaluator.getInstance());
                }
            }
        }
        return value;
    }

    private static Keyframe createNewKeyframe(Keyframe sampleKeyframe, float fraction) {
        if (sampleKeyframe.getType() == Float.TYPE) {
            return Keyframe.ofFloat(fraction);
        }
        if (sampleKeyframe.getType() == Integer.TYPE) {
            return Keyframe.ofInt(fraction);
        }
        return Keyframe.ofObject(fraction);
    }

    private static void distributeKeyframes(Keyframe[] keyframes, float gap, int startIndex, int endIndex) {
        float increment = gap / ((float) ((endIndex - startIndex) + 2));
        for (int i = startIndex; i <= endIndex; i++) {
            keyframes[i].setFraction(keyframes[i - 1].getFraction() + increment);
        }
    }

    private static Keyframe loadKeyframe(Context context, Resources res, Theme theme, AttributeSet attrs, int valueType, XmlPullParser parser) throws XmlPullParserException, IOException {
        TypedArray a = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_KEYFRAME);
        Keyframe keyframe = null;
        float fraction = TypedArrayUtils.getNamedFloat(a, parser, "fraction", 3, -1.0f);
        TypedValue keyframeValue = TypedArrayUtils.peekNamedValue(a, parser, CryptoUri.VALUE, 0);
        boolean hasValue = keyframeValue != null;
        if (valueType == 4) {
            if (hasValue && isColorType(keyframeValue.type)) {
                valueType = 3;
            } else {
                valueType = 0;
            }
        }
        if (hasValue) {
            switch (valueType) {
                case 0:
                    keyframe = Keyframe.ofFloat(fraction, TypedArrayUtils.getNamedFloat(a, parser, CryptoUri.VALUE, 0, 0.0f));
                    break;
                case 1:
                case 3:
                    keyframe = Keyframe.ofInt(fraction, TypedArrayUtils.getNamedInt(a, parser, CryptoUri.VALUE, 0, 0));
                    break;
            }
        } else if (valueType == 0) {
            keyframe = Keyframe.ofFloat(fraction);
        } else {
            keyframe = Keyframe.ofInt(fraction);
        }
        int resID = TypedArrayUtils.getNamedResourceId(a, parser, "interpolator", 1, 0);
        if (resID > 0) {
            keyframe.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, resID));
        }
        a.recycle();
        return keyframe;
    }

    private static ObjectAnimator loadObjectAnimator(Context context, Resources res, Theme theme, AttributeSet attrs, float pathErrorScale, XmlPullParser parser) throws NotFoundException {
        ObjectAnimator anim = new ObjectAnimator();
        loadAnimator(context, res, theme, attrs, anim, pathErrorScale, parser);
        return anim;
    }

    private static ValueAnimator loadAnimator(Context context, Resources res, Theme theme, AttributeSet attrs, ValueAnimator anim, float pathErrorScale, XmlPullParser parser) throws NotFoundException {
        TypedArray arrayAnimator = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_ANIMATOR);
        TypedArray arrayObjectAnimator = TypedArrayUtils.obtainAttributes(res, theme, attrs, AndroidResources.STYLEABLE_PROPERTY_ANIMATOR);
        if (anim == null) {
            anim = new ValueAnimator();
        }
        parseAnimatorFromTypeArray(anim, arrayAnimator, arrayObjectAnimator, pathErrorScale, parser);
        int resID = TypedArrayUtils.getNamedResourceId(arrayAnimator, parser, "interpolator", 0, 0);
        if (resID > 0) {
            anim.setInterpolator(AnimationUtilsCompat.loadInterpolator(context, resID));
        }
        arrayAnimator.recycle();
        if (arrayObjectAnimator != null) {
            arrayObjectAnimator.recycle();
        }
        return anim;
    }

    private static boolean isColorType(int type) {
        return type >= 28 && type <= 31;
    }
}
