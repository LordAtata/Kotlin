package com.falco.sbhw_10_8

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FormState(val valid: Boolean, val message: String) : Parcelable
