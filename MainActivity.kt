package com.example.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var b1 : Button
    private lateinit var b2 : Button
    private lateinit var b3 : Button
    private lateinit var b4 : Button
    private lateinit var b5 : Button
    private lateinit var b6 : Button
    private lateinit var b7 : Button
    private lateinit var b8 : Button
    private lateinit var b9 : Button
    private lateinit var b0 : Button

    private lateinit var b_equal : Button
    private lateinit var b_multiply : Button
    private lateinit var b_add : Button
    private lateinit var b_division : Button
    private lateinit var b_sub : Button
    private lateinit var b_dot : Button
    private lateinit var b_para2 : Button
    private lateinit var b_clear : Button
    private lateinit var b_para1 : Button

    private lateinit var t1 : TextView
    private lateinit var t2: TextView
    private var ACTION : Char = ' '

    private var val1 = Double.NaN
    private var val2 = 0.0

    private val ADDITION = '+'
    private val SUBTRACTION = '-'
    private val MULTIPLY = '*'
    private val DIVISION = '/'
    private val EQUALS = '='
    private val EXTRA = '@'
    private val MODULUS = '%'


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewSetUp()

        b1.setOnClickListener{
            ifErrorOnOutput()
            exceedlength()

            t1.text=t1.text.toString()+"1"
        }

        b2.setOnClickListener{
            ifErrorOnOutput()
            exceedlength()

            t1.text=t1.text.toString()+"2"
        }

        b3.setOnClickListener{
            ifErrorOnOutput()
            exceedlength()

            t1.text=t1.text.toString()+"3"
        }

        b4.setOnClickListener{
            ifErrorOnOutput()
            exceedlength()

            t1.text=t1.text.toString()+"4"
        }

        b5.setOnClickListener{
            ifErrorOnOutput()
            exceedlength()

            t1.text=t1.text.toString()+"5"
        }

        b6.setOnClickListener{
            ifErrorOnOutput()
            exceedlength()

            t1.text=t1.text.toString()+"6"
        }

        b7.setOnClickListener{
            ifErrorOnOutput()
            exceedlength()

            t1.text=t1.text.toString()+"7"
        }

        b8.setOnClickListener{
            ifErrorOnOutput()
            exceedlength()

            t1.text=t1.text.toString()+"8"
        }

        b9.setOnClickListener{
            ifErrorOnOutput()
            exceedlength()

            t1.text=t1.text.toString()+"9"
        }

        b0.setOnClickListener{
            ifErrorOnOutput()
            exceedlength()

            t1.text=t1.text.toString()+"0"
        }

        b_dot.setOnClickListener{
            exceedlength()
            t1.text = t1.text.toString()+"."
        }

        b_para1.setOnClickListener{
            if(t1.text.isNotEmpty()){
                ACTION = MODULUS  // Corrected from ADDITION to MODULUS
                operation()
                if(!ReallyDecimal()){
                    t2.text = "val1%"
                } else {
                    t2.text = "${val1.toInt()}%"
                }
                t1.text=""
            } else {
                t2.text="Error"
            }
        }

        b_para2.setOnClickListener{
            if(!t2.text.toString().isNotEmpty() || !t1.text.toString().isNotEmpty()){
                val1 = t1.text.toString().toDouble()
                ACTION = EXTRA
                t2.text = "-${t1.text.toString()}"
                t1.text = ""
            }else{
                t2.text = "Error"
            }
        }

        b_equal.setOnClickListener{
            if(t1.text.isNotEmpty()){
                operation()
                ACTION = EQUALS
                if(!ReallyDecimal()){
                    t2.text = "${val1.toString()}"
                }else{
                    t2.text = "${val1.toInt()}"
                }
                t1.text=""
            }else{
                t2.text = "Error"
            }
        }

        b_add.setOnClickListener{
            if(t1.text.isNotEmpty()){
                ACTION = ADDITION
                operation()
                if(!ReallyDecimal()){
                    t2.text = "val1+"
                }else{
                    t2.text = "${val1.toInt()}+"
                }
                t1.text=""
            }else{
                t2.text="Error"
            }
        }

        b_multiply.setOnClickListener{
            if(t1.text.isNotEmpty()){
                ACTION = MULTIPLY
                operation()
                if(!ReallyDecimal()){
                    t2.text = "val1*"
                }else{
                    t2.text = "${val1.toInt()}*"
                }
                t1.text=""
            }else{
                t2.text="Error"
            }
        }

        b_sub.setOnClickListener{
            if(t1.text.isNotEmpty()){
                ACTION = SUBTRACTION
                operation()
                if(!ReallyDecimal()){
                    t2.text = "val1-"
                }else{
                    t2.text = "${val1.toInt()}-"
                }
                t1.text=""
            }else{
                t2.text="Error"
            }
        }

        b_division.setOnClickListener{
            if(t1.text.isNotEmpty()){
                ACTION = DIVISION
                operation()
                if(!ReallyDecimal()){
                    t2.text = "val1/"
                }else{
                    t2.text = "${val1.toInt()}/"
                }
                t1.text=""
            }else{
                t2.text="Error"
            }
        }

        b_clear.setOnClickListener{
            if(t1.text.isNotEmpty()){
                val name = t1.text.toString()
                t1.text = name.subSequence(0,name.length-1)
            }else{
                val1 = Double.NaN
                val2 = Double.NaN

                t1.text = ""
                t2.text = ""
            }
        }

        b_clear.setOnLongClickListener{
            val1 = Double.NaN
            val2 = Double.NaN
            t1.text = ""
            t2.text = ""
            true
        }
    }

    private fun viewSetUp(){
        b1 = findViewById(R.id.button1)
        b2 = findViewById(R.id.button2)
        b3 = findViewById(R.id.button3)
        b4 = findViewById(R.id.button4)
        b5 = findViewById(R.id.button5)
        b6 = findViewById(R.id.button6)
        b7 = findViewById(R.id.button7)
        b8 = findViewById(R.id.button8)
        b9 = findViewById(R.id.button9)
        b0 = findViewById(R.id.button)

        b_clear = findViewById(R.id.button19)
        b_para1 = findViewById(R.id.button17)
        b_para2 = findViewById(R.id.button18)
        b_equal = findViewById(R.id.button12)
        b_division = findViewById(R.id.button16)
        b_multiply = findViewById(R.id.button15)
        b_add = findViewById(R.id.button13)
        b_sub = findViewById(R.id.button14)
        b_dot = findViewById(R.id.button11)

        t1 = findViewById(R.id.input)
        t2 = findViewById(R.id.output)
    }

    private fun operation(){
        if(!val1.isNaN()){
            if(t2.text.toString().startsWith("-")){
                val1 = -val1
            }
            val2 = t1.text.toString().toDouble()
            when(ACTION){
                ADDITION -> val1+=val2
                SUBTRACTION -> val1-=val2
                MULTIPLY -> val1*=val2
                DIVISION -> val1/=val2
                MODULUS -> val1%=val2
                EXTRA -> val1 = -val2
                EQUALS -> {

                }
            }
        }else{
            val1 = t1.text.toString().toDouble()
        }
    }

    private fun ReallyDecimal():Boolean{
        return val1 == val1.toInt().toDouble()
    }

    private fun exceedlength(){
        if(t1.text.toString().length > 10){
            t1.setTextSize(TypedValue.COMPLEX_UNIT_SP,20f)
        }
    }

    private fun ifErrorOnOutput(){
        if(t2.text.toString() == "Error"){
            t2.text = ""
        }
    }
}