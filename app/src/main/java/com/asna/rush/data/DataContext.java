package com.asna.rush.data;

import android.content.Context;
import android.util.Log;

import com.asna.rush.core.Region;
import com.asna.rush.core.Section;
import com.asna.rush.drawerapp.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DataContext {
    Context context;
    public DataContext(Context context)
    {
        this.context = context;
    }

    String separator = ";";

    public List<Section> ReadSections() {
        List<Section> sections = new ArrayList<Section>();
        BufferedReader reader = null;
        InputStream inputStream = null;
        String line = "";
        try {
            inputStream = context.getResources().openRawResource(R.raw.sections);
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(separator);
                int id = Integer.parseInt(data[0]);
                int parentId = Integer.parseInt(data[1]);
                String name = data[2];
                Section section = new Section(id, parentId, name);
                sections.add(section);
            }

            Log.d("ReadSections", "Completed with size: " + sections.size());

        } catch (IOException e) {
            Log.wtf("ReadSections", "Error reading data file on line: " + line, e);
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sections;

    }

    public List<Region> ReadRegions() {
        List<Region> regions = new ArrayList<Region>();
        BufferedReader reader = null;
        InputStream inputStream = null;
        String line = "";
        try {
            inputStream = context.getResources().openRawResource(R.raw.regions);
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(separator);
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                Region region = new Region(id, name);
                regions.add(region);
            }

            Log.d("PopulateRegions", "Completed with size: " + regions.size());

        } catch (IOException e) {
            Log.wtf("PopulateRegions", "Error reading data file on line: " + line, e);
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return regions;
    }
}
