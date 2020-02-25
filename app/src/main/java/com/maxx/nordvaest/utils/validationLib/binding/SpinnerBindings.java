package com.maxx.nordvaest.utils.validationLib.binding;

import android.widget.Spinner;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import com.maxx.nordvaest.R;
import com.maxx.nordvaest.utils.validationLib.rule.SpinnerSelectedRule;
import com.maxx.nordvaest.utils.validationLib.util.ErrorMessageHelper;
import com.maxx.nordvaest.utils.validationLib.util.SpinnerHandler;
import com.maxx.nordvaest.utils.validationLib.util.ViewTagHelper;


/**
 * Created by amanandhar on 2/5/18.
 */
public class SpinnerBindings {
        @BindingAdapter(value = {"validateSelected", "validateSelectedMessage", "validateSpinnerAutoDismiss"}, requireAll = false)
    public static void spinnerNotSelected(Spinner view, TextView errorLabel, String errorMessage, boolean autoDismiss) {
        // To dismiss the error when use performs any action on the component
        if (autoDismiss) {
            SpinnerHandler.disableErrorOnChanged(view, errorLabel);
        }
        // To get error message that is passed on from the XML file, if no message is passed then pick a common error message for empty validation
        String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(errorLabel, errorMessage, R.string.error_message_empty_validation);
        ViewTagHelper.appendValue(R.id.validator_rule, view, new SpinnerSelectedRule(view, errorLabel, handledErrorMessage));
    }
}
