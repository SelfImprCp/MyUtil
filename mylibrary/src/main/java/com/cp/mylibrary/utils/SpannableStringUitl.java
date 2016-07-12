package com.cp.mylibrary.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Browser;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jerry on 2016/7/1.
 */
public class SpannableStringUitl {


    /**
     * 超链接
     */
    public void addUrlSpan(TextView tv,String httpUrl) {
        SpannableString spanString = new SpannableString("超链接");
        URLSpan span = new URLSpan(httpUrl);
        spanString.setSpan(span, 0, 0, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.append(spanString);
    }


    /**
     * 让TextView自动解析URL并高亮设置点击链接(链接不支持中文)
     * Note:深深的体会到，写一个正则不容易啊，Android居然还不支持POSIX字符
     * <p/>
     * Created by kymjs(www.kymjs.com) on 8/5/15.
     *
     * @param tv      TextView
     * @param content 要高亮的内容
     * @return 已经解析之后的TextView
     */
    public static TextView handleText(TextView tv, String content) {
        SpannableStringBuilder sp = new SpannableStringBuilder(content);
        //又碰上一个坑，在Android中的\p{Alnum}和Java中的\p{Alnum}不是同一个值，非得要我换成[a-zA-Z0-9]才行
        Pattern pattern = Pattern.compile("(http|https|ftp|svn)://([a-zA-Z0-9]+[/?.?])" +
                "+[a-zA-Z0-9]*\\??([a-zA-Z0-9]*=[a-zA-Z0-9]*&?)*");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String url = matcher.group();
            int start = content.indexOf(url);
            if (start >= 0) {
                int end = start + url.length();
                sp.setSpan(new URLSpan(url), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                sp.setSpan(getClickableSpan(url), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        tv.setText(sp);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        return tv;
    }

    /**
     * 处理html数据的高亮与响应
     *
     * @param tv
     * @param content
     * @return
     */
    public static TextView handleHtmlText(TextView tv, String content) {
        SpannableStringBuilder sp = new SpannableStringBuilder(Html.fromHtml(content));
        URLSpan[] urlSpans = sp.getSpans(0, sp.length(), URLSpan.class);
        for (final URLSpan span : urlSpans) {
            int start = sp.getSpanStart(span);
            int end = sp.getSpanEnd(span);
            sp.setSpan(getClickableSpan(span.getURL()), start, end, Spanned
                    .SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        tv.setText(sp);
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        return tv;
    }

    /**
     * 设置链接跳转与高亮样式
     *
     * @param url
     * @return
     */
    private static ClickableSpan getClickableSpan(final String url) {
        return new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Uri uri = Uri.parse(url);
                Context context = widget.getContext();
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.putExtra(Browser.EXTRA_APPLICATION_ID, context.getPackageName());
                context.startActivity(intent);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
                ds.setUnderlineText(false);
            }
        };
    }




    /**
     * 文字背景颜色
     */
    public static void addBackColorSpan(TextView textView,int color) {
        SpannableString spanString = new SpannableString(textView.getText().toString());

        BackgroundColorSpan span = new BackgroundColorSpan(color);
        spanString.setSpan(span, 0, textView.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spanString);
    }


    /**
     * 文字颜色
     */
    public static  void addForeColorSpan(TextView textView,int color) {
        SpannableString spanString = new SpannableString(textView.getText().toString());
        ForegroundColorSpan span = new ForegroundColorSpan(color);
        spanString.setSpan(span, 0,  textView.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spanString);
    }

//
//    /**
//     * 字体大小
//     */
//    private void addFontSpan() {
//        SpannableString spanString = new SpannableString("36号字体");
//        AbsoluteSizeSpan span = new AbsoluteSizeSpan(36);
//        spanString.setSpan(span, 0, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tv.append(spanString);
//    }
//
//
//    /**
//     * 粗体，斜体
//     */
//    private void addStyleSpan() {
//        SpannableString spanString = new SpannableString("BIBI");
//        StyleSpan span = new StyleSpan(Typeface.BOLD_ITALIC);
//        spanString.setSpan(span, 0, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tv.append(spanString);
//    }
//
//
    /**
     * 删除线
     */
    public static void addStrikeSpan(TextView textView) {
        SpannableString spanString = new SpannableString(textView.getText().toString());
        StrikethroughSpan span = new StrikethroughSpan();
        spanString.setSpan(span, 0, textView.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spanString);
    }

    /**
     * 下划线
     */
    public static void addUnderLineSpan(TextView textView) {
        SpannableString spanString = new SpannableString(textView.getText().toString());
        UnderlineSpan span = new UnderlineSpan();
        spanString.setSpan(span, 0, textView.getText().toString().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spanString);
    }



    /**
     * 一个TextView 显示 两种颜色
     */
    public static String contextColor = "#242424";
    public static String oneColor = "#999999";
    public static String twoColor = "#ff1d62";
    public static String blackColor = "#000000";

    public static void textViewShowTwoColor(TextView textView, String oneStr,
                                            String twoStr, String oneColor, String twoColor) {
        // mholder.item_mfocus_content.setText();
        String focus_content = oneStr + twoStr;

        SpannableStringBuilder builder = new SpannableStringBuilder(
                focus_content.toString());

        // ForegroundColorSpan 为文字前景色，BackgroundColorSpan为文字背景色
        // ForegroundColorSpan redSpan = new ForegroundColorSpan(oneColor);
        // ForegroundColorSpan whiteSpan = new ForegroundColorSpan(twoColor);
        ForegroundColorSpan redSpan = new ForegroundColorSpan(
                Color.parseColor(oneColor));
        ForegroundColorSpan whiteSpan = new ForegroundColorSpan(
                Color.parseColor(twoColor));

        builder.setSpan(redSpan, 0, oneStr.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(whiteSpan, oneStr.length(), focus_content.length(),
                Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        textView.setText(builder);
    }



//
//
//    /**
//     * 图片
//     */
//    private void addImageSpan() {
//        SpannableString spanString = new SpannableString(" ");
//        Drawable d = getResources().getDrawable(R.drawable.ic_launcher);
//        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
//        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
//        spanString.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tv.append(spanString);
//    }

    public static int getGridViewHeightByColumns(int columns, int allDataSize) {
        // 返回的grivView 的高度
        int gridViewHeight = 0;
        // 每行item高度
        int itemHeight = 450;

        // 一共有多少列 gridView
        int itemAll = 0;
        if (allDataSize % 2 == 0) {

            itemAll = allDataSize / 2;

            // 被2整除
        } else {
            itemAll = allDataSize / 2 + 1;

        }

        gridViewHeight = itemAll * itemHeight;
        return gridViewHeight;

    }


}
