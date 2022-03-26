package mx.edu.greengates.dvazquez.userprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

import mx.edu.greengates.dvazquez.userprofile.persistance.CSVReadWrite;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editUserName;
    EditText editPassword;
    EditText editEmail;
    EditText editSurname;
    EditText editFirstName;
    EditText editPhone;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUserName = findViewById(R.id.editUserName);
        editPassword = findViewById(R.id.editPassword);
        editEmail = findViewById(R.id.editEmail);
        editSurname = findViewById(R.id.editSurname);
        editFirstName = findViewById(R.id.editFirstName);
        editPhone = findViewById(R.id.editPhone);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == button){
            String strUserName = editUserName.getText().toString() ;
            String strPassword = editPassword.getText().toString();
            String strEmail = editEmail.getText().toString() ;
            String strSurname = editSurname.getText().toString() ;
            String strFirstName = editFirstName.getText().toString();
            String strPhone = editPhone.getText().toString();

            String[] userData = {
                    strUserName, strPassword, strEmail, strSurname, strFirstName, strPhone
            };

            CSVReadWrite writer = new CSVReadWrite(this, "Questions.csv");
            try {
                writer.writeUserDataCSV("users.csv", userData);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Intent myIntent = new Intent(MainActivity.this, ResultActivity.class);
            MainActivity.this.startActivity(myIntent);
        }
    }
}