package com.bkapps.news.utils

import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

fun <T> observing(
    initialValue: T,
    willSet: () -> Unit = {},
    didSet: () -> Unit = {}
): ObservableProperty<T> {
    return object : ObservableProperty<T>(initialValue) {

        override fun beforeChange(property: KProperty<*>, oldValue: T, newValue: T) =
            true.apply {
                willSet()
            }

        override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) = didSet()
    }
}