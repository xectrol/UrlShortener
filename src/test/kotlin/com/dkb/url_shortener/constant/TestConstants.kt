package com.dkb.url_shortener.constant

import com.dkb.url_shortener.controller.UrlController
import com.dkb.url_shortener.domain.Url
import com.dkb.url_shortener.service.exception.UrlNotFoundException

class TestConstants {

    companion object {
        const val ANY_ID = "ANY_ID"
        const val ANY_URL = "ANY_URL"
        const val HASHED_ID = "3b02ca08"
        const val GET_URL_PATH = "/api/url/ANY_ID"
        const val POST_URL_PATH = "/api/url"
        val url = Url("ANY_ID", "ANY_URL")
        val not_found_url = Url("ANY_ID", "ANY_URL")
        val hashedUrl = Url("3b02ca08", "ANY_URL")
        fun createUrlDto(url: String): UrlController.UrlDto {
            return UrlController.UrlDto(url)
        }

        val exception = UrlNotFoundException("Unable to find url for id")
    }
}