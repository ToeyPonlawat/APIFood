package api.dev.apifood;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.Permission;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReturnBillFragment extends Fragment {

    private MyConstant myConstant = new MyConstant();

    public ReturnBillFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Return Controller
        returnController();
//        Exit Controller
        exitController();
    } // Main Method

    private void exitController() {
        Button button = getView().findViewById(R.id.btnExit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity()
                        .getSharedPreferences(myConstant.getFileSharedPreferences(),
                                Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
            }
        });
    }

    private void returnController() {
        Button button = getView().findViewById(R.id.btnReturnBill);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });
    }

    private void checkPermission() {
//        Read Value From SharedPreferences
        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences(myConstant.getFileSharedPreferences(),
                        Context.MODE_PRIVATE);
        String ptUsrCode = sharedPreferences.getString("ptUserCode", "");
        Log.d("6MayV1", "ptUserCode = Current ==> " + ptUsrCode);

        String ptPosCode = "002";
        String ptDocNo = "S190001002-0000063";

        try {

            CheckPermissionThread checkPermissionThread = new CheckPermissionThread(getActivity());
            checkPermissionThread.execute(ptPosCode,ptUsrCode,ptDocNo,myConstant.getUrlCheckPermission());

            String response = checkPermissionThread.get();
            Log.d("6MayV1", "response ==>  " + response);

            JSONObject jsonObject = new JSONObject(response);
            jsonObject =  new JSONObject(jsonObject.get(jsonObject.keys().next()).toString());
            Log.d("6MayV1", "jsonObject ==>  " + jsonObject);

            String messageDetails = jsonObject.getString("MessageDetails");
            Log.d("6MayV1", "Message Detail ==>  " + messageDetails);

            JSONArray jsonArray = new JSONArray(messageDetails);
            if (messageDetails.equals("[\"API_AuthenNotFound\"]")) {
//                Permission Pass
            } else {
                Log.d("6MayV2", "result ==>  " + messageDetails);
            }

        }catch (Exception e){
            Log.d("6MayV1","e returnBill ==>  " + e.toString());
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_return_bill, container, false);
    }

}
