package com.example.databindingandintent

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class Second : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
        textViewReceived.text = String.format("%s: %s", getString(R.string.Message), message)

        buttonDone.setOnClickListener{
            val intent = getIntent() //who called me
            if(!editTextReply.text.isEmpty())
            {
                val reply = editTextReply.text.toString()
                intent.putExtra(MainActivity.EXTRA_REPLY, reply)
                setResult(Activity.RESULT_OK, intent)
            }
            else
            {
                setResult(Activity.RESULT_CANCELED, intent)
            }

            //close this activity
            finish()
        }

    }
}
