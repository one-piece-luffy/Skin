# Skin
android皮肤切换

# 使用：

1.在根目录gradle加入： maven { url 'https://jitpack.io' }


2.在项目gradle 引入： implementation 'com.github.one-piece-luffy:Skin:1.0.0

3.在application 初始化

     SkinManager.getInstance().init(this);

4.添加新的皮肤res目录（eg: res-dark），并在app的buil.gradle添加res路径：

    sourceSets {
    main {
        res {
            srcDirs 'src\\main\\res', 'src\\main\\res-dark'
        }
    }
}


4.切换皮肤

    SkinManager.getInstance().loadSkin(ThemeConstant.DARK);













