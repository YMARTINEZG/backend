package us.learning.backend.domain

import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

private const val INCOME_SOURCE_GENERATOR = "IncomeSourceGenerator"
@Entity
class IncomeSource {

    @Id
    @SequenceGenerator(
            name = INCOME_SOURCE_GENERATOR,
            sequenceName = "INCOME_SOURCE_SEQ",
            initialValue = 1000,
            allocationSize = 1
    )
    @GeneratedValue(generator = INCOME_SOURCE_GENERATOR)
    var id: Long? = null

    /**
     * The name of the source of income (APL, Agirc/Arrco, etc.)
     */
    @NotEmpty
    lateinit var name: String

    /**
     * The maximum monthly amount (in euros) that the income source can give as income. Null if no known maximum.
     */
    var maxMonthlyAmount: BigDecimal? = null

    constructor()

    constructor(id: Long, name: String) {
        this.id = id
        this.name = name
        this.maxMonthlyAmount = BigDecimal.valueOf(0.0)
    }

}