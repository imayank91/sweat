package com.app.sweat.model;

import java.util.ArrayList;

/**
 * Created by mayank on January 08 2020
 *
 * Model class for program suggestions
 */

public class ProgramSuggestionsModel {

    public int id;
    public String name;
    public ArrayList<Attributes> attributes;
    public Trainer trainer;
    public ArrayList<Tags> tags;
    public ArrayList<String> stringTags;

    public class Attributes {
        public String name;
        public String value;
        public String total;
    }

    public class Trainer {
        public String name;
        public String image_url;
    }

    public class Tags {
        public String name;
    }

}
