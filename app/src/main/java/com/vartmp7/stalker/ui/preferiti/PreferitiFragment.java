package com.vartmp7.stalker.ui.preferiti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vartmp7.stalker.R;


/**
 * @author Xiaowei Wen, Lorenzo Taschin
 */
public class PreferitiFragment extends Fragment {
    public static final String TAG ="com.vartmp7.stalker.ui.preferiti.PreferitiFragment";

    private PreferitiViewModel preferitiViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        preferitiViewModel =
                ViewModelProviders.of(this).get(PreferitiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_preferiti, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        preferitiViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
