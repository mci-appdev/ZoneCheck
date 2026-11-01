# Zone Check – Build-Along Snippets

MCI App Development, Lesson 3. We build the app together, step by step.
**✋ = hands up when it runs.** "By hand" = do it yourself, slowly. "Paste" = copy the block below.

Test: age **30**, heart rate **145** → `145 bpm = CARDIO (76%, indoor)`

Lost? File → New → **Project from Version Control** → paste this repo's URL. You get the finished app.

---

## Build-Along 1 – The Screen

1. **New project:** Empty **Views** Activity, name `ZoneCheck`, package `at.mci.zonecheck`, language **Java**
2. Open `res/layout/activity_main.xml`, top right **Split**
3. **By hand – one button:** drag a Button under *Hello World* · `id` = `btnCheck` · `text` = `Check`, then Alt+Enter → *Extract string resource* · drag the top dot to *Hello World*, the side dots to the edges
4. **Run** ✋
5. **Paste** Block A into `res/values/strings.xml` and Block B into `activity_main.xml` (replace everything)
6. **Run** ✋ – six widgets, no red marks in the Component Tree

**Done early?** `res/values-de/strings.xml` with German texts, switch the phone language.

## Build-Along 2 – The Tap

1. Open `MainActivity.java`. Our code goes **below** the insets block, inside `onCreate`
2. **By hand:** type Block C. Red names → Alt+Enter → import
3. **Run**, tap Check → a Toast ✋
4. **Paste** Block D (from your Exercise 3) **above** `onCreate`
5. **Paste** Block E above `onCreate`. **Replace** Block C with Block F. Alt+Enter on red names (or paste the imports at the end)
6. **Run**, type 30 and 145, tap ✋ · Logcat → filter `tag:Zone`
7. Empty a field, tap → a Toast, no crash

**Done early?** Show the max heart rate too. Change the zone limits and test again.

---

## Block A – `res/values/strings.xml`
```xml
<resources>
    <string name="app_name">ZoneCheck</string>
    <string name="title">Zone Check</string>
    <string name="hint_age">Age</string>
    <string name="hint_hr">Heart rate (bpm)</string>
    <string name="outdoor">Outdoor</string>
    <string name="check">Check</string>
    <string name="error_input">Enter age and heart rate</string>
</resources>
```

## Block B – `res/layout/activity_main.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <TextView
        android:id="@+id/txtTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="24dp"
        android:text="@string/title"
        android:textSize="28sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <EditText
        android:id="@+id/editAge"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="24dp"
        android:layout_marginTop="24dp"
        android:hint="@string/hint_age"
        android:textColorHint="#616161"
        android:inputType="number"
        android:minHeight="48dp"
        android:importantForAutofill="no"
        app:layout_constraintTop_toBottomOf="@id/txtTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <EditText
        android:id="@+id/editHr"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="24dp"
        android:layout_marginTop="8dp"
        android:hint="@string/hint_hr"
        android:textColorHint="#616161"
        android:inputType="number"
        android:minHeight="48dp"
        android:importantForAutofill="no"
        app:layout_constraintTop_toBottomOf="@id/editAge"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <com.google.android.material.materialswitch.MaterialSwitch
        android:id="@+id/switchOutdoor"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="24dp"
        android:layout_marginTop="16dp"
        android:text="@string/outdoor"
        android:minHeight="48dp"
        app:layout_constraintTop_toBottomOf="@id/editHr"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <Button
        android:id="@+id/btnCheck"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="24dp"
        android:layout_marginTop="24dp"
        android:text="@string/check"
        app:layout_constraintTop_toBottomOf="@id/switchOutdoor"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <TextView
        android:id="@+id/txtResult"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="24dp"
        android:layout_marginTop="24dp"
        android:textSize="18sp"
        app:layout_constraintTop_toBottomOf="@id/btnCheck"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

## Block C – type this by hand
```java
        Button btnCheck = findViewById(R.id.btnCheck);
        btnCheck.setOnClickListener(v -> Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show());
```

## Block D – above `onCreate` (your Exercise 3)
```java
    enum Zone { REST, FAT_BURN, CARDIO, PEAK }

    static Zone zone(int hr, int maxHr) {
        int pct = hr * 100 / maxHr;
        if (pct < 50) return Zone.REST;
        if (pct < 70) return Zone.FAT_BURN;
        if (pct < 85) return Zone.CARDIO;
        return Zone.PEAK;
    }
```

## Block E – fields, above `onCreate`
```java
    private EditText editAge;
    private EditText editHr;
    private MaterialSwitch switchOutdoor;
    private Button btnCheck;
    private TextView txtResult;
```

## Block F – replaces Block C
```java
        editAge = findViewById(R.id.editAge);
        editHr = findViewById(R.id.editHr);
        switchOutdoor = findViewById(R.id.switchOutdoor);
        btnCheck = findViewById(R.id.btnCheck);
        txtResult = findViewById(R.id.txtResult);

        btnCheck.setOnClickListener(v -> {
            String ageText = editAge.getText().toString().trim();
            String hrText = editHr.getText().toString().trim();
            try {
                int age = Integer.parseInt(ageText);
                int hr = Integer.parseInt(hrText);
                int maxHr = 220 - age;
                Zone z = zone(hr, maxHr);
                String where = switchOutdoor.isChecked() ? "outdoor" : "indoor";
                txtResult.setText(String.format(Locale.getDefault(), "%d bpm = %s (%d%%, %s)",
                        hr, z, hr * 100 / maxHr, where));
                Log.d("Zone", "age=" + age + " hr=" + hr + " -> " + z);
            } catch (NumberFormatException e) {
                Toast.makeText(this, R.string.error_input, Toast.LENGTH_SHORT).show();
            }
        });
```

## Imports (if Alt+Enter is not your friend today)
```java
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.materialswitch.MaterialSwitch;
import java.util.Locale;
```
