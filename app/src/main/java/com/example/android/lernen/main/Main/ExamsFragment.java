package com.example.android.lernen.main.Main;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.lernen.R;
import com.example.android.lernen.main.Database.ExamsDatabaseHelper;

/**
 * Created by alfredchang on 2017-02-20.
 */

public class ExamsFragment extends Fragment {

    ExamsDatabaseHelper examsDb;

    // Array to store each TextView that will be filled with exams
    final TextView[] exams = new TextView[4];
    TextView firstExam;
    TextView secondExam;
    TextView thirdExam;
    TextView fourthExam;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_exams, container, false);
        examsDb = new ExamsDatabaseHelper(getActivity());
        firstExam = (TextView) rootView.findViewById(R.id.firstExam);
        secondExam = (TextView) rootView.findViewById(R.id.secondExam);
        thirdExam = (TextView) rootView.findViewById(R.id.thirdExam);
        fourthExam = (TextView) rootView.findViewById(R.id.fourthExam);

        displayData(rootView);

        FloatingActionButton fabAdd = (FloatingActionButton) rootView.findViewById(R.id.fabAdd);
        FloatingActionButton fabRemove = (FloatingActionButton) rootView.findViewById(R.id.fabRemove);
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startDialog();
            }
        });
        fabRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });

        // Number identifies which table row to update
        firstExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData("1");
            }
        });
        secondExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData("2");
            }
        });
        thirdExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData("3");
            }
        });
        fourthExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData("4");
            }
        });

        return rootView;
    }

    // Helper method to display saved data when this fragment is created
    public void displayData(View rootView) {
        Cursor cursor = examsDb.query();

        // ExamFragment stuff
//        firstExam = (TextView) rootView.findViewById(R.id.firstExam);
//        secondExam = (TextView) rootView.findViewById(R.id.secondExam);
//        thirdExam = (TextView) rootView.findViewById(R.id.thirdExam);
//        fourthExam = (TextView) rootView.findViewById(R.id.fourthExam);

        // Populate TextViews manually
        exams[0] = firstExam;
        exams[1] = secondExam;
        exams[2] = thirdExam;
        exams[3] = fourthExam;

        boolean rowIndex = cursor.moveToFirst();
        int counter = cursor.getPosition();

        while (rowIndex) {
            System.out.println("Row index: " + rowIndex);
            System.out.println("Counter: " + counter);
            System.out.println("Number of rows: " + cursor.getCount() + "\n");
            try {
                String data = getColumnData(ExamsDatabaseHelper.COLUMN_2, cursor) + "\n" +
                        getColumnData(ExamsDatabaseHelper.COLUMN_3, cursor) + "\n" +
                        getColumnData(ExamsDatabaseHelper.COLUMN_4, cursor);

                exams[counter].setText(data);
            } catch (IllegalArgumentException e) {
                System.out.println("Error in displayData: " + e);
            }
            counter++;
            rowIndex = cursor.moveToNext();
        }
    }

    public boolean saveData(EditText examName, EditText examTime, EditText examLocation) {
        boolean isSuccess = examsDb.insert(examName.getText().toString(), examTime.getText().toString(),
                examLocation.getText().toString());
        return isSuccess;
    }

    public String getColumnData(String column, Cursor cursor) {
        String data = "";
        try {
            if (cursor.getCount() >= 1) {
                // Could also use cursor.getString(columnIndex) to get data in column
                data = cursor.getString(cursor.getColumnIndexOrThrow(column));
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error in getColumnData: " + e);
        }
        return data;
    }

    // Helper method to delete data
    public void deleteData() {
        // TODO: Fix remove button positioning
        // TODO: Display all available data upon start up
        // TODO: Can only add new data by pressing add button
        // TODO: Can only update data by pressing on a non-empty data
        // TODO
    }

    // Helper method to update data;
    // Different from deleteData + insertData because
    // this method doesn't need data to first be deleted
    public void updateData(String id) {
        final Dialog examDialog = new Dialog(getActivity());
        examDialog.setContentView(R.layout.fragment_create_exam);

        final EditText examName = (EditText) examDialog.findViewById(R.id.exam_name);
        final EditText examTime = (EditText) examDialog.findViewById(R.id.exam_time);
        final EditText examLocation = (EditText) examDialog.findViewById(R.id.exam_location);
        final Button addExamButton = (Button) examDialog.findViewById(R.id.add_exam_button);

        final int numId = Integer.parseInt(id);

        addExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numId == 1) {
                    firstExam.setText(examName.getText().toString() + "\n" + examTime.getText().toString() + "\n"
                            + examLocation.getText().toString());
                } else if (numId == 2) {
                    secondExam.setText(examName.getText().toString() + "\n" +  examTime.getText().toString() + "\n"
                            + examLocation.getText().toString());
                } else if (numId == 3) {
                    thirdExam.setText(examName.getText().toString() + "\n" +  examTime.getText().toString() + "\n"
                            + examLocation.getText().toString());
                } else {
                    fourthExam.setText(examName.getText().toString() + "\n" +  examTime.getText().toString() + "\n"
                            + examLocation.getText().toString());
                }

                // Test
                //Cursor c = examsDb.query();
                System.out.println("Exam name: " + examName.getText().toString());
                System.out.println("Exam time: " + examTime.getText().toString());
                System.out.println("Exam location: " + examLocation.getText().toString());
//                System.out.println("First column: " + c.getColumnName(0) + " = "
//                        + c.getString(c.getColumnIndex(ExamsDatabaseHelper.COLUMN_1)));
//                System.out.println("Second column: " + c.getColumnName(1) + " = "
//                        + c.getString(c.getColumnIndex(ExamsDatabaseHelper.COLUMN_2)));

                examsDb.update(Integer.toString(numId), examName.getText().toString(), examTime.getText().toString(), examLocation.getText().toString());
                examDialog.dismiss();
            }
        });

        examDialog.show();

        // Test
        //System.out.println("Number of rows updated: " + numRowsUpdated);
    }

    public void startDialog() {
        final Dialog examDialog = new Dialog(getActivity());
        examDialog.setContentView(R.layout.fragment_create_exam);

        // CreateExamFragment stuff
        // Variables made final due to access in inner class
        final EditText examName = (EditText) examDialog.findViewById(R.id.exam_name);
        final EditText examTime = (EditText) examDialog.findViewById(R.id.exam_time);
        final EditText examLocation = (EditText) examDialog.findViewById(R.id.exam_location);
        final Button addExamButton = (Button) examDialog.findViewById(R.id.add_exam_button);

        // ExamFragment stuff
//        firstExam = (TextView) getActivity().findViewById(R.id.firstExam);
//        secondExam = (TextView) getActivity().findViewById(R.id.secondExam);
//        thirdExam = (TextView) getActivity().findViewById(R.id.thirdExam);
//        fourthExam = (TextView) getActivity().findViewById(R.id.fourthExam);

        // Populate TextViews manually
        exams[0] = firstExam;
        exams[1] = secondExam;
        exams[2] = thirdExam;
        exams[3] = fourthExam;

        addExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!examName.getText().toString().equals("")) {
                    // Insert exam into TextView
                    for (int i = 0; i < exams.length; i++) {
                        if (exams[i] == null || exams[i].getText().equals("")) {
                            exams[i].setText(examName.getText().toString() + "\n" + examTime.getText().toString()
                                    + "\n" + examLocation.getText().toString());

                            boolean isSuccess = saveData(examName, examTime, examLocation);

                            // Tests; remove later
                            if (isSuccess) {
                                Toast.makeText(getActivity(), "Data successfully inserted!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getActivity(), "Data not inserted.", Toast.LENGTH_LONG).show();
                            }

                            examDialog.dismiss();
                            break;
                        }
                        if (i >= 3) {
                            Toast.makeText(getActivity(), "You are only allowed a maximum of 4 exams!", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    Toast.makeText(getActivity(), "Enter exam name.", Toast.LENGTH_LONG).show();
                }
            }
        });

        examDialog.show();
    }
}
