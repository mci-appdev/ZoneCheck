# Zone Check

The course app of MCI App Development (WS26). It grows by one feature block per lesson, built together in class.

- **Build it with us:** [SNIPPETS.md](SNIPPETS.md) – the steps of every build-along and every copy-paste block
- **Lost?** Every build-along ends in a tag: `git checkout l04-1` (`l04-2`, `l04-3`, `lesson-04`) in the Android Studio terminal, then Run. No clone yet? Android Studio → File → New → Project from Version Control → this repo's URL
- Test data: age 30, heart rate 145 / 170 / 92 → `145 bpm = CARDIO (76%, indoor)`, Session: `3 readings, max HR 190`

| Lesson | Tag | Zone Check can |
|---|---|---|
| 3 (03.11.) | `lesson-03` | one screen: enter age and heart rate, get your heart-rate zone |
| 4 (17.11.) | `lesson-04` | a second screen (explicit Intent), survives rotation, lists every reading (`RecyclerView`), shares the summary (implicit Intent) |

Java, Views (XML layouts), `minSdk 26`. The zone logic (`model/Zone.java`) is Exercise 3 from Lesson 3.
