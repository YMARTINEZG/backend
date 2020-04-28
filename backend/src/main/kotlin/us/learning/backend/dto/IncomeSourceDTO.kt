package us.learning.backend.dto

import java.math.BigDecimal
import javax.validation.constraints.NotEmpty

class IncomeSourceDTO(
        @field:NotEmpty val name: String,
        val maxMonthlyAmount: BigDecimal? = null
)