package at.mci.zonecheck;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class SessionActivity extends AppCompatActivity {

    // the keys live on the receiver: the sender cannot make a typo
    public static final String EXTRA_MAX_HR = "maxHr";
    public static final String EXTRA_READINGS = "readings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_session);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ===== YOUR CODE GOES HERE =====
        Intent intent = getIntent();
        int maxHr = intent.getIntExtra(EXTRA_MAX_HR, 190);          // always a default
        ArrayList<Integer> readings = intent.getIntegerArrayListExtra(EXTRA_READINGS);
        if (readings == null) readings = new ArrayList<>();

        TextView txtSummary = findViewById(R.id.txtSummary);
        String summary = getString(R.string.summary_fmt, readings.size(), maxHr);
        txtSummary.setText(summary);
    }
}
