package pe.edu.cibertec.appnote.data.database.presentation.composable.main_screen

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Create
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.appnote.R
import pe.edu.cibertec.appnote.common.Constants
import pe.edu.cibertec.appnote.data.database.domain.model.Note
import pe.edu.cibertec.appnote.data.database.domain.navigation.NoteNavRoute
import pe.edu.cibertec.appnote.data.database.domain.viewmodel.MainViewModel
import pe.edu.cibertec.appnote.data.database.domain.viewmodel.MainViewModelFactory
import pe.edu.cibertec.appnote.data.database.presentation.theme.AppNoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(navController: NavHostController, viewModel: MainViewModel) {

    val context = LocalContext.current as Activity

    val notes = viewModel.getAllNotes().observeAsState(initial = listOf()).value

    AppNoteTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
        ) {
            Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
                CenterAlignedTopAppBar(title = {
                    Text(
                        text = stringResource(id = R.string.app_name)
                    )
                }, actions = {
                    IconButton(onClick = {
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(Constants.Url)
                        context.startActivity(intent)
                    }) {
                        Icon(
                            imageVector = Icons.TwoTone.Info,
                            contentDescription = stringResource(R.string.app_name)
                        )
                    }
                })
            }, floatingActionButton = {
                FloatingActionButton(onClick = {
                    viewModel.apply {
                        currentNote.value = Note()
                        isNoteExists.value = false
                    }
                    navController.navigate(NoteNavRoute.NoteDetail.route)
                }) {
                    Icon(
                        imageVector = Icons.TwoTone.Create,
                        contentDescription = stringResource(id = R.string.create_note)
                    )
                }
            }) { padding ->
                Box(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (notes.isNotEmpty()) {
                        LazyVerticalGrid(
                            modifier = Modifier.fillMaxSize(), columns = GridCells.Adaptive(160.dp)
                        ) {
                            items(notes) { note ->
                                NoteItem(note = note) {
                                    viewModel.apply {
                                        currentNote.value = note
                                        isNoteExists.value = true
                                    }
                                    navController.navigate(NoteNavRoute.NoteDetail.route)
                                }
                            }
                            item {
                                Spacer(
                                    modifier = Modifier.height(128.dp)
                                )
                            }
                        }
                    } else {
                        Text(
                            text = stringResource(R.string.create_note_suggest),
                            modifier = Modifier.padding(16.dp),
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNoteListScreen() {

    val context = LocalContext.current
    val viewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    NoteListScreen(navController = rememberNavController(), viewModel = viewModel)
}