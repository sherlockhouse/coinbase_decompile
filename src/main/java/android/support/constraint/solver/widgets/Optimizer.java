package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;

public class Optimizer {
    static boolean[] flags = new boolean[3];

    static void checkMatchParent(ConstraintWidgetContainer container, LinearSystem system, ConstraintWidget widget) {
        if (container.mListDimensionBehaviors[0] != DimensionBehaviour.WRAP_CONTENT && widget.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_PARENT) {
            int left = widget.mLeft.mMargin;
            int right = container.getWidth() - widget.mRight.mMargin;
            widget.mLeft.mSolverVariable = system.createObjectVariable(widget.mLeft);
            widget.mRight.mSolverVariable = system.createObjectVariable(widget.mRight);
            system.addEquality(widget.mLeft.mSolverVariable, left);
            system.addEquality(widget.mRight.mSolverVariable, right);
            widget.mHorizontalResolution = 2;
            widget.setHorizontalDimension(left, right);
        }
        if (container.mListDimensionBehaviors[1] != DimensionBehaviour.WRAP_CONTENT && widget.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_PARENT) {
            int top = widget.mTop.mMargin;
            int bottom = container.getHeight() - widget.mBottom.mMargin;
            widget.mTop.mSolverVariable = system.createObjectVariable(widget.mTop);
            widget.mBottom.mSolverVariable = system.createObjectVariable(widget.mBottom);
            system.addEquality(widget.mTop.mSolverVariable, top);
            system.addEquality(widget.mBottom.mSolverVariable, bottom);
            if (widget.mBaselineDistance > 0 || widget.getVisibility() == 8) {
                widget.mBaseline.mSolverVariable = system.createObjectVariable(widget.mBaseline);
                system.addEquality(widget.mBaseline.mSolverVariable, widget.mBaselineDistance + top);
            }
            widget.mVerticalResolution = 2;
            widget.setVerticalDimension(top, bottom);
        }
    }

    private static boolean optimizableMatchConstraint(ConstraintWidget constraintWidget, int orientation) {
        int i = 1;
        if (constraintWidget.mListDimensionBehaviors[orientation] != DimensionBehaviour.MATCH_CONSTRAINT) {
            return false;
        }
        if (constraintWidget.mDimensionRatio != 0.0f) {
            DimensionBehaviour[] dimensionBehaviourArr = constraintWidget.mListDimensionBehaviors;
            if (orientation != 0) {
                i = 0;
            }
            return dimensionBehaviourArr[i] == DimensionBehaviour.MATCH_CONSTRAINT ? false : false;
        } else {
            if (orientation == 0) {
                if (!(constraintWidget.mMatchConstraintDefaultWidth == 0 && constraintWidget.mMatchConstraintMinWidth == 0 && constraintWidget.mMatchConstraintMaxWidth == 0)) {
                    return false;
                }
            } else if (constraintWidget.mMatchConstraintDefaultHeight != 0 || constraintWidget.mMatchConstraintMinHeight != 0) {
                return false;
            } else {
                if (constraintWidget.mMatchConstraintMaxHeight != 0) {
                    return false;
                }
            }
            return true;
        }
    }

