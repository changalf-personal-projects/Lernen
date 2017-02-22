package com.example.android.lernen.main.Main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.android.lernen.R;

/**
 * Created by alfredchang on 2017-02-20.
 */

public class NotesFragment extends Fragment {

    private EditText mEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        mEditText = (EditText) rootView.findViewById(R.id.edittext_notes);
        return rootView;
    }
}
