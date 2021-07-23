package fr.designpatterncommand.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import fr.designpatterncommand.R;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private int[] img;
    Context context;

    public MyAdapter(Context context, int img[]){
        this.context = context;
        this.img = img;
    }

    public void setImg(int[] img) {
        this.img = img;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public ViewHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
        public ImageView getImageView(){
            return imageView;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.img_recycler_view, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.getImageView().setImageResource(img[position]);
    }

    @Override
    public int getItemCount() {
        return img.length;
    }
}

