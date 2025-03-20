package com.baofu.skin.ui;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baofu.lib.skin.SkinActivity;
import com.baofu.lib.skin.SkinManager;
import com.baofu.skin.R;
import com.baofu.skin.adpter.AppearanceAdapter;
import com.baofu.skin.constants.ThemeConstant;
import com.baofu.skin.databinding.ActivityMainBinding;
import com.baofu.skin.model.AppearanceModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends SkinActivity {

    ActivityMainBinding binding;
    AppearanceAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        List<AppearanceModel> list = new ArrayList<>();
        AppearanceModel normal = new AppearanceModel();
        normal.bg_color = getColor(R.color.skin_bg1);
        normal.text_color = getColor(R.color.skin_text1);
        normal.theme = ThemeConstant.NORMAL;

        AppearanceModel dark = new AppearanceModel();
        dark.bg_color = getColor(R.color.skin_bg1_dark);
        dark.text_color = getColor(R.color.skin_text1_dark);
        dark.theme = ThemeConstant.DARK;

        list.add(normal);
        list.add(dark);
        adapter = new AppearanceAdapter(MainActivity.this, list, null);
        binding.rv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        binding.rv.setAdapter(adapter);

    }

    @Override
    public void changeStatusBar() {
        switch (SkinManager.getInstance().mSkinSuffix) {
            case ThemeConstant.DARK: {
                setSystemBarFontColor(false);
                break;
            }
            default: {
                setSystemBarFontColor(true);
            }
        }
    }

    /**
     * 设置状态栏字体颜色
     */
    private void setSystemBarFontColor(boolean dark){
        Window window = getWindow();
        if (window != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                WindowInsetsController controller = window.getInsetsController();
                if (controller != null) {
                    if (dark) {
                        // 设置状态栏文字颜色为深色
                        controller.setSystemBarsAppearance(
                                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                        );
                    } else {
                        // 或者设置为浅色
                        controller.setSystemBarsAppearance(
                                0,
                                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                        );
                    }
                }
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                View decorView = window.getDecorView();
                int flags = decorView.getSystemUiVisibility();
                if (dark) {
                    // 设置状态栏文字颜色为深色
                    flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    // 或者设置为浅色
                    flags &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(flags);
            }
        }

    }
}