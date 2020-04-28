package us.learning.backend.repository

import us.learning.backend.domain.Income

interface IncomeDao : MasterRepository<Income, Long> {
}