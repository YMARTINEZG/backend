package us.learning.backend.web.customer

import com.fasterxml.jackson.annotation.JsonUnwrapped
import us.learning.backend.domain.Customer
import us.learning.backend.domain.Note
import java.time.LocalDate

data class CustomerDTO(
        //@field:JsonUnwrapped val identity: CustomerIdentityDTO,
        val identity: CustomerIdentityDTO,
        val address: String?,
        val email: String?

){
    constructor(customer: Customer) : this(
            identity = CustomerIdentityDTO(customer),
            address = customer.address,
            email = customer.email)
}