package com.application.formosity_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class loginPage : AppCompatActivity() {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var btnsignin: Button
    private lateinit var DB: DBHelper

    private fun getApplicationContect(): Any {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        username = findViewById(R.id.shapeBox1);
        password = findViewById(R.id.shapeBox2);
        btnsignin = findViewById(R.id.button2);
        DB = DBHelper(this);

        btnsignin.setOnClickListener{

            val user: String = username.text.toString();
            val pass: String = password.text.toString();

            if(user.isEmpty()||pass.isEmpty())
                Toast.makeText(this@loginPage, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            else{
                val checkuserpass: Boolean = DB.checkUsernamePassword(user, pass)
                if(checkuserpass){
                    Toast.makeText(this@loginPage, "Sign in successfull", Toast.LENGTH_SHORT).show();
                    val intent = Intent(applicationContext,CreateAccount::class.java);
                    startActivity(intent);
                }else{
                    Toast.makeText(this@loginPage, "Invalid Credentials", Toast.LENGTH_SHORT).show();

                }
            }
        }

    }
}
