package anibalbastias.hnmobiletest.ui.activity;

import android.app.AlertDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import anibalbastias.hnmobiletest.R;
import anibalbastias.hnmobiletest.util.Libcomun;

/**
 * Created by root on 17-03-16.
 */
public class BaseActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState, int resLayoutID) {
        super.onCreate(savedInstanceState);
        setContentView(resLayoutID);

        setAboutDialog();
    }

    private void setAboutDialog() {
        (findViewById(R.id.info)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialogBuilder2 = Libcomun.getAlertDialog(BaseActivity.this, R.layout.dialog_about);
                final AlertDialog alertDialog2 = dialogBuilder2
                        .create();
                alertDialog2.show();

                Libcomun.setFontTextViewLight((TextView) alertDialog2.findViewById(R.id.version), BaseActivity.this);
                Libcomun.setFontTextViewLight((TextView) alertDialog2.findViewById(R.id.powered), BaseActivity.this);

                Libcomun.setFontTextViewLight((TextView) alertDialog2.findViewById(R.id.libraries), BaseActivity.this);
                Libcomun.setFontTextViewLight((TextView) alertDialog2.findViewById(R.id.title_libraries), BaseActivity.this);

                PackageInfo pInfo = null;
                try {
                    pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                String version = pInfo.versionName;

                ((TextView) alertDialog2.findViewById(R.id.version)).setText(getString(R.string.app_name) + " v" + version);
            }
        });
    }
}
