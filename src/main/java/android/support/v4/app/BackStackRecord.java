package android.support.v4.app;

import android.os.Build.VERSION;
import android.support.v4.util.LogWriter;
import android.util.Log;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

final class BackStackRecord extends FragmentTransaction implements OpGenerator {
    static final boolean SUPPORTS_TRANSITIONS = (VERSION.SDK_INT >= 21);
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack = true;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    ArrayList<Runnable> mCommitRunnables;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    int mIndex = -1;
    final FragmentManagerImpl mManager;
    String mName;
    ArrayList<Op> mOps = new ArrayList();
    int mPopEnterAnim;
    int mPopExitAnim;
    boolean mReorderingAllowed = false;
    ArrayList<String> mSharedElementSourceNames;
    ArrayList<String> mSharedElementTargetNames;
    int mTransition;
    int mTransitionStyle;

    static final class Op {
        int cmd;
        int enterAnim;
        int exitAnim;
        Fragment fragment;
        int popEnterAnim;
        int popExitAnim;

        Op() {
        }

        Op(int cmd, Fragment fragment) {
            this.cmd = cmd;
            this.fragment = fragment;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("BackStackEntry{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        if (this.mIndex >= 0) {
            sb.append(" #");
            sb.append(this.mIndex);
        }
        if (this.mName != null) {
            sb.append(" ");
            sb.append(this.mName);
        }
        sb.append("}");
        return sb.toString();
    }

    public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {
        dump(prefix, writer, true);
    }

    public void dump(String prefix, PrintWriter writer, boolean full) {
        if (full) {
            writer.print(prefix);
            writer.print("mName=");
            writer.print(this.mName);
            writer.print(" mIndex=");
            writer.print(this.mIndex);
            writer.print(" mCommitted=");
            writer.println(this.mCommitted);
            if (this.mTransition != 0) {
                writer.print(prefix);
                writer.print("mTransition=#");
                writer.print(Integer.toHexString(this.mTransition));
                writer.print(" mTransitionStyle=#");
                writer.println(Integer.toHexString(this.mTransitionStyle));
            }
            if (!(this.mEnterAnim == 0 && this.mExitAnim == 0)) {
                writer.print(prefix);
                writer.print("mEnterAnim=#");
                writer.print(Integer.toHexString(this.mEnterAnim));
                writer.print(" mExitAnim=#");
                writer.println(Integer.toHexString(this.mExitAnim));
            }
            if (!(this.mPopEnterAnim == 0 && this.mPopExitAnim == 0)) {
                writer.print(prefix);
                writer.print("mPopEnterAnim=#");
                writer.print(Integer.toHexString(this.mPopEnterAnim));
                writer.print(" mPopExitAnim=#");
                writer.println(Integer.toHexString(this.mPopExitAnim));
            }
            if (!(this.mBreadCrumbTitleRes == 0 && this.mBreadCrumbTitleText == null)) {
                writer.print(prefix);
                writer.print("mBreadCrumbTitleRes=#");
                writer.print(Integer.toHexString(this.mBreadCrumbTitleRes));
                writer.print(" mBreadCrumbTitleText=");
                writer.println(this.mBreadCrumbTitleText);
            }
            if (!(this.mBreadCrumbShortTitleRes == 0 && this.mBreadCrumbShortTitleText == null)) {
                writer.print(prefix);
                writer.print("mBreadCrumbShortTitleRes=#");
                writer.print(Integer.toHexString(this.mBreadCrumbShortTitleRes));
                writer.print(" mBreadCrumbShortTitleText=");
                writer.println(this.mBreadCrumbShortTitleText);
            }
        }
        if (!this.mOps.isEmpty()) {
            writer.print(prefix);
            writer.println("Operations:");
            String innerPrefix = prefix + "    ";
            int numOps = this.mOps.size();
            for (int opNum = 0; opNum < numOps; opNum++) {
                String cmdStr;
                Op op = (Op) this.mOps.get(opNum);
                switch (op.cmd) {
                    case 0:
                        cmdStr = "NULL";
                        break;
                    case 1:
                        cmdStr = "ADD";
                        break;
                    case 2:
                        cmdStr = "REPLACE";
                        break;
                    case 3:
                        cmdStr = "REMOVE";
                        break;
                    case 4:
                        cmdStr = "HIDE";
                        break;
                    case 5:
                        cmdStr = "SHOW";
                        break;
                    case 6:
                        cmdStr = "DETACH";
                        break;
                    case 7:
                        cmdStr = "ATTACH";
                        break;
                    case 8:
                        cmdStr = "SET_PRIMARY_NAV";
                        break;
                    case 9:
                        cmdStr = "UNSET_PRIMARY_NAV";
                        break;
                    default:
                        cmdStr = "cmd=" + op.cmd;
                        break;
                }
                writer.print(prefix);
                writer.print("  Op #");
                writer.print(opNum);
                writer.print(": ");
                writer.print(cmdStr);
                writer.print(" ");
                writer.println(op.fragment);
                if (full) {
                    if (!(op.enterAnim == 0 && op.exitAnim == 0)) {
                        writer.print(prefix);
                        writer.print("enterAnim=#");
                        writer.print(Integer.toHexString(op.enterAnim));
                        writer.print(" exitAnim=#");
                        writer.println(Integer.toHexString(op.exitAnim));
                    }
                    if (op.popEnterAnim != 0 || op.popExitAnim != 0) {
                        writer.print(prefix);
                        writer.print("popEnterAnim=#");
                        writer.print(Integer.toHexString(op.popEnterAnim));
                        writer.print(" popExitAnim=#");
                        writer.println(Integer.toHexString(op.popExitAnim));
                    }
                }
            }
        }
    }

