package us.learning.backend.repository

import us.learning.backend.domain.Customer
import us.learning.backend.domain.IncomeSource

interface IncomeSourceDao : MasterRepository<IncomeSource, Long>{
    fun findByName(name: String): IncomeSource?
}