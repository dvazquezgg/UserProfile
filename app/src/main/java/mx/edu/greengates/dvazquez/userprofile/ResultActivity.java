package mx.edu.greengates.dvazquez.userprofile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.util.List;

import mx.edu.greengates.dvazquez.userprofile.persistance.CSVReadWrite;

public class ResultActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView = (TextView) findViewById(R.id.textView);

        CSVReadWrite reader = new CSVReadWrite(this, "Questions.csv");
        List<String[]> userData = reader.readUserDataCSV("users.csv");

        StringBuffer sb = new StringBuffer();
        for(String[] row: userData){
            for(String data: row){
                Log.d("USERDATA", data);
                sb.append(data);
                sb.append(", ");
            }
            sb.append('\n');
        }
        String userDataRaw = sb.toString();
        textView.setText(userDataRaw);

        button = findViewById(R.id.button2);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent myIntent = new Intent(ResultActivity.this, MainActivity.class);
        ResultActivity.this.startActivity(myIntent);
    }
}