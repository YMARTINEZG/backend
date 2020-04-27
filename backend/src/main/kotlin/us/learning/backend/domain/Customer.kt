package us.learning.backend.domain

import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

private const val CUSTOMER_GENERATOR = "CustomerGenerator"
@Entity
class Customer {
    @Id
    @SequenceGenerator(name = CUSTOMER_GENERATOR, sequenceName = "CUSTOMER_SEQ", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(generator = CUSTOMER_GENERATOR)
    var id: Long? = null
    @NotEmpty
    lateinit var firstName: String
    @NotEmpty
    lateinit var lastName: String
    var address: String? = null
    @Email
    var email: String? = null

    @OneToMany(fetch = FetchType.LAZY,cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinTable(
            name = "customer_note",
            joinColumns = [JoinColumn(name = "customer_id")],
            inverseJoinColumns = [JoinColumn(name = "note_id")]
    )
    private val notes: MutableSet<Note> = HashSet()

    @OneToMany(mappedBy = "customer", cascade = [CascadeType.ALL], orphanRemoval = true)
    private val incomes: MutableSet<Income> = HashSet()

    constructor()
    constructor(id: Long) {
        this.id = id
    }
    constructor(id:Long,firstName: String, lastName: String) {
        this.id = id
        this.firstName = firstName
        this.lastName = lastName
    }
    fun getNotes(): Set<Note> {
        return Collections.unmodifiableSet(notes)
    }
    fun addNote(note: Note) {
        this.notes.add(note)
    }
    fun removeNote(note: Note) {
        this.notes.remove(note)
    }
    fun addIncome(income: Income) {
        income.customer = this
        this.incomes.add(income)
    }

    fun removeIncome(income: Income) {
        this.incomes.remove(income)
    }

    fun getIncomes(): Set<Income> {
        return Collections.unmodifiableSet(incomes)
    }

    override fun toString(): String {
        return "Customer(id=$id, firstName='$firstName', lastName='$lastName', address=$address, email=$email)"
    }
}