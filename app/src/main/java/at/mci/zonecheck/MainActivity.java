package at.mci.zonecheck;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    private EditText editAge;
    private EditText editHr;
    private MaterialSwitch switchOutdoor;
    private Button btnCheck;
    private TextView txtResult;

    // Lesson 4: what the Session screen needs
    private Button btnSession;
    private ArrayList<Integer> readings = new ArrayList<>();
    private int maxHr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        txtResult = findViewById(R.id.txtResult);
        btnSession = findViewById(R.id.btnSession);

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

        // Lesson 4: an explicit Intent opens our own second screen
        btnSession.setOnClickListener(v -> {
            Intent intent = new Intent(this, SessionActivity.class);
            intent.putExtra(SessionActivity.EXTRA_MAX_HR, maxHr);
            intent.putIntegerArrayListExtra(SessionActivity.EXTRA_READINGS, readings);
            startActivity(intent);
        });
    }
}
