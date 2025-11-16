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
    int number1, number2, result;


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

    public void add(View view) {
        number1 = Integer.parseInt(binding.number1EditText.getText().toString());
        number2 = Integer.parseInt(binding.number2EditText.getText().toString());
        result = number1 + number2;
        binding.resultText.setText(Integer.toString(result));
    }

    public void substract(View view) {
        number1 = Integer.parseInt(binding.number1EditText.getText().toString());
        number2 = Integer.parseInt(binding.number2EditText.getText().toString());
        result = number1 - number2;
        binding.resultText.setText(Integer.toString(result));
    }

    public void multiply(View view) {
        number1 = Integer.parseInt(binding.number1EditText.getText().toString());
        number2 = Integer.parseInt(binding.number2EditText.getText().toString());
        result = number1 * number2;
        binding.resultText.setText(Integer.toString(result));
    }

    public void divide(View view) {
        number1 = Integer.parseInt(binding.number1EditText.getText().toString());
        number2 = Integer.parseInt(binding.number2EditText.getText().toString());
        result = number1 / number2;
        binding.resultText.setText(Integer.toString(result));
    }
}