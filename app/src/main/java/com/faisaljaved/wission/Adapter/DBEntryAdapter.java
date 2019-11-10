package com.faisaljaved.wission.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.faisaljaved.wission.db.DBEntry;
import com.faisaljaved.wission.R;

import java.util.List;

public class DBEntryAdapter extends ArrayAdapter<DBEntry> {

    private AppCompatActivity context;

    private List<DBEntry> dbEntryList;

    public DBEntryAdapter(@NonNull AppCompatActivity context, List<DBEntry> dbEntryList) {
        super(context, R.layout.list_layout, dbEntryList);

        this.context = context;
        this.dbEntryList = dbEntryList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listViewItem = context.getLayoutInflater().inflate(R.layout.list_layout,null,true);

        TextView name = (TextView) listViewItem.findViewById(R.id.name_textview);
        TextView email = (TextView) listViewItem.findViewById(R.id.email_textview);
        TextView phone = (TextView) listViewItem.findViewById(R.id.phone_textview);

        DBEntry dbEntry = dbEntryList.get(position);

        name.setText(dbEntry.getName());
        email.setText(dbEntry.getEmail());
        phone.setText(dbEntry.getPhone());

        return listViewItem;
    }
}
