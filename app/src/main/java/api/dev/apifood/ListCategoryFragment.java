package api.dev.apifood;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListCategoryFragment extends Fragment {

//    Explicit

    private MyConstant myConstant = new MyConstant();


    public ListCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//      Create RecyclerView

        createRecyclerView();

    } // Main Method

    private void createRecyclerView() {
        try {

            ReadCategoryThread readCategoryThread = new ReadCategoryThread(getActivity());

            readCategoryThread.execute("", myConstant.getUrlCategory());

            String response = readCategoryThread.get();

            JSONObject jsonObject = new JSONObject(response);
            jsonObject =  new JSONObject(jsonObject.get(jsonObject.keys().next()).toString());
            Log.d("5MayV1","response ==> "+jsonObject.toString());

            String dataString = jsonObject.getString("Data");
            Log.d("5MayV3","dataString ==> " + dataString);

            JSONArray jsonArray = new JSONArray(dataString);
            Log.d("5MayV3","Length ==> " + jsonArray.length());

            ArrayList<String> stringArrayList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                stringArrayList.add(jsonObject1.getString("FTPsgName"));
                Log.d("5MayV3" ,stringArrayList.get(i));
            }

            RecyclerView recyclerView = getView().findViewById(R.id.recyclerViewCategory);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                    LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(linearLayoutManager);

            CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), stringArrayList, new OnClickItem() {
                @Override
                public void onClickItem(View view, int position) {
                    Log.d("5MayV3", "You Click ==>" + position);
                }
            });
            recyclerView.setAdapter(categoryAdapter);

        } catch (Exception e) {
            Log.d("5MayV1", "e at FragmentListCategory == >" + e.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_category, container, false);
    }

} // Main Class
