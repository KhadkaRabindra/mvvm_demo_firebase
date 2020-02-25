package com.eightsquarei.eremitpay.utils.view.numerickeyboard.util

import android.annotation.SuppressLint
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.maxx.nordvaest.utils.view.numerickeyboard.IconifiedTextView

/**
 * Created by Evgeny Eliseyev on 11/02/2018.
 */

internal class KeyClickListener(
    private val maxLength: Int,
    private val field: EditText?
): View.OnClickListener {
    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        val field = this.field ?: return

        if (view is IconifiedTextView) {
            val previousChar = field.selectionEnd - 1

            if (previousChar >= 0) {
                var value = field.text.toString()
                value = "${value.substring(0, previousChar)}${value.substring(previousChar + 1)}"

                field.setText(value)
                field.setSelection(previousChar)
            }
        } else if (view is TextView) {
            val textLength = field.text.length
            if (maxLength > 0 && textLength == maxLength) {
                return
            }

            val selectionEnd = field.selectionEnd
            val builder = StringBuilder(field.text.subSequence(0, selectionEnd))
            builder.append(view.text).append(field.text.subSequence(selectionEnd, field.length()))

            field.setText(builder.toString())

            if (selectionEnd < textLength) {
                field.setSelection(selectionEnd + 1)
            } else if (selectionEnd == textLength) {
                field.setSelection(field.text.length)
            }
        }
    }
}