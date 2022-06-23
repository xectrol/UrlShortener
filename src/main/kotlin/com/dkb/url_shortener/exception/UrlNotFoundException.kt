package com.dkb.url_shortener.exception

class UrlNotFoundException(override val message: String): Exception(message) {
}