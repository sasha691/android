package com.example.mymagazun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import android.widget.Toast

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val linkToReg: TextView = findViewById(R.id.link_to_reg)
        val userLogin: EditText = findViewById(R.id.login_user_auth)
        val userPassword: EditText = findViewById(R.id.login_password_auth)
        val button: Button = findViewById(R.id.btn_login_auth)

        linkToReg.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if(login == "" || password == "")
                Toast.makeText(this, "Не всі поля заповнені", Toast.LENGTH_LONG).show()
            else {
                val db = DbHelper(this, null)
                val isAuth = db.getUser(login, password)

                if(isAuth){
                    Toast.makeText(this, "Користувач $login увійшов", Toast.LENGTH_LONG).show()
                    userLogin.text.clear()
                    userPassword.text.clear()

                    val intent = Intent(this, ItemsActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Користувач $login не увійшов", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}