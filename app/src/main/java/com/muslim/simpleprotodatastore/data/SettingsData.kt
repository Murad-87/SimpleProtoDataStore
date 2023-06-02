package com.muslim.simpleprotodatastore.data

import com.muslim.simpleprotodatastore.ui.theme.Red
import kotlinx.serialization.Serializable

@Serializable
data class SettingsData(
    val textSize: Int = 40,
    val bgColor: ULong = Red.value,
)
