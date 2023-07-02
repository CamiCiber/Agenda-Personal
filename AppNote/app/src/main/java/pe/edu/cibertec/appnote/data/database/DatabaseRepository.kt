package pe.edu.cibertec.appnote.data.database

import androidx.lifecycle.LiveData
import pe.edu.cibertec.appnote.data.database.domain.model.Note

interface DatabaseRepository {

    val getAllNotes: LiveData<List<Note>>

    suspend fun createNote(note: Note, onSuccess: () -> Unit)

    suspend fun updateNote(note: Note, onSuccess: () -> Unit)

    suspend fun deleteNote(note: Note, onSuccess: () -> Unit)

}