package cl.ionix.tbk_ewallet_sdk_android.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import cl.ionix.tbk_ewallet_sdk_android.R;

public class QROnepayView extends AppCompatImageView {

    private Context mContext;
    private AttributeSet attrs;
    private int styleAttr;
    private String ott;

    public QROnepayView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public QROnepayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        this.attrs = attrs;
        initView();
    }

    public QROnepayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs);
        this.mContext = context;
        this.attrs = attrs;
        this.styleAttr = defStyleAttr;
        initView();
    }

    private void initView() {
        TypedArray arr = mContext.obtainStyledAttributes(attrs, R.styleable.QROnepayView,
                styleAttr,0);
        ott = arr.getString(R.styleable.QROnepayView_ott);
        Bitmap qrBitmap = generateQR(ott);
        setImageBitmap(qrBitmap);
        arr.recycle();
    }

    private Bitmap generateQR(String ott) {
        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
        try {
            Bitmap bitmap = barcodeEncoder.encodeBitmap("onepay=ott:" + ott, BarcodeFormat.QR_CODE, 400, 400);
            return bitmap;
        } catch (WriterException e) {
            return null;
        }
    }

    public void setOtt(String ott) {
        this.ott = ott;
        Bitmap qrBitmap = generateQR(ott);
        setImageBitmap(qrBitmap);
    }
}
