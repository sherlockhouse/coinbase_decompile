package com.coinbase.android.settings.tiers;

import android.os.Bundle;
import android.text.TextUtils;
import com.coinbase.android.ControllerScope;
import com.coinbase.android.identityverification.IdentityVerificationController;
import com.coinbase.android.identityverification.InAppIdentityAcceptableDocumentsController;
import com.coinbase.android.task.GetUserTask.AdminFlags;
import com.coinbase.android.ui.ActionBarController;
import com.coinbase.api.internal.models.tiers.Requirement;
import com.coinbase.api.internal.models.tiers.Requirement.Status;
import java.util.HashMap;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.functions.Func1;

@ControllerScope
public class InvestmentTiersRequirementsRouter {
    private static final String DOCUMENT_VERIFICATION_REQUIREMENT = "document_verification";
    private static final String DOCUMENT_VERIFICATION_WITH_FACEMATCH_REQUIREMENT = "document_verification_with_facematch";
    private static final String IDENTITY_VERIFICATION_REQUIREMENT = "identity_verification";
    private static final String PHONE_NUMBER_REQUIREMENT = "phone";
    private static final String SOURCE_OF_FUNDS_REQUIREMENT = "source_of_funds";
    private final ActionBarController mController;
    private final Logger mLogger = LoggerFactory.getLogger(InvestmentTiersRequirementsRouter.class);
    private HashMap<String, Func1<Status, ActionBarController>> mRequirementControllerMap = new HashMap();

    @Inject
    public InvestmentTiersRequirementsRouter(ActionBarController controller) {
        this.mController = controller;
        initRequirementControllerMap();
    }

    private void initRequirementControllerMap() {
        this.mRequirementControllerMap.put(PHONE_NUMBER_REQUIREMENT, InvestmentTiersRequirementsRouter$$Lambda$1.lambdaFactory$(this));
        this.mRequirementControllerMap.put(IDENTITY_VERIFICATION_REQUIREMENT, InvestmentTiersRequirementsRouter$$Lambda$2.lambdaFactory$(this));
        this.mRequirementControllerMap.put(DOCUMENT_VERIFICATION_REQUIREMENT, InvestmentTiersRequirementsRouter$$Lambda$3.lambdaFactory$(this));
        this.mRequirementControllerMap.put(DOCUMENT_VERIFICATION_WITH_FACEMATCH_REQUIREMENT, InvestmentTiersRequirementsRouter$$Lambda$4.lambdaFactory$(this));
        this.mRequirementControllerMap.put(SOURCE_OF_FUNDS_REQUIREMENT, InvestmentTiersRequirementsRouter$$Lambda$5.lambdaFactory$(this));
    }

    static /* synthetic */ ActionBarController lambda$initRequirementControllerMap$2(InvestmentTiersRequirementsRouter this_, Status status) {
        if (status != Status.INCOMPLETE) {
            return new IdentityVerificationController(this_.mController.appendModalArgsWithRoot(new Bundle()));
        }
        return new InAppIdentityAcceptableDocumentsController(this_.mController.appendModalArgsWithRoot(new Bundle()));
    }

    static /* synthetic */ ActionBarController lambda$initRequirementControllerMap$3(InvestmentTiersRequirementsRouter this_, Status status) {
        if (status != Status.INCOMPLETE) {
            return new IdentityVerificationController(this_.mController.appendModalArgsWithRoot(this_.appendFaceMatchArgs(new Bundle())));
        }
        return new InAppIdentityAcceptableDocumentsController(this_.mController.appendModalArgsWithRoot(this_.appendFaceMatchArgs(new Bundle())));
    }

    void showTierSuccess() {
        this.mController.getRouter().popCurrentController();
    }

    void routeRequirement(Requirement requirement) {
        if (requirement == null) {
            this.mLogger.error("Couldn't route Requirement, requirement is NULL");
            return;
        }
        String requirementIdentifier = requirement.getIdentifier();
        if (TextUtils.isEmpty(requirementIdentifier)) {
            this.mLogger.error("Couldn't route Requirement, identifier is empty");
            return;
        }
        Func1<Status, ActionBarController> actionBarController = (Func1) this.mRequirementControllerMap.get(requirement.getIdentifier());
        if (actionBarController == null) {
            this.mLogger.error("Unknown ActionBarController for " + requirementIdentifier);
        } else {
            this.mController.pushModalController((ActionBarController) actionBarController.call(Status.fromString(requirement.getStatus())));
        }
    }

    private Bundle appendFaceMatchArgs(Bundle args) {
        args.putBoolean(AdminFlags.REQUIRE_JUMIO_FACE_MATCH.toString(), true);
        return args;
    }
}
