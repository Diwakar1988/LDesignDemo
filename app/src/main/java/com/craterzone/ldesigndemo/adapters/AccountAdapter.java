package com.craterzone.ldesigndemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.craterzone.ldesigndemo.R;
import com.craterzone.ldesigndemo.modal.Account;

import java.util.ArrayList;

/**
 * Created by Diwakar on 6/1/2015.
 */
public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountViewHolder> {

    private ItemClickListener listener;
    private ArrayList<Account> accounts;
    private LayoutInflater inflater;

    public AccountAdapter(Context context, ArrayList<Account> accounts) {
        this.accounts = accounts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public AccountViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.layout_account_list_item, parent, false);
        AccountViewHolder holder = new AccountViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final AccountViewHolder holder, int position) {
        Account account = accounts.get(position);
        holder.tvTitle.setText(account.owner);
        holder.tvDesc.setText(account.number);
        holder.tvType.setText(account.type);
        holder.view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClicked(view, holder.getPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public void setListener(ItemClickListener listener) {
        this.listener = listener;
    }


    class AccountViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvDesc;
        TextView tvType;
        View view;

        public AccountViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
            tvType = (TextView) itemView.findViewById(R.id.tv_type);

        }
    }

    public static interface ItemClickListener {
        public void onItemClicked(View view, int position);
    }
}
