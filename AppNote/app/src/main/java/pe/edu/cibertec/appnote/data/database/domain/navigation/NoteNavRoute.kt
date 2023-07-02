package pe.edu.cibertec.appnote.data.database.domain.navigation

sealed class NoteNavRoute(val route: String) {
    object MainScreen : NoteNavRoute(route = "main_screen")
    object NoteDetail : NoteNavRoute(route = "note_detail")
    object LoginScreen : NoteNavRoute(route = "login_screen")
    object SignUpScreen :NoteNavRoute(route = "signup_screen")
}