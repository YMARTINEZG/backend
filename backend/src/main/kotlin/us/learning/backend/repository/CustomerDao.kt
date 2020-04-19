package us.learning.backend.repository

import us.learning.backend.dao.CustomerDaoCustom
import us.learning.backend.domain.Customer

interface CustomerDao: MasterRepository<Customer, Long>, CustomerDaoCustom {
}