package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.*;

public class MainActivity extends AppCompatActivity {


    private EditText etResultCalc;
    private double result;
    private String resultText;
    private boolean existFirstFactor;
    private double firstFactor;
    private int operator;
    private boolean isChanged;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        existFirstFactor=false;
        isChanged=false;
        firstFactor=0.0;
        result=0.0;
        resultText="0";
        operator=0;
        etResultCalc= findViewById(R.id.etResultCalc);
        //txtResult.setText("0");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0;
        Button btnPlus, btnMin, btnDiv, btnMul, btnEq, btnComma, btnAC;

        btn1= findViewById(R.id.btn1);
        btn2= findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4= findViewById(R.id.btn4);
        btn5= findViewById(R.id.btn5);
        btn6= findViewById(R.id.btn6);
        btn7= findViewById(R.id.btn7);
        btn8= findViewById(R.id.btn8);
        btn9= findViewById(R.id.btn9);
        btn0= findViewById(R.id.btn0);

        btn1.setOnClickListener(numberHandler);
        btn2.setOnClickListener(numberHandler);
        btn3.setOnClickListener(numberHandler);
        btn4.setOnClickListener(numberHandler);
        btn5.setOnClickListener(numberHandler);
        btn6.setOnClickListener(numberHandler);
        btn7.setOnClickListener(numberHandler);
        btn8.setOnClickListener(numberHandler);
        btn9.setOnClickListener(numberHandler);
        btn0.setOnClickListener(numberHandler);

        btnPlus= findViewById(R.id.btnPlus);
        btnMin= findViewById(R.id.btnMin);
        btnMul= findViewById(R.id.btnMul);
        btnDiv= findViewById(R.id.btnDiv);
        btnEq=  findViewById(R.id.btnEqual);
        btnAC= findViewById(R.id.btnAC);

        btnPlus.setOnClickListener(operatorHandler);
        btnMin.setOnClickListener(operatorHandler);
        btnMul.setOnClickListener(operatorHandler);
        btnDiv.setOnClickListener(operatorHandler);
        btnEq.setOnClickListener(operatorHandler);
        btnAC.setOnClickListener(AcHandler);

        btnComma= findViewById(R.id.btnComma);
        btnComma.setOnClickListener(commaHandler);


    }

    View.OnClickListener numberHandler= new View.OnClickListener() {
        public void onClick(View v) {
            Button btnClicked;
            btnClicked = findViewById(v.getId());
            addNumber(btnClicked.getText().toString());
            isChanged=true;


        }
    };

    public void addNumber(String strNumber){

        etResultCalc= findViewById(R.id.etResultCalc);

       // Toast.makeText(this, "resultText="+ resultText, Toast.LENGTH_LONG).show();

        if(resultText.compareTo("0")==0)
            resultText=strNumber;
        else
            resultText =resultText + strNumber;

        /*if(etResultCalc.getText().toString().compareTo("0")==0)
            resultText=strNumber;
        else
            resultText =resultText + strNumber;*/

        etResultCalc.setText(resultText);
    }

    View.OnClickListener operatorHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.btnPlus:addOperator(1);break;
                case R.id.btnMin:addOperator(2);break;
                case R.id.btnMul:addOperator(3);break;
                case R.id.btnDiv:addOperator(4);break;
                case R.id.btnEqual:addOperator(5);break;
            }

        }
    };
    public void addOperator(int op){


        etResultCalc= findViewById(R.id.etResultCalc);
        //Toast.makeText(this, "operador="+ String.valueOf(op), Toast.LENGTH_SHORT).show();
        //Formato doble

            if(!existFirstFactor)
            {firstFactor= Double.valueOf(getDoubleFormat(etResultCalc.getText().toString()));
                operator=op;
                resultText="0";
                isChanged=false;
                existFirstFactor=true;
            }
            else
            { if(isChanged) {   //Ya había un factor
                //Haga el cálculo con el operador que había
                firstFactor = calculate(Double.valueOf(getDoubleFormat(etResultCalc.getText().toString())));
                etResultCalc.setText(getStringFormat(firstFactor));

            }
                    resultText="0";
                    isChanged=false;
                    operator=op;
                }
            if(op==5)
                operator=0;

            }

    View.OnClickListener AcHandler= new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            etResultCalc= findViewById(R.id.etResultCalc);
            etResultCalc.setText("0");
        }
    };

    View.OnClickListener commaHandler= new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            addComma();


        }
    };

        public void addComma(){
            etResultCalc= findViewById(R.id.etResultCalc);

            int existComma=0;
            existComma=resultText.indexOf(',');
            //Toast.makeText(this, "IndexOf: "+String.valueOf(existComma), Toast.LENGTH_SHORT).show();
            if(resultText.indexOf(',')==-1) {
                resultText = resultText + ',';
                etResultCalc.setText(resultText);
            }
        }

    public double calculate(double secondFactor){
        double result=0.0;
        switch (operator)
        { case 1: result= firstFactor + secondFactor; break;
          case 2: result= firstFactor - secondFactor; break;
            case 3: result= firstFactor * secondFactor; break;
            case 4: result= firstFactor / secondFactor; break;

        }
        return result;
    }

    String getStringFormat(double res)
    {
        res=Math.round(res*100.0)/100.0;
        String strValue=String.valueOf(res);

        String [] strArray= strValue.split("\\.");

        //Toast.makeText(this, strArray[0], Toast.LENGTH_LONG).show();

        if(Integer.valueOf(strArray[1])==0)
             return strArray[0];
        else
             return(strArray[0]+","+strArray[1]);

    }

    String getDoubleFormat(String strInput)
    {  return strInput.replace(',','.');

    }
}