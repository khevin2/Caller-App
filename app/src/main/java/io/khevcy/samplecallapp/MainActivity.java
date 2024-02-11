package io.khevcy.samplecallapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private Button btnCall;
    private TextInputLayout inputMain;

    private String phoneNbr;

    private String getPhoneNbr(TextInputLayout phoneObj){
        return phoneObj.getEditText().getText().toString().trim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCall = findViewById(R.id.btnCall);
        inputMain = findViewById(R.id.inputMain);

        // Initially disable the button
        btnCall.setEnabled(false);

        // Add a TextWatcher to inputMain
        inputMain.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // Enable or disable the button based on whether inputMain contains text
                if (s.length() > 0) {
                    btnCall.setEnabled(true);  // Enable the button
                    phoneNbr = s.toString().trim(); // Update phoneNbr with the current input
                } else {
                    btnCall.setEnabled(false); // Disable the button
                    phoneNbr = ""; // Reset phoneNbr to empty string
                }
            }
        });

        // Set OnClickListener for btnCall
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Make a call using the phone number obtained from inputMain
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + phoneNbr));
                startActivity(intent);
            }
        });
    }
}
