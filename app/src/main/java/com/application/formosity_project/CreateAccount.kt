package com.application.formosity_project

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class CreateAccount : AppCompatActivity() {
    private lateinit var NameUsername: EditText
    private lateinit var Email: EditText
    private lateinit var Confirmpassword: EditText
    private lateinit var Password: EditText
    private lateinit var SignIn: Button
    private lateinit var DB: DBHelper


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        NameUsername = findViewById(R.id.editText1);
        Email = findViewById(R.id.shapeBox1);
        Confirmpassword = findViewById(R.id.shapeBox2);
        Password = findViewById(R.id.shapeBox3);
        SignIn = findViewById(R.id.button2);
        DB = DBHelper(this)

        SignIn.setOnClickListener(View.OnClickListener() {
            val user: String = NameUsername.text.toString();
            val pass: String = Password.text.toString();
            val confirmPass: String = Confirmpassword.text.toString();

            if (user.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()){
                Toast.makeText(this@CreateAccount, "Please enter all the fields", Toast.LENGTH_SHORT).show()
            }
            else{
                if(pass == confirmPass) {
                    val checkUser: Boolean = DB.checkUsername(user)
                    if(!checkUser) {
                        val insert: Boolean = DB.insertData(user, pass)
                        if(insert){
                            Toast.makeText(this@CreateAccount, "Rgistered successfully", Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext,HomePage::class.java)
                            startActivity(intent);
                        }else{
                            Toast.makeText(this@CreateAccount, "Registration failed", Toast.LENGTH_SHORT).show()
                        }
                        Toast.makeText(this@CreateAccount, "User already exists!", Toast.LENGTH_SHORT).show()
                    }
                }    else{
                    Toast.makeText(this@CreateAccount, "Password not matching", Toast.LENGTH_SHORT).show()
                }
            }
        })


    }
}
