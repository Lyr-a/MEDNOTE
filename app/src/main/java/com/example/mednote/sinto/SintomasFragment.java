package com.example.mednote.sinto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mednote.R;
import com.example.mednote.recvi.SintomasAdapter;
import com.example.mednote.recvi.SintomasItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SintomasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class SintomasFragment extends Fragment {


    static int NEW_ITEM_REQUEST = 1;
    List<SintomasItem> SinItens = new ArrayList<>();
    SintomasAdapter sintomasAdapter;

    //region BARULO

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SintomasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SintomasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SintomasFragment newInstance(String param1, String param2) {
        SintomasFragment fragment = new SintomasFragment();
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

    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_sintomas, container, false);

        FloatingActionButton BtnNewSintoma = v.findViewById(R.id.FbtnSintomasCreate);



        sintomasAdapter = new SintomasAdapter(this, SinItens);

        RecyclerView RvSintomas = v.findViewById(R.id.RvSintomas);
        RvSintomas.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        RvSintomas.setLayoutManager(layoutManager);
        RvSintomas.setAdapter(sintomasAdapter);


        BtnNewSintoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SintomasAddActivity.class);
                startActivityForResult(intent, NEW_ITEM_REQUEST);

            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        { super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == NEW_ITEM_REQUEST) {
                if (resultCode == Activity.RESULT_OK) {
                    if (data != null) {

                        String STitle = data.getStringExtra("SinTitle");
                        String SDesc = data.getStringExtra("SinDesc");

                        SintomasItem novoSintoma = new SintomasItem();

                        novoSintoma.Title = STitle;
                        novoSintoma.Desc = SDesc;

                        SinItens.add(novoSintoma);

                        sintomasAdapter.notifyItemInserted(SinItens.size()-1);
                    }
                }
            }
        }
    }
}