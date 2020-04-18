package us.learning.backend.Domain

import java.util.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

class Customer {
    @NotEmpty
    lateinit var firstName: String
    @NotEmpty
    lateinit var lastName: String
    var address: String? = null
    @Email
    var email: String? = null
    private val notes: MutableSet<String> = HashSet()
    constructor()
    constructor(firstName: String, lastName: String) {
        this.firstName = firstName
        this.lastName = lastName
    }
    fun getNotes(): Set<String> {
        return Collections.unmodifiableSet(notes)
    }

    fun addNote(note: String) {
        this.notes.add(note)
    }

    fun removeNote(note: String) {
        this.notes.remove(note)
    }
}