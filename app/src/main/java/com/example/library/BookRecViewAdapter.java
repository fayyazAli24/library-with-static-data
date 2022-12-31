package com.example.library;

import static com.example.library.BooksActivity.BOOK_ID_KEY;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {

    private ArrayList<Book> books= new ArrayList<>();
    private Context mContext;
    private String parentActivity;


    public BookRecViewAdapter(Context mContext, String parentActivity) {
        this.mContext = mContext;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_books,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtBook.setText(books.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImageURL())
                .into(holder.imgBook);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,BooksActivity.class);
                intent.putExtra(BOOK_ID_KEY,books.get(position).getId());
                mContext.startActivity(intent);
            }
        });
        holder.txtAuthorName.setText(books.get(position).getAuthor());
        holder.txtShortDesc.setText(books.get(position).getShortDescription());


        if(books.get(position).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.btnDownArrow.setVisibility(View.GONE);

            if(parentActivity.equals("allBooks")){
                holder.btnDelete.setVisibility(View.GONE);
            }

            else if(parentActivity.equals("alreadyRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("are you sure you want to delete this book");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(mContext).removeFromAlreadyRead(books.get(position)) ){
                                    Toast.makeText(mContext, "Book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();

                    }
                });
            }
            else if(parentActivity.equals("wantToRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("are you sure you want to delete this book");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(mContext).removeFromWantToRead(books.get(position)) ){
                                    Toast.makeText(mContext, "Book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();

                    }

                });
            }
            else if(parentActivity.equals("favourites")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("are you sure you want to delete this book");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(mContext).removeFromFavourites(books.get(position)) ){
                                    Toast.makeText(mContext, "Book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                    }
                });
            }
            else if (parentActivity.equals("currentlyReading")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setMessage("are you sure you want to delete this book");
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Utils.getInstance(mContext).removeFromCurrentlyReading(books.get(position)) ){
                                    Toast.makeText(mContext, "Book removed", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        builder.create().show();
                    }
                });
            }
        }
        else {
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.btnDownArrow.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imgBook;
        private TextView txtBook;
        private ImageView btnUpArrow,btnDownArrow;
        private TextView txtAuthorName,txtShortDesc;
        private RelativeLayout expandedRelLayout;
        private TextView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent=itemView.findViewById(R.id.parent);
            imgBook=itemView.findViewById(R.id.imgBook);
            txtBook=itemView.findViewById(R.id.txtBook);
            btnDownArrow=itemView.findViewById(R.id.btnDownArrow);
            btnUpArrow=itemView.findViewById(R.id.btnUpArrow);
            expandedRelLayout=itemView.findViewById(R.id.expandedRelLayout);
            txtAuthorName=itemView.findViewById(R.id.txtAuthorName);
            txtShortDesc=itemView.findViewById(R.id.txtShortDesc);
            btnDelete=itemView.findViewById(R.id.btnDelete);

            btnDownArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            btnUpArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExpanded(!book.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}

