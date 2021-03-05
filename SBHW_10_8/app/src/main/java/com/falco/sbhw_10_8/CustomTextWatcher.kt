package com.falco.sbhw_10_8

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText

class CustomTextWatcher(
    private val button: Button,
    private val checkBox: CheckBox,
    private val edList: List<EditText>
) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        for (editText in edList) {
            if (editText.text.toString().isEmpty() || !checkBox.isChecked) {
                button.isEnabled = false
                break
            } else button.isEnabled = true
        }
    }
}
