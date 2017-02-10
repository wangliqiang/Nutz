package com.app.nuts.app.mvp.ui.adapter;

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
import com.app.nuts.app.common.App;
import com.app.nuts.app.mvp.entity.MovieInfo;
import com.app.nuts.widget.imageloader.ImageLoader;
import com.app.nuts.widget.imageloader.glide.GlideImageConfig;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 王立强 on 2016/10/9.
 */

public class MovieInfoAdapter extends Adapter<ViewHolder> {
    private Context ctx;
    private List<MovieInfo.SubjectsBean> list;
    private ImageLoader imageLoader;
    private App app;

    public MovieInfoAdapter(Context ctx, List<MovieInfo.SubjectsBean> list) {
        this.ctx = ctx;
        this.list = list;
        this.app = (App) ctx.getApplicationContext();
        this.imageLoader = app.getAppComponent().imageLoader();
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
        View view = LayoutInflater.from(ctx).inflate(R.layout.item_movieinfo, parent, false);
        return new itemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (holder instanceof itemViewHolder) {

            imageLoader.loadImage(app, GlideImageConfig
                    .builder()
                    .url(list.get(position).getImages().getMedium())
                    .imagerView(((itemViewHolder) holder).movieImage)
                    .build());
            ((itemViewHolder) holder).movieName.setText(position + 1 + ". " + list.get(position).getTitle());
            String cast = "";
            for (int i = 0; i < list.get(position).getCasts().size(); i++) {
                cast += list.get(position).getCasts().get(i).getName() + " / ";
            }
            ((itemViewHolder) holder).movieCast.setText("导演：" + list.get(position).getDirectors().get(0).getName() +
                    "\n演员：" + cast.substring(0, cast.lastIndexOf("/")));
            ((itemViewHolder) holder).movieGenres.setText("类型：" + list.get(position).getGenres().toString().replace("[", "").replace("]", ""));
            if (onItemClickListener != null) {
                ((itemViewHolder) holder).itemClick.setOnClickListener(v -> {
                    int position1 = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(((itemViewHolder) holder).itemClick, position1);
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class itemViewHolder extends ViewHolder {

        @BindView(R.id.item_click)
        CardView itemClick;
        @BindView(R.id.movie_image)
        ImageView movieImage;
        @BindView(R.id.movie_name)
        TextView movieName;
        @BindView(R.id.movie_cast)
        TextView movieCast;
        @BindView(R.id.movie_genres)
        TextView movieGenres;

        public itemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
