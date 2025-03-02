package com.assessment.voyatek.core.presentation.util

import android.content.Context
import com.assessment.voyatek.R
import com.assessment.voyatek.core.domain.util.NetworkError


infix fun NetworkError.toLocalizedString(context: Context): String{
    val resId = when (this) {
        NetworkError.REQUEST_TIMEOUT -> R.string.request_timeout
        NetworkError.TOO_MANY_REQUESTS -> R.string.too_many_requests
        NetworkError.NO_INTERNET_CONNECTION -> R.string.no_internet_connection
        NetworkError.SERVER_ERROR -> R.string.error_unknown
        NetworkError.SERIALIZATION_ERROR -> R.string.serialization_error
        NetworkError.UNKNOWN_ERROR -> R.string.error_unknown
    }
    return context.getString(resId)
}