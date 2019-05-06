package api.dev.apifood;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Add Fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.contentMainFragment,new AuthenFragment())
                    .commit();
        }

    } // Main Method

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Log.d("6MayV1", "You Click Back");

    }
} // Main Class
