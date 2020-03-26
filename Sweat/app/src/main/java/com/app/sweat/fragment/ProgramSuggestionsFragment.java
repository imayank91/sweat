package com.app.sweat.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.app.sweat.R;
import com.app.sweat.adapter.ProgramSuggestionAdapter;
import com.app.sweat.databinding.FragmentProgramSuggestionsBinding;
import com.app.sweat.model.ProgramSuggestionsModel;
import com.app.sweat.utils.NetworkUtils;
import com.app.sweat.viewmodel.ProgramSuggestionsViewModel;

import java.util.ArrayList;

/**
 * Created by mayank on January 08 2020
 */
public class ProgramSuggestionsFragment extends Fragment {

    private ProgramSuggestionsViewModel viewModel;
    private FragmentProgramSuggestionsBinding viewBinding;
    private ProgramSuggestionAdapter programSuggestionAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_program_suggestions, container, false);
        viewModel = ViewModelProviders.of(this).get(ProgramSuggestionsViewModel.class);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewBinding.noNetworkLayout.setFragment(this);
        setUpRecyclerView();

        checkInternet();

        //Observing list to display it on adapter
        viewModel.mutableProgramList.observe(getViewLifecycleOwner(), new Observer<ArrayList<ProgramSuggestionsModel>>() {
            @Override
            public void onChanged(ArrayList<ProgramSuggestionsModel> programSuggestionsModels) {
                programSuggestionAdapter.setProgramList(programSuggestionsModels);
            }
        });
    }

    private void setUpRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        viewBinding.recyclerView.setLayoutManager(linearLayoutManager);
        viewBinding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        programSuggestionAdapter = new ProgramSuggestionAdapter(getContext());
        programSuggestionAdapter.setHasStableIds(true);
        viewBinding.recyclerView.setAdapter(programSuggestionAdapter);
    }

    public void checkInternet() {
        if(NetworkUtils.checkNetworkConnection(getContext())){
            viewBinding.noNetworkLayout.rootView.setVisibility(View.GONE);

            //Fetching the api-response from resources
            viewModel.fetchProgramList();
        }else{
            viewBinding.noNetworkLayout.rootView.setVisibility(View.VISIBLE);
        }
    }
}
