package pe.edu.cibertec.appnote.data.user.repository

import pe.edu.cibertec.appnote.common.ApiClient
import pe.edu.cibertec.appnote.data.user.UserService
import pe.edu.cibertec.appnote.data.user.model.User
import pe.edu.cibertec.newlogin.util.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
    private val userService: UserService = ApiClient.getUserInterface()
) {
    fun login(username: String, password: String, callback: (Result<Boolean>) -> Unit) {


        userService.login(username, password).enqueue(object : Callback<List<User>> {

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                if (!response.isSuccessful) {
                    callback(Result.Error("Unsuccessful response"))
                    return
                }

                if (response.body() == null) {
                    callback(Result.Error("No data found"))
                    return
                }

                if (response.body()!!.isEmpty()) {
                    callback(Result.Error("Wrong credentials"))
                    return
                }
                callback(Result.Success(true))
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback(Result.Error("Login not available"))
            }

        })
    }

    private fun validateUser(username: String, callback: (Result<Boolean>) -> Unit) {

        userService.validateUser(username).enqueue(object : Callback<List<User>> {

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {

                if (!response.isSuccessful) {
                    callback(Result.Error("Unsuccessful response"))
                    return
                }

                if (response.body() == null) {
                    callback(Result.Error("No data found"))
                    return
                }

                if (response.body()!!.isNotEmpty()) {
                    callback(Result.Error("Username already registered"))
                    return
                }

                callback(Result.Success(true))

            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                callback(Result.Error("Validate not available"))
            }
        })
    }

    fun createUser(
        name: String,
        lastName: String,
        email: String,
        username: String,
        password: String,
        confirmPassword: String,
        callback: (Result<Boolean>) -> Unit
    ) {

        if (password != confirmPassword) {
            callback(Result.Error("Passwords don't match"))
            return
        }

        validateUser(username) { result ->

            if (result is Result.Error) {
                callback(Result.Error(result.message.toString()))
                return@validateUser
            }

            userService.createUser(User(name,lastName,email,username,password)).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (!response.isSuccessful) {
                        callback(Result.Error("Unsuccessful response"))
                        return
                    }

                    if (response.body() == null) {
                        callback(Result.Error("No data found"))
                        return
                    }
                    callback(Result.Success(true))

                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    callback(Result.Error("Create user not available"))
                }
            })

        }
    }
}