    static void analyze(int optimisationLevel, ConstraintWidget widget) {
        boolean optimiseDimensions;
        widget.updateResolutionNodes();
        ResolutionAnchor leftNode = widget.mLeft.getResolutionNode();
        ResolutionAnchor topNode = widget.mTop.getResolutionNode();
        ResolutionAnchor rightNode = widget.mRight.getResolutionNode();
        ResolutionAnchor bottomNode = widget.mBottom.getResolutionNode();
        if ((optimisationLevel & 8) == 8) {
            optimiseDimensions = true;
        } else {
            optimiseDimensions = false;
        }
        if (!(leftNode.type == 4 || rightNode.type == 4)) {
            if (widget.mListDimensionBehaviors[0] == DimensionBehaviour.FIXED) {
                if (widget.mLeft.mTarget == null && widget.mRight.mTarget == null) {
                    leftNode.setType(1);
                    rightNode.setType(1);
                    if (optimiseDimensions) {
                        rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        rightNode.dependsOn(leftNode, widget.getWidth());
                    }
                } else if (widget.mLeft.mTarget != null && widget.mRight.mTarget == null) {
                    leftNode.setType(1);
                    rightNode.setType(1);
                    if (optimiseDimensions) {
                        rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        rightNode.dependsOn(leftNode, widget.getWidth());
                    }
                } else if (widget.mLeft.mTarget == null && widget.mRight.mTarget != null) {
                    leftNode.setType(1);
                    rightNode.setType(1);
                    leftNode.dependsOn(rightNode, -widget.getWidth());
                    if (optimiseDimensions) {
                        leftNode.dependsOn(rightNode, -1, widget.getResolutionWidth());
                    } else {
                        leftNode.dependsOn(rightNode, -widget.getWidth());
                    }
                } else if (!(widget.mLeft.mTarget == null || widget.mRight.mTarget == null)) {
                    leftNode.setType(2);
                    rightNode.setType(2);
                    if (optimiseDimensions) {
                        widget.getResolutionWidth().addDependent(leftNode);
                        widget.getResolutionWidth().addDependent(rightNode);
                        leftNode.setOpposite(rightNode, -1, widget.getResolutionWidth());
                        rightNode.setOpposite(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        leftNode.setOpposite(rightNode, (float) (-widget.getWidth()));
                        rightNode.setOpposite(leftNode, (float) widget.getWidth());
                    }
                }
            } else if (widget.mListDimensionBehaviors[0] == DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(widget, 0)) {
                int width = widget.getWidth();
                leftNode.setType(1);
                rightNode.setType(1);
                if (widget.mLeft.mTarget == null && widget.mRight.mTarget == null) {
                    if (optimiseDimensions) {
                        rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                    } else {
                        rightNode.dependsOn(leftNode, width);
                    }
                } else if (widget.mLeft.mTarget == null || widget.mRight.mTarget != null) {
                    if (widget.mLeft.mTarget != null || widget.mRight.mTarget == null) {
                        if (!(widget.mLeft.mTarget == null || widget.mRight.mTarget == null)) {
                            if (optimiseDimensions) {
                                widget.getResolutionWidth().addDependent(leftNode);
                                widget.getResolutionWidth().addDependent(rightNode);
                            }
                            if (widget.mDimensionRatio == 0.0f) {
                                leftNode.setType(3);
                                rightNode.setType(3);
                                leftNode.setOpposite(rightNode, 0.0f);
                                rightNode.setOpposite(leftNode, 0.0f);
                            } else {
                                leftNode.setType(2);
                                rightNode.setType(2);
                                leftNode.setOpposite(rightNode, (float) (-width));
                                rightNode.setOpposite(leftNode, (float) width);
                                widget.setWidth(width);
                            }
                        }
                    } else if (optimiseDimensions) {
                        leftNode.dependsOn(rightNode, -1, widget.getResolutionWidth());
                    } else {
                        leftNode.dependsOn(rightNode, -width);
                    }
                } else if (optimiseDimensions) {
                    rightNode.dependsOn(leftNode, 1, widget.getResolutionWidth());
                } else {
                    rightNode.dependsOn(leftNode, width);
                }
            }
        }
        if (topNode.type != 4 && bottomNode.type != 4) {
            if (widget.mListDimensionBehaviors[1] == DimensionBehaviour.FIXED) {
                if (widget.mTop.mTarget == null && widget.mBottom.mTarget == null) {
                    topNode.setType(1);
                    bottomNode.setType(1);
                    if (optimiseDimensions) {
                        bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                    } else {
                        bottomNode.dependsOn(topNode, widget.getHeight());
                    }
                    if (widget.mBaseline.mTarget != null) {
                        widget.mBaseline.getResolutionNode().setType(1);
                        topNode.dependsOn(1, widget.mBaseline.getResolutionNode(), -widget.mBaselineDistance);
                    }
                } else if (widget.mTop.mTarget != null && widget.mBottom.mTarget == null) {
                    topNode.setType(1);
                    bottomNode.setType(1);
                    if (optimiseDimensions) {
                        bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                    } else {
                        bottomNode.dependsOn(topNode, widget.getHeight());
                    }
                    if (widget.mBaselineDistance > 0) {
                        widget.mBaseline.getResolutionNode().dependsOn(1, topNode, widget.mBaselineDistance);
                    }
                } else if (widget.mTop.mTarget == null && widget.mBottom.mTarget != null) {
                    topNode.setType(1);
                    bottomNode.setType(1);
                    if (optimiseDimensions) {
                        topNode.dependsOn(bottomNode, -1, widget.getResolutionHeight());
                    } else {
                        topNode.dependsOn(bottomNode, -widget.getHeight());
                    }
                    if (widget.mBaselineDistance > 0) {
                        widget.mBaseline.getResolutionNode().dependsOn(1, topNode, widget.mBaselineDistance);
                    }
                } else if (widget.mTop.mTarget != null && widget.mBottom.mTarget != null) {
                    topNode.setType(2);
                    bottomNode.setType(2);
                    if (optimiseDimensions) {
                        topNode.setOpposite(bottomNode, -1, widget.getResolutionHeight());
                        bottomNode.setOpposite(topNode, 1, widget.getResolutionHeight());
                        widget.getResolutionHeight().addDependent(topNode);
                        widget.getResolutionWidth().addDependent(bottomNode);
                    } else {
                        topNode.setOpposite(bottomNode, (float) (-widget.getHeight()));
                        bottomNode.setOpposite(topNode, (float) widget.getHeight());
                    }
                    if (widget.mBaselineDistance > 0) {
                        widget.mBaseline.getResolutionNode().dependsOn(1, topNode, widget.mBaselineDistance);
                    }
                }
            } else if (widget.mListDimensionBehaviors[1] == DimensionBehaviour.MATCH_CONSTRAINT && optimizableMatchConstraint(widget, 1)) {
                int height = widget.getHeight();
                topNode.setType(1);
                bottomNode.setType(1);
                if (widget.mTop.mTarget == null && widget.mBottom.mTarget == null) {
                    if (optimiseDimensions) {
                        bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                    } else {
                        bottomNode.dependsOn(topNode, height);
                    }
                } else if (widget.mTop.mTarget == null || widget.mBottom.mTarget != null) {
                    if (widget.mTop.mTarget != null || widget.mBottom.mTarget == null) {
                        if (widget.mTop.mTarget != null && widget.mBottom.mTarget != null) {
                            if (optimiseDimensions) {
                                widget.getResolutionHeight().addDependent(topNode);
                                widget.getResolutionWidth().addDependent(bottomNode);
                            }
                            if (widget.mDimensionRatio == 0.0f) {
                                topNode.setType(3);
                                bottomNode.setType(3);
                                topNode.setOpposite(bottomNode, 0.0f);
                                bottomNode.setOpposite(topNode, 0.0f);
                                return;
                            }
                            topNode.setType(2);
                            bottomNode.setType(2);
                            topNode.setOpposite(bottomNode, (float) (-height));
                            bottomNode.setOpposite(topNode, (float) height);
                            widget.setHeight(height);
                            if (widget.mBaselineDistance > 0) {
                                widget.mBaseline.getResolutionNode().dependsOn(1, topNode, widget.mBaselineDistance);
                            }
                        }
                    } else if (optimiseDimensions) {
                        topNode.dependsOn(bottomNode, -1, widget.getResolutionHeight());
                    } else {
                        topNode.dependsOn(bottomNode, -height);
                    }
                } else if (optimiseDimensions) {
                    bottomNode.dependsOn(topNode, 1, widget.getResolutionHeight());
                } else {
                    bottomNode.dependsOn(topNode, height);
                }
            }
        }
    }

    static boolean applyChainOptimized(ConstraintWidgetContainer container, LinearSystem system, int orientation, int offset, ConstraintWidget first) {
        ConstraintAnchor nextAnchor;
        ConstraintWidget next;
        boolean isChainSpread;
        boolean isChainSpreadInside;
        boolean isChainPacked;
        ConstraintWidget widget = first;
        ConstraintWidget firstVisibleWidget = null;
        ConstraintWidget lastVisibleWidget = null;
        boolean done = false;
        int numMatchConstraints = 0;
        float totalWeights = 0.0f;
        ConstraintWidget firstMatchConstraintsWidget = null;
        ConstraintWidget previousMatchConstraintsWidget = null;
        if (container.mListDimensionBehaviors[orientation] == DimensionBehaviour.WRAP_CONTENT) {
        }
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
        float totalSize = 0.0f;
        float totalMargins = 0.0f;
        int numVisibleWidgets = 0;
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
                numVisibleWidgets++;
                if (orientation == 0) {
                    totalSize += (float) widget.getWidth();
                } else {
                    totalSize += (float) widget.getHeight();
                }
                if (widget != firstVisibleWidget) {
                    totalSize += (float) widget.mListAnchors[offset].getMargin();
                }
                totalMargins = (totalMargins + ((float) widget.mListAnchors[offset].getMargin())) + ((float) widget.mListAnchors[offset + 1].getMargin());
            }
            ConstraintAnchor begin = widget.mListAnchors[offset];
            widget.mListNextMatchConstraintsWidget[orientation] = null;
            if (widget.getVisibility() != 8 && widget.mListDimensionBehaviors[orientation] == DimensionBehaviour.MATCH_CONSTRAINT) {
                numMatchConstraints++;
                if (orientation == 0) {
                    if (widget.mMatchConstraintDefaultWidth != 0) {
                        return false;
                    }
                    if (!(widget.mMatchConstraintMinWidth == 0 && widget.mMatchConstraintMaxWidth == 0)) {
                        return false;
                    }
                } else if (widget.mMatchConstraintDefaultHeight != 0) {
                    return false;
                } else {
                    if (!(widget.mMatchConstraintMinHeight == 0 && widget.mMatchConstraintMaxHeight == 0)) {
                        return false;
                    }
                }
                totalWeights += widget.mWeight[orientation];
                if (firstMatchConstraintsWidget == null) {
                    firstMatchConstraintsWidget = widget;
                } else {
                    previousMatchConstraintsWidget.mListNextMatchConstraintsWidget[orientation] = widget;
                }
                previousMatchConstraintsWidget = widget;
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
        ResolutionAnchor firstNode = first.mListAnchors[offset].getResolutionNode();
        ResolutionAnchor lastNode = last.mListAnchors[offset + 1].getResolutionNode();
        if (firstNode.target == null || lastNode.target == null) {
            return false;
        }
        if (firstNode.target.state != 1 && lastNode.target.state != 1) {
            return false;
        }
        if (numMatchConstraints > 0 && numMatchConstraints != numVisibleWidgets) {
            return false;
        }
        float distance;
        float extraMargin = 0.0f;
        if (isChainPacked || isChainSpread || isChainSpreadInside) {
            if (firstVisibleWidget != null) {
                extraMargin = (float) firstVisibleWidget.mListAnchors[offset].getMargin();
            }
            if (lastVisibleWidget != null) {
                extraMargin += (float) lastVisibleWidget.mListAnchors[offset + 1].getMargin();
            }
        }
        float firstOffset = firstNode.target.resolvedOffset;
        float lastOffset = lastNode.target.resolvedOffset;
        if (firstOffset < lastOffset) {
            distance = (lastOffset - firstOffset) - totalSize;
        } else {
            distance = (firstOffset - lastOffset) - totalSize;
        }
        Metrics metrics;
        float dimension;
        if (numMatchConstraints <= 0 || numMatchConstraints != numVisibleWidgets) {
            if (distance < totalSize) {
                return false;
            }
            if (isChainPacked) {
                widget = firstVisibleWidget;
                distance = firstOffset + (first.getHorizontalBiasPercent() * (distance - extraMargin));
                while (widget != null) {
                    if (LinearSystem.sMetrics != null) {
                        metrics = LinearSystem.sMetrics;
                        metrics.nonresolvedWidgets--;
                        metrics = LinearSystem.sMetrics;
                        metrics.resolvedWidgets++;
                        metrics = LinearSystem.sMetrics;
                        metrics.chainConnectionResolved++;
                    }
                    next = widget.mListNextVisibleWidget[orientation];
                    if (next != null || widget == lastVisibleWidget) {
                        if (orientation == 0) {
                            dimension = (float) widget.getWidth();
                        } else {
                            dimension = (float) widget.getHeight();
                        }
                        distance += (float) widget.mListAnchors[offset].getMargin();
                        widget.mListAnchors[offset].getResolutionNode().resolve(firstNode.resolvedTarget, distance);
                        widget.mListAnchors[offset + 1].getResolutionNode().resolve(firstNode.resolvedTarget, distance + dimension);
                        widget.mListAnchors[offset].getResolutionNode().addResolvedValue(system);
                        widget.mListAnchors[offset + 1].getResolutionNode().addResolvedValue(system);
                        distance = (distance + dimension) + ((float) widget.mListAnchors[offset + 1].getMargin());
                    }
                    widget = next;
                }
            } else if (isChainSpread || isChainSpreadInside) {
                if (isChainSpread) {
                    distance -= extraMargin;
                } else if (isChainSpreadInside) {
                    distance -= extraMargin;
                }
                widget = firstVisibleWidget;
                float gap = distance / ((float) (numVisibleWidgets + 1));
                if (isChainSpreadInside) {
                    if (numVisibleWidgets > 1) {
                        gap = distance / ((float) (numVisibleWidgets - 1));
                    } else {
                        gap = distance / 2.0f;
                    }
                }
                distance = firstOffset + gap;
                if (isChainSpreadInside && numVisibleWidgets > 1) {
                    distance = firstOffset + ((float) firstVisibleWidget.mListAnchors[offset].getMargin());
                }
                if (isChainSpread && firstVisibleWidget != null) {
                    distance += (float) firstVisibleWidget.mListAnchors[offset].getMargin();
                }
                while (widget != null) {
                    if (LinearSystem.sMetrics != null) {
                        metrics = LinearSystem.sMetrics;
                        metrics.nonresolvedWidgets--;
                        metrics = LinearSystem.sMetrics;
                        metrics.resolvedWidgets++;
                        metrics = LinearSystem.sMetrics;
                        metrics.chainConnectionResolved++;
                    }
                    next = widget.mListNextVisibleWidget[orientation];
                    if (next != null || widget == lastVisibleWidget) {
                        if (orientation == 0) {
                            dimension = (float) widget.getWidth();
                        } else {
                            dimension = (float) widget.getHeight();
                        }
                        widget.mListAnchors[offset].getResolutionNode().resolve(firstNode.resolvedTarget, distance);
                        widget.mListAnchors[offset + 1].getResolutionNode().resolve(firstNode.resolvedTarget, distance + dimension);
                        widget.mListAnchors[offset].getResolutionNode().addResolvedValue(system);
                        widget.mListAnchors[offset + 1].getResolutionNode().addResolvedValue(system);
                        distance += dimension + gap;
                    }
                    widget = next;
                }
            }
            return true;
        } else if (widget.getParent() != null && widget.getParent().mListDimensionBehaviors[orientation] == DimensionBehaviour.WRAP_CONTENT) {
            return false;
        } else {
            distance = (distance + totalSize) - totalMargins;
            widget = firstVisibleWidget;
            float position = firstOffset;
            if (isChainSpread) {
                distance -= totalMargins - extraMargin;
            }
            if (isChainSpread) {
                position += (float) widget.mListAnchors[offset + 1].getMargin();
                next = widget.mListNextVisibleWidget[orientation];
                if (next != null) {
                    position += (float) next.mListAnchors[offset].getMargin();
                }
            }
            while (widget != null) {
                if (LinearSystem.sMetrics != null) {
                    metrics = LinearSystem.sMetrics;
                    metrics.nonresolvedWidgets--;
                    metrics = LinearSystem.sMetrics;
                    metrics.resolvedWidgets++;
                    metrics = LinearSystem.sMetrics;
                    metrics.chainConnectionResolved++;
                }
                next = widget.mListNextVisibleWidget[orientation];
                if (next != null || widget == lastVisibleWidget) {
                    dimension = distance / ((float) numMatchConstraints);
                    if (totalWeights > 0.0f) {
                        dimension = (widget.mWeight[orientation] * distance) / totalWeights;
                    }
                    position += (float) widget.mListAnchors[offset].getMargin();
                    widget.mListAnchors[offset].getResolutionNode().resolve(firstNode.resolvedTarget, position);
                    widget.mListAnchors[offset + 1].getResolutionNode().resolve(firstNode.resolvedTarget, position + dimension);
                    widget.mListAnchors[offset].getResolutionNode().addResolvedValue(system);
                    widget.mListAnchors[offset + 1].getResolutionNode().addResolvedValue(system);
                    position = (position + dimension) + ((float) widget.mListAnchors[offset + 1].getMargin());
                }
                widget = next;
            }
            return true;
        }
    }
}
