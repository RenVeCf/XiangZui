package com.ipd.xiangzui.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wlj
 * @date 2017/3/29
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils.utils
 * @desc: 验证数据合法性
 */

public class VerifyUtils {
    /**
     * verify whether email is valid
     * 验证电子邮件是否有效
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern pattern = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)" +
                        "+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * verify whether email is valid
     * 验证电子邮件是否有效
     *
     * @param email
     * @return
     */
    public static boolean isEmail2(String email) {
        String expr = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return email.matches(expr);
    }

    /**
     * verify whether mobile number is valid
     * 验证移动电话号码是否有效
     *
     * @param number
     * @return
     */
    public static boolean isMobileNumber(String number) {
        String expr = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        return number.matches(expr);
    }

    /**
     * verify whether url is valid
     * 验证URL是否有效
     *
     * @param url
     * @return
     */
    public static boolean isUrl(String url) {
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher(url);
        return matcher.matches();
    }

    /**
     * verify whether number and letter are only contained
     * 核实数字和字母是否包含
     *
     * @param data
     * @return
     */
    public static boolean isNumberAndLetter(String data) {
        String expr = "^[A-Za-z0-9]+$";
        return data.matches(expr);
    }

    /**
     * verify whether number is only contained
     * 验证数字是否包含
     *
     * @param data
     * @return
     */
    public static boolean isNumber(String data) {
        String expr = "^[0-9]+$";
        return data.matches(expr);
    }

    /**
     * verify whether letter is only contained
     * 核实信息是否包含某字符串
     *
     * @param data
     * @return
     */
    public static boolean isLetter(String data) {
        String expr = "^[A-Za-z]+$";
        return data.matches(expr);
    }

    /**
     * verify whether Chinese is only contained
     * 核实是否只包含中文
     *
     * @param data
     * @return
     */
    public static boolean isChinese(String data) {
        String expr = "^[\u0391-\uFFE5]+$";
        return data.matches(expr);
    }

    /**
     * verify whether Chinese is contained
     * 核实是否包含中文
     *
     * @param data
     * @return
     */
    public static boolean isContainsChinese(String data) {
        String chinese = "[\u0391-\uFFE5]";
        if (!StringUtils.isEmpty(data)) {
            for (int i = 0; i < data.length(); i++) {
                String temp = data.substring(i, i + 1);
                boolean flag = temp.matches(chinese);
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * verify whether English is only contained
     * 核实是否只包含英文
     *
     * @param data
     * @return
     */
    public static boolean isEnglish(String data) {
        String expr = "^[a-zA-Z]{0,}$";
        return data.matches(expr);
    }

    /**
     * "a a a a a a a"
     * 核实是否字符与字符间只能用一个空格相连
     *
     * @param str
     * @return true为存在多个空格相连
     */
    public static boolean isHasTwinSpace(String str) {
        String content = new String(str);
        String tem = "";
        boolean hasTwinSpace = false;
        String[] contents = content.split("");
        for (int i = 0; i < contents.length; i++) {
            if (tem.matches("^[\\s]$") && tem.equals(contents[i])) {
                hasTwinSpace = true;
                break;
            } else {
                tem = contents[i];
            }
        }
        return hasTwinSpace;
    }

    /**
     * verify whether Num is only contained
     * 核实是否只包含数字
     *
     * @param str
     * @return
     */

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * verify whether chinese identity card is valid
     * 核实中国身份证是否有效
     *
     * @param data
     * @return
     */
    public static boolean isChineseCard(String data) {
        String expr = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" + "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        return data.matches(expr);
    }

    /**
     * verify whether post code is valid
     * 验证邮政编码是否有效
     *
     * @param data
     * @return
     */
    public static boolean isPostCode(String data) {
        String expr = "^[0-9]{6,10}";
        return data.matches(expr);
    }
}
