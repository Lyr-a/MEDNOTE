package com.example.mednote.recvi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mednote.R;
import com.example.mednote.trat.TratamentoFragment;
import com.example.mednote.trat.TratamentoViewHolder;
import com.example.mednote.trat.TratamentoZoomActivity;

import java.util.List;

public class TratamentoAdapter extends RecyclerView.Adapter {

    TratamentoFragment tratamentoFragment;
    List<TratamentoItem> TraItens;

    public TratamentoAdapter(TratamentoFragment tratamentoFragment,List<TratamentoItem> TraItens){
        this.tratamentoFragment = tratamentoFragment;
        this.TraItens = TraItens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater TraInflater;
        TraInflater = LayoutInflater.from(tratamentoFragment.getContext());
        View v = TraInflater.inflate(R.layout.item_tratamento_list, parent, false);
        return new TratamentoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TratamentoItem tratamentoItem = TraItens.get(position);
        View v = holder.itemView;
        TextView Title = v.findViewById(R.id.TvTraTitle);
        TextView Desc  = v.findViewById(R.id.TvTraDesc);
        Title.setText(tratamentoItem.Title);
        Desc.setText(tratamentoItem.Desc);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tratamentoFragment.getContext(), TratamentoZoomActivity.class);
                intent.putExtra("Titulo", tratamentoItem.Title);
                intent.putExtra("Desc", tratamentoItem.Desc);
                intent.putExtra("Data", tratamentoItem.Data);
                intent.putExtra("Hora", tratamentoItem.Hora);
                tratamentoFragment.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return TraItens.size();
    }
}
