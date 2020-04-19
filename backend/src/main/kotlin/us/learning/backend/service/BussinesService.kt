package us.learning.backend.service

import org.springframework.stereotype.Service
import us.learning.backend.domain.Customer
import us.learning.backend.domain.Note
import org.springframework.beans.factory.annotation.Autowired
import us.learning.backend.repository.CustomerDao

@Service
class BussinesService(@Autowired private val repository : CustomerDao) {
    fun findAllCustomers(): List<Customer> {
        if(repository.findAll().size == 0) {
            dbSetup()
        }
        return repository.findAll()
    }
    fun saveCustomer(customer: Customer): Customer {
        return repository.save(customer)
    }
    private fun dbSetup(){
        repository.save(Customer(firstName = "Jimmy", lastName = "Kans"))
        repository.save(Customer(firstName = "Juan", lastName = "Perez"))
        val newCustomer = Customer(firstName = "Sandy", lastName = "Lnad")
        newCustomer.addNote(Note("Good Customer"))
        newCustomer.addNote(Note("Pay on time"))
        newCustomer.email = "myaddress@test.com"
        repository.save(newCustomer)
    }
}