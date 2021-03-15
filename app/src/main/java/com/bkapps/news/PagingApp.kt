package com.bkapps.news

import android.app.Application
import androidx.emoji.bundled.BundledEmojiCompatConfig
import androidx.emoji.text.EmojiCompat

class PagingApp : Application() {

    companion object {
        private lateinit var INSTANCE: PagingApp
        fun getApplication() = INSTANCE
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        setupEmojiConfig()
    }

    private fun setupEmojiConfig() {
        val config: EmojiCompat.Config
        config = BundledEmojiCompatConfig(applicationContext)
        EmojiCompat.init(config)
    }
}