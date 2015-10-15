package com.prjvt.intellifitn.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prjvt.intellifitn.R;
import com.prjvt.intellifitn.domain.DiasExercicio;
import com.prjvt.intellifitn.domain.ExercicioDetalhe;
import com.prjvt.intellifitn.domain.MyLinearLayoutManager;
import com.prjvt.intellifitn.interfaces.RecyclerViewClick;

import java.util.List;

/**
 * Created by Vitor on 28/05/2015.
 */
public class TreinoDiasAdapter extends RecyclerView.Adapter<TreinoDiasAdapter.MyViewHolder> {
    private List<DiasExercicio> mList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private static RecyclerViewClick.RecyclerViewClickDetailListener itemListenerExercicio;

    public TreinoDiasAdapter(Context c, List<DiasExercicio> l, RecyclerViewClick.RecyclerViewClickDetailListener itemListenerExercicio) {
        mList = l;
        mContext = c;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemListenerExercicio = itemListenerExercicio;
    }

    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_treinodias, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    public ExercicioDetalhe getExercicio(int positionDetail, int positionMaster) {
        return mList.get(positionMaster).getExercicio().get(positionDetail);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvDia.setText(mList.get(position).getDia().toString(mContext));

        MyLinearLayoutManager llm = new MyLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        holder.rvExercicio.setLayoutManager(llm);

        ExercicioAdapter tAdapter = new ExercicioAdapter(mContext, mList.get(position).getExercicio(), itemListenerExercicio, holder.getPosition());
        holder.rvExercicio.setAdapter(tAdapter);
      //  TreinoExercicioAdapter tAdapter = new TreinoExercicioAdapter(mContext, mList.get(position).getExercicio());
      //  holder.rvExercicio.setAdapter(tAdapter);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDia;
        public RecyclerView rvExercicio;

        public MyViewHolder(View itemView) {
            super(itemView);

            rvExercicio = (RecyclerView) itemView.findViewById(R.id.rv_exercicios);
            tvDia = (TextView) itemView.findViewById(R.id.tv_dia);
        }
    }
}
