package com.bkapps.news.api.exceptions

import java.io.IOException


class NoNetworkException : IOException() {
    override val message: String
        get() = "Not Internet connection available"
}