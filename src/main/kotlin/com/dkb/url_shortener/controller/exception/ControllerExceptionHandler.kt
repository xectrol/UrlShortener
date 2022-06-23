package com.dkb.url_shortener.controller.exception

import com.dkb.url_shortener.service.exception.UrlNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ControllerExceptionHandler {
    @ExceptionHandler(value = [UrlNotFoundException::class])
    fun doHandleExceptions(ex: Exception): ResponseEntity<Body> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Body(ex.message.orEmpty()))
    }

    data class Body(val message: String)
}