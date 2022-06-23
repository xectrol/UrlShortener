package com.dkb.url_shortener.controller

import com.dkb.url_shortener.domain.Url
import com.dkb.url_shortener.service.UrlService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/api/url")
class UrlController(private val urlService: UrlService) {

    /**
     * Returns the original URL.
     */
    @ResponseBody
    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: String): Url {
        return urlService.getUrl(id)
    }

    /**
     * Returns a short URL.
     */
    @ResponseBody
    @PostMapping
    fun save(@RequestBody urlDto: UrlDto): Url {
        return urlService.saveUrl(urlDto)
    }

    data class UrlDto(
        @get:NotBlank val url: String,
    )
}