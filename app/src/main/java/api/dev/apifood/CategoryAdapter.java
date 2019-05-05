package api.dev.apifood;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<String> stringArrayList;
    private OnClickItem onClickItem;
    private LayoutInflater layoutInflater;

    public CategoryAdapter(Context context,
                           ArrayList<String> stringArrayList,
                           OnClickItem onClickItem) {
        this.layoutInflater = LayoutInflater.from(context);
        this.stringArrayList = stringArrayList;
        this.onClickItem = onClickItem;
    } // Constructor

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = layoutInflater.inflate(R.layout.recycler_view_category,viewGroup,false);
        CategoryViewHolder categoryViewHolder = new CategoryViewHolder(view);
        return categoryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoryViewHolder categoryViewHolder, int i) {

        String title = stringArrayList.get(i);
        categoryViewHolder.textView.setText(title);
        categoryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClickItem(v,categoryViewHolder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.txtTitle);

        }
    }

} // Main Class
