package us.learning.backend.stream

import org.apache.logging.log4j.LogManager
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.Payload
import us.learning.backend.web.customer.CustomerIdentityDTO

@EnableBinding(CustomProcessor::class)
class CustomProcessorMessageListener {
    companion object {
        private val logger = LogManager.getLogger()
    }
    @StreamListener("profile")
    fun handleIncome(@Payload customer : CustomerIdentityDTO) {
        logger.info("Income Message need to persist customer profile into mongo db.... ")
        logger.info(customer.toString())
    }
}