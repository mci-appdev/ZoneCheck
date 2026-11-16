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
