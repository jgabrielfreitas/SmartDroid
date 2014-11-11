package br.com.activitys;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import jfreitas.com.br.smartdroid.R;

public class MainActivity extends Activity implements View.OnClickListener{

    EditText name;
    EditText date;
    Button   button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instanceViews();

    }

    private void instanceViews() {

        name = (EditText) findViewById(R.id.nameEditText);
        date = (EditText) findViewById(R.id.dateEditText);
        button = (Button) findViewById(R.id.saveButton);

        button.setOnClickListener(this);

    }

    public void onClick(View view) {

    }
}
