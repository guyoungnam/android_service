package org.techtown.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();

                Intent intent = new Intent(getApplicationContext(),MyService.class);
                intent.putExtra("command", "show");
                intent.putExtra("name", name); //인텐트 객체 만들고 부가 데이터 넣기

                startService(intent); //서비스 시작하기
            }
        });
        Intent passedIntent = getIntent();
        processIntent(passedIntent); //액티비티가 새로 만들어질 때 전달된 인텐트 처리하기

    }
    @Override
    protected  void onNewIntent(Intent intent){
        processIntent(intent);
        super.onNewIntent(intent); //액티비티가 이미 만들어져 있을 때 전달된 인텐트 처리하기
    }

    private void processIntent(Intent intent) {
        if(intent != null){
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");

            Toast.makeText(this, "command: "+command + ", name:"+name, Toast.LENGTH_LONG).show();
        }
    }
}
