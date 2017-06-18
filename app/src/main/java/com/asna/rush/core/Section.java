package com.asna.rush.core;

public class Section {
    public int Id;
    public int ParentId;
    public String Name;

    public Section(int id, int parentId, String name)
    {
        Id = id;
        ParentId = parentId;
        Name = name;
    }
}
