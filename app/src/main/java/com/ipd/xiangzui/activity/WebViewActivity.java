package com.ipd.xiangzui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;

import butterknife.BindView;

import static com.ipd.xiangzui.common.config.UrlConfig.BASE_LOCAL_URL;

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.tv_webview_top)
    TopView tvWebviewTop;
    @BindView(R.id.tv_top_title)
    TextView ivTopTitle;
    @BindView(R.id.wv_content)
    WebView wvContent;

    //    private CommonDialogUtils dialogUtils;
    String h5Url = "";
    int h5Type = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvWebviewTop);

//        dialogUtils = new CommonDialogUtils();
//        dialogUtils.showProgress(this, "Loading...");

        h5Type = getIntent().getIntExtra("h5Type", 0);
        switch (h5Type) {
            case 1: //用户协议
                h5Url = BASE_LOCAL_URL + "H5/document/userNotice.html";
                break;
            case 2: //平台协议
                h5Url = BASE_LOCAL_URL + "H5/document/platformProtocol.html";
                break;
            case 3: //关于平台
//                h5Url = BASE_LOCAL_URL + "H5/document/userNotice.html";
                break;
            case 4: //保险
                h5Url = BASE_LOCAL_URL + "H5/document/Insurance.html";
                break;
            case 5: //vip服务
                h5Url = BASE_LOCAL_URL + "H5/document/vipService.html";
                break;
            case 6: //专家会诊
                h5Url = BASE_LOCAL_URL + "H5/document/expertConsultation.html";
                break;
            case 7: //麻醉筹建
                h5Url = BASE_LOCAL_URL + "H5/document/anesthesia.html";
                break;
            case 8: //设备租聘
                h5Url = BASE_LOCAL_URL + "H5/document/equipmentRental.html";
                break;
            case 9: //轮播
                h5Url = getIntent().getStringExtra("h5Url");
            case 10: //图文
                wvContent.loadData(getHtmlData(getIntent().getStringExtra("h5_url")), "text/html;charset=utf-8", "utf-8");
                break;
        }

        if (h5Type == 10) {
            WebSettings settings = wvContent.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setUseWideViewPort(true);
            settings.setLoadWithOverviewMode(true);
            wvContent.setWebViewClient(new MyWebViewClient(this));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
            } else {
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
            }
        } else {
            WebSettings webSettings = wvContent.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setUseWideViewPort(true);
            //支持自动加载图片
            webSettings.setLoadsImagesAutomatically(true);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);// 排版适应屏幕
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
            wvContent.loadUrl(h5Url);
        }
    }

    static class MyWebViewClient extends WebViewClient {
        private Dialog dialog;
        private Activity activity;

        public MyWebViewClient(Activity activity) {
            dialog = new Dialog(activity);
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (!activity.isFinishing()) dialog.show();
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
            super.onReceivedSslError(view, handler, error);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (!activity.isFinishing()) dialog.dismiss();
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        //设置客户端，让点击跳转操作在当前应用内存进行操作
        wvContent.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

//                if (dialogUtils != null) {
//                    dialogUtils.dismissProgress();
//                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            //当发生证书认证错误时，采用默认的处理方法handler.cancel()，停止加载问题页面
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                handler.cancel();
            }
        });

        wvContent.setWebChromeClient(new WebChromeClient() {
            //返回当前加载网页的进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }

            //获取当前网页的标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (h5Type == 10)
                    ivTopTitle.setText("详情");
                else
                    ivTopTitle.setText(title);
            }
        });
    }

    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>html{padding:15px;} body{word-wrap:break-word;font-size:13px;padding:0px;margin:0px} p{padding:0px;margin:0px;font-size:13px;color:#222222;line-height:1.3;} img{padding:0px,margin:0px;max-width:100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
}