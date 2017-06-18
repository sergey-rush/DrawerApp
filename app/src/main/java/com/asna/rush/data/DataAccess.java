package com.asna.rush.data;

public abstract class DataAccess {
    
    public static RegionManager getRegions()
    {
        return RegionManager.getInstance();
    }
}
