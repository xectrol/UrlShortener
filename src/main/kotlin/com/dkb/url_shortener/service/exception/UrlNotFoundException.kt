package com.dkb.url_shortener.service.exception

class UrlNotFoundException(override val message: String): Exception(message) {
}