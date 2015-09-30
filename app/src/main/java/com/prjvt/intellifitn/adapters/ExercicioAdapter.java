package com.prjvt.intellifitn.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prjvt.intellifitn.R;
import com.prjvt.intellifitn.domain.ExercicioDetalhe;
import com.prjvt.intellifitn.interfaces.RecyclerViewClick;

import java.util.List;

/**
 * Created by Vitor on 08/09/2015.
 */
public class ExercicioAdapter extends RecyclerView.Adapter<ExercicioAdapter.MyViewHolder> {
    private LayoutInflater mLayoutInflater;
    private List<ExercicioDetalhe> mList;
    private int mPosition;
    private Context c;
    RecyclerViewClick.RecyclerViewClickDetailListener itemListener;

    public ExercicioAdapter(Context c, List<ExercicioDetalhe> l, RecyclerViewClick.RecyclerViewClickDetailListener itemListener, int positionHolder) {
        mList = l;
        this.c = c;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemListener = itemListener;
        this.mPosition = positionHolder;
    }

    @Override
    public ExercicioAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_exercicio, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    public ExercicioDetalhe getExercicio(int position) {
        return mList.get(position);
    }

    @Override
    public void onBindViewHolder(ExercicioAdapter.MyViewHolder holder, int position) {
        holder.tv_titExercicio.setText(mList.get(position).getExercicio().getNome());
        holder.tv_series.setText(mList.get(position).getSeries());
        holder.tv_repeticoes.setText(mList.get(position).getRep());
        holder.iv_grupomusc.setImageResource(mList.get(position).getExercicio().getGrupoMuscular().getImagem(c));

//        String img = "<!DOCTYPE html><html><body><img src=\"" + mList.get(position).getExercicio().getImagem() + "\" alt=\"Como Fazer\" width=\"100%\" height=\"100%\"></body></html>";
//        holder.wv_gifExercicio.loadData(img, "text/html", "utf-8");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_titExercicio, tv_series, tv_repeticoes;
        private ImageView iv_grupomusc;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_titExercicio = (TextView) itemView.findViewById(R.id.tv_descr_exerc);
            tv_series = (TextView) itemView.findViewById(R.id.tv_series);
            tv_repeticoes = (TextView) itemView.findViewById(R.id.tv_repeticao);
            iv_grupomusc = (ImageView) itemView.findViewById(R.id.iv_grupomusc);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemListener != null)
                itemListener.recyclerViewListDetailClicked(v, mPosition, this.getPosition());
        }
    }
}
