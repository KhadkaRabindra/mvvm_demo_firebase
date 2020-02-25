package com.maxx.nordvaest.utils.validationLib.util;

import android.graphics.Color;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by amanandhar on 2/5/18.
 */

public class SpinnerHandler {
    public static void removeError(TextView textView) {
        SpinnerHandler.setError(textView, null);
    }

    public static void setError(TextView textView, String errorMessage) {
        if (null == errorMessage) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
            textView.setText(errorMessage);
            textView.setTextColor(Color.RED);
        }
    }

    public static void disableErrorOnChanged(final Spinner spinner, final TextView textView) {
        if (spinner.isDirty())
            SpinnerHandler.setError(textView, null);
    }

}
