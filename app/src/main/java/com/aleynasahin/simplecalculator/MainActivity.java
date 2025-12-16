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

    // Sayı butonlarına tıklanınca (XML'de android:onClick="numberClick" kullandıysanız)
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
            // Sayıları yan yana ekle (örn: 9+6=96)
            currentNumber += number;
        }

        binding.display.setText(currentNumber);
    }

    // İşlem butonlarına tıklanınca (XML'de android:onClick="operatorClick" kullanın)
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
                // Önceki işlemi hesapla
                calculate();
            } else {
                firstNumber = Double.parseDouble(currentNumber);
            }

            operator = newOperator;
            currentNumber = "";
        }
    }

    // Eşittir butonuna tıklanınca (XML'de android:onClick="equalsClick")
    public void equalsClick(View view) {
        if (!currentNumber.isEmpty() && !operator.isEmpty()) {
            calculate();
            operator = "";
            isNewCalculation = true;
        }
    }

    // Nokta butonuna tıklanınca (XML'de android:onClick="decimalClick")
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

    // Temizle butonuna tıklanınca (XML'de android:onClick="clearClick")
    public void clearClick(View view) {
        currentNumber = "";
        operator = "";
        firstNumber = 0;
        binding.display.setText("0");
        isNewCalculation = true;
    }

    // Sil butonuna tıklanınca (XML'de android:onClick="deleteClick")
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

    // Hesaplama işlemi
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
                    binding.display.setText("Hata");
                    currentNumber = "";
                    operator = "";
                    isNewCalculation = true;
                    return;
                }
                break;
        }

        // Sonucu ekranda göster
        if (result == (int) result) {
            currentNumber = String.valueOf((int) result);
        } else {
            currentNumber = String.valueOf(result);
        }

        binding.display.setText(currentNumber);
        firstNumber = result;
    }

    // Alternatif: XML'de onClick kullanmadıysanız bu metodu onCreate'te çağırın
    private void setupButtonListeners() {
        // Sayı butonları
        binding.btn0.setOnClickListener(this::numberClick);
        binding.btn1.setOnClickListener(this::numberClick);
        binding.btn2.setOnClickListener(this::numberClick);
        binding.btn3.setOnClickListener(this::numberClick);
        binding.btn4.setOnClickListener(this::numberClick);
        binding.btn5.setOnClickListener(this::numberClick);
        binding.btn6.setOnClickListener(this::numberClick);
        binding.btn7.setOnClickListener(this::numberClick);
        binding.btn8.setOnClickListener(this::numberClick);
        binding.btn9.setOnClickListener(this::numberClick);

        // İşlem butonları
        binding.btnAdd.setOnClickListener(this::operatorClick);
        binding.btnSubstract.setOnClickListener(this::operatorClick);
        binding.btnMultiply.setOnClickListener(this::operatorClick);
        binding.btnDivide.setOnClickListener(this::operatorClick);

        // Diğer butonlar
        binding.btnDecimal.setOnClickListener(this::decimalClick);
        binding.btnEquals.setOnClickListener(this::equalsClick);
        binding.btnAllClear.setOnClickListener(this::clearClick);
        binding.btnDelete.setOnClickListener(this::deleteClick);
    }
}
