package us.learning.backend.stream

import org.apache.logging.log4j.LogManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.integration.support.MessageBuilder
import us.learning.backend.service.ProcessorService
import us.learning.backend.web.customer.CustomerDTO
import us.learning.backend.web.note.NoteDTO
@EnableBinding(Sink::class)
class ProcessorMessageListener(val processorService : ProcessorService) {
    companion object {
        private val logger = LogManager.getLogger()
    }
    data class NoteCustomer(
            val customerId : String,
            val text : String
    )
    @StreamListener(Sink.INPUT)
    fun handle(note : NoteCustomer) {
        val customer = processorService.handleCustomerMessage(NoteDTO(customerId= note.customerId.toLong(),noteText = note.text ))
        logger.info(customer.toString())
    }
}