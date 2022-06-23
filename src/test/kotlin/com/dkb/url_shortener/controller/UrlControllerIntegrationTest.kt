package com.dkb.url_shortener.controller

import com.dkb.url_shortener.constant.TestConstants
import com.dkb.url_shortener.service.UrlService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

@WebMvcTest
class UrlControllerIntegrationTest(@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var urlService: UrlService

    private val mapper = jacksonObjectMapper()

    @Test
    fun shouldGetUrlThenReturnsJsonWithStatus200() {
        every { urlService.getUrl(TestConstants.ANY_ID) } returns TestConstants.url
        mockMvc.perform(get(TestConstants.GET_URL_PATH))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(TestConstants.ANY_ID))
    }

    @Test
    fun shouldNotGetUrlWhenIdNotFound() {
        every { urlService.getUrl(TestConstants.ANY_ID) } throws TestConstants.exception
        mockMvc.perform(get(TestConstants.GET_URL_PATH))
            .andExpect(status().isNotFound)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    }

    @Test
    fun shouldSaveUrl() {
        every { urlService.saveUrl(TestConstants.createUrlDto(TestConstants.ANY_URL)) } returns TestConstants.url
        mockMvc.perform(post(TestConstants.POST_URL_PATH).content(mapper.writeValueAsString(TestConstants.url)).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(TestConstants.ANY_ID))
    }
}