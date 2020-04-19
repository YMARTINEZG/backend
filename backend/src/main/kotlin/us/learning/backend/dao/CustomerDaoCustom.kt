package us.learning.backend.dao

import us.learning.backend.domain.Customer

interface CustomerDaoCustom {
    fun numberOfCustomerByState(state: String): List<Customer>
}