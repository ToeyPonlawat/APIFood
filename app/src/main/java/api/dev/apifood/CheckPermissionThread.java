package api.dev.apifood;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.textclassifier.TextLinks;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONObject;

public class CheckPermissionThread extends AsyncTask<String,Void,String> {

    private Context context;

    public CheckPermissionThread(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            MediaType jsonType = MediaType.parse("application/json; charset=utf-8");

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("ptPosCode", strings[0]);
            jsonObject.put("ptUsrCode", strings[1]);
            jsonObject.put("ptDocNo", strings[2]);

            String jsonString = jsonObject.toString();
            Log.d("6MayV1","jsonString ==>  " + jsonString);

            RequestBody requestBody = RequestBody.create(jsonType,jsonString);
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[3]).post(requestBody).build();

            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();


        }catch (Exception e){
            Log.d("6MayV1", "e Thread ==> " + e.toString());
            return null;
        }

    }
}
