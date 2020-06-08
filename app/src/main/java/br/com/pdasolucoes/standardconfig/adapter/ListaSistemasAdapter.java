package br.com.pdasolucoes.standardconfig.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import br.com.pdasolucoes.standardconfig.R;
import br.com.pdasolucoes.standardconfig.model.Sistema;
import br.com.pdasolucoes.standardconfig.utils.ConfigurationHelper;

/**
 * Created by PDA_Vinicius on 08/05/2018.
 */

public class ListaSistemasAdapter extends RecyclerView.Adapter<ListaSistemasAdapter.MyViewHolder> {
    private Context context;
    private List<Sistema> lista;
    private LayoutInflater layoutInflater;
    private ItemClick itemClick;

    public interface ItemClick {
        void onClick(Sistema s);
    }

    public void ItemClickListener(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public ListaSistemasAdapter(Context context, List<Sistema> lista) {
        this.context = context;
        this.lista = lista;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.adapter_item_list_main, parent, false);

        MyViewHolder mv = new MyViewHolder(v);
        return mv;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Sistema s = lista.get(position);

        Glide.with(context).load(ConfigurationHelper.loadPreference(ConfigurationHelper.ConfigurationEntry.ServerAddress, "").concat(
                ConfigurationHelper.loadPreference(ConfigurationHelper.ConfigurationEntry.Directory, "").concat(
                        s.getImagePath()
                ))).
                into(holder.imageView);

        holder.textView.setText(s.getNome());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;

        MyViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClick.onClick(lista.get(getAdapterPosition()));
        }
    }
}
