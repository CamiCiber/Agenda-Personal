package pe.edu.cibertec.appnote.data.database.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import pe.edu.cibertec.appnote.data.database.domain.model.Note

@Dao
interface NoteRoomDao {

    @Query("SELECT * FROM notes_table")
    fun getAllNotes(): LiveData<List<Note>>

    @Insert
    suspend fun createNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

}