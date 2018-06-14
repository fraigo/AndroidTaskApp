package me.franciscoigor.tasks.models;

import me.franciscoigor.tasks.base.DatabaseSchema;

public class TaskSchema extends DatabaseSchema{

    public TaskSchema(){
        super("tasks");
        addField("title");
        addField("description");
        addField("category");
        addField("finished");
        addField("tags");
    }
}
