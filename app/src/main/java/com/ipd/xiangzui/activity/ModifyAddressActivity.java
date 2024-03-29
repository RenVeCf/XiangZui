package com.ipd.xiangzui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.AddAddressBean;
import com.ipd.xiangzui.bean.AddressListBean;
import com.ipd.xiangzui.bean.CityAddressBean;
import com.ipd.xiangzui.bean.DelAddressBean;
import com.ipd.xiangzui.bean.ModifyAddressBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.common.view.TwoBtDialog;
import com.ipd.xiangzui.contract.AddressContract;
import com.ipd.xiangzui.presenter.AddressPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：添加地址/修改地址
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/9.
 */
public class ModifyAddressActivity extends BaseActivity<AddressContract.View, AddressContract.Presenter> implements AddressContract.View {

    @BindView(R.id.tv_modify_address)
    TopView tvModifyAddress;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.bt_top_del_address)
    Button btTopDelAddress;
    @BindView(R.id.stv_address)
    SuperTextView stvAddress;
    @BindView(R.id.et_detailed_address)
    AppCompatEditText etDetailedAddress;
    @BindView(R.id.cb_default_address)
    MaterialCheckBox cbDefaultAddress;

    private OptionsPickerView pvOptions; //条件选择器
    private ArrayList<CityAddressBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private int addressType; //1: 添加地址, 2:修改地址
    private int addressId;
    private String prov, city, dist;

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_address;
    }

    @Override
    public AddressContract.Presenter createPresenter() {
        return new AddressPresenter(this);
    }

    @Override
    public AddressContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvModifyAddress);

        initJsonData(); //加载地区选择器数据源

        addressId = getIntent().getIntExtra("addressId", 0);
        addressType = getIntent().getIntExtra("addressType", 0);
        switch (addressType) {
            case 1:
                tvTopTitle.setText("添加地址");
                btTopDelAddress.setVisibility(View.GONE);
                break;
            case 2:
                tvTopTitle.setText("修改地址");
                prov = getIntent().getStringExtra("prov");
                city = getIntent().getStringExtra("city");
                dist = getIntent().getStringExtra("dist");
                stvAddress.setCenterString(prov + " " + city + " " + dist);
                etDetailedAddress.setText(getIntent().getStringExtra("address"));
                cbDefaultAddress.setChecked("2".equals(getIntent().getStringExtra("defaultType")) ? true : false);
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    // 定位权限
    private void rxPermissionLocation() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if (granted) {
                    pickCity();
                } else {
                    // 权限被拒绝
                    ToastUtil.showLongToast(R.string.permission_rejected);
                }
            }
        });
    }

    // 选择城市
    private void pickCity() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                prov = options1Items.get(options1).getPickerViewText().replaceAll("市", "");
                city = options2Items.get(options1).get(options2);
                dist = options3Items.get(options1).get(options2).get(options3);
                String citys = prov + "  " +
                        city + "  " +
                        dist;
                stvAddress.setCenterString(citys);
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
                        tvTitle.setText("选择所在区域");
                        final Button tvSubmit = (Button) v.findViewById(R.id.bt_pickview_confirm);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isFastClick()) {
                                    pvOptions.returnData();
                                    pvOptions.dismiss();
                                }
                            }
                        });
                    }
                })
                .setTitleText("")
                .setCancelText(getResources().getString(R.string.cancel))
                .setSubmitText(getResources().getString(R.string.sure))
                .setOutSideCancelable(true)
                .setTextColorCenter(Color.BLACK)
                .setDividerColor(getResources().getColor(R.color.transparent))
                .setContentTextSize(16)
                .setDecorView(getWindow().getDecorView().findViewById(android.R.id.content))
                .build();
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级联动城市选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = getJson(ModifyAddressActivity.this, "province.json");//获取assets目录下的json文件数据

        ArrayList<CityAddressBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }

    public String getJson(Context context, String fileName) {

        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public ArrayList<CityAddressBean> parseData(String result) {//Gson 解析
        ArrayList<CityAddressBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                CityAddressBean entity = gson.fromJson(data.optJSONObject(i).toString(), CityAddressBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    @OnClick({R.id.bt_top_del_address, R.id.stv_address, R.id.sb_save_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_top_del_address:
                new TwoBtDialog(this, "确认删除地址？", "确认") {
                    @Override
                    public void confirm() {
                        TreeMap<String, String> delAddressMap = new TreeMap<>();
                        delAddressMap.put("userId", SPUtil.get(ModifyAddressActivity.this, USER_ID, "") + "");
                        delAddressMap.put("addressId", addressId + "");
                        delAddressMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(delAddressMap.toString().replaceAll(" ", "") + SIGN)));
                        getPresenter().getDelAddress(delAddressMap, false, false);
                    }
                }.show();
                break;
            case R.id.stv_address:
                rxPermissionLocation();
                break;
            case R.id.sb_save_address:
                if (isFastClick())
                    switch (addressType) {
                        case 1:
                            TreeMap<String, String> addAddressMap = new TreeMap<>();
                            addAddressMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                            addAddressMap.put("prov", prov);
                            addAddressMap.put("city", city);
                            addAddressMap.put("dist", dist);
                            addAddressMap.put("detail", etDetailedAddress.getText().toString().trim());
                            addAddressMap.put("defaultAddress", cbDefaultAddress.isChecked() ? "2" : "1");
                            addAddressMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(addAddressMap.toString().replaceAll(" ", "") + SIGN)));
                            getPresenter().getAddAddress(addAddressMap, false, false);
                            break;
                        case 2:
                            TreeMap<String, String> modifyAddressMap = new TreeMap<>();
                            modifyAddressMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                            modifyAddressMap.put("prov", prov);
                            modifyAddressMap.put("city", city);
                            modifyAddressMap.put("dist", dist);
                            modifyAddressMap.put("detail", etDetailedAddress.getText().toString().trim());
                            modifyAddressMap.put("defaultAddress", cbDefaultAddress.isChecked() ? "2" : "1");
                            modifyAddressMap.put("addressId", addressId + "");
                            modifyAddressMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(modifyAddressMap.toString().replaceAll(" ", "") + SIGN)));
                            getPresenter().getModifyAddress(modifyAddressMap, false, false);
                            break;
                    }
                break;
        }
    }

    @Override
    public void resultAddressList(AddressListBean data) {

    }

    @Override
    public void resultAddAddress(AddAddressBean data) {
        switch (data.getCode()) {
            case 200:
                setResult(RESULT_OK, new Intent().putExtra("add_address", 1));
                finish();
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void resultModifyAddress(ModifyAddressBean data) {
        switch (data.getCode()) {
            case 200:
                setResult(RESULT_OK, new Intent().putExtra("modify_address", 1));
                finish();
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void resultDelAddress(DelAddressBean data) {
        switch (data.getCode()) {
            case 200:
                setResult(RESULT_OK, new Intent().putExtra("del_address", 1));
                finish();
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
