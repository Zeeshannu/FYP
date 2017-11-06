package com.example.zeeshan.tablename;

import android.app.ProgressDialog;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by Zeeshan on 10/5/2017.
 */

    public class ProgressDialogue extends AppCompatActivity {



        @VisibleForTesting

        public ProgressDialog mProgressDialog;



        public void showProgressDialog() {

            if (mProgressDialog == null) {

                mProgressDialog = new ProgressDialog(this);

                mProgressDialog.setMessage(getString(R.string.loading));

                mProgressDialog.setIndeterminate(true);

            }



            mProgressDialog.show();

        }



        public void hideProgressDialog() {

            if (mProgressDialog != null && mProgressDialog.isShowing()) {

                mProgressDialog.dismiss();

            }

        }



        @Override

        public void onStop() {

            super.onStop();

            hideProgressDialog();

        }



    }