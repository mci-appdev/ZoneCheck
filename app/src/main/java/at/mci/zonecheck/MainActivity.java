package at.mci.zonecheck;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.materialswitch.MaterialSwitch;

import at.mci.zonecheck.model.Zone;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_READINGS = "readings";
    private static final String KEY_MAX_HR = "maxHr";
    private static final String KEY_RESULT = "result";

    private EditText editAge;
    private EditText editHr;
    private MaterialSwitch switchOutdoor;
    private Button btnCheck;
    private Button btnSession;
    private TextView txtResult;

    private ArrayList<Integer> readings = new ArrayList<>();
    private int maxHr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Life", "Main onCreate");
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ===== YOUR CODE GOES HERE =====
        editAge = findViewById(R.id.editAge);
        editHr = findViewById(R.id.editHr);
        switchOutdoor = findViewById(R.id.switchOutdoor);
        btnCheck = findViewById(R.id.btnCheck);
        btnSession = findViewById(R.id.btnSession);
        txtResult = findViewById(R.id.txtResult);

        if (savedInstanceState != null) {          // coming back after a rotation
            ArrayList<Integer> saved = savedInstanceState.getIntegerArrayList(KEY_READINGS);
            if (saved != null) readings = saved;
            maxHr = savedInstanceState.getInt(KEY_MAX_HR, 0);
            txtResult.setText(savedInstanceState.getString(KEY_RESULT, ""));
        }

        btnCheck.setOnClickListener(v -> {
            String ageText = editAge.getText().toString().trim();
            String hrText = editHr.getText().toString().trim();
            try {
                int age = Integer.parseInt(ageText);
                int hr = Integer.parseInt(hrText);
                maxHr = 220 - age;                 // the field, not a new local variable
                Zone z = Zone.of(hr, maxHr);
                readings.add(hr);
                String where = switchOutdoor.isChecked() ? "outdoor" : "indoor";
                txtResult.setText(String.format(Locale.getDefault(), "%d bpm = %s (%d%%, %s)",
                        hr, z, hr * 100 / maxHr, where));
                Log.d("Zone", "age=" + age + " hr=" + hr + " -> " + z);
            } catch (NumberFormatException e) {
                Toast.makeText(this, R.string.error_input, Toast.LENGTH_SHORT).show();
            }
        });

        btnSession.setOnClickListener(v -> {
            Intent intent = new Intent(this, SessionActivity.class);
            intent.putExtra(SessionActivity.EXTRA_MAX_HR, maxHr);
            intent.putIntegerArrayListExtra(SessionActivity.EXTRA_READINGS, readings);
            startActivity(intent);
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("Life", "Main onSaveInstanceState");
        outState.putIntegerArrayList(KEY_READINGS, readings);
        outState.putInt(KEY_MAX_HR, maxHr);
        outState.putString(KEY_RESULT, txtResult.getText().toString());
    }

    @Override
    protected void onStart() {
        super.onStart();          // always first on the way up
        Log.d("Life", "Main onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Life", "Main onResume");
    }

    @Override
    protected void onPause() {
        Log.d("Life", "Main onPause");
        super.onPause();          // on the way down: your code first
    }

    @Override
    protected void onStop() {
        Log.d("Life", "Main onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("Life", "Main onDestroy");
        super.onDestroy();
    }
}
