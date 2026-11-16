# Zone Check – Build-Along Snippets

The course app of MCI App Development (WS26). One section per lesson, newest first.

- **Lesson 4** (17.11.): [steps](#lesson-4--two-screens-rotation-a-list) · [blocks L4-A … L4-M](#lesson-4-blocks)
- **Lesson 3** (03.11.): [steps](#lesson-3--one-screen) · [blocks A … F](#lesson-3-blocks)

**Lost?** Every build-along ends in a tag. In Android Studio: Terminal → `git checkout l04-1` (or `l04-2`, `l04-3`, `lesson-04`) → Run.
No clone yet? File → New → **Project from Version Control** → `https://github.com/mci-appdev/ZoneCheck.git`, then the same.

| Tag | State |
|---|---|
| `lesson-03` | end of Lesson 3: one screen |
| `l04-1` | after Build-Along 1: the Session screen, Main sends the readings |
| `l04-2` | after Build-Along 2: callbacks logged, readings survive rotation |
| `l04-3` | after Build-Along 3: the list and the Share button |
| `lesson-04` | `l04-3` plus L4-M: one colour per zone |

---

# Lesson 4 – Two Screens, Rotation, a List

**Test data, the whole lesson:** age **30**, heart rate **145**, **170**, **92** → `145 bpm = CARDIO (76%, indoor)`, `170 bpm = PEAK (89%, indoor)`, `92 bpm = REST (48%, indoor)`, max HR 190. Session: `3 readings, max HR 190`.

"Paste" = copy the block below into the file named in its title. "By hand" = do it in Android Studio.

## Build-Along 1 – The Session Screen

1. **Paste** L4-A into `res/values/strings.xml`, inside `<resources>`
2. **By hand:** `activity_main.xml`, Design view: select `editAge` + `editHr` → right-click → Chains → **Create Horizontal Chain**. In the XML of `editHr`: `Top_toBottomOf="@id/editAge"` → `Top_toTopOf="@id/editAge"`. Margins 24 · 8 · 24
3. **By hand:** `txtResult` → 32sp, bold, centred. A new Button `btnSession` right of `btnCheck`, chained the same way. Stuck? **L4-B** replaces the whole file
4. **Paste** L4-C: new package `model`, new enum `Zone` in it. In `MainActivity` delete the old `enum Zone` and `zone()`, call `Zone.of(hr, maxHr)`. Run: `145 bpm = CARDIO (76%, indoor)` as before
5. **By hand:** New → Activity → **Empty Views Activity** → `SessionActivity` (Java, not a launcher activity). Look at `AndroidManifest.xml`. Then **paste** L4-D into `SessionActivity` and L4-E over `activity_session.xml`
6. **By hand:** `MainActivity` sends the readings: three fields, `btnSession`, the Intent listener. Stuck? **L4-F**
7. **Run:** 30/145, 30/170, 30/92, Session → `3 readings, max HR 190`. Back → **tag `l04-1`**

**`max HR 0`?** You wrote `int maxHr = 220 - age;` in the listener: that is a new local variable, the field stays 0. Delete the `int`.

## Build-Along 2 – Rotate and Lose

1. **By hand:** `MainActivity`, Ctrl+O, override `onStart`, `onResume`, `onPause`, `onStop`, `onDestroy`. One `Log.d("Life", "Main onStart")` each, one in `onCreate`. Or **paste** L4-G
2. **Run**, Logcat filter `tag:Life`. Start, Session, Back, Home, rotate: watch the callbacks
3. Three Checks, Session: 3 readings. Back, **rotate**, Session: `0 readings, max HR 0`
4. **Paste** L4-H: three constants, `onSaveInstanceState`, the restore block in `onCreate`
5. **Run:** three Checks, rotate, Session: `3 readings, max HR 190` → **tag `l04-2`**

## Build-Along 3 – The List

1. **By hand:** New → Layout Resource File `item_reading`, root `LinearLayout`, horizontal. Two TextViews `txtBpm` (0dp, weight 1) and `txtZone` (bold). Or **paste** L4-I
2. **Paste** L4-J: new Java class `ReadingAdapter` next to `SessionActivity`
3. **By hand:** `activity_session.xml`: **first** a Button `btnShare` at the bottom, **then** a RecyclerView `listReadings` between summary and button, `0dp` both ways. Or **paste** L4-K over the file
4. **Paste** the first part of L4-L into `SessionActivity.onCreate`: three lines of wiring
5. **Run:** three Checks, Session: three rows. Twenty Checks: it scrolls
6. **Paste** the second part of L4-L: the Share button. Tap it: the share sheet → **tag `l04-3`**
7. Optional: **paste** L4-M, one colour per zone → **tag `lesson-04`**

---

# Lesson 4 blocks

## L4-A – into `res/values/strings.xml`, inside `<resources>`
```xml
    <!-- Lesson 4 (L4-A) -->
    <string name="session">Session</string>
    <string name="share">Share</string>
    <string name="summary_fmt">%1$d readings, max HR %2$d</string>
    <string name="bpm_fmt">%d bpm</string>
    <string name="share_title">Share session</string>
    <string name="share_fmt">Zone Check: %1$s</string>
```

## L4-B – replaces `res/layout/activity_main.xml` (if the chains went wrong)
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

    <!-- Lesson 4: age and heart rate side by side, a horizontal chain -->
    <EditText
        android:id="@+id/editAge"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="24dp"
        android:layout_marginEnd="8dp"
        android:layout_marginTop="24dp"
        android:hint="@string/hint_age"
        android:textColorHint="#616161"
        android:inputType="number"
        android:minHeight="48dp"
        android:importantForAutofill="no"
        app:layout_constraintTop_toBottomOf="@id/txtTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/editHr" />

    <EditText
        android:id="@+id/editHr"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginEnd="24dp"
        android:hint="@string/hint_hr"
        android:textColorHint="#616161"
        android:inputType="number"
        android:minHeight="48dp"
        android:importantForAutofill="no"
        app:layout_constraintTop_toTopOf="@id/editAge"
        app:layout_constraintStart_toEndOf="@id/editAge"
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

    <!-- Lesson 4: Check and Session in one row, a second chain -->
    <Button
        android:id="@+id/btnCheck"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="24dp"
        android:layout_marginEnd="8dp"
        android:layout_marginTop="24dp"
        android:text="@string/check"
        app:layout_constraintTop_toBottomOf="@id/switchOutdoor"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toStartOf="@id/btnSession" />

    <Button
        android:id="@+id/btnSession"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginEnd="24dp"
        android:text="@string/session"
        app:layout_constraintTop_toTopOf="@id/btnCheck"
        app:layout_constraintStart_toEndOf="@id/btnCheck"
        app:layout_constraintEnd_toEndOf="parent" />

    <!-- Lesson 4: the number that matters is the biggest thing on the screen -->
    <TextView
        android:id="@+id/txtResult"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="24dp"
        android:layout_marginTop="24dp"
        android:gravity="center"
        android:textSize="32sp"
        android:textStyle="bold"
        app:layout_constraintTop_toBottomOf="@id/btnCheck"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

## L4-C – `model/Zone.java` (new package `model`, new enum `Zone`, replace everything)
```java
package at.mci.zonecheck.model;

// From Exercise 3, moved to its own file: two screens need it now
public enum Zone {
    REST, FAT_BURN, CARDIO, PEAK;

    public static Zone of(int hr, int maxHr) {
        int pct = hr * 100 / maxHr;
        if (pct < 50) return REST;
        if (pct < 70) return FAT_BURN;
        if (pct < 85) return CARDIO;
        return PEAK;
    }
}
```
Then in `MainActivity`: delete the old `enum Zone { … }` line and the `zone(…)` method, write `Zone.of(hr, maxHr)` instead of `zone(hr, maxHr)`, Alt+Enter → import `at.mci.zonecheck.model.Zone`.

## L4-D – `SessionActivity.java`, two pieces
```java
    // 1) inside the class, above onCreate
    // the keys live on the receiver: the sender cannot make a typo
    public static final String EXTRA_MAX_HR = "maxHr";
    public static final String EXTRA_READINGS = "readings";

    // 2) in onCreate, below the insets block
        Intent intent = getIntent();
        int maxHr = intent.getIntExtra(EXTRA_MAX_HR, 190);          // always a default
        ArrayList<Integer> readings = intent.getIntegerArrayListExtra(EXTRA_READINGS);
        if (readings == null) readings = new ArrayList<>();

        TextView txtSummary = findViewById(R.id.txtSummary);
        String summary = getString(R.string.summary_fmt, readings.size(), maxHr);
        txtSummary.setText(summary);
```
Alt+Enter on `Intent`, `ArrayList`, `TextView`. The whole file: `git show l04-1:app/src/main/java/at/mci/zonecheck/SessionActivity.java`.

## L4-E – replaces `res/layout/activity_session.xml`
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".SessionActivity">

    <TextView
        android:id="@+id/txtTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="24dp"
        android:text="@string/session"
        android:textSize="28sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <TextView
        android:id="@+id/txtSummary"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="24dp"
        android:layout_marginTop="8dp"
        android:gravity="center"
        android:textSize="18sp"
        tools:text="3 readings, max HR 190"
        app:layout_constraintTop_toBottomOf="@id/txtTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

## L4-F – `MainActivity.java` sends the readings, four pieces
```java
    // 1) fields, next to the other five
    private Button btnSession;
    private ArrayList<Integer> readings = new ArrayList<>();
    private int maxHr;

    // 2) in onCreate, next to the other findViewById lines
        btnSession = findViewById(R.id.btnSession);

    // 3) in the Check listener: the field, not a new local variable; then remember the reading
                maxHr = 220 - age;
                Zone z = Zone.of(hr, maxHr);
                readings.add(hr);

    // 4) in onCreate, below the Check listener
        btnSession.setOnClickListener(v -> {
            Intent intent = new Intent(this, SessionActivity.class);
            intent.putExtra(SessionActivity.EXTRA_MAX_HR, maxHr);
            intent.putIntegerArrayListExtra(SessionActivity.EXTRA_READINGS, readings);
            startActivity(intent);
        });
```
Alt+Enter on `Intent`, `ArrayList`. The whole file: `git show l04-1:app/src/main/java/at/mci/zonecheck/MainActivity.java`.

## L4-G – `MainActivity.java`, the six callbacks logged
```java
    // 1) directly after super.onCreate(savedInstanceState);
        Log.d("Life", "Main onCreate");

    // 2) below onCreate, inside the class
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
```

## L4-H – `MainActivity.java` survives rotation, three pieces
```java
    // 1) constants, next to the fields
    private static final String KEY_READINGS = "readings";
    private static final String KEY_MAX_HR = "maxHr";
    private static final String KEY_RESULT = "result";

    // 2) in onCreate, AFTER the findViewById lines (txtResult must exist)
        if (savedInstanceState != null) {          // coming back after a rotation
            ArrayList<Integer> saved = savedInstanceState.getIntegerArrayList(KEY_READINGS);
            if (saved != null) readings = saved;
            maxHr = savedInstanceState.getInt(KEY_MAX_HR, 0);
            txtResult.setText(savedInstanceState.getString(KEY_RESULT, ""));
        }

    // 3) below onCreate, its own method
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("Life", "Main onSaveInstanceState");
        outState.putIntegerArrayList(KEY_READINGS, readings);
        outState.putInt(KEY_MAX_HR, maxHr);
        outState.putString(KEY_RESULT, txtResult.getText().toString());
    }
```
Alt+Enter on `NonNull` (`androidx.annotation.NonNull`). The whole file: `git show l04-2:app/src/main/java/at/mci/zonecheck/MainActivity.java`.

## L4-I – `res/layout/item_reading.xml` (New → Layout Resource File, then replace everything)
```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- one row of the readings list -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="horizontal"
    android:minHeight="48dp"
    android:gravity="center_vertical"
    android:paddingHorizontal="24dp">

    <TextView
        android:id="@+id/txtBpm"
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="wrap_content"
        android:textSize="18sp"
        tools:text="145 bpm" />

    <TextView
        android:id="@+id/txtZone"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="18sp"
        android:textStyle="bold"
        tools:text="CARDIO" />

</LinearLayout>
```

## L4-J – `ReadingAdapter.java` (New → Java Class, next to `SessionActivity`, replace everything)
```java
package at.mci.zonecheck;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.mci.zonecheck.model.Zone;

import java.util.List;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.ViewHolder> {

    private final List<Integer> readings;
    private final int maxHr;

    public ReadingAdapter(List<Integer> readings, int maxHr) {
        this.readings = readings;
        this.maxHr = maxHr;
    }

    // H: one holder per row on screen, finds the two labels once
    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txtBpm, txtZone;

        ViewHolder(View row) {
            super(row);
            txtBpm = row.findViewById(R.id.txtBpm);
            txtZone = row.findViewById(R.id.txtZone);
        }
    }

    // 2: create an empty row from item_reading.xml
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reading, parent, false);
        return new ViewHolder(row);
    }

    // 3: put reading number `position` into this row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        int hr = readings.get(position);
        h.txtBpm.setText(h.itemView.getContext().getString(R.string.bpm_fmt, hr));
        h.txtZone.setText(Zone.of(hr, maxHr).name());
    }

    // 1: how many rows?
    @Override
    public int getItemCount() {
        return readings.size();
    }
}
```

## L4-K – replaces `res/layout/activity_session.xml` (button first, then the list)
```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".SessionActivity">

    <TextView
        android:id="@+id/txtTitle"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="24dp"
        android:text="@string/session"
        android:textSize="28sp"
        android:textStyle="bold"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <TextView
        android:id="@+id/txtSummary"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="24dp"
        android:layout_marginTop="8dp"
        android:gravity="center"
        android:textSize="18sp"
        tools:text="3 readings, max HR 190"
        app:layout_constraintTop_toBottomOf="@id/txtTitle"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <!-- Build-Along 3: the button first, then the list fills the space above it -->
    <Button
        android:id="@+id/btnShare"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginHorizontal="24dp"
        android:layout_marginBottom="24dp"
        android:text="@string/share"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/listReadings"
        android:layout_width="0dp"
        android:layout_height="0dp"
        android:layout_marginTop="16dp"
        app:layout_constraintTop_toBottomOf="@id/txtSummary"
        app:layout_constraintBottom_toTopOf="@id/btnShare"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

</androidx.constraintlayout.widget.ConstraintLayout>
```

## L4-L – `SessionActivity.onCreate`, two pieces, both below the summary
```java
        // 1) three lines of wiring
        RecyclerView list = findViewById(R.id.listReadings);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(new ReadingAdapter(readings, maxHr));

        // 2) share: an implicit Intent, Android asks who takes text
        Button btnShare = findViewById(R.id.btnShare);
        btnShare.setOnClickListener(v -> {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_fmt, summary));
            startActivity(Intent.createChooser(share, getString(R.string.share_title)));
        });
```
Alt+Enter on `RecyclerView`, `LinearLayoutManager`, `Button`. The whole file: `git show l04-3:app/src/main/java/at/mci/zonecheck/SessionActivity.java`.

## L4-M – one colour per zone (optional)
`res/values/colors.xml`, inside `<resources>`:
```xml
    <!-- Lesson 4 (L4-M): one colour per zone, all >= 4.5:1 on white -->
    <color name="zone_rest">#1565C0</color>
    <color name="zone_fat_burn">#2E7D32</color>
    <color name="zone_cardio">#D84315</color>
    <color name="zone_peak">#C62828</color>
```
New file `res/values-night/colors.xml` (right-click `res/values` → New → Values Resource File, name `colors`, qualifier **Night mode**):
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Lesson 4 (L4-M): lighter zone colours for dark mode, all >= 4.5:1 on the dark surface -->
    <color name="zone_rest">#90CAF9</color>
    <color name="zone_fat_burn">#A5D6A7</color>
    <color name="zone_cardio">#FFAB91</color>
    <color name="zone_peak">#EF9A9A</color>
</resources>
```
`ReadingAdapter.java`:
```java
    // 1) in onBindViewHolder, replace the txtZone line
        Zone zone = Zone.of(hr, maxHr);
        h.txtZone.setText(zone.name());
        h.txtZone.setTextColor(ContextCompat.getColor(h.itemView.getContext(), colorOf(zone)));

    // 2) below getItemCount
    // colour carries meaning: one colour per zone (values/ and values-night/)
    private static int colorOf(Zone zone) {
        switch (zone) {
            case REST: return R.color.zone_rest;
            case FAT_BURN: return R.color.zone_fat_burn;
            case CARDIO: return R.color.zone_cardio;
            default: return R.color.zone_peak;
        }
    }
```
Alt+Enter on `ContextCompat` (`androidx.core.content.ContextCompat`).

## Lesson 4 imports (if Alt+Enter is not your friend today)
```java
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import at.mci.zonecheck.model.Zone;
import java.util.ArrayList;
```

---

# Lesson 3 – One Screen

MCI App Development, Lesson 3. We build the app together, step by step.
**✋ = hands up when it runs.** "By hand" = do it yourself, slowly. "Paste" = copy the block below.

Test: age **30**, heart rate **145** → `145 bpm = CARDIO (76%, indoor)`

Lost? File → New → **Project from Version Control** → paste this repo's URL. You get the finished app.

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

# Lesson 3 blocks

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

