package com.example.android.popularmoviesstage_1;
import android.content.Context;
import com.example.android.popularmoviesstage_1.Movies;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewAnimator;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter <MovieAdapter.ViewHolder> {

    private  Movies[] movies;
    private final ONCLICK monclick = null;
    private Context Context;

    public MovieAdapter(Movies[] movies , ONCLICK monclick)
    {
        movies =movies ;
        monclick = monclick ;
    }



    public interface ONCLICK
           {
               void onClick(int adapterPosition);
           }



   public   class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public  ImageView imageView;
        public ViewHolder(@NonNull View  itemView) {
            super(itemView);
            imageView = (ImageView)  itemView.findViewById(R.id.img_view);
            itemView.setOnClickListener(this);
        }

       @Override
       public void onClick(View v) {
           int adapterPosition = getAdapterPosition();
           monclick.onClick(adapterPosition);
           }
   }



    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int LayoutOfImg = R.layout.movies;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean attachtoparrent = false;
        View view = inflater.inflate(LayoutOfImg, viewGroup, attachtoparrent);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;

          }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int postion)
    {


                Picasso.with(Context)
                .load(movies[postion].getPOSTER())
                .fit().centerCrop()
                .error(R.mipmap.ic_launcher_round)
                .placeholder(R.mipmap.ic_launcher_round)
                .into(viewHolder.imageView);
        //(ImageView) viewHolder.imageView.findViewById(R.id.img_view);
    }


    @Override
    public int getItemCount() {
        if (movies == null) {
            return 0;
        }
        else  return movies.length;
    }







}
