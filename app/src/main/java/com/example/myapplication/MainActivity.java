package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText etLeft, etRight;
    Spinner spOperator;
    Button btnCalc;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLeft = findViewById(R.id.etLeft);
        etRight = findViewById(R.id.etRight);
        spOperator = findViewById(R.id.spOperator);
        btnCalc = findViewById(R.id.btnCalc);
        tvResult = findViewById(R.id.tvResult);

        // Spinner に + - * / をセット
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.operators,
                        android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOperator.setAdapter(adapter);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 空チェック
                if (etLeft.getText().toString().isEmpty() ||
                        etRight.getText().toString().isEmpty()) {
                    tvResult.setText("計算エラー");
                    return;
                }

                int left = Integer.parseInt(etLeft.getText().toString());
                int right = Integer.parseInt(etRight.getText().toString());
                String op = spOperator.getSelectedItem().toString();

                int result = 0;
                switch (op) {
                    case "+":
                        result = left + right;
                        break;
                    case "-":
                        result = left - right;
                        break;
                    case "*":
                        result = left * right;
                        break;
                    case "/":
                        if (right == 0) {
                            tvResult.setText("計算エラー");
                            return;
                        }
                        result = left / right;
                        break;
                }

                tvResult.setText("計算結果: " + result);
            }
        });
    }
}
