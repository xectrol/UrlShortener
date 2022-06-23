package com.dkb.url_shortener.service

import com.dkb.url_shortener.repository.UrlRedisRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.junit.runner.RunWith
import java.util.*
import com.dkb.url_shortener.constant.TestConstants
import com.dkb.url_shortener.service.exception.UrlNotFoundException
import org.junit.Assert.assertThrows


@RunWith(SpringRunner::class)
@SpringBootTest
class UrlServiceUnitTest {

    private final val urlRedisRepository: UrlRedisRepository = mockk()
    val urlService = UrlService(urlRedisRepository)

    @Test
    fun shouldGetUrl() {
        //given
        every { urlRedisRepository.findById(TestConstants.ANY_ID) } returns Optional.of(TestConstants.url)

        //when
        val result = urlService.getUrl(TestConstants.ANY_ID)

        //then
        verify(exactly = 1) { urlRedisRepository.findById(TestConstants.ANY_ID) }
        assertEquals(TestConstants.url, result)
    }

    @Test
    fun shouldNotGetUrlWhenIdIsNotIn() {
        //given
        every { urlRedisRepository.findById(TestConstants.ANY_ID) } returns Optional.empty()
        assertThrows(UrlNotFoundException::class.java){
            //when
            urlService.getUrl(TestConstants.ANY_ID)
        }
    }

    @Test
    fun shouldSaveUrl() {
        //given
        every { urlRedisRepository.save(any()) } returns TestConstants.hashedUrl
        //when
        val result = urlService.saveUrl(TestConstants.createUrlDto(TestConstants.ANY_URL))
        //then
        verify(exactly = 1) { urlRedisRepository.save(any()) }
        assertEquals(TestConstants.hashedUrl, result)
    }
}
