package com.app.nuts.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.nuts.R;
import com.app.nuts.model.MovieInfo;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 王立强 on 2016/10/9.
 */

public class MovieInfoAdapter extends Adapter<ViewHolder> {
    private Context ctx;
    private List<MovieInfo.SubjectsBean> list;
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;


    public MovieInfoAdapter(Context ctx, List<MovieInfo.SubjectsBean> list) {
        this.ctx = ctx;
        this.list = list;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(ctx).inflate(R.layout.item_movieinfo, parent, false);
            return new itemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(ctx).inflate(R.layout.item_foot, parent, false);
            return new footViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (holder instanceof itemViewHolder) {
            Glide.with(ctx).load(list.get(position).getImages().getMedium()).into(((itemViewHolder) holder).movieImage);
            ((itemViewHolder) holder).movieName.setText(position + 1 + ". " + list.get(position).getTitle());
            String cast = "";
            for (int i = 0; i < list.get(position).getCasts().size(); i++) {
                cast += list.get(position).getCasts().get(i).getName() + " / ";
            }
            ((itemViewHolder) holder).movieCast.setText("导演：" + list.get(position).getDirectors().get(0).getName() +
                    "\n演员：" + cast.substring(0, cast.lastIndexOf("/")));
            ((itemViewHolder) holder).movieGenres.setText("类型：" + list.get(position).getGenres().toString().replace("[", "").replace("]", ""));
            if (onItemClickListener != null) {
                ((itemViewHolder) holder).itemClick.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = holder.getLayoutPosition();
                        onItemClickListener.onItemClick(((itemViewHolder) holder).itemClick, position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 0 : list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    static class itemViewHolder extends ViewHolder {

        @Bind(R.id.item_click)
        CardView itemClick;
        @Bind(R.id.movie_image)
        ImageView movieImage;
        @Bind(R.id.movie_name)
        TextView movieName;
        @Bind(R.id.movie_cast)
        TextView movieCast;
        @Bind(R.id.movie_genres)
        TextView movieGenres;

        public itemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public class footViewHolder extends ViewHolder {
        public footViewHolder(View view) {
            super(view);
        }
    }
}
