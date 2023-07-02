package pe.edu.cibertec.appnote.data.database.room.repository

import androidx.lifecycle.LiveData
import pe.edu.cibertec.appnote.data.database.DatabaseRepository
import pe.edu.cibertec.appnote.data.database.room.dao.NoteRoomDao
import pe.edu.cibertec.appnote.data.database.domain.model.Note

class RoomRepository(private val dao: NoteRoomDao) : DatabaseRepository {
    override val getAllNotes: LiveData<List<Note>>
        get() = dao.getAllNotes()

    override suspend fun createNote(note: Note, onSuccess: () -> Unit) {
        dao.createNote(note = note)
        onSuccess()
    }

    override suspend fun updateNote(note: Note, onSuccess: () -> Unit) {
        dao.updateNote(note = note)
        onSuccess()
    }

    override suspend fun deleteNote(note: Note, onSuccess: () -> Unit) {
        dao.deleteNote(note = note)
        onSuccess()
    }
}