package pe.edu.cibertec.appnote.common

import pe.edu.cibertec.appnote.data.user.UserService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val URL = "https://incongruous-spiral-frog.glitch.me/"

    private var retrofit: Retrofit? = null
    private var userService: UserService? = null

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit as Retrofit
    }

    fun getUserInterface(): UserService {
        if (userService == null){
            userService = getRetrofit().create(UserService::class.java)
        }
        return userService as UserService
    }
}