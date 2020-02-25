package com.maxx.nordvaest.utils.validationLib.rule;

import android.widget.TextView;
import com.maxx.nordvaest.utils.extensions.ValidationExtKt;
import com.maxx.nordvaest.utils.validationLib.util.EditTextHandler;

/**
 * Created by anup on 06/09/2018
 */
public class BonusLinkRule extends Rule<TextView, Boolean> {

    public BonusLinkRule(TextView view, Boolean value, String errorMessage) {
        super(view, value, errorMessage);
    }

    @Override
    protected boolean isValid(TextView view) {
        return !value || ValidationExtKt.validateBonusLinkId(view.getText().toString());
    }

    @Override
    protected void onValidationSucceeded(TextView view) {
        EditTextHandler.removeError(view);
    }

    @Override
    protected void onValidationFailed(TextView view) {
        EditTextHandler.setError(view, errorMessage);
    }
}
