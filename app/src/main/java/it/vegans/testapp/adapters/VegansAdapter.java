package it.vegans.testapp.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import it.vegans.testapp.R;
import it.vegans.testapp.models.ContentItem;
import it.vegans.testapp.ui.fragment.Fragment_List_Data_Detail;
import it.vegans.testapp.utils.ItemClickListener;

public class VegansAdapter extends RecyclerView.Adapter<VegansAdapter.ViewHolder> {

    private List<ContentItem> list_data;
    Activity mContext;
    Bundle bundle;


    public VegansAdapter(Activity context, List<ContentItem> list_data_act) {
        this.list_data = list_data_act;
        this.mContext = context;

    }

    @Override
    public VegansAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_structure, parent, false);
        return new VegansAdapter.ViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final VegansAdapter.ViewHolder holder, final int position) {

        final ContentItem data_from = list_data.get(position);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    // String stat = feedItem.getStatus();
                } else {

                    bundle = new Bundle();
                    bundle.putString("data_title", data_from.getMediaTitleCustom() );
                    bundle.putString("data_media_url", data_from.getMediaUrl() );
                    bundle.putString("data_media_date", data_from.getMediaDate().getDateString() );
                    Navigation.findNavController(view).navigate(R.id.action_list_data_to_detail_data,bundle);


                }
            }
        });



        holder.Label_Title.setText(data_from.getMediaTitleCustom());
        holder.Label_Date.setText(data_from.getMediaDate().getDateString().substring(0, 16));

    }


    @Override
    public int getItemCount() {
        return (null != list_data ? list_data.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        public TextView Label_Title,Label_Date;
        public ImageView MediaImage;
        private ItemClickListener clickListener;


        public ViewHolder(View itemView) {
            super(itemView);
            Label_Title = itemView.findViewById(R.id.label_title);
            Label_Date =  itemView.findViewById(R.id.label_date);
            MediaImage =  itemView.findViewById(R.id.imageView);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);


        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }


        //not needed
        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }


}
