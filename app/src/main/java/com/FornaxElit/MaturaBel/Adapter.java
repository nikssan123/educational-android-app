package com.FornaxElit.MaturaBel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {


    Context myContext;
    List<itemForRv> myList;
    int testCheck = 3;


    public Adapter(Context myContext, List<itemForRv> myList, int testCheck) {
        this.myContext = myContext;
        this.myList = myList;
        this.testCheck = testCheck;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        View v = layoutInflater.inflate(R.layout.card_item, parent, false);


        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.authorImage.setImageResource(myList.get(position).getAuthorImage());
        //holder.background.setImageResource(myList.get(position).getBackground());
        holder.authorName.setText(myList.get(position).getAuthorName());
        holder.button.setTag(myList.get(position).getAuthorName());
        if(testCheck < 2) {
            holder.button.setText("Тест");
        }

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{


        ImageView authorImage;//, background;
        TextView authorName;
        Button button;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            authorImage = itemView.findViewById(R.id.imageViewAuthorImageForHisWork);
            //background = itemView.findViewById(R.id.imageViewBackground);
            authorName = itemView.findViewById(R.id.textViewAuthor);
            button = itemView.findViewById(R.id.btnViewContent);
        }
    }
}
