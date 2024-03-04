package com.studiobrend.abraj;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NamesAdapter extends RecyclerView.Adapter<NamesAdapter.NameViewHolder> {

    private List<NameItem> names;
    private MediaPlayer mediaPlayer;
    private Context context;
    private int expandedPosition = -1;
    private RecyclerView recyclerView;

    public NamesAdapter(List<NameItem> names, Context context, RecyclerView recyclerView) {
        this.names = names;
        this.context = context;
        this.recyclerView = recyclerView;
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public NameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.name_item, parent, false);
        return new NameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NameViewHolder holder, int position) {
        NameItem item = names.get(position);
        holder.bind(item, position);

        holder.imageView.setOnClickListener(v -> {
            expandedPosition = (position == expandedPosition) ? -1 : position;
            notifyDataSetChanged();

            if (expandedPosition != -1) {
                ((LinearLayoutManager) recyclerView.getLayoutManager()).scrollToPositionWithOffset(expandedPosition, 0);
            }

            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.reset();
            mediaPlayer = MediaPlayer.create(context, item.getAudioResId());
            mediaPlayer.start();
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    class NameViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        View descriptionLayout;

        NameViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textView = itemView.findViewById(R.id.text_view);
            descriptionLayout = itemView.findViewById(R.id.description_layout);
        }

        void bind(NameItem item, int position) {
            imageView.setImageResource(item.getImageResId());
            textView.setText(item.getDescription());
            descriptionLayout.setVisibility(position == expandedPosition ? View.VISIBLE : View.GONE);
        }
    }
}


