package us.learning.backend.web.customer

import java.time.LocalDate
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern

const val ZIPCODE_NUMBER_REGEXP = "\\d{5}|^$"
class CustomerCommandDTO(
        @field:NotEmpty val firstName: String,
        @field:NotEmpty val lastName: String,
        val streetName: String?,
        val cityName: String?,
        val stateName: String?,
        @field:Pattern(regexp = ZIPCODE_NUMBER_REGEXP) val zipCode: String?,
        val email: String?
)