package com.sinw.composestudy.unit3.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic (
    @StringRes val stringResourceId: Int,
    val views: Int,
    @DrawableRes val imageResourceId: Int
)