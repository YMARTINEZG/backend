package us.learning.backend.repository

import us.learning.backend.domain.Customer

interface CustomerDao: MasterRepository<Customer, Long> {
}