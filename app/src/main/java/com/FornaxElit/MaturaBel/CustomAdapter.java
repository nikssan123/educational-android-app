package com.FornaxElit.MaturaBel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.myViewHolder> {


    Context myContext;
    List<ItemForRvForAuthorWor> myList;

    public CustomAdapter(Context context, List<ItemForRvForAuthorWor> myList){
        this.myContext = context;
        this.myList = myList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(myContext);
        View v = layoutInflater.inflate(R.layout.activity_authors_work_and_introduction, parent, false);


        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final myViewHolder holder, final int position) {
        holder.spinner.setAdapter(new ArrayAdapter<String>(myContext, R.layout.spinner_item, myList.get(position).getListViewItems()));
        holder.imageViewAuthor.setImageResource(myList.get(position).getImage());
        holder.textViewAuthorName.setText(myList.get(position).getAuthorName());
        //  R.layout.spinner_item
        //   android.R.layout.simple_list_item_1
        //holder.buttonChoose.setTag(myList.get(position).getAuthorName());
        //holder.buttonIntro.setTag(myList.get(position).getAuthorName());




        //holder.spinner.setTag(myList.get(position).getAuthorName());


        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                holder.buttonIntro.setTag(myList.get(position).getAuthorName() + "/" + holder.spinner.getSelectedItem().toString());
                holder.buttonChoose.setTag(myList.get(position).getAuthorName() + "/" + holder.spinner.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(myContext, "nothing selected", Toast.LENGTH_SHORT).show();
            }
        });

       //holder.spinner.setSelection(position, false);

        //holder.spinner.setPrompt("Избери нещо");
    }



    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewAuthor;
        Spinner spinner;
        TextView textViewAuthorName;
        Button buttonChoose, buttonIntro;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewAuthor = itemView.findViewById(R.id.imageViewAuthorImageForHisWork);
            spinner = itemView.findViewById(R.id.spinner);
            textViewAuthorName = itemView.findViewById(R.id.textViewAuthorNameForHisWork);
            buttonChoose = itemView.findViewById(R.id.buttonChoose);
            buttonIntro = itemView.findViewById(R.id.buttonIntro);

        }
    }
}
