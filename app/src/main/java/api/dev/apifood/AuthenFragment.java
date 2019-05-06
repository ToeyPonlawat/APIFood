package api.dev.apifood;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class AuthenFragment extends Fragment {

    private String ptUserCode;
    private MyConstant myConstant = new MyConstant();

    public AuthenFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Login Contoller
        loginContoller();
    }

    private void loginContoller() {
        Button button = getView().findViewById(R.id.btnLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAuthen();

            }
        });
    }

    private void checkAuthen() {

        EditText userEditText = getView().findViewById(R.id.edtUser);
        String user = userEditText.getText().toString().trim();

        ptUserCode = user;
        savePtUserCode(ptUserCode);

        moveToService();

    }

    private void moveToService() {

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentMainFragment,new ReturnBillFragment())
                .commit();
    }

    private void savePtUserCode(String ptUserCode) {

        SharedPreferences sharedPreferences = getActivity()
                .getSharedPreferences(myConstant.getFileSharedPreferences(),
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ptUserCode",ptUserCode);
        editor.commit();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authen, container, false);
    }

}
