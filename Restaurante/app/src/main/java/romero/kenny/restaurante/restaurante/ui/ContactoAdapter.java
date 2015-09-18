package romero.kenny.restaurante.restaurante.ui;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import romero.kenny.restaurante.restaurante.R;
import romero.kenny.restaurante.restaurante.ui.modelos.Contacto;

/**
 * Adaptador del recycler view
 */
public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder>
        implements ItemClickListener {

    /**
     * Lista de objetos {@link Contacto} que representan la fuente de datos
     * de inflado
     */
    private List<Contacto> items;

    /*
    contzto donde actua el recycler view
     */
    private Context context;

    public ContactoAdapter(List<Contacto> items, Context context){
        this.context = context;
        this.items = items;
    }


    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new ContactoViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ContactoViewHolder holder, int position) {
        holder.name.setText(items.get(position).getNombre());
        holder.email.setText(items.get(position).getEmail());
        holder.genero.setText(items.get(position).getGenero());
       /* holder.mobil.setText(items.get(position).getMobil());
        holder.home.setText(items.get(position).getHome());
        holder.office.setText(items.get(position).getOffice());*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Sobrescritura del método de la interfaz {@link ItemClickListener}
     *
     * @param view     item actual
     * @param position posición del item actual
     */
    @Override
    public void onItemClick(View view, int position) {
        MainActivity.launch(
                (Activity) context, items.get(position).getIdContacto());
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{
        // campos respectivos de un item
        public TextView name;
        public TextView email;
        public TextView genero;
        public TextView mobil;
        public TextView home;
        public TextView office;
        public ItemClickListener listener;

        public ContactoViewHolder(View v, ItemClickListener listener) {
            super(v);
            name = (TextView)v.findViewById(R.id.txt_nombre);
            email = (TextView)v.findViewById(R.id.txt_email);
            genero = (TextView)v.findViewById(R.id.txt_genero);
            mobil = (TextView)v.findViewById(R.id.txt_mobil);
            home = (TextView)v.findViewById(R.id.txt_home);
            office = (TextView)v.findViewById(R.id.txt_ofice);
            this.listener = listener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getPosition());
        }

    }
}


interface ItemClickListener {
    void onItemClick(View view, int position);
}