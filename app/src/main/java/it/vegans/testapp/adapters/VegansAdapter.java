package it.vegans.testapp.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VegansAdapter extends RecyclerView.Adapter<AdapterTags.ViewHolder> {

    private List<ListPostMetaItem> list_tags;
    Activity mContext;

    public AdapterTags(Activity context, List<ListPostMetaItem> list_tags_original) {
        this.list_tags = list_tags_original;
        this.mContext = context;
    }

    @Override
    public AdapterTags.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.structure_item_tags, parent, false);
        return new AdapterTags.ViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(final AdapterTags.ViewHolder holder, final int position) {

        final ListPostMetaItem tag_item = list_tags.get(position);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    // String stat = feedItem.getStatus();
                } else {

                }
            }
        });

        holder.TagName.setText(tag_item.getMetaValue().trim());

    }


    @Override
    public int getItemCount() {
        return (null != list_tags ? list_tags.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {
        public TextView TagName;
        private final AppCompatButton buttonRegion;
        private ItemClickListener clickListener;


        public ViewHolder(View itemView) {
            super(itemView);
            buttonRegion = itemView.findViewById(R.id.btn_region);
            TagName = (TextView) itemView.findViewById(R.id.tag_name);

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

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }


}
