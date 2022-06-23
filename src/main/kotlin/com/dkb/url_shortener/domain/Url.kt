package com.dkb.url_shortener.domain

import org.springframework.data.redis.core.RedisHash

@RedisHash("url")
class Url (
    var id: String,
    val url: String
)
