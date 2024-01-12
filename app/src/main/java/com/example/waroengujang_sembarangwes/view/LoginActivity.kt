package com.example.waroengujang_sembarangwes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.waroengujang_sembarangwes.R
import com.example.waroengujang_sembarangwes.viewmodel.WaiterViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    private val hardcodedUsername = "sembarangwes"
    private val hardcodedPassword = "321321"
    private lateinit var waiterViewModel: WaiterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameInput = findViewById<TextInputEditText>(R.id.txtInputUsername)
        val passwordInput = findViewById<TextInputEditText>(R.id.txtInputPassword)
        val loginButton = findViewById<Button>(R.id.btnLogin)



        waiterViewModel = ViewModelProvider(this).get(WaiterViewModel::class.java)

        loginButton.setOnClickListener {
            val enteredUsername = usernameInput.text.toString()
            val enteredPassword = passwordInput.text.toString()

            waiterViewModel.getWaiter(enteredUsername).observe(this, Observer { waiter ->
                if (waiter != null && waiter.password == enteredPassword) {

                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("username",enteredUsername )
                    startActivity(intent)

                    finish()
                    Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                } else {
                    if (waiter != null) {
                        Log.e("XXX", "Username:${waiter.username}, ${waiter.password} ", )
                    }
                    else {
                        Log.e("XXX", "Waiter NULL", )
                    }
                    Snackbar.make(loginButton, "Username atau Password anda Salah", Snackbar.LENGTH_SHORT).show()
                }
            })
        }

//        loginButton.setOnClickListener {
//            val enteredUsername = usernameInput.text.toString()
//            val enteredPassword = passwordInput.text.toString()
//
//            if (enteredUsername == hardcodedUsername && enteredPassword == hardcodedPassword) {
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//                Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
//
//            } else {
//                Snackbar.make(loginButton, "Username atau Password anda Salah", Snackbar.LENGTH_SHORT).show()
//            }
//        }
    }
}