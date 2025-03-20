使用方法：
1.在Application初始化
   SkinManager.getInstance().init(this);

2.继承 SkinActivity

3.修改资源名称
  加前缀：需要替换的皮肤资源要加前缀："skin_",  比如原理的有个color叫"mainText",则修改为"skin_mainText"
  加后缀：默认皮肤不用加后缀，新皮肤需要加后缀。
        比如原理的有个color叫"skin_mainText",则再新皮肤新增一个"skin_mainText_dark",_dark 是皮肤后缀,名字随意

3. 切换皮肤
  SkinManager.getInstance().loadSkin("_dark");//暗黑模式,_dark为皮肤后缀
  SkinManager.getInstance().restoreDefaultTheme()//默认皮肤