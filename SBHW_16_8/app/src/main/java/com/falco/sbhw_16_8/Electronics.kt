package com.falco.sbhw_16_8

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Electronics {

    @Parcelize
    data class Smartphone(
        val name: String,
        val imageLink: String,
        val screenSize: String,
        val countryOfOrigin: String,
        val price: Int,
        val processor: String,
        val memory: Int,
        val os: String,
        val ram: Int,
        val camera: String,
        val battery: Int
    ) : Electronics(), Parcelable

    @Parcelize
    data class Television(
        val name: String,
        val imageLink: String,
        val screenSize: String,
        val countryOfOrigin: String,
        val price: Int,
        val isSmart: Boolean,
        val smartPlatform: String?,
        val resolutionHD: String
    ) : Electronics(), Parcelable

    @Parcelize
    data class GameConsole(
        val name: String,
        val imageLink: String,
        val screenSize: String?,
        val resolution: String?,
        val countryOfOrigin: String,
        val price: Int,
        val processor: String,
        val memory: Int,
        val rayTracing: Boolean,
        val gProcessor: String,
        val ssd: Boolean
    ) : Electronics(), Parcelable
}
