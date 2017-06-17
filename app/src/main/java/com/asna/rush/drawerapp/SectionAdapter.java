package com.asna.rush.drawerapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SectionAdapter extends ArrayAdapter<String> {

    Context context;
    List<Section> sections;

    public SectionAdapter(Context context, List<Section> sections) {
        super(context, R.layout.section_row);
        this.context = context;
        this.sections = sections;
    }

    @Override
    public int getCount() {
        return sections.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.section_row, parent, false);
            holder.tvId = (TextView) convertView.findViewById(R.id.tvId);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Section section = sections.get(position);

        holder.tvId.setText(section.Id + "");
        holder.tvName.setText(section.Name);
        return convertView;
    }

    static class ViewHolder {
        TextView tvId;
        TextView tvName;
    }
}
