# Skin
android皮肤切换

1.在application初始化：

SkinManager.getInstance().init(this);

2.添加新的皮肤res目录（eg: res-dark），并在app的buil.gradle添加res路径：
sourceSets {
    main {
        res {
            srcDirs 'src\\main\\res', 'src\\main\\res-dark'
        }
    }
}

3.切换
SkinManager.getInstance().loadSkin(ThemeConstant.DARK);
