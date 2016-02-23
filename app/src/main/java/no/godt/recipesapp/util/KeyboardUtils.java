/*
 * Copyright (c) 2016. All rights reserved.
 */

package no.godt.recipesapp.util;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class KeyboardUtils {

    private KeyboardUtils() {
        // prevent from instantiate
    }

    public static void hideSoftKeyboardIfOpened(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

        View currFocus = activity.getCurrentFocus();
        if (currFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(currFocus.getWindowToken(), 0);
        }
    }

    public static void hideSoftKeyboardIfOpened(Activity activity, View viewToHide) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(viewToHide.getWindowToken(), 0);
    }

}
