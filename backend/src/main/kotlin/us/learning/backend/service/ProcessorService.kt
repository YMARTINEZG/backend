package us.learning.backend.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import us.learning.backend.domain.Customer
import us.learning.backend.domain.Note
import us.learning.backend.exception.NotFoundException
import us.learning.backend.repository.CustomerDao
import us.learning.backend.web.note.NoteDTO
import javax.transaction.Transactional

@Service
@Transactional
class ProcessorService(@Autowired private val repository : CustomerDao) {
    fun handleCustomerMessage(noteDTO: NoteDTO) : Customer {
        val customer = repository.findByIdOrNull(noteDTO.customerId) ?: throw NotFoundException()
        val note = Note()
        note.text = noteDTO.noteText
        customer.addNote(note)
        repository.flush()
        return customer
    }
}