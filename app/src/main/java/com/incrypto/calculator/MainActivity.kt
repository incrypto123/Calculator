package com.incrypto.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private lateinit var result: TextView
    private lateinit var newNumber : TextView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById((R.id.newNumber))

        val button0: TextView = findViewById(R.id.button0)
        val button1: TextView = findViewById(R.id.button1)
        val button2: TextView = findViewById(R.id.button2)
        val button3: TextView = findViewById(R.id.button3)
        val button4: TextView = findViewById(R.id.button4)
        val button5: TextView = findViewById(R.id.button5)
        val button6: TextView = findViewById(R.id.button6)
        val button7: TextView = findViewById(R.id.button7)
        val button8: TextView = findViewById(R.id.button8)
        val button9: TextView = findViewById(R.id.button9)
        val buttonDot: TextView = findViewById(R.id.buttonDot)
        val buttonClear:TextView =findViewById(R.id.buttonClear)
        val buttonRightBracket: TextView=findViewById(R.id.buttonRightBracket)
        val buttonLeftBracket: TextView=findViewById(R.id.buttonLeftBracket)
        val buttonBackspace: TextView=findViewById(R.id.buttonBackspace)

        //Operation TextViews
        val buttonEquals: TextView = findViewById(R.id.buttonEquals)
        val buttonPlus: TextView = findViewById(R.id.buttonPlus)
        val buttonMinus: TextView = findViewById(R.id.buttonMinus)
        val buttonMultiply: TextView = findViewById(R.id.buttonMultiply)
        val buttonDivide: TextView = findViewById(R.id.buttonDivide)


        val listener = View.OnClickListener { v ->
            val b = v as TextView
            newNumber.append(b.text)
            if(result.text.isNotEmpty())
                result.setText("")
        }
        val oplistener = View.OnClickListener { v ->
            val b = v as TextView
            if (newNumber.text.isNotEmpty())
                newNumber.append(b.text)
            else {
                newNumber.setText(result.text.toString())
                newNumber.append(b.text)
                result.setText("")
            }
        }

        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        buttonDot.setOnClickListener(listener)
        buttonMinus.setOnClickListener(oplistener)
        buttonPlus.setOnClickListener(oplistener)
        buttonDivide.setOnClickListener(oplistener)
        buttonMultiply.setOnClickListener(oplistener)
        buttonEquals.setOnClickListener(oplistener)
        buttonEquals.setOnClickListener(oplistener)
        buttonRightBracket.setOnClickListener(listener)
        buttonLeftBracket.setOnClickListener(listener)

        buttonClear.setOnClickListener { _ ->
            result.setText("")
            newNumber.setText("")

        }
        buttonBackspace.setOnClickListener({ _ ->
            val string = newNumber.text.toString()
            if (string.isNotEmpty()){
                newNumber.text = string.substring(0,string.length -1)
            }
        })

       


        buttonEquals.setOnClickListener({ _ ->
            try{
                val expression =ExpressionBuilder(newNumber.text.toString()).build()
                val resultValue =expression.evaluate()
                val longResult = resultValue.toLong()
                if(resultValue==longResult.toDouble())
                    result.text=longResult.toString()
                else
                    result.text= resultValue.toString()
                    newNumber.setText("")

            }catch(e:Exception){

                Log.d("buttonEquals","message : "+e.message)
                if(e.message=="Division by zero!")
                    result.text="Infinity"
                    newNumber.setText("")

            }
        })

    }


}

