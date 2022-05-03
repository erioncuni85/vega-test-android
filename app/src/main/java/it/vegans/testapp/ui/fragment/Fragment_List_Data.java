package it.vegans.testapp.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import it.vegans.testapp.R;
import it.vegans.testapp.adapters.VegansAdapter;
import it.vegans.testapp.models.ContentItem;
import it.vegans.testapp.models.VegansModel;
import it.vegans.testapp.rest.Api;
import it.vegans.testapp.rest.AppConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_List_Data  extends Fragment {

   //i used private because i want them to use only inside this class
    private VegansAdapter datalist_adapter;
    private SwipeRefreshLayout swipeRefreshRecycle;
    private List<ContentItem> dataList = new ArrayList<ContentItem>();
    private RecyclerView datalist_recycleview;
    Dialog dialog_loading;
    Gson gson;
    Api apiClient;
    Call<VegansModel> call;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_layout, container, false);
        init(v);
        getData();
        swipe();
        swipeRefreshRecycle.setOnRefreshListener(
                () -> {
                    getData();
                    swipeRefreshRecycle.setRefreshing(false); // off refresh icon
                }
        );

        return v;
    }

    public void init(View insideview){
        gson=new GsonBuilder().create();
        datalist_recycleview = insideview.findViewById(R.id.list_data);
        swipeRefreshRecycle = insideview.findViewById(R.id.swipe_data_refresh);
        datalist_recycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        datalist_recycleview.setItemAnimator(itemAnimator);
    }

    public void swipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Toast.makeText(getActivity(), "on Move", Toast.LENGTH_SHORT).show();
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAbsoluteAdapterPosition();
                dataList.remove(position);
                datalist_adapter.notifyDataSetChanged();

            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(datalist_recycleview);
    }

    public void loading() {
        dialog_loading = new Dialog(getActivity());
        dialog_loading.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog_loading.setContentView(R.layout.dialog_structure);
        dialog_loading.show();
    }

    private void getData(){
        //Toast.makeText(getActivity(),"hyrii",Toast.LENGTH_SHORT).show();
        loading();
        apiClient = AppConfig.createAPI_No_Token();
        call = apiClient.getData();
        call.enqueue(new Callback<VegansModel>() {
            @Override
            public void onResponse(Call<VegansModel> call, Response<VegansModel> response) {
                if (response.code()==200) {

                    dialog_loading.dismiss();
                    dataList = response.body().getContent();
                    Log.e("DateResponse:", gson.toJson(dataList));
                    datalist_adapter = new VegansAdapter(getActivity(),dataList);
                    datalist_recycleview.setAdapter(datalist_adapter);
                }else{
                    dialog_loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<VegansModel> call, Throwable t) {
                Log.e("ErrorResponse", t.getMessage());
                dialog_loading.dismiss();
            }

        });

    }

}
