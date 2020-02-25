package com.maxx.nordvaest.utils.validationLib.util;

import android.graphics.Color;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

/**
 * Created by amanandhar on 2/5/18.
 */

public class RadioGroupHandler {
    public static void removeError(TextView textView) {
        RadioGroupHandler.setError(textView, null);
    }

    public static void setError(TextView textView, String errorMessage) {
        if (null == errorMessage) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(errorMessage);
            textView.setTextColor(Color.RED);
        }
    }

    public static void disableErrorOnChanged(final RadioGroup radioGroup, final TextView textView) {
        if (radioGroup.isDirty())
            RadioGroupHandler.setError(textView, null);
    }
}
