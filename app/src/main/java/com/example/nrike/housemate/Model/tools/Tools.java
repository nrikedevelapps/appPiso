package com.example.nrike.housemate.Model.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by develapps15 on 10/8/16.
 */
public class Tools {

    public byte[] uriToByteArray(Context context, Uri uri) throws IOException {
        byte[] data =null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap mBitmap = resizeUriBitmap(context,uri, 400,500);
        mBitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        data  =baos.toByteArray();
        return data;
    }

    public Bitmap resizeUriBitmap(Context context,Uri uri,int newWith, int newHeight){
        InputStream image_stream = null;
        try {
            image_stream = context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap mBitmap= BitmapFactory.decodeStream(image_stream );
        int width = mBitmap.getWidth();
        int height = mBitmap.getHeight();
        float scaleWidth = ((float) newWith) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);

    }

}
