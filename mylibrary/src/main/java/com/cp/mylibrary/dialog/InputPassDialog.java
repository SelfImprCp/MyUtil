package com.cp.mylibrary.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cp.mylibrary.R;

/**
 * Created by Jerry on 2016/8/23.
 */
public class InputPassDialog extends Dialog {
    private EditText edit_input_pass_dialog_txt;
    private TextView edittext_input_pass_dialog_cannel_btn_b, edittext_input_pass_dialog_sure_btn_b;
    private TextView edittext_input_pass_dialog_title;
    private TextView edittext_input_pass_dialog_txt,edittext_input_pass_dialog_txt_two;

    public InputPassDialog(Context context, String title, String tagOne,String tagTwo,String hint) {
        super(context, R.style.dialog_common);
        setCustomDialog();

        edittext_input_pass_dialog_title.setText(title);
        edittext_input_pass_dialog_txt.setText(tagOne);
        edittext_input_pass_dialog_txt_two.setText(tagTwo);
        edit_input_pass_dialog_txt.setHint(hint);



    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dilog_pass_edittext, null);
        edittext_input_pass_dialog_title = (TextView) mView.findViewById(R.id.edittext_input_pass_dialog_title);
        edittext_input_pass_dialog_txt = (TextView) mView.findViewById(R.id.edittext_input_pass_dialog_txt);
        edittext_input_pass_dialog_txt_two = (TextView) mView.findViewById(R.id.edittext_input_pass_dialog_txt_two);



        edit_input_pass_dialog_txt = (EditText) mView.findViewById(R.id.edit_input_pass_dialog_txt);

//        edit_config_dialog_txt.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD);

        edittext_input_pass_dialog_cannel_btn_b = (TextView) mView.findViewById(R.id.edittext_input_pass_dialog_cannel_btn_b);
        edittext_input_pass_dialog_sure_btn_b = (TextView) mView.findViewById(R.id.edittext_input_pass_dialog_sure_btn_b);
        super.setContentView(mView);
    }

    public View getEditText() {
//
//        edit_config_dialog_txt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//


        return edit_input_pass_dialog_txt;
    }

    @Override
    public void setContentView(int layoutResID) {
    }





    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
    }

    @Override
    public void setContentView(View view) {
    }

    /**
     * 确定键监听器
     *
     * @param listener
     */
    public void setOnPositiveListener(View.OnClickListener listener) {
        edittext_input_pass_dialog_sure_btn_b.setOnClickListener(listener);
    }

    /**
     * 取消键监听器
     *
     * @param listener
     */
    public void setOnNegativeListener(View.OnClickListener listener) {
        edittext_input_pass_dialog_cannel_btn_b.setOnClickListener(listener);
    }
}