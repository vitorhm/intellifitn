package com.prjvt.intellifitn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prjvt.intellifitn.R;
import com.prjvt.intellifitn.domain.DietaHorario;
import com.prjvt.intellifitn.interfaces.RecyclerViewClick;

import java.util.List;

/**
 * Created by vitor on 14/10/2015.
 */
public class AlimentoAdapter extends RecyclerView.Adapter<AlimentoAdapter.MyViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<DietaHorario> mList;
    private int mPosition;
    private Context c;
    RecyclerViewClick.RecyclerViewClickDetailListener itemListener;

    public AlimentoAdapter(Context c, List<DietaHorario> l, RecyclerViewClick.RecyclerViewClickDetailListener itemListener, int positionHolder) {
        mList = l;
        this.c = c;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemListener = itemListener;
        this.mPosition = positionHolder;
    }

    @Override
    public AlimentoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_alimento, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    public DietaHorario getDietaHorario(int position) {
        return mList.get(position);
    }

    @Override
    public void onBindViewHolder(AlimentoAdapter.MyViewHolder holder, int position) {
        holder.tv_alimento.setText(mList.get(position).getAlimento());
        holder.iv_tipoalimento.setImageResource(mList.get(position).getTipoAlimento().getImagem(c));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_alimento;
        private ImageView iv_tipoalimento;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_alimento = (TextView) itemView.findViewById(R.id.tv_descr_alimento);
            iv_tipoalimento = (ImageView) itemView.findViewById(R.id.iv_tipoalimento);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemListener != null)
                itemListener.recyclerViewListDetailClicked(v, mPosition, this.getPosition());
        }
    }
}
