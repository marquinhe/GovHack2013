package com.viewpagerindicator.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TextView;




public final class Maps extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";
    private TableLayout fbTableLayout;

    private String mContent = "???";

    public static Maps newInstance(String query) {
        Maps map = new Maps();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append(query).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        map.mContent = builder.toString();

        return map;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Button button = new Button(getActivity());
        LinearLayout layout = new LinearLayout(getActivity());

        button.setText("New button");
        layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);
        //layout.addView(text);
        layout.addView(button);

        return layout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
    }
}
