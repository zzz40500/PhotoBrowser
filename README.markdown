本框架是基于Android-Universal-Image-Loader,
和 PhotoView两个库上面做简单的集成.
放效果图拉:

![效果图.gif](http://upload-images.jianshu.io/upload_images/166866-44c7227aac936790.gif)

github 地址:



2个库我都是已 jar 包的形式引用的.
加入了好看的下载进度提示.
如果你用的图片缓存不是 image-loader 的花,可以手动的修改PhotoViewFragment 这个类中 onCreateView中 的方法.


##usage:##
以 android library 的形式导入 library库.
使用方法:
多张图片
~~~
Intent intent=new Intent(MainActivity.this,PhotoViewActivity.class);
//用 JSONArray  存放 url
                JSONArray urls=new JSONArray();
                urls.put("http://tp.cdn.di5.net:8880/2013/mxy/0322/1/1.jpg.680.510.jpg");
                urls.put("http://tupian.enterdesk.com/2013/mxy/0529/1/9.jpg.680.510.jpg");
                urls.put("http://tupian.enterdesk.com/2013/mxy/0529/1/12.jpg.680.510.jpg");
                urls.put("http://tupian.enterdesk.com/2013/mxy/0529/1/2.jpg.680.510.jpg");
                urls.put("http://tupian.enterdesk.com/2013/mxy/0529/1/5.jpg.680.510.jpg");
                urls.put("http://tupian.enterdesk.com/2013/mxy/0529/1/7.jpg.680.510.jpg");
                urls.put("http://tupian.enterdesk.com/2013/mxy/0529/1/8.jpg.680.510.jpg");

//用 JSONArray  存放 图片对应的详情,key 为PhotoViewActivity.URL_LIST
                JSONArray titles=new JSONArray();
                titles.put("上位1");
                titles.put("上位2");
                titles.put("上位3");
                titles.put("上位4");
                titles.put("上位5");
                titles.put("上位6");
                titles.put("上位7");
//传递 所以图片的所有 url
                intent.putExtra(PhotoViewActivity.URL_LIST, urls.toString());
//传递图片对应的详情,可以为空,为空则不显示详情, key 为PhotoViewActivity.URL_TITLES
                intent.putExtra(PhotoViewActivity.URL_TITLES, titles.toString());
//传递Viewpager 的下标,以0开始.可以不传,默认为0;
                intent.putExtra(PhotoViewActivity.INDEX,2);
                startActivity(intent);
~~~ 
一张图片:
~~~
Intent intent=new Intent(MainActivity.this,PhotoViewActivity.class);
//用 JSONArray  存放 url
                JSONArray urls=new JSONArray();
                urls.put("http://tp.cdn.di5.net:8880/2013/mxy/0322/1/1.jpg.680.510.jpg");
  JSONArray titles=new JSONArray();
                titles.put("上位1");
 intent.putExtra(PhotoViewActivity.URL_LIST, urls.toString());
//传递图片对应的详情,可以为空,为空则不显示详情, key 为PhotoViewActivity.URL_TITLES
                intent.putExtra(PhotoViewActivity.URL_TITLES, titles.toString());
                startActivity(intent);
~~~



###图片的 URl的格式:###
因为使用的是 image-loader 的缓存机制,所以可以显示从网络获取的,从本地获取的,从资源获取的,从文件夹中获取的, 视频的缩略图...
~~~
"http://site.com/image.png" // from Web  来至网络
"file:///mnt/sdcard/image.png" // from SD card sd 卡中
"file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail) sd 卡中的视频缩略图
"content://media/external/images/media/13" // from content provider
"content://media/external/video/media/13" // from content provider (video thumbnail)
"assets://image.png" // from assets
"drawable://" + R.drawable.img // from drawables (non-9patch images)
~~~






