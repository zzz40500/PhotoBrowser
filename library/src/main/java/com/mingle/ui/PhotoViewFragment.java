package com.mingle.ui;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mingle.library.R;
import com.mingle.utils.ImageLoaderUtils;
import com.mingle.widget.CircularProgressBar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class PhotoViewFragment extends Fragment {



    private String url;
    private CircularProgressBar progressBar;

    public PhotoViewFragment() {
        // Required empty public constructor
    }


    public PhotoViewFragment(String url){

        this.url=url;

    }


    private PhotoView imageView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view =inflater.inflate(R.layout.fragment_photo_view, container, false);


        imageView = (PhotoView) view.findViewById(R.id.photoIm);
        progressBar= (CircularProgressBar) view.findViewById(R.id.progressBar);

        ImageLoader loader = ImageLoader.getInstance();
        ImageLoaderUtils.initImageLoader(getActivity());
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true)
                .build();


        imageView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
            @Override
            public void onViewTap(View view, float x, float y) {
                getActivity().finish();
            }
        });

        loader.displayImage(url, imageView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if (imageUri.equals(url)) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        },new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String s, View view, int i, int i2) {
                progressBar.setProgressPecentage((float)i/i2);
            }
        });


        return view;
    }




}
