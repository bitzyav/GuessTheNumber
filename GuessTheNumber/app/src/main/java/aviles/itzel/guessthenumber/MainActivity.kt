package aviles.itzel.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var maxNum = 0
    var minNum = 100
    var num: Int = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessings: TextView = findViewById(R.id.txtGuessings)
        val down: Button = findViewById(R.id.btnDown)
        val up: Button = findViewById(R.id.btnUp)
        val generate: Button = findViewById(R.id.btnGenerate)
        val guessed: Button = findViewById(R.id.btnGuessed)

        generate.setOnClickListener{
            num = Random.nextInt(minNum, maxNum)
            guessings.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener{
            minNum = num
            if(checkLimits()){
                num = Random.nextInt(minNum, maxNum)
                guessings.setText(num.toString())
            }else {
                guessings.setText("You won!!")
            }

        }

        down.setOnClickListener{
            maxNum = num
            if(checkLimits()){
                num = Random.nextInt(minNum, maxNum)
                guessings.setText(num.toString())
            }else {
                guessings.setText("You won!!")
            }
        }

        guessed.setOnClickListener {
            if(!won) {
                guessings.setText("I guessed it, your number is $num")
                guessed.setText("Play again")
            }else {
                generate.visibility = View.VISIBLE
                guessings.setText("Tap on generate to start")
                guessed.setText("Guessed")
                guessed.visibility = View.GONE
                resetValues()
            }
        }
    }

    fun checkLimits(): Boolean{
        return minNum != maxNum
    }

    fun resetValues(){
        minNum = 0
        maxNum = 0
        num = 0
        won = false
    }
}