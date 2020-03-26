package com.app.sweat.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.app.sweat.model.ProgramSuggestionsModel;
import com.app.sweat.utils.StringContract;
import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by mayank on January 08 2020
 */
public class ProgramSuggestionsViewModel extends ViewModel {

    public MutableLiveData<ArrayList<ProgramSuggestionsModel>> mutableProgramList = new MutableLiveData<>();

    @SuppressWarnings("UnstableApiUsage")
    public void fetchProgramList() {

        InputStream inputStream = Objects.requireNonNull(ProgramSuggestionsViewModel.class.getClassLoader()).getResourceAsStream(String.format("api-response/%s", StringContract.apiResponseFile));
        try {
           String jsonOutput = new String(ByteStreams.toByteArray(inputStream));
            Gson gson = new Gson();
            Type listType = new TypeToken<List<ProgramSuggestionsModel>>(){}.getType();
            ArrayList<ProgramSuggestionsModel> programs = gson.fromJson(jsonOutput, listType);

            for(ProgramSuggestionsModel model: programs){
                if(model.tags != null) {
                    ArrayList<String> stringsTag = new ArrayList<>();
                    for (ProgramSuggestionsModel.Tags tags : model.tags) {
                        stringsTag.add(tags.name);
                    }
                    model.stringTags = stringsTag;
                }
            }
            mutableProgramList.setValue(programs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
