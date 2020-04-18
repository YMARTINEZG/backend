package us.learning.backend.Services

import org.springframework.stereotype.Service
import us.learning.backend.Domain.Customer

@Service
class BussinesService {
    fun findAllCustomers(): List<Customer> {
        val newCustomer = Customer(firstName="Sandy", lastName="Lnad")
        newCustomer.addNote("Good Customer")
        newCustomer.addNote("Pay on time")
        newCustomer.email = "myaddress@test.com"

        val allCustomers = listOf(Customer(firstName = "Jimmy", lastName = "Kans"),
                                  Customer(firstName = "Juan", lastName = "Perez"), newCustomer)

        return allCustomers
    }
}