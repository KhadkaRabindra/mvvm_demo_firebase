package com.maxx.nordvaest.utils.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;


import java.util.Random;

import androidx.appcompat.widget.AppCompatEditText;


public class BackSpaceListenEditText extends AppCompatEditText {

    private Random r = new Random();

    public BackSpaceListenEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public BackSpaceListenEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BackSpaceListenEditText(Context context) {
        super(context);
    }

    public void setRandomBackgroundColor() {
        setBackgroundColor(Color.rgb(r.nextInt(256), r.nextInt(256), r
                .nextInt(256)));
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return new ZanyInputConnection(super.onCreateInputConnection(outAttrs),
                true);
    }

    private class ZanyInputConnection extends InputConnectionWrapper {

        public ZanyInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        @Override
        public boolean sendKeyEvent(KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getKeyCode() == KeyEvent.KEYCODE_DEL) {
                //BackSpaceListenEditText.this.setRandomBackgroundColor();
                // Un-comment if you wish to cancel the backspace:
                // return false;
            }
            return super.sendKeyEvent(event);
        }


        @Override
        public boolean deleteSurroundingText(int beforeLength, int afterLength) {
            // magic: in latest Android, deleteSurroundingText(1, 0) will be called for backspace
            if (beforeLength == 1 && afterLength == 0) {
                // backspace
                return sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL))
                        && sendKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_DEL));
            }

            return super.deleteSurroundingText(beforeLength, afterLength);
        }

    }

}

