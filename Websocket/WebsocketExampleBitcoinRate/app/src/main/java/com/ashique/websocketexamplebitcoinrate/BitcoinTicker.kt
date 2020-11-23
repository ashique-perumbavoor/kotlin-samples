package com.ashique.websocketexamplebitcoinrate

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BitcoinTicker (val price: String?)