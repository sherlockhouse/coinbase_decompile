package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;

class Chain {
    static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem system, int orientation) {
        int offset;
        int chainsSize;
        ConstraintWidget[] chainsArray;
        if (orientation == 0) {
            offset = 0;
            chainsSize = constraintWidgetContainer.mHorizontalChainsSize;
            chainsArray = constraintWidgetContainer.mHorizontalChainsArray;
        } else {
            offset = 2;
            chainsSize = constraintWidgetContainer.mVerticalChainsSize;
            chainsArray = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i = 0; i < chainsSize; i++) {
            ConstraintWidget first = chainsArray[i];
            if (!constraintWidgetContainer.optimizeFor(4)) {
                applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
            } else if (!Optimizer.applyChainOptimized(constraintWidgetContainer, system, orientation, offset, first)) {
                applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
            }
        }
    }

    static void applyChainConstraints(ConstraintWidgetContainer container, LinearSystem system, int orientation, int offset, ConstraintWidget first) {
        ConstraintAnchor nextAnchor;
        ConstraintWidget next;
        boolean isChainSpread;
        boolean isChainSpreadInside;
        boolean isChainPacked;
        SolverVariable begin;
        SolverVariable beginTarget;
        SolverVariable endTarget;
        int beginMargin;
        ConstraintWidget widget = first;
        ConstraintWidget firstVisibleWidget = null;
        ConstraintWidget lastVisibleWidget = null;
        boolean done = false;
        int numMatchConstraints = 0;
        float totalWeights = 0.0f;
        ConstraintWidget firstMatchConstraintsWidget = null;
        ConstraintWidget previousMatchConstraintsWidget = null;
        boolean isWrapContent = container.mListDimensionBehaviors[orientation] == DimensionBehaviour.WRAP_CONTENT;
        ConstraintWidget head = first;
        if (orientation == 0 && container.isRtl()) {
            while (!done) {
                nextAnchor = widget.mListAnchors[offset + 1].mTarget;
                if (nextAnchor != null) {
                    next = nextAnchor.mOwner;
                    if (next.mListAnchors[offset].mTarget == null || next.mListAnchors[offset].mTarget.mOwner != widget) {
                        next = null;
                    }
                } else {
                    next = null;
                }
                if (next != null) {
                    widget = next;
                } else {
                    done = true;
                }
            }
            head = widget;
            widget = first;
            done = false;
        }
        if (orientation == 0) {
            isChainSpread = head.mHorizontalChainStyle == 0;
            isChainSpreadInside = head.mHorizontalChainStyle == 1;
            if (head.mHorizontalChainStyle == 2) {
                isChainPacked = true;
            } else {
                isChainPacked = false;
            }
        } else {
            isChainSpread = head.mVerticalChainStyle == 0;
            isChainSpreadInside = head.mVerticalChainStyle == 1;
            isChainPacked = head.mVerticalChainStyle == 2;
        }
        while (!done) {
            widget.mListNextVisibleWidget[orientation] = null;
            if (widget.getVisibility() != 8) {
                if (lastVisibleWidget != null) {
                    lastVisibleWidget.mListNextVisibleWidget[orientation] = widget;
                }
                if (firstVisibleWidget == null) {
                    firstVisibleWidget = widget;
                }
                lastVisibleWidget = widget;
            }
            ConstraintAnchor begin2 = widget.mListAnchors[offset];
            int strength = 1;
            int margin = begin2.getMargin();
            if (!(begin2.mTarget == null || widget == first || widget.getVisibility() == 8)) {
                margin += begin2.mTarget.getMargin();
            }
            if (!(!isChainPacked || widget == first || widget == firstVisibleWidget)) {
                strength = 6;
            }
            if (widget == firstVisibleWidget) {
                system.addGreaterThan(begin2.mSolverVariable, begin2.mTarget.mSolverVariable, margin, 5);
            } else {
                system.addGreaterThan(begin2.mSolverVariable, begin2.mTarget.mSolverVariable, margin, 6);
            }
            system.addEquality(begin2.mSolverVariable, begin2.mTarget.mSolverVariable, margin, strength);
            widget.mListNextMatchConstraintsWidget[orientation] = null;
            if (widget.getVisibility() != 8 && widget.mListDimensionBehaviors[orientation] == DimensionBehaviour.MATCH_CONSTRAINT) {
                numMatchConstraints++;
                totalWeights += widget.mWeight[orientation];
                if (firstMatchConstraintsWidget == null) {
                    firstMatchConstraintsWidget = widget;
                } else {
                    previousMatchConstraintsWidget.mListNextMatchConstraintsWidget[orientation] = widget;
                }
                previousMatchConstraintsWidget = widget;
                if (isWrapContent) {
                    system.addGreaterThan(widget.mListAnchors[offset + 1].mSolverVariable, widget.mListAnchors[offset].mSolverVariable, 0, 6);
                }
            }
            if (isWrapContent) {
                system.addGreaterThan(widget.mListAnchors[offset].mSolverVariable, container.mListAnchors[offset].mSolverVariable, 0, 6);
            }
            nextAnchor = widget.mListAnchors[offset + 1].mTarget;
            if (nextAnchor != null) {
                next = nextAnchor.mOwner;
                if (next.mListAnchors[offset].mTarget == null || next.mListAnchors[offset].mTarget.mOwner != widget) {
                    next = null;
                }
            } else {
                next = null;
            }
            if (next != null) {
                widget = next;
            } else {
                done = true;
            }
        }
        ConstraintWidget last = widget;
        if (!(lastVisibleWidget == null || last.mListAnchors[offset + 1].mTarget == null)) {
            ConstraintAnchor end = lastVisibleWidget.mListAnchors[offset + 1];
            system.addLowerThan(end.mSolverVariable, last.mListAnchors[offset + 1].mTarget.mSolverVariable, -end.getMargin(), 5);
        }
        if (isWrapContent) {
            system.addGreaterThan(container.mListAnchors[offset + 1].mSolverVariable, last.mListAnchors[offset + 1].mSolverVariable, last.mListAnchors[offset + 1].getMargin(), 6);
        }
        if (numMatchConstraints > 0) {
            widget = firstMatchConstraintsWidget;
            while (widget != null) {
                next = widget.mListNextMatchConstraintsWidget[orientation];
                if (next != null) {
                    int currentDimensionDefault;
                    int nextDimensionDefault;
                    float currentWeight = widget.mWeight[orientation];
                    float nextWeight = next.mWeight[orientation];
                    begin = widget.mListAnchors[offset].mSolverVariable;
                    SolverVariable end2 = widget.mListAnchors[offset + 1].mSolverVariable;
                    SolverVariable nextBegin = next.mListAnchors[offset].mSolverVariable;
                    SolverVariable nextEnd = next.mListAnchors[offset + 1].mSolverVariable;
                    if (orientation == 0) {
                        currentDimensionDefault = widget.mMatchConstraintDefaultWidth;
                        nextDimensionDefault = next.mMatchConstraintDefaultWidth;
                    } else {
                        currentDimensionDefault = widget.mMatchConstraintDefaultHeight;
                        nextDimensionDefault = next.mMatchConstraintDefaultHeight;
                    }
                    boolean applyEquality = (currentDimensionDefault == 0 || currentDimensionDefault == 3) && (nextDimensionDefault == 0 || nextDimensionDefault == 3);
                    if (applyEquality) {
                        ArrayRow row = system.createRow();
                        row.createRowEqualMatchDimensions(currentWeight, totalWeights, nextWeight, begin, end2, nextBegin, nextEnd);
                        system.addConstraint(row);
                    }
                }
                widget = next;
            }
        }
        if (firstVisibleWidget != null && (firstVisibleWidget == lastVisibleWidget || isChainPacked)) {
            begin2 = first.mListAnchors[offset];
            end = last.mListAnchors[offset + 1];
            beginTarget = first.mListAnchors[offset].mTarget != null ? first.mListAnchors[offset].mTarget.mSolverVariable : null;
            endTarget = last.mListAnchors[offset + 1].mTarget != null ? last.mListAnchors[offset + 1].mTarget.mSolverVariable : null;
            if (firstVisibleWidget == lastVisibleWidget) {
                begin2 = firstVisibleWidget.mListAnchors[offset];
                end = firstVisibleWidget.mListAnchors[offset + 1];
            }
            if (!(beginTarget == null || endTarget == null)) {
                float bias;
                if (orientation == 0) {
                    bias = head.mHorizontalBiasPercent;
                } else {
                    bias = head.mVerticalBiasPercent;
                }
                beginMargin = begin2.getMargin();
                if (lastVisibleWidget == null) {
                    lastVisibleWidget = last;
                }
                system.addCentering(begin2.mSolverVariable, beginTarget, beginMargin, bias, endTarget, end.mSolverVariable, lastVisibleWidget.mListAnchors[offset + 1].getMargin(), 5);
            }
        } else if (isChainSpread && firstVisibleWidget != null) {
            widget = firstVisibleWidget;
            previousVisibleWidget = firstVisibleWidget;
            while (widget != null) {
                next = widget.mListNextVisibleWidget[orientation];
                if (next != null || widget == lastVisibleWidget) {
                    beginAnchor = widget.mListAnchors[offset];
                    begin = beginAnchor.mSolverVariable;
                    beginTarget = beginAnchor.mTarget != null ? beginAnchor.mTarget.mSolverVariable : null;
                    if (previousVisibleWidget != widget) {
                        beginTarget = previousVisibleWidget.mListAnchors[offset + 1].mSolverVariable;
                    } else if (widget == firstVisibleWidget && previousVisibleWidget == widget) {
                        beginTarget = first.mListAnchors[offset].mTarget != null ? first.mListAnchors[offset].mTarget.mSolverVariable : null;
                    }
                    beginNext = null;
                    beginMargin = beginAnchor.getMargin();
                    nextMargin = widget.mListAnchors[offset + 1].getMargin();
                    if (next != null) {
                        beginNextAnchor = next.mListAnchors[offset];
                        beginNext = beginNextAnchor.mSolverVariable;
                        beginNextTarget = beginNextAnchor.mTarget != null ? beginNextAnchor.mTarget.mSolverVariable : null;
                    } else {
                        beginNextAnchor = last.mListAnchors[offset + 1].mTarget;
                        if (beginNextAnchor != null) {
                            beginNext = beginNextAnchor.mSolverVariable;
                        }
                        beginNextTarget = widget.mListAnchors[offset + 1].mSolverVariable;
                    }
                    if (beginNextAnchor != null) {
                        nextMargin += beginNextAnchor.getMargin();
                    }
                    if (previousVisibleWidget != null) {
                        beginMargin += previousVisibleWidget.mListAnchors[offset + 1].getMargin();
                    }
                    if (!(begin == null || beginTarget == null || beginNext == null || beginNextTarget == null)) {
                        int margin1 = beginMargin;
                        if (widget == firstVisibleWidget) {
                            margin1 = firstVisibleWidget.mListAnchors[offset].getMargin();
                        }
                        int margin2 = nextMargin;
                        if (widget == lastVisibleWidget) {
                            margin2 = lastVisibleWidget.mListAnchors[offset + 1].getMargin();
                        }
                        system.addCentering(begin, beginTarget, margin1, 0.5f, beginNext, beginNextTarget, margin2, 4);
                    }
                }
                previousVisibleWidget = widget;
                widget = next;
            }
        } else if (isChainSpreadInside && firstVisibleWidget != null) {
            widget = firstVisibleWidget;
            previousVisibleWidget = firstVisibleWidget;
            while (widget != null) {
                next = widget.mListNextVisibleWidget[orientation];
                if (!(widget == firstVisibleWidget || widget == lastVisibleWidget || next == null)) {
                    if (next == lastVisibleWidget) {
                        next = null;
                    }
                    beginAnchor = widget.mListAnchors[offset];
                    begin = beginAnchor.mSolverVariable;
                    if (beginAnchor.mTarget != null) {
                        beginTarget = beginAnchor.mTarget.mSolverVariable;
                    }
                    beginTarget = previousVisibleWidget.mListAnchors[offset + 1].mSolverVariable;
                    beginNext = null;
                    beginMargin = beginAnchor.getMargin();
                    nextMargin = widget.mListAnchors[offset + 1].getMargin();
                    if (next != null) {
                        beginNextAnchor = next.mListAnchors[offset];
                        beginNext = beginNextAnchor.mSolverVariable;
                        beginNextTarget = beginNextAnchor.mTarget != null ? beginNextAnchor.mTarget.mSolverVariable : null;
                    } else {
                        beginNextAnchor = widget.mListAnchors[offset + 1].mTarget;
                        if (beginNextAnchor != null) {
                            beginNext = beginNextAnchor.mSolverVariable;
                        }
                        beginNextTarget = widget.mListAnchors[offset + 1].mSolverVariable;
                    }
                    if (beginNextAnchor != null) {
                        nextMargin += beginNextAnchor.getMargin();
                    }
                    if (previousVisibleWidget != null) {
                        beginMargin += previousVisibleWidget.mListAnchors[offset + 1].getMargin();
                    }
                    if (!(begin == null || beginTarget == null || beginNext == null || beginNextTarget == null)) {
                        system.addCentering(begin, beginTarget, beginMargin, 0.5f, beginNext, beginNextTarget, nextMargin, 4);
                    }
                }
                previousVisibleWidget = widget;
                widget = next;
            }
            begin2 = firstVisibleWidget.mListAnchors[offset];
            ConstraintAnchor beginTarget2 = first.mListAnchors[offset].mTarget;
            end = lastVisibleWidget.mListAnchors[offset + 1];
            ConstraintAnchor endTarget2 = last.mListAnchors[offset + 1].mTarget;
            if (beginTarget2 != null) {
                if (firstVisibleWidget != lastVisibleWidget) {
                    system.addEquality(begin2.mSolverVariable, beginTarget2.mSolverVariable, begin2.getMargin(), 5);
                } else if (endTarget2 != null) {
                    system.addCentering(begin2.mSolverVariable, beginTarget2.mSolverVariable, begin2.getMargin(), 0.5f, end.mSolverVariable, endTarget2.mSolverVariable, end.getMargin(), 5);
                }
            }
            if (!(endTarget2 == null || firstVisibleWidget == lastVisibleWidget)) {
                system.addEquality(end.mSolverVariable, endTarget2.mSolverVariable, -end.getMargin(), 5);
            }
        }
        if ((isChainSpread || isChainSpreadInside) && firstVisibleWidget != null) {
            begin2 = firstVisibleWidget.mListAnchors[offset];
            end = lastVisibleWidget.mListAnchors[offset + 1];
            beginTarget = begin2.mTarget != null ? begin2.mTarget.mSolverVariable : null;
            endTarget = end.mTarget != null ? end.mTarget.mSolverVariable : null;
            if (firstVisibleWidget == lastVisibleWidget) {
                begin2 = firstVisibleWidget.mListAnchors[offset];
                end = firstVisibleWidget.mListAnchors[offset + 1];
            }
            if (beginTarget != null && endTarget != null) {
                beginMargin = begin2.getMargin();
                if (lastVisibleWidget == null) {
                    lastVisibleWidget = last;
                }
                system.addCentering(begin2.mSolverVariable, beginTarget, beginMargin, 0.5f, endTarget, end.mSolverVariable, lastVisibleWidget.mListAnchors[offset + 1].getMargin(), 5);
            }
        }
    }
}
