package us.learning.backend.dto

import java.math.BigDecimal
import javax.validation.constraints.NotEmpty

class IncomeDTO(
   @field:NotEmpty val customerId: Long,
   @field:NotEmpty val source: IncomeSourceDTO,
   @field:NotEmpty val amount: BigDecimal
)