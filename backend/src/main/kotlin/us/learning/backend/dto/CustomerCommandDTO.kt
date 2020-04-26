package us.learning.backend.dto

import javax.validation.constraints.NotEmpty

class CustomerCommandDTO(
        @field:NotEmpty val firstName: String,
        @field:NotEmpty val lastName: String)