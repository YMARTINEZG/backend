package us.learning.backend.stream

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.SubscribableChannel

interface CustomProcessor {

    @Input
    fun profile() : SubscribableChannel
    @Output
    fun income() : MessageChannel
}