package com.aleynasahin.simplecalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.aleynasahin.simplecalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String currentNumber = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isNewCalculation = true;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void numberClick(View view) {
        String number = "";

        // Hangi butona tıklandığını belirle
        if (view.getId() == binding.btn0.getId()) {
            number = "0";
        } else if (view.getId() == binding.btn1.getId()) { //binding.btn1 e tıklandıysa ?? mı
            number = "1";
        } else if (view.getId() == binding.btn2.getId()) {
            number = "2";
        } else if (view.getId() == binding.btn3.getId()) {
            number = "3";
        } else if (view.getId() == binding.btn4.getId()) {
            number = "4";
        } else if (view.getId() == binding.btn5.getId()) {
            number = "5";
        } else if (view.getId() == binding.btn6.getId()) {
            number = "6";
        } else if (view.getId() == binding.btn7.getId()) {
            number = "7";
        } else if (view.getId() == binding.btn8.getId()) {
            number = "8";
        } else if (view.getId() == binding.btn9.getId()) {
            number = "9";
        }

        if (isNewCalculation) {
            currentNumber = number;
            isNewCalculation = false;
        } else {
            currentNumber += number;
        }

        binding.display.setText(currentNumber);
    }

    public void operatorClick(View view) {
        String newOperator = "";

        if (view.getId() == binding.btnAdd.getId()) {
            newOperator = "+";
        } else if (view.getId() == binding.btnSubstract.getId()) {
            newOperator = "-";
        } else if (view.getId() == binding.btnMultiply.getId()) {
            newOperator = "×";
        } else if (view.getId() == binding.btnDivide.getId()) {
            newOperator = "÷";
        }

        if (!currentNumber.isEmpty()) {
            if (!operator.isEmpty()) {
                calculate();
            } else {
                firstNumber = Double.parseDouble(currentNumber);
            }

            operator = newOperator;
            currentNumber = "";
        }
    }

    public void equalsClick(View view) {
        if (!currentNumber.isEmpty() && !operator.isEmpty()) {
            calculate();
            operator = "";
            isNewCalculation = true;
        }
    }

    public void decimalClick(View view) {
        if (!currentNumber.contains(".")) {
            if (currentNumber.isEmpty()) {
                currentNumber = "0.";
            } else {
                currentNumber += ".";
            }
            binding.display.setText(currentNumber);
            isNewCalculation = false;
        }
    }

    public void clearClick(View view) {
        currentNumber = "";
        operator = "";
        firstNumber = 0;
        binding.display.setText("0");
        isNewCalculation = true;
    }

    public void deleteClick(View view) {
        if (!currentNumber.isEmpty()) {
            currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
            if (currentNumber.isEmpty()) {
                binding.display.setText("0");
                isNewCalculation = true;
            } else {
                binding.display.setText(currentNumber);
            }
        }
    }

    private void calculate() {
        if (currentNumber.isEmpty() || operator.isEmpty()) return;

        double secondNumber = Double.parseDouble(currentNumber);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "×":
                result = firstNumber * secondNumber;
                break;
            case "÷":
                if (secondNumber != 0) {
                    result = firstNumber / secondNumber;
                } else {
                    binding.display.setText("Error");
                    currentNumber = "";
                    operator = "";
                    isNewCalculation = true;
                    return;
                }
                break;
        }

        if (result == (int) result) {
            currentNumber = String.valueOf((int) result);
        } else {
            currentNumber = String.valueOf(result);
        }

        binding.display.setText(currentNumber);
        firstNumber = result;
    }
}
