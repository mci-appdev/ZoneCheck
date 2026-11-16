package at.mci.zonecheck;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
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

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView txtBpm, txtZone;

        ViewHolder(View row) {
            super(row);
            txtBpm = row.findViewById(R.id.txtBpm);
            txtZone = row.findViewById(R.id.txtZone);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reading, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder h, int position) {
        int hr = readings.get(position);
        Zone zone = Zone.of(hr, maxHr);
        h.txtBpm.setText(h.itemView.getContext().getString(R.string.bpm_fmt, hr));
        h.txtZone.setText(zone.name());
        h.txtZone.setTextColor(ContextCompat.getColor(h.itemView.getContext(), colorOf(zone)));
    }

    @Override
    public int getItemCount() {
        return readings.size();
    }

    // L4-M: colour carries meaning, one colour per zone (values/ and values-night/)
    private static int colorOf(Zone zone) {
        switch (zone) {
            case REST: return R.color.zone_rest;
            case FAT_BURN: return R.color.zone_fat_burn;
            case CARDIO: return R.color.zone_cardio;
            default: return R.color.zone_peak;
        }
    }
}
