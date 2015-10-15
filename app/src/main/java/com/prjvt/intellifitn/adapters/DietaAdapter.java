package com.prjvt.intellifitn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prjvt.intellifitn.R;
import com.prjvt.intellifitn.domain.Dieta;
import com.prjvt.intellifitn.interfaces.RecyclerViewClick;

import java.util.List;

/**
 * Created by Vitor on 15/10/2015.
 */
public class DietaAdapter extends RecyclerView.Adapter<DietaAdapter.MyViewHolder> {
    private List<Dieta> listaDieta;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewClick.RecyclerViewClickListener itemListener;
    private Context c;

    public DietaAdapter(Context c, List<Dieta> l, RecyclerViewClick.RecyclerViewClickListener itemListener) {
        this.c = c;
        this.listaDieta = l;
        this.itemListener = itemListener;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Dieta getDieta(int position) {
        return listaDieta.get(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_dieta, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvNome.setText(listaDieta.get(position).getDescricao());
//        holder.tvDias.setText("");
    }

    @Override
    public int getItemCount() {
        return listaDieta.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvNome, tvDias;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvNome = (TextView) itemView.findViewById(R.id.tv_dieta);
//            tvDias = (TextView) itemView.findViewById(R.id.tv_dias);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            itemListener.recyclerViewListClicked(v, this.getPosition());
        }
    }
}
