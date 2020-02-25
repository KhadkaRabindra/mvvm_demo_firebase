package com.maxx.nordvaest.utils.validationLib.rule;

import android.util.Log;
import android.widget.Spinner;
import android.widget.TextView;

import com.maxx.nordvaest.utils.validationLib.util.SpinnerHandler;


/**
 * Created by amanandhar on 2/5/18.
 */

public class SpinnerSelectedRule extends Rule<TextView, Spinner> {

    public SpinnerSelectedRule(Spinner view, TextView errorLabel, String errorMessage) {
        super(errorLabel, view, errorMessage);

    }

    @Override
    public boolean isValid(TextView view) {
        return value.getSelectedItemPosition() != 0;
    }

    @Override
    public void onValidationSucceeded(TextView view) {
        Log.v("TEST", "success");
        SpinnerHandler.removeError(view);
    }

    @Override
    public void onValidationFailed(TextView view) {
        Log.v("TEST", "failed");
        SpinnerHandler.setError(view, errorMessage);
    }
}