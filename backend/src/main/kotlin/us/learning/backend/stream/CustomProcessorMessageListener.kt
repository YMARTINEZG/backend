package us.learning.backend.stream

import org.apache.logging.log4j.LogManager
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.messaging.handler.annotation.Payload
import us.learning.backend.web.customer.CustomerIdentityDTO
import us.learning.backend.web.note.NoteDTO

@EnableBinding(CustomProcessor::class)
class CustomProcessorMessageListener {
    companion object {
        private val logger = LogManager.getLogger()
    }

    @StreamListener("profile")
    fun handleIncome(@Payload customer : CustomerIdentityDTO) {
        logger.info("Income Message consumed.... ")
        logger.info(customer.toString())
    }
}