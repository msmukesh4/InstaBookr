package com.example.instabookr;

/**
 * Created by msmuk on 23-10-2016.
 */
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
/**
 * Created by Mukesh on 10-23-2016.
 */
public class ProgressView extends Dialog {

    public ProgressView(Context context, int resourceIdOfImage) {
        super(context, R.style.ProgressView);
        WindowManager.LayoutParams wlmp = getWindow().getAttributes();
        wlmp.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(wlmp);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        LinearLayout layout = new LinearLayout(context);
//        layout.setId(R.id.progressBar);
        layout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        ImageView iv = new ImageView(context);
        iv.setImageResource(resourceIdOfImage);
        layout.addView(iv, params);
        addContentView(layout, params);
    }

    @Override
    public void show() {
//        if (!TiatrosConstants.isTestApp)
        super.show();

    }

    @Override
    public void dismiss() {
//        if (!TiatrosConstants.isTestApp)
        super.dismiss();

    }
}
