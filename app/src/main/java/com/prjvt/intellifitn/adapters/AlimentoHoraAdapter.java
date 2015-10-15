package com.prjvt.intellifitn.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prjvt.intellifitn.R;
import com.prjvt.intellifitn.domain.DietaHorario;
import com.prjvt.intellifitn.domain.DietaHorarioLista;
import com.prjvt.intellifitn.domain.MyLinearLayoutManager;
import com.prjvt.intellifitn.interfaces.RecyclerViewClick;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by vitor on 14/10/2015.
 */
public class AlimentoHoraAdapter extends RecyclerView.Adapter<AlimentoHoraAdapter.MyViewHolder> {
    private List<DietaHorarioLista> mList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private int mPosition;
    private static RecyclerViewClick.RecyclerViewClickDetailListener itemListenerDieta;

    public AlimentoHoraAdapter(Context c, List<DietaHorarioLista> l, RecyclerViewClick.RecyclerViewClickDetailListener itemListenerDieta) {
        mList = l;
        mContext = c;
        mLayoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemListenerDieta = itemListenerDieta;
    }

    public DietaHorario getDietaHorario(int positionDetail, int positionMaster) {
        return mList.get(positionMaster).getDietaHorarioList().get(positionDetail);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = mLayoutInflater.inflate(R.layout.item_alimentohora, parent, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Long millis = mList.get(position).getHorario();

        String Hora = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)));

        holder.tv_hora.setText(Hora);

        MyLinearLayoutManager llm = new MyLinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        holder.rv_alimentos.setLayoutManager(llm);

        AlimentoAdapter alimentoAdapter = new AlimentoAdapter(mContext, mList.get(position).getDietaHorarioList(), itemListenerDieta, holder.getPosition());
        holder.rv_alimentos.setAdapter(alimentoAdapter);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_hora;
        private RecyclerView rv_alimentos;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_hora = (TextView) itemView.findViewById(R.id.tv_hora);
            rv_alimentos = (RecyclerView) itemView.findViewById(R.id.rv_alimentos);
        }


        @Override
        public void onClick(View v) {
//            if (itemListenerDieta != null)
//                itemListenerDieta.recyclerViewListDetailClicked(v, );
        }
    }
}
