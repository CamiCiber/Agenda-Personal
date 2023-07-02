package pe.edu.cibertec.appnote.data.database.domain.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.edu.cibertec.appnote.data.database.domain.viewmodel.MainViewModel
import pe.edu.cibertec.appnote.data.database.domain.viewmodel.MainViewModelFactory
import pe.edu.cibertec.appnote.data.user.composable.login_screen.Login
import pe.edu.cibertec.appnote.data.database.presentation.composable.main_screen.NoteListScreen
import pe.edu.cibertec.appnote.data.database.presentation.composable.note_detail.NoteDetail
import pe.edu.cibertec.appnote.data.user.composable.signup_screen.Register

@Composable
fun NoteNavHost() {

    val context = LocalContext.current
    val navController = rememberNavController()

    val viewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    NavHost(
        navController = navController,
        startDestination = NoteNavRoute.LoginScreen.route
    ) {
        composable(NoteNavRoute.MainScreen.route) {
            NoteListScreen(navController, viewModel)
        }
        composable(NoteNavRoute.NoteDetail.route) {
            NoteDetail(navController, viewModel)
        }
        composable(NoteNavRoute.LoginScreen.route) {
            Login(navController)
        }
        composable(NoteNavRoute.SignUpScreen.route) {
            Register(navController)
        }

    }

}