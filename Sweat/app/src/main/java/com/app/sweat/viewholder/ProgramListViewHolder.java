package com.app.sweat.viewholder;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.sweat.databinding.ListItemProgramSuggestionsBinding;

/**
 * Created by mayank on January 08 2020
 */
public class ProgramListViewHolder extends RecyclerView.ViewHolder {

    public final ListItemProgramSuggestionsBinding binding;

    public ProgramListViewHolder(@NonNull ListItemProgramSuggestionsBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }
}

