package com.qf.administrator.baozou.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qf.administrator.baozou.MainActivity;
import com.qf.administrator.baozou.R;
import com.qf.administrator.baozou.adapter.SimpleChatAdapter;
import com.qf.administrator.baozou.entity.DicqConstant;
import com.qf.administrator.baozou.entity.MessageInfo;

import java.io.File;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class FanKuiFragment extends Fragment{
    private EditText edit;
    private ListView show;
    private ImageView send;
    private ImageView feedback_contact;
    private SimpleChatAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.from(getActivity()).inflate(R.layout.fankuifragment,null,false);
        adapter = new SimpleChatAdapter(getActivity());
        adapter.addMessage(new MessageInfo(DicqConstant.DEFAULTHOSTMAC,DicqConstant.HOSTMSG,DicqConstant.hostprortait));

        edit = (EditText)view.findViewById(R.id.team_singlechat_id_edit);
        show = (ListView)view.findViewById(R.id.team_singlechat_id_showlist);
        send = (ImageView)view.findViewById(R.id.team_singlechat_id_send);
        feedback_contact = (ImageView)view.findViewById(R.id.feedback_contact);

        show.setAdapter(adapter);
        send.setOnClickListener(new MyClickListener());
        feedback_contact.setOnClickListener(new MyClickListener());
        return view;
    }
    class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.team_singlechat_id_send:
                    send();
                    break;
                case R.id.feedback_contact:
                    showCustomDialog();
                    break;

                default:
                    break;
            }
        }
        /**
         * 发送聊天信息
         */
        private void send() {
            if(edit.getText().toString().length()!=0){
                adapter.addMessage(new MessageInfo(DicqConstant.DEFAULTMAC,edit.getText().toString(),DicqConstant.prortait));
                edit.setText(null);
            }else{
                Toast.makeText(getActivity(), "不能发送空消息", Toast.LENGTH_LONG).show();
            }
        }
        public void showCustomDialog()
        {
            final AlertDialog customDialog;
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.feedback_contact_dialog, null);
            builder.setView(dialogView);
            customDialog = builder.create();
            customDialog.show();

            TextView btnSureButton = (TextView)dialogView.findViewById(R.id.btnSave);
            TextView btnCancleButton = (TextView)dialogView.findViewById(R.id.btnCancel);

            btnCancleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
                }
            });
            btnSureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    customDialog.dismiss();
                }
            });
        }
        private String formatSize(long size) {
            return Formatter.formatFileSize(getActivity(), size);
        }
    }
}
