package us.learning.backend.web.customer

import us.learning.backend.domain.Customer

data class CustomerIdentityDTO(
        val id: Long,
        val firstName: String,
        val lastName: String
) {
    constructor(customer: Customer) : this(
            customer.id!!,
            customer.firstName,
            customer.lastName
    )
}