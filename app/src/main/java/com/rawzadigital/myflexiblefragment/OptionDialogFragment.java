package com.rawzadigital.myflexiblefragment;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class OptionDialogFragment extends DialogFragment implements View.OnClickListener {

    private Button btnClose, btnChoose;
    private RadioGroup rgOptions;
    private RadioButton rbSaf, rbMou, rbLvg, rbMoyes;
    private OnOptionDialogListener onOptionDialogListener;

    public OptionDialogFragment() {
        //required public constructor
    }
    public OnOptionDialogListener getOnOptionDialogListener() {
        return onOptionDialogListener;
    }

    public void setOnOptionDialogListener(OnOptionDialogListener onOptionDialogListener) {
        this.onOptionDialogListener = onOptionDialogListener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_option_dialog, container, false);

        btnClose = view.findViewById(R.id.btn_close);
        btnClose.setOnClickListener(this);
        btnChoose = view.findViewById(R.id.btn_choose);
        btnChoose.setOnClickListener(this);

        rgOptions = view.findViewById(R.id.rg_options);
        rbSaf = view.findViewById(R.id.rb_saf);
        rbMou = view.findViewById(R.id.rb_mou);
        rbLvg = view.findViewById(R.id.rb_lvg);
        rbMoyes = view.findViewById(R.id.rb_moyes);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                getDialog().cancel();
                break;
            case R.id.btn_choose:
                int checkedRadioButtonId = rgOptions.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1) {
                    String coach = null;
                    switch (checkedRadioButtonId) {
                        case R.id.rb_saf:
                            coach = rbSaf.getText().toString().trim();
                            break;
                        case R.id.rb_mou:
                            coach = rbMou.getText().toString().trim();
                            break;
                        case R.id.rb_lvg:
                            coach = rbLvg.getText().toString().trim();
                            break;
                        case R.id.rb_moyes:
                            coach = rbMoyes.getText().toString().trim();
                            break;
                    }
                    getOnOptionDialogListener().onOptionChosen(coach);
                    getDialog().cancel();
                }
                break;
        }
    }

    public interface OnOptionDialogListener {
        void onOptionChosen(String text);
    }


}
