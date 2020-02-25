package com.maxx.nordvaest.utils.validationLib.rule;

import android.widget.RadioGroup;
import android.widget.TextView;

import com.maxx.nordvaest.utils.validationLib.util.RadioGroupHandler;


/**
 * Created by amanandhar on 2/5/18.
 */

public class RadioGroupSelectedRule extends Rule<TextView, RadioGroup> {

    public RadioGroupSelectedRule(RadioGroup view, TextView errorLabel, String errorMessage) {
        super(errorLabel, view, errorMessage);
    }

    @Override
    public boolean isValid(TextView view) {
        return value.getCheckedRadioButtonId() != -1;
    }

    @Override
    public void onValidationSucceeded(TextView view) {
        RadioGroupHandler.removeError(view);
    }

    @Override
    public void onValidationFailed(TextView view) {
        RadioGroupHandler.setError(view, errorMessage);
    }
}
