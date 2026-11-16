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
