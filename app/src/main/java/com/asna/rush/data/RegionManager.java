package com.asna.rush.data;

public abstract  class RegionManager extends DataAccess {

    private static RegionManager _instance = new RegionProvider();

    public static RegionManager getInstance()
    {
        return _instance;
    }
}
