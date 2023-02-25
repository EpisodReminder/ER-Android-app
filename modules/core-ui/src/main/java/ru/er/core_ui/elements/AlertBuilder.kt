package ru.er.core_ui.elements

import android.app.AlertDialog
import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import ru.er.core_ui.R

fun showDefaultErrorAlertDialog(context: Context, positiveButtonText: () -> Unit): AlertDialog =
    showCustomAlertDialog(
        context = context,
        titleRes = R.string.dialog_alert__default_error_title,
        descriptionRes = R.string.dialog_alert__default_error_message,
        positiveButtonTextRes = R.string.dialog_alert__default_error_positive_button_text,
        positiveButtonAction = positiveButtonText,
    )

fun showWillBeSoonAlertDialog(context: Context): AlertDialog = showCustomAlertDialog(
    context = context,
    titleRes = R.string.dialog_alert__will_be_soon_title,
    descriptionRes = R.string.dialog_alert__will_be_soon_message,
    positiveButtonTextRes = R.string.dialog_alert__will_be_soon_positive_button_text,
)


private fun showCustomAlertDialog(
    context: Context,
    @StringRes titleRes: Int? = null,
    title: CharSequence? = null,
    @StringRes descriptionRes: Int? = null,
    description: CharSequence? = null,
    @StringRes positiveButtonTextRes: Int? = null,
    positiveButtonText: CharSequence? = null,
    positiveButtonAction: () -> Unit = {},
    @StringRes negativeButtonTextRes: Int? = null,
    negativeButtonText: CharSequence? = null,
    negativeButtonAction: () -> Unit = {},
    @DrawableRes iconRes: Int? = null
) = AlertDialog.Builder(context).apply {
    checkPairOrNull(context, titleRes, title) { resTitle -> setTitle(resTitle) }
    checkPairOrNull(context, descriptionRes, description) { resTitle -> setMessage(resTitle) }
    checkPairOrNull(
        context,
        positiveButtonTextRes,
        positiveButtonText
    ) { resTitle ->
        setPositiveButton(
            resTitle
        ) { _, _ -> positiveButtonAction() }
    }
    checkPairOrNull(
        context,
        negativeButtonTextRes,
        negativeButtonText
    ) { resTitle ->
        setNegativeButton(
            resTitle
        ) { _, _ -> negativeButtonAction() }
    }
    iconRes?.let { setIcon(iconRes) }
}.show()


private fun checkPairOrNull(
    context: Context,
    resId: Int?,
    title: CharSequence?,
    action: (String) -> Unit
) {
    if (resId == null) {
        if (title != null) action(title.toString())
    } else {
        action(context.getString(resId))
    }
}