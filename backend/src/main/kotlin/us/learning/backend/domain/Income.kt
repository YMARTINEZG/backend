package us.learning.backend.domain

import java.math.BigDecimal
import javax.persistence.*
import javax.validation.constraints.NotNull
private const val INCOME_GENERATOR = "IncomeGenerator"
@Entity
class Income {

    @Id
    @SequenceGenerator(
            name = INCOME_GENERATOR,
            sequenceName = "INCOME_SEQ",
            initialValue = 1000,
            allocationSize = 1
    )
    @GeneratedValue(generator = INCOME_GENERATOR)
    var id: Long? = null

    /**
     * The customer which benefits from this income
     */
    @ManyToOne
    @NotNull
    lateinit var customer: Customer

    /**
     * The source of the income
     */
    @ManyToOne
    @NotNull
    lateinit var source: IncomeSource

    /**
     * The monthly amount of the income
     */
    @NotNull
    lateinit var monthlyAmount: BigDecimal
    constructor()
    constructor(id: Long) {
        this.id = id
    }
}