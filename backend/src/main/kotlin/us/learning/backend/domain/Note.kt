package us.learning.backend.domain

import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
private const val NOTE_GENERATOR = "NoteGenerator"

@Entity(name="notes")
class Note {
    @Id
    @SequenceGenerator(name = NOTE_GENERATOR, sequenceName = "NOTE_SEQ", initialValue = 1000, allocationSize = 1)
    @GeneratedValue(generator = NOTE_GENERATOR)
    var id: Long? = null

    @NotEmpty
    lateinit var text: String

    @NotNull
    var creationInstant = Instant.now()
    constructor()
    constructor(text: String) {
        this.text = text
    }
}