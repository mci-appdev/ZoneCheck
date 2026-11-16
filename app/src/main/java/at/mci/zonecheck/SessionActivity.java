package at.mci.zonecheck;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        // Build-Along 3: three lines of wiring
        RecyclerView list = findViewById(R.id.listReadings);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new ReadingAdapter(readings, maxHr));

        // share: an implicit Intent, Android asks who takes text
        Button btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(v -> {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_fmt, summary));
            startActivity(Intent.createChooser(share, getString(R.string.share_title)));
        });
    }
}
