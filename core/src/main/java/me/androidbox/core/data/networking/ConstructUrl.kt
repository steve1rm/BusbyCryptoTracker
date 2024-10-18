package me.androidbox.core.data.networking

import me.androidbox.core.BuildConfig

fun constructUrl(url: String): String {
    return when {
        url.contains(BuildConfig.BASE_URL) -> url
        url.contains("/") -> BuildConfig.BASE_URL + url.drop(1)
        else -> {
            BuildConfig.BASE_URL + url
        }
    }
}