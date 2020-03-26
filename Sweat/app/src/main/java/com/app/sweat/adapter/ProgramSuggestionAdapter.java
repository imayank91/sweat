package com.app.sweat.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.app.sweat.R;
import com.app.sweat.databinding.ListItemProgramSuggestionsBinding;
import com.app.sweat.model.ProgramSuggestionsModel;
import com.app.sweat.viewholder.ProgramListViewHolder;

import java.util.ArrayList;

/**
 * Created by mayank on January 08 2020
 */
public class ProgramSuggestionAdapter extends RecyclerView.Adapter<ProgramListViewHolder> {

    private ArrayList<ProgramSuggestionsModel> programList = new ArrayList<>();
    private Drawable filledSweatDrops;
    private Typeface typeface;

    public ProgramSuggestionAdapter(Context context) {
        filledSweatDrops = context.getDrawable(R.drawable.sweat_drop_filled);
        typeface = ResourcesCompat.getFont(context, R.font.opensans_semibold);
    }

    @NonNull
    @Override
    public ProgramListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ListItemProgramSuggestionsBinding binding = DataBindingUtil.inflate(
                layoutInflater, R.layout.list_item_program_suggestions, parent, false);
        return new ProgramListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramListViewHolder holder, int position) {

        ProgramSuggestionsModel item = programList.get(position);

        String intensity = item.attributes.get(0).value;

        if (intensity.equalsIgnoreCase("1.0")) {
            holder.binding.ivSweatDropOne.setImageDrawable(filledSweatDrops);
        } else if (intensity.equalsIgnoreCase("2.0")) {
            holder.binding.ivSweatDropOne.setImageDrawable(filledSweatDrops);
            holder.binding.ivSweatDropTwo.setImageDrawable(filledSweatDrops);
        } else {
            holder.binding.ivSweatDropOne.setImageDrawable(filledSweatDrops);
            holder.binding.ivSweatDropTwo.setImageDrawable(filledSweatDrops);
            holder.binding.ivSweatDropThree.setImageDrawable(filledSweatDrops);

        }

        if (item.stringTags != null) {
            holder.binding.tagView.setTagTypeface(typeface);
            holder.binding.tagView.setTags(item.stringTags);
        }

        holder.binding.setModel(item);

    }

    @Override
    public long getItemId(int position) {
        return programList.get(position).id;
    }

    @Override
    public int getItemCount() {
        return programList.size();
    }

    public void setProgramList(ArrayList<ProgramSuggestionsModel> programList) {
        this.programList = programList;
    }
}
