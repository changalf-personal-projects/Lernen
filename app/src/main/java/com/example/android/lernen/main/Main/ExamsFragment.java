package com.example.android.lernen.main.Main;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.android.lernen.R;

/**
 * Created by alfredchang on 2017-02-20.
 */

public class ExamsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exams, container, false);

        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDialog();
            }
        });

        return rootView;
    }

    public void startDialog() {
        Dialog examDialog = new Dialog(getActivity());
        examDialog.setContentView(R.layout.fragment_create_exam);

        EditText examName = (EditText) getActivity().findViewById(R.id.exam_name);
        EditText examTime = (EditText) getActivity().findViewById(R.id.exam_time);
        EditText examLocation = (EditText) getActivity().findViewById(R.id.exam_location);

        examDialog.show();

    }
}
