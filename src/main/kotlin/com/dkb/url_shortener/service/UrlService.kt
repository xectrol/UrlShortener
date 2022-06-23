package com.dkb.url_shortener.service

import com.dkb.url_shortener.controller.UrlController
import com.dkb.url_shortener.domain.Url
import com.dkb.url_shortener.repository.UrlRedisRepository
import com.dkb.url_shortener.exception.UrlNotFoundException
import com.google.common.hash.Hashing
import mu.KotlinLogging
import org.springframework.stereotype.Service
import java.nio.charset.Charset

@Service
class UrlService(private val urlRedisRepository: UrlRedisRepository) {

    val logger = KotlinLogging.logger {}

    fun getUrl(id: String): Url {
        logger.info("Getting url");
        return urlRedisRepository.findById(id).orElseThrow {
            UrlNotFoundException("Unable to find url for $id id")
        }
    }

    fun saveUrl(urlDto: UrlController.UrlDto): Url {
        logger.info("Creating shortened url...");
        val id = Hashing.adler32().hashString(urlDto.url, Charset.defaultCharset()).toString()
        return urlRedisRepository.save(Url(id = id, url = urlDto.url))
    }
}