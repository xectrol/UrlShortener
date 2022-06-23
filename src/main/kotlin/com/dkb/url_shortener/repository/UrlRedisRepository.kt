package com.dkb.url_shortener.repository

import com.dkb.url_shortener.domain.Url
import org.springframework.data.repository.CrudRepository

interface UrlRedisRepository: CrudRepository<Url, String> {}