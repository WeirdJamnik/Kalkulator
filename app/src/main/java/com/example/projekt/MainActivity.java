package com.example.projekt;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText pokaz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokaz = findViewById(R.id.input);
        pokaz.setShowSoftInputOnFocus(false);

        pokaz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.pokaż).equals(pokaz.getText().toString())){
                    pokaz.setText("");
                }
            }
        });

    }

    private void updateText(String strAdd){
        String oldString = pokaz.getText().toString();
        int cursorPosition = pokaz.getSelectionStart();
        String leftString = oldString.substring(0, cursorPosition);
        String rightString = oldString.substring(cursorPosition);
        if (getString(R.string.pokaż).equals(pokaz.getText().toString())){
            pokaz.setText(strAdd);
            pokaz.setSelection(cursorPosition + 1);
        }
        else {
            pokaz.setText(String.format("%s%s%s", leftString, strAdd, rightString));
            pokaz.setSelection(cursorPosition + 1);
        }
    }

    public void button0(View view){
        updateText("0");
    }
    public void button1(View view){
        updateText("1");

    }
    public void button2(View view){
        updateText("2");
    }
    public void button3(View view){
        updateText("3");
    }
    public void button4(View view){
        updateText("4");
    }
    public void button5(View view){
        updateText("5");
    }
    public void button6(View view){
        updateText("6");
    }
    public void button7(View view){
        updateText("7");
    }
    public void button8(View view){
        updateText("8");
    }
    public void button9(View view){
        updateText("9");
    }
    public void buttonWyczyść(View view){
        pokaz.setText("");
    }
    public void buttonPotęgowanie(View view){
        updateText("^");
    }
    public void buttonNawiasy(View view){
        int cursorPosition = pokaz.getSelectionStart();
        int nawiasOtwarty = 0;
        int nawiasZamkniety = 0;
        int textLength = pokaz.getText().length();

        for (int i = 0; i < cursorPosition; i++){
            if(pokaz.getText().toString().substring(i, i+1).equals("(")){
                nawiasOtwarty +=1;
            }
            if(pokaz.getText().toString().substring(i, i+1).equals(")")){
                nawiasZamkniety +=1;
            }
        }

        if (nawiasOtwarty == nawiasZamkniety || pokaz.getText().toString().substring(textLength-1, textLength).equals(nawiasOtwarty)){
            updateText("(");
        }
        else if (nawiasZamkniety < nawiasOtwarty && !pokaz.getText().toString().substring(textLength-1, textLength).equals(nawiasOtwarty)){
            updateText(")");
        }
        pokaz.setSelection(cursorPosition+1);
    }
    public void buttonDzielenie(View view){
        updateText("/");
    }
    public void buttonMnożenie(View view){
        updateText("x");
    }
    public void buttonDodawanie(View view){
        updateText("+");
    }
    public void buttonOdejmowanie(View view){
        updateText("-");
    }
    public void buttonPlusMinus(View view){
        updateText("-");
    }
    public void buttonKropka(View view){
        updateText(".");
    }
    public void buttonWynik(View view){
        String userExpression = pokaz.getText().toString();
         userExpression = userExpression.replaceAll("x","*");
         Expression expression = new Expression(userExpression);
         String wynik = String.valueOf(expression.calculate());
         pokaz.setText(wynik);
         pokaz.setSelection(wynik.length());
    }
    public void buttonUsuń(View view){
        int cursorPosition = pokaz.getSelectionStart();
        int textLength = pokaz.getText().length();

        if(cursorPosition != 0 && textLength != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) pokaz.getText();
            selection.replace(cursorPosition - 1,cursorPosition,"");
            pokaz.setText(selection);
            pokaz.setSelection(cursorPosition - 1);
        }
    }


}