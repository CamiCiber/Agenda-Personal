package pe.edu.cibertec.appnote.data.database.domain.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import pe.edu.cibertec.appnote.data.database.room.AppRoomDatabase
import pe.edu.cibertec.appnote.data.database.room.repository.RoomRepository
import pe.edu.cibertec.appnote.data.database.domain.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application = application) {

    private var repository: RoomRepository

    init {
        val dao = AppRoomDatabase.getInstance(application).getRoomDao()
        repository = RoomRepository(dao = dao)
    }

    fun getAllNotes() = repository.getAllNotes

    fun createNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.createNote(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(note = note) {
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    var currentNote = mutableStateOf(Note())

    var isNoteExists = mutableStateOf(false)

}