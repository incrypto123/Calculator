package com.incrypto.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    private lateinit var result: TextView
    private lateinit var newNumber : TextView
    private  val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }


    //Variables to hold valures for calculation
    private var operand1 : Double? =null
    private var operand2 : Double=0.0
    private var pendingOperation = "="

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

        buttonClear.setOnClickListener( { v ->
            result.setText("")
            newNumber.setText("")
            displayOperation.setText("")
        }
        )

        val oplistener = View.OnClickListener { v ->
            val op = (v as TextView).text.toString()
            val value = newNumber.text.toString()
            if (value.isNotEmpty()) {
                performOperation(op, value)
            }
            pendingOperation = op
            displayOperation.text = pendingOperation

        }


        buttonEquals.setOnClickListener(oplistener)
        buttonMinus.setOnClickListener(oplistener)
        buttonPlus.setOnClickListener(oplistener)
        buttonDivide.setOnClickListener(oplistener)
        buttonMultiply.setOnClickListener(oplistener)
        buttonEquals.setOnClickListener(oplistener)
        buttonEquals.setOnClickListener(oplistener)
    }
        private fun performOperation(operation :String,value :String) {
            if (operand1 == null) {
                operand1 = value.toDouble()
            } else {
                operand2 = value.toDouble()


                when (pendingOperation) {
                    "=" -> operand1 = operand2
                    "/" -> if (operand1 == 0.0) {
                        operand1 = Double.NaN
                    } else {
                        operand1 = operand1!! / operand2
                    }
                    "*" -> operand1 = operand1!! * operand2
                    "+" -> operand1 = operand1!! + operand2
                    "-" -> operand1 = operand1!! + operand2
                }
            }
            result.setText(operand1.toString())
            newNumber.setText("")

        }














}

