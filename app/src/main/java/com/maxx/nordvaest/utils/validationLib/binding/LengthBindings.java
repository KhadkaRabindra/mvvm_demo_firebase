/*
 * Copyright (c) 2017-present Ilhasoft.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.maxx.nordvaest.utils.validationLib.binding;

import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import com.maxx.nordvaest.R;
import com.maxx.nordvaest.utils.validationLib.rule.EmptyRule;
import com.maxx.nordvaest.utils.validationLib.rule.MaxLengthRule;
import com.maxx.nordvaest.utils.validationLib.rule.MinLengthRule;
import com.maxx.nordvaest.utils.validationLib.util.EditTextHandler;
import com.maxx.nordvaest.utils.validationLib.util.ErrorMessageHelper;
import com.maxx.nordvaest.utils.validationLib.util.ViewTagHelper;

/**
 * Created by john-mac on 5/14/16.
 */
public class LengthBindings {

    @BindingAdapter(value = {"validateMinLength", "validateMinLengthMessage", "validateMinLengthAutoDismiss"}, requireAll = false)
    public static void bindingMinLength(TextView view, int minLength, String errorMessage, boolean autoDismiss) {
        if (autoDismiss) {
            EditTextHandler.disableErrorOnChanged(view);
        }

        String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view,
                errorMessage, R.string.error_message_min_length, minLength);
        ViewTagHelper.appendValue(R.id.validator_rule, view, new MinLengthRule(view, minLength, handledErrorMessage));
    }

    @BindingAdapter(value = {"validateMaxLength", "validateMaxLengthMessage", "validateMaxLengthAutoDismiss"}, requireAll = false)
    public static void bindingMaxLength(TextView view, int maxLength, String errorMessage, boolean autoDismiss) {
        if (autoDismiss) {
            EditTextHandler.disableErrorOnChanged(view);
        }

        String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view,
                errorMessage, R.string.error_message_max_length, maxLength);
        ViewTagHelper.appendValue(R.id.validator_rule, view, new MaxLengthRule(view, maxLength, handledErrorMessage));
    }

    @BindingAdapter(value = {"validateEmpty", "validateEmptyMessage", "validateEmptyAutoDismiss"}, requireAll = false)
    public static void bindingEmpty(TextView view, boolean empty, String errorMessage, boolean autoDismiss) {
        if (autoDismiss) {
            EditTextHandler.disableErrorOnChanged(view);
        }

        String handledErrorMessage = ErrorMessageHelper.getStringOrDefault(view,
                errorMessage, R.string.error_message_empty_validation);
        ViewTagHelper.appendValue(R.id.validator_rule, view, new EmptyRule(view, empty, handledErrorMessage));
    }

}
