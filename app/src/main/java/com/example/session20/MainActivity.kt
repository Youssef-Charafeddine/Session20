package com.example.session20

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class MultiActivity : AppCompatActivity() {
    private lateinit var reference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db=Firebase.database
        val reference=db.getReference("Msg")
        reference.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.d("Online", "Insert key: " + dataSnapshot.key)
                Log.d("Online", "Insert val: " + dataSnapshot.value)
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                return
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                return
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                return
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("Online", "onCancelled", databaseError.toException())
                Toast.makeText(this@MultiActivity, "Comments:Failed To Load", Toast.LENGTH_SHORT)
                    .show()
            }

        })
        val btn=findViewById<Button>(R.id.btn)
         btn.setOnClickListener {
            role()


        }
    }
        private fun role() {
            val dice=mutableListOf<Int>()

            for (i in 0 until 4) {
                dice.add((1..6).random())
            }
            val timeStamp: String=SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            val username=findViewById<EditText>(R.id.username).text.toString()
            reference.child(timeStamp).setValue(username)


        }

    }


