package com.maxx.nordvaest.utils.validationLib.binding;


import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import com.maxx.nordvaest.R;
import com.maxx.nordvaest.utils.validationLib.rule.BonusLinkRule;
import com.maxx.nordvaest.utils.validationLib.rule.OptionalEmailRule;
import com.maxx.nordvaest.utils.validationLib.util.EditTextHandler;
import com.maxx.nordvaest.utils.validationLib.util.ErrorMessageHelper;
import com.maxx.nordvaest.utils.validationLib.util.ViewTagHelper;

/**
 * Created by anup on 06/09/2018
 */
public class ExtraEditTextBindings {

    @BindingAdapter(value = {"validateBonusLink", "validateBonusLinkMessage", "validateBonusLinkAutoDismiss"}, requireAll = false)
    public static void bindBonusLinkEditText(TextView view, boolean isValid, String errorMessage, boolean autoDismiss) {
        if (autoDismiss)
            EditTextHandler.disableErrorOnChanged(view);

        String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view,
                errorMessage, R.string.error_message_invalid_bonus_link);
        ViewTagHelper.appendValue(R.id.validator_rule, view, new BonusLinkRule(view, isValid, handledErrorMessage));
    }

    @BindingAdapter(value = {"validateOptionalEmail", "validateOptionalEmailMessage", "validateOptionalEmailAutoDismiss"}, requireAll = false)
    public static void bindOptionalEmailEditText(TextView view, boolean isValid, String errorMessage, boolean autoDismiss) {
        if (autoDismiss)
            EditTextHandler.disableErrorOnChanged(view);

        String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view,
                errorMessage, R.string.error_message_email);
        ViewTagHelper.appendValue(R.id.validator_rule, view, new OptionalEmailRule(view, isValid, handledErrorMessage));
    }
}
