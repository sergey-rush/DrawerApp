package com.asna.rush.drawerapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.asna.rush.data.DataAccess;
import com.google.android.gms.plus.PlusOneButton;

import static com.asna.rush.drawerapp.R.id.btnCreateDatabase;

public class DatabaseManagerFragment extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private OnFragmentInteractionListener mListener;

    public DatabaseManagerFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DatabaseManagerFragment newInstance(String param1, String param2) {
        DatabaseManagerFragment fragment = new DatabaseManagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    EditText txbParentId;
    EditText txbName;
    TextView txvDisplay;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_database_manager, container, false);

        txbParentId = (EditText) view.findViewById(R.id.txbParentId);
        txbName = (EditText) view.findViewById(R.id.txbName);
        txvDisplay = (TextView) view.findViewById(R.id.txvDisplay);

        final Button btnCreateDatabase = (Button) view.findViewById(R.id.btnCreateDatabase);
        btnCreateDatabase.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        CreateDatabase(v);
                    }
                }
        );

        final Button btnInsertSection = (Button) view.findViewById(R.id.btnInsertSection);
        btnInsertSection.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        insertSection(v);
                    }
                }
        );

        return view;
    }

    private void insertSection(View view)
    {
        int parentId =Integer.parseInt(txbParentId.getText().toString());
        String name = txbName.getText().toString();

        //DataManager dataManager = new DataManager(this.getContext());
        //long result = dataManager.insertSection(parentId, name);
        //txvDisplay.setText(Long.toString(result));
    }

    private void CreateDatabase(View view)
    {
        DataAccess dataAccess = DataAccess.getInstance(this.getContext());
        dataAccess.createDatabase();
        txvDisplay.setText("Create Database called!");
        //Toast.makeText(this, "CreateDatabase!", Toast.LENGTH_LONG).show();
        //dataProvider.CreateDatabase();
        //lblInfo.setText("Database created!");
    }



    @Override
    public void onResume() {
        super.onResume();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
