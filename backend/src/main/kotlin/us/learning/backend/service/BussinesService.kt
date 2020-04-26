package us.learning.backend.service

import org.springframework.stereotype.Service
import us.learning.backend.domain.Customer
import us.learning.backend.domain.Note
import org.springframework.beans.factory.annotation.Autowired
import us.learning.backend.dto.CustomerCommandDTO
import us.learning.backend.exception.NotFoundException
import us.learning.backend.repository.CustomerDao
import javax.transaction.Transactional

@Service
@Transactional
class BussinesService(@Autowired private val repository : CustomerDao) {

    fun searchByStateOfAddress(search: String) : List<Customer> {
        return repository.numberOfCustomerByState(search)
    }
    fun findAllCustomers(): List<Customer> {
        if(repository.findAll().size == 0) {
            dbSetup()
        }
        return repository.findAll()
    }
    fun findCustomer(id : Long) : Customer {
        return repository.findByIdOrNull(id) ?: throw NotFoundException()
    }
    fun saveCustomer(customer: Customer): Customer {
        return repository.save(customer)
    }
    private fun dbSetup(){
        val customer1 = Customer()
        copyCommandToCustomer(CustomerCommandDTO(firstName = "Jim", lastName = "Kans"), customer1)
        repository.save(customer1)
        val customer2 = Customer()
        copyCommandToCustomer(CustomerCommandDTO(firstName = "Sandy", lastName = "Lora"), customer2)
        repository.save(customer2)
        val newCustomer= Customer()
        copyCommandToCustomer(CustomerCommandDTO(firstName = "Juan", lastName = "Perez"), newCustomer)
        newCustomer.addNote(Note("Good Customer"))
        newCustomer.addNote(Note("Pay on Time"))
        newCustomer.email = "myaddress@test.com"
        repository.save(newCustomer)
    }
    private fun copyCommandToCustomer(command: CustomerCommandDTO, customer: Customer) {
        with(customer) {
            firstName = command.firstName
            lastName = command.lastName
        }
    }
}