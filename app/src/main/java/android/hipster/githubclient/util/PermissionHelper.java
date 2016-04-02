package android.hipster.githubclient.util;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.hipster.githubclient.R;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import javax.inject.Inject;
import javax.inject.Singleton;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * Created by yurykorotin on 02/04/16.
 */
@Singleton
public class PermissionHelper {
    public static final int REQUEST_READ_CONTACTS = 0;

    @Inject
    public PermissionHelper() {

    }

    public boolean mayRequestContacts(AppCompatActivity appCompatActivity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (appCompatActivity.checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (appCompatActivity.shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            /*Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });*/
        } else {
            appCompatActivity.requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /*public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }*/

}