    public BackStackRecord(FragmentManagerImpl manager) {
        this.mManager = manager;
    }

    void addOp(Op op) {
        this.mOps.add(op);
        op.enterAnim = this.mEnterAnim;
        op.exitAnim = this.mExitAnim;
        op.popEnterAnim = this.mPopEnterAnim;
        op.popExitAnim = this.mPopExitAnim;
    }

    public FragmentTransaction add(Fragment fragment, String tag) {
        doAddOp(0, fragment, tag, 1);
        return this;
    }

    public FragmentTransaction add(int containerViewId, Fragment fragment, String tag) {
        doAddOp(containerViewId, fragment, tag, 1);
        return this;
    }

    private void doAddOp(int containerViewId, Fragment fragment, String tag, int opcmd) {
        Class fragmentClass = fragment.getClass();
        int modifiers = fragmentClass.getModifiers();
        if (fragmentClass.isAnonymousClass() || !Modifier.isPublic(modifiers) || (fragmentClass.isMemberClass() && !Modifier.isStatic(modifiers))) {
            throw new IllegalStateException("Fragment " + fragmentClass.getCanonicalName() + " must be a public static class to be  properly recreated from" + " instance state.");
        }
        fragment.mFragmentManager = this.mManager;
        if (tag != null) {
            if (fragment.mTag == null || tag.equals(fragment.mTag)) {
                fragment.mTag = tag;
            } else {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + tag);
            }
        }
        if (containerViewId != 0) {
            if (containerViewId == -1) {
                throw new IllegalArgumentException("Can't add fragment " + fragment + " with tag " + tag + " to container view with no id");
            } else if (fragment.mFragmentId == 0 || fragment.mFragmentId == containerViewId) {
                fragment.mFragmentId = containerViewId;
                fragment.mContainerId = containerViewId;
            } else {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + containerViewId);
            }
        }
        addOp(new Op(opcmd, fragment));
    }

    public FragmentTransaction replace(int containerViewId, Fragment fragment) {
        return replace(containerViewId, fragment, null);
    }

    public FragmentTransaction replace(int containerViewId, Fragment fragment, String tag) {
        if (containerViewId == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        doAddOp(containerViewId, fragment, tag, 2);
        return this;
    }

    public FragmentTransaction remove(Fragment fragment) {
        addOp(new Op(3, fragment));
        return this;
    }

    public FragmentTransaction detach(Fragment fragment) {
        addOp(new Op(6, fragment));
        return this;
    }

    public FragmentTransaction attach(Fragment fragment) {
        addOp(new Op(7, fragment));
        return this;
    }

    public FragmentTransaction addToBackStack(String name) {
        if (this.mAllowAddToBackStack) {
            this.mAddToBackStack = true;
            this.mName = name;
            return this;
        }
        throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
    }

    void bumpBackStackNesting(int amt) {
        if (this.mAddToBackStack) {
            if (FragmentManagerImpl.DEBUG) {
                Log.v("FragmentManager", "Bump nesting in " + this + " by " + amt);
            }
            int numOps = this.mOps.size();
            for (int opNum = 0; opNum < numOps; opNum++) {
                Op op = (Op) this.mOps.get(opNum);
                if (op.fragment != null) {
                    Fragment fragment = op.fragment;
                    fragment.mBackStackNesting += amt;
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v("FragmentManager", "Bump nesting of " + op.fragment + " to " + op.fragment.mBackStackNesting);
                    }
                }
            }
        }
    }

    public void runOnCommitRunnables() {
        if (this.mCommitRunnables != null) {
            int N = this.mCommitRunnables.size();
            for (int i = 0; i < N; i++) {
                ((Runnable) this.mCommitRunnables.get(i)).run();
            }
            this.mCommitRunnables = null;
        }
    }

    public int commit() {
        return commitInternal(false);
    }

    public int commitAllowingStateLoss() {
        return commitInternal(true);
    }

    int commitInternal(boolean allowStateLoss) {
        if (this.mCommitted) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Commit: " + this);
            PrintWriter pw = new PrintWriter(new LogWriter("FragmentManager"));
            dump("  ", null, pw, null);
            pw.close();
        }
        this.mCommitted = true;
        if (this.mAddToBackStack) {
            this.mIndex = this.mManager.allocBackStackIndex(this);
        } else {
            this.mIndex = -1;
        }
        this.mManager.enqueueAction(this, allowStateLoss);
        return this.mIndex;
    }

    public boolean generateOps(ArrayList<BackStackRecord> records, ArrayList<Boolean> isRecordPop) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v("FragmentManager", "Run: " + this);
        }
        records.add(this);
        isRecordPop.add(Boolean.valueOf(false));
        if (this.mAddToBackStack) {
            this.mManager.addBackStackState(this);
        }
        return true;
    }

    boolean interactsWith(int containerId) {
        int numOps = this.mOps.size();
        for (int opNum = 0; opNum < numOps; opNum++) {
            int fragContainer;
            Op op = (Op) this.mOps.get(opNum);
            if (op.fragment != null) {
                fragContainer = op.fragment.mContainerId;
            } else {
                fragContainer = 0;
            }
            if (fragContainer != 0 && fragContainer == containerId) {
                return true;
            }
        }
        return false;
    }

    boolean interactsWith(ArrayList<BackStackRecord> records, int startIndex, int endIndex) {
        if (endIndex == startIndex) {
            return false;
        }
        int numOps = this.mOps.size();
        int lastContainer = -1;
        for (int opNum = 0; opNum < numOps; opNum++) {
            Op op = (Op) this.mOps.get(opNum);
            int container = op.fragment != null ? op.fragment.mContainerId : 0;
            if (!(container == 0 || container == lastContainer)) {
                lastContainer = container;
                for (int i = startIndex; i < endIndex; i++) {
                    BackStackRecord record = (BackStackRecord) records.get(i);
                    int numThoseOps = record.mOps.size();
                    for (int thoseOpIndex = 0; thoseOpIndex < numThoseOps; thoseOpIndex++) {
                        Op thatOp = (Op) record.mOps.get(thoseOpIndex);
                        if ((thatOp.fragment != null ? thatOp.fragment.mContainerId : 0) == container) {
                            return true;
                        }
                    }
                }
                continue;
            }
        }
        return false;
    }

    void executeOps() {
        int numOps = this.mOps.size();
        for (int opNum = 0; opNum < numOps; opNum++) {
            Op op = (Op) this.mOps.get(opNum);
            Fragment f = op.fragment;
            if (f != null) {
                f.setNextTransition(this.mTransition, this.mTransitionStyle);
            }
            switch (op.cmd) {
                case 1:
                    f.setNextAnim(op.enterAnim);
                    this.mManager.addFragment(f, false);
                    break;
                case 3:
                    f.setNextAnim(op.exitAnim);
                    this.mManager.removeFragment(f);
                    break;
                case 4:
                    f.setNextAnim(op.exitAnim);
                    this.mManager.hideFragment(f);
                    break;
                case 5:
                    f.setNextAnim(op.enterAnim);
                    this.mManager.showFragment(f);
                    break;
                case 6:
                    f.setNextAnim(op.exitAnim);
                    this.mManager.detachFragment(f);
                    break;
                case 7:
                    f.setNextAnim(op.enterAnim);
                    this.mManager.attachFragment(f);
                    break;
                case 8:
                    this.mManager.setPrimaryNavigationFragment(f);
                    break;
                case 9:
                    this.mManager.setPrimaryNavigationFragment(null);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + op.cmd);
            }
            if (!(this.mReorderingAllowed || op.cmd == 1 || f == null)) {
                this.mManager.moveFragmentToExpectedState(f);
            }
        }
        if (!this.mReorderingAllowed) {
            this.mManager.moveToState(this.mManager.mCurState, true);
        }
    }

    void executePopOps(boolean moveToState) {
        for (int opNum = this.mOps.size() - 1; opNum >= 0; opNum--) {
            Op op = (Op) this.mOps.get(opNum);
            Fragment f = op.fragment;
            if (f != null) {
                f.setNextTransition(FragmentManagerImpl.reverseTransit(this.mTransition), this.mTransitionStyle);
            }
            switch (op.cmd) {
                case 1:
                    f.setNextAnim(op.popExitAnim);
                    this.mManager.removeFragment(f);
                    break;
                case 3:
                    f.setNextAnim(op.popEnterAnim);
                    this.mManager.addFragment(f, false);
                    break;
                case 4:
                    f.setNextAnim(op.popEnterAnim);
                    this.mManager.showFragment(f);
                    break;
                case 5:
                    f.setNextAnim(op.popExitAnim);
                    this.mManager.hideFragment(f);
                    break;
                case 6:
                    f.setNextAnim(op.popEnterAnim);
                    this.mManager.attachFragment(f);
                    break;
                case 7:
                    f.setNextAnim(op.popExitAnim);
                    this.mManager.detachFragment(f);
                    break;
                case 8:
                    this.mManager.setPrimaryNavigationFragment(null);
                    break;
                case 9:
                    this.mManager.setPrimaryNavigationFragment(f);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown cmd: " + op.cmd);
            }
            if (!(this.mReorderingAllowed || op.cmd == 3 || f == null)) {
                this.mManager.moveFragmentToExpectedState(f);
            }
        }
        if (!this.mReorderingAllowed && moveToState) {
            this.mManager.moveToState(this.mManager.mCurState, true);
        }
    }

    Fragment expandOps(ArrayList<Fragment> added, Fragment oldPrimaryNav) {
        int opNum = 0;
        while (opNum < this.mOps.size()) {
            Op op = (Op) this.mOps.get(opNum);
            switch (op.cmd) {
                case 1:
                case 7:
                    added.add(op.fragment);
                    break;
                case 2:
                    Fragment f = op.fragment;
                    int containerId = f.mContainerId;
                    boolean alreadyAdded = false;
                    for (int i = added.size() - 1; i >= 0; i--) {
                        Fragment old = (Fragment) added.get(i);
                        if (old.mContainerId == containerId) {
                            if (old == f) {
                                alreadyAdded = true;
                            } else {
                                if (old == oldPrimaryNav) {
                                    this.mOps.add(opNum, new Op(9, old));
                                    opNum++;
                                    oldPrimaryNav = null;
                                }
                                Op removeOp = new Op(3, old);
                                removeOp.enterAnim = op.enterAnim;
                                removeOp.popEnterAnim = op.popEnterAnim;
                                removeOp.exitAnim = op.exitAnim;
                                removeOp.popExitAnim = op.popExitAnim;
                                this.mOps.add(opNum, removeOp);
                                added.remove(old);
                                opNum++;
                            }
                        }
                    }
                    if (!alreadyAdded) {
                        op.cmd = 1;
                        added.add(f);
                        break;
                    }
                    this.mOps.remove(opNum);
                    opNum--;
                    break;
                case 3:
                case 6:
                    added.remove(op.fragment);
                    if (op.fragment != oldPrimaryNav) {
                        break;
                    }
                    this.mOps.add(opNum, new Op(9, op.fragment));
                    opNum++;
                    oldPrimaryNav = null;
                    break;
                case 8:
                    this.mOps.add(opNum, new Op(9, oldPrimaryNav));
                    opNum++;
                    oldPrimaryNav = op.fragment;
                    break;
                default:
                    break;
            }
            opNum++;
        }
        return oldPrimaryNav;
    }

    Fragment trackAddedFragmentsInPop(ArrayList<Fragment> added, Fragment oldPrimaryNav) {
        for (int opNum = 0; opNum < this.mOps.size(); opNum++) {
            Op op = (Op) this.mOps.get(opNum);
            switch (op.cmd) {
                case 1:
                case 7:
                    added.remove(op.fragment);
                    break;
                case 3:
                case 6:
                    added.add(op.fragment);
                    break;
                case 8:
                    oldPrimaryNav = null;
                    break;
                case 9:
                    oldPrimaryNav = op.fragment;
                    break;
                default:
                    break;
            }
        }
        return oldPrimaryNav;
    }

    boolean isPostponed() {
        for (int opNum = 0; opNum < this.mOps.size(); opNum++) {
            if (isFragmentPostponed((Op) this.mOps.get(opNum))) {
                return true;
            }
        }
        return false;
    }

    void setOnStartPostponedListener(OnStartEnterTransitionListener listener) {
        for (int opNum = 0; opNum < this.mOps.size(); opNum++) {
            Op op = (Op) this.mOps.get(opNum);
            if (isFragmentPostponed(op)) {
                op.fragment.setOnStartEnterTransitionListener(listener);
            }
        }
    }

    private static boolean isFragmentPostponed(Op op) {
        Fragment fragment = op.fragment;
        return (fragment == null || !fragment.mAdded || fragment.mView == null || fragment.mDetached || fragment.mHidden || !fragment.isPostponed()) ? false : true;
    }

    public String getName() {
        return this.mName;
    }
}
