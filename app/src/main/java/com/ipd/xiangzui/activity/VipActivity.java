package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.view.View;
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
import butterknife.OnClick;

/**
 * Description ：VIP服务
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/15.
 */
public class VipActivity extends BaseActivity {

    @BindView(R.id.tv_vip)
    TopView tvVip;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    //    @BindView(R.id.tv_vip_text)
//    TextView tvVipText;
    @BindView(R.id.wv_content)
    WebView wvContent;

    private int demandType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_vip;
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
        ImmersionBar.setTitleBar(this, tvVip);

        demandType = getIntent().getIntExtra("demandType", 0);
    }

    @Override
    public void initData() {
//        tvVipText.setText("享醉医疗目前运营项目共有20余钟，我们针对不同客户群体需要，进行私人订制方案，依托于大基因中心的188项全方位体检，对客户身体情况进行精准筛查，由资深专家对客户体检报告进行1对1解读，深度剖析身体状况，并有针对性的提供解决方案和配套服务。 配合罗氏第五代、欧姆龙等医学仪器，给客户提供更为精准的全方位检查，特色的结合专家1对1解读体检报告，给客户更加私密的检查环境，结合体检报告给出科学建议，以及私人订制方案等都是我们的特色所在。");

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
        wvContent.loadUrl(getIntent().getStringExtra("h5Url"));


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
                tvTopTitle.setText(title);
            }
        });
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.sb_historical_demand, R.id.sb_push_demand})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_historical_demand:
                //历史需求
                startActivity(new Intent(this, HistoricalDemandActivity.class).putExtra("demandType", demandType));
                break;
            case R.id.sb_push_demand:
                //发布需求
                startActivity(new Intent(this, PushDemandActivity.class).putExtra("demandType", demandType));
                break;
        }
    }
}