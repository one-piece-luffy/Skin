# Skin
android皮肤切换

* Retrofit 在我看来并不是那么好用，因为很多常用的功能实现起来比较麻烦，动态 Host 要写拦截器，日志打印要写拦截器，就连最常用的添加全局参数也要写拦截器，一个拦截器意味着要写很多代码，如果写得不够严谨还有可能出现 Bug，从而影响整个 OkHttp 请求流程，我经常在想这些功能能不能都用一句代码搞定，因为我觉得这些功能是设计框架的时候本应该考虑的，这便是我做这个框架的初心。


*

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













