package com.kvbashkirov.app1;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by kvbashkirov on 05.04.2017.
 */

public class StudentCursorAdapter extends CursorAdapter {

    public StudentCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView studentName = (TextView) view.findViewById(R.id.student_name);
        TextView studentBalance = (TextView) view.findViewById(R.id.student_balance);

        String name = cursor.getString(cursor.getColumnIndexOrThrow(App1Contract.StudentEntry.COLUMN_NAME_NAME));
        int balance = cursor.getInt(cursor.getColumnIndexOrThrow(App1Contract.StudentEntry.COLUMN_NAME_BALANCE));

        studentName.setText(name);
        studentBalance.setText(String.valueOf(balance));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.student_list_item, parent);
    }
}
