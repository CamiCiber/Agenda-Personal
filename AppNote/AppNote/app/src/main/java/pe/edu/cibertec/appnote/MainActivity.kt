package pe.edu.cibertec.appnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import pe.edu.cibertec.appnote.data.database.domain.navigation.NoteNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteNavHost()
        }
    }
}

