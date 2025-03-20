package com.baofu.skin.adpter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baofu.lib.skin.SkinManager;
import com.baofu.skin.constants.ThemeConstant;
import com.baofu.skin.databinding.ItemAppearanceBinding;
import com.baofu.skin.listener.AdapterOnClickListener;
import com.baofu.skin.model.AppearanceModel;
import com.baofu.skin.utils.ShapeUtil;
import com.baofu.skin.R;
import java.util.List;

public class AppearanceAdapter extends RecyclerView.Adapter {
    List<AppearanceModel> list;
    Context context;
    AdapterOnClickListener click;
    boolean isAllSelect;

    public AppearanceAdapter(Context context, List<AppearanceModel> list, AdapterOnClickListener click) {
        this.context = context;
        this.list = list;
        this.click = click;
    }

    public void setList(List<AppearanceModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public List<AppearanceModel> getList() {
        return list;
    }

    private AppearanceModel getItem(int position) {
        if (list == null || position >= list.size()) {
            return null;
        }
        return list.get(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemAppearanceBinding itemBinding =
                ItemAppearanceBinding.inflate(layoutInflater, parent, false);
        return new ContentViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        AppearanceModel model = getItem(position);
        if (model == null)
            return;
        int strongColor = model.theme.equals(SkinManager.getInstance().mSkinSuffix) ? context.getColor(R.color.theme2) : 0;
        ContentViewHolder holder = (ContentViewHolder) viewHolder;
        GradientDrawable gradientDrawable = ShapeUtil.createShape(3,
                8,
                0, strongColor, model.bg_color);

        holder.binding.iv.setBackground(gradientDrawable);
        holder.binding.tv.setTextColor(model.text_color);
        holder.itemView.setOnClickListener(v -> {
//            AppSharePreference.saveTheme(model.theme);
//            Intent intent = new Intent(context, MainActivity.class);
//            intent.putExtra("restart", true);
//            context.startActivity(intent);
            if (ThemeConstant.DARK.equals(model.theme)) {

                SkinManager.getInstance().loadSkin(ThemeConstant.DARK);
            } else {
                SkinManager.getInstance().restoreDefaultTheme();
            }
            notifyDataSetChanged();
        });


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {
        public ItemAppearanceBinding binding;

        public ContentViewHolder(ItemAppearanceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}
