package us.learning.backend.domain

import java.util.*
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

private const val CUSTOMER_GENERATOR = "CustomerGenerator"
@Entity(name="customers")
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
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinTable(
            name = "customer_note",
            joinColumns = [JoinColumn(name = "customer_id")],
            inverseJoinColumns = [JoinColumn(name = "note_id")]
    )
    private val notes: MutableSet<Note> = HashSet()
    constructor()
    constructor(firstName: String, lastName: String) {
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
}