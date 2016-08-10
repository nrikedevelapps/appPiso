package com.example.nrike.housemate.Model.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by develapps15 on 10/8/16.
 */
public class Tools {

    public byte[] uriToByteArray(Context context, Uri uri) throws IOException {
        byte[] data =null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap mBitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        mBitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        data  =baos.toByteArray();
        return data;
    }

}
