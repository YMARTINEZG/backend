package us.learning.backend.stream

import org.apache.logging.log4j.LogManager
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.cloud.stream.messaging.Sink
import us.learning.backend.service.CustomerService
import us.learning.backend.web.note.NoteDTO
@EnableBinding(Sink::class)
class NotesMessageListener(val customerService : CustomerService) {
    companion object {
        private val logger = LogManager.getLogger()
    }
    data class NoteCustomer(
            val customerId : String,
            val text : String
    )
    @StreamListener(Sink.INPUT)
    fun handle(note : NoteCustomer) {
        val customer = customerService.handleCustomerMessage(NoteDTO(customerId= note.customerId.toLong(),noteText = note.text ))
        logger.info(customer.toString())
    }
}