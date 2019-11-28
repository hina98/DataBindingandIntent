package com.example.databindingandintent

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.databindingandintent.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val myContact = Contact("See", "0123456789")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //display ui/layout
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //setContentView(R.layout.activity_main)

        //assign attribute of local variable to ui variable
        binding.contact = myContact

        binding.buttonUpdate.setOnClickListener{
            binding.apply{
                contact?.name = "My new name"
                contact?.phone = "2222222"
                invalidateAll()//refresh the ui
            }
        }

        //create an event handler for buttonDone
        buttonSend.setOnClickListener{
            sendMessage()
        }
    }

    private fun sendMessage(){
        //create an explicit intent for the secondActivity
        val intent = Intent(this, Second::class.java)

        //prepare extra
        val message = editTextMessage.text.toString()
        intent.putExtra(EXTRA_MESSAGE, message)

        //start an activity with no return value
        //startActivity(intent)
        //start an activity with a return value(s) / result(s)
        startActivityForResult(intent, REQUEST_REPLY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == REQUEST_REPLY)
        {
            if(resultCode == Activity.RESULT_OK)
            {
                val reply = data?.getStringExtra(MainActivity.EXTRA_REPLY)
                textViewReply.text = String.format("%s : %s", getString(R.string.Reply), reply)
            }
            else
            {
                textViewReply.text = String.format("%s : %s", getString(R.string.Reply), "No reply")
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object{
        //unique val
        const val EXTRA_MESSAGE = "com.example.databindingandintent.MESSAGE"
        const val EXTRA_REPLY = "com.example.databindingandintent.REPLY"
        const val REQUEST_REPLY = 1 //integer type
    }
}
