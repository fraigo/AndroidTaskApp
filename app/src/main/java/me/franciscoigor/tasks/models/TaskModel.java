package me.franciscoigor.tasks.models;

import me.franciscoigor.tasks.base.DataModel;

public class TaskModel extends DataModel {

    public static final String FIELD_TITLE = "title";
    public static final String FIELD_DESCRIPTION = "description";

    public TaskModel(){
        super("tasks");
        addField(FIELD_TITLE);
        addField(FIELD_DESCRIPTION);
        addField("category");
        addField("finished");
        addField("tags");
    }

    public TaskModel(String title,String description){
        super("tasks");
        addField(FIELD_TITLE);
        addField(FIELD_DESCRIPTION);
        addField("category");
        addField("finished");
        addField("tags");

        setValue(FIELD_TITLE, title);
        setValue(FIELD_DESCRIPTION, description);
    }
}
