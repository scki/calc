package com.example.calc_307_pie_rybakov;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Пишем называние
 */
public class MainActivity extends AppCompatActivity {
    TextView mDisplay;
    TextView mDisplay2;
    TextView mDisplay3;
    TextView mDisplay4;

    Button mBtn0;
    Button mBtn1;
    Button mBtn2;
    Button mBtn3;
    Button mBtn4;
    Button mBtn5;
    Button mBtn6;
    Button mBtn7;
    Button mBtn8;
    Button mBtn9;
    Button mBack;
    Button mClear;
    Button mPlus;
    Button mDivide;
    Button mMultiply;
    Button mMinus;
    Button mChangeSin;
    Button mComma;
    Button mResult;

    float mValue = 0;
    String mOperator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDisplay = findViewById(R.id.Display);
        mDisplay2 = findViewById(R.id.display2);
        mDisplay3 = findViewById(R.id.display3);
        mDisplay4 = findViewById(R.id.display4);

        mBtn0 = findViewById(R.id.Btn0);
        mBtn1 = findViewById(R.id.Btn1);
        mBtn2 = findViewById(R.id.Btn2);
        mBtn3 = findViewById(R.id.Btn3);
        mBtn4 = findViewById(R.id.Btn4);
        mBtn5 = findViewById(R.id.Btn5);
        mBtn6 = findViewById(R.id.Btn6);
        mBtn7 = findViewById(R.id.Btn7);
        mBtn8 = findViewById(R.id.Btn8);
        mBtn9 = findViewById(R.id.Btn9);

        mPlus = findViewById(R.id.Plus);
        mMinus = findViewById(R.id.Minus);
        mDivide = findViewById(R.id.Divide);
        mMultiply = findViewById(R.id.Multiply);

        mResult = findViewById(R.id.Result);

        mClear = findViewById(R.id.Clear);
        mBack = findViewById(R.id.Back);
        mComma = findViewById(R.id.Comma);
        mChangeSin = findViewById(R.id.ChangeSin);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNumberClick(v);
            }

            public void onNumberClick(View button) {
                String number = ((Button) button).getText().toString();
                String display = getDisplayString(mDisplay);
                String display3 = getDisplayString(mDisplay3);

                if (display.equals("0"))
                    display = number;
                else
                    display += number;

                displayText(display, display3);
            }
        };
        mBtn0.setOnClickListener(numberListener);
        mBtn1.setOnClickListener(numberListener);
        mBtn2.setOnClickListener(numberListener);
        mBtn3.setOnClickListener(numberListener);
        mBtn4.setOnClickListener(numberListener);
        mBtn5.setOnClickListener(numberListener);
        mBtn6.setOnClickListener(numberListener);
        mBtn7.setOnClickListener(numberListener);
        mBtn8.setOnClickListener(numberListener);
        mBtn9.setOnClickListener(numberListener);

        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOperatorListener(v);
            }

            public void onOperatorListener(View button) {
                String operator = ((Button) button).getText().toString();
                mOperator = operator;

                String display = getDisplayString(mDisplay);
                mValue = Float.parseFloat(display);

                mDisplay3.setText(operator);
                mDisplay.setText("0");
            }
        };
        mPlus.setOnClickListener(operatorListener);
        mMinus.setOnClickListener(operatorListener);
        mDivide.setOnClickListener(operatorListener);
        mMultiply.setOnClickListener(operatorListener);

        View.OnClickListener resultListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResultListener(v);
            }

            public void onResultListener(View button) {
                float value = getDisplayFloat(mDisplay);
                float result = value;
                boolean flag = false;

                switch (mOperator) {
                    case "+": {
                        result = mValue + value;
                        break;
                    }
                    case "-": {
                        result = mValue - value;
                        break;
                    }
                    case "*": {
                        result = mValue * value;
                        break;
                    }
                    case "/": {
                        if (value == 0) {
                            flag = true;
                        } else {
                            flag = false;
                            result = mValue / value;
                        }
                        break;
                    }
                }

                String resultText = floatToStringForDisplay(result);

                if (flag) {
                    mDisplay.setTextSize(24);
                    mDisplay.setText(getResources().getString(R.string.error_zero));

                } else {
                    mDisplay.setText(resultText);
                    mValue = result;
                    mOperator = "";
                }

            }
        };
        mResult.setOnClickListener(resultListener);

        View.OnClickListener clearListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClearListener(v);
            }

            public void onClearListener(View button) {
                mValue = 0;
                mOperator = "";
                mDisplay.setText("0");
                mDisplay2.setText("");
                mDisplay3.setText("");
                mDisplay4.setText("");
            }
        };
        mClear.setOnClickListener(clearListener);

        View.OnClickListener backspaceListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackspaceListener(v);
            }

            public void onBackspaceListener(View button) {
                String display = getDisplayString(mDisplay);
                String display3 = getDisplayString(mDisplay3);

                display = display.substring(0, display.length() - 1);
                displayText(display, display3);
            }
        };
        mBack.setOnClickListener(backspaceListener);

        View.OnClickListener changeSinListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChangeSinListener(v);
            }

            public void onChangeSinListener(View button) {
                String display3 = getDisplayString(mDisplay);
                float value = getDisplayFloat(mDisplay);

                value = value * -1;
                String resultText = floatToStringForDisplay(value);
                displayText(resultText, display3);
            }
        };
        mChangeSin.setOnClickListener(changeSinListener);

        View.OnClickListener commaListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCommaListener(v);
            }

            public void onCommaListener(View button) {
                String display = getDisplayString(mDisplay);
                String display3 = getDisplayString(mDisplay3);

                display = display + ",";

                displayText(display, display3);
            }
        };
        mComma.setOnClickListener(commaListener);

    }

    private void displayText(String display, String display3) {
        display = display.replace('.', ',');
        mDisplay.setText(display);
        if (display3.equals("")) {
            mDisplay2.setText(display);
        }
        else {
            mDisplay4.setText(display);
        }
    }

    private String floatToStringForDisplay(float result) {
        DecimalFormat format = new DecimalFormat(("0.######"));
        format.setRoundingMode(RoundingMode.DOWN);
        return format.format(result);
    }

    private String getDisplayString(TextView mDisplay) {
        String display = mDisplay.getText().toString();
        display = display.replace(',', '.');
        return display;
    }

    private Float getDisplayFloat(TextView mDisplay) {
        String display = getDisplayString(mDisplay);
        float value = Float.parseFloat(display);
        return value;
    }
}