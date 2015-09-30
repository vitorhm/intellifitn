package com.prjvt.intellifitn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.prjvt.intellifitn.R;
import com.prjvt.intellifitn.domain.Treino;
import com.prjvt.intellifitn.interfaces.RecyclerViewClick;

import java.util.List;

/**
 * Created by Vitor on 21/05/2015.
 */
public class TreinoAdapter extends RecyclerView.Adapter<TreinoAdapter.MyViewHolder> {
    private List<Treino> mList;
    private LayoutInflater mLayoutInflater;
    private static RecyclerViewClick.RecyclerViewClickListener itemListener;

    public TreinoAdapter(Context c, List<Treino> l, RecyclerViewClick.RecyclerViewClickListener itemListener) {
        mList = l;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemListener = itemListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_treino, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    public Treino getTreino(int position) {
        return mList.get(position);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //holder.ivTreino.setImageResource();
      //  holder.tvTreino.setText( mList.get(position).getId() );
        holder.tvTreino.setText( mList.get(position).getDescricao() );
//        holder.tvObjetivo.setText( mList.get(position).getObjetivo().getDescr() );
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvTreino;
        public TextView tvObjetivo;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTreino    = (TextView) itemView.findViewById(R.id.tv_treino);
//            tvObjetivo  = (TextView) itemView.findViewById(R.id.tv_objetivo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            itemListener.recyclerViewListClicked(v, this.getPosition());
        }
    }

}
