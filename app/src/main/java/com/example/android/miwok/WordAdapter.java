package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * 编写listview的适配器类
 */
public class WordAdapter extends ArrayAdapter<Word> {

    private int mColor = R.color.category_numbers;//默认为橙色

    public WordAdapter(@NonNull Context context,@NonNull ArrayList<Word> words,int mColor) {
        super(context, 0, words);
        this.mColor = mColor;
    }

    /**
     * 重写getview方法，适配布局
     * @param position listview的位置变量
     * @param convertView list_item,listview子布局
     * @param parent listview
     * @return list_item
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }
        //获取相应位置的Word类
        Word aword = getItem(position);
        //设置图片，有则显示，无则不显示
        ImageView imageWord = listItemView.findViewById(R.id.image_word);
        if (aword.hasImage()) {
            imageWord.setImageResource(aword.getmImageResourceId());
            imageWord.setVisibility(View.VISIBLE);
        } else {
            imageWord.setVisibility(View.GONE);//不可见且不占空间
        }
        //设置miwok文字
        TextView miwokTextVie = listItemView.findViewById(R.id.miwok_text_view);
        miwokTextVie.setText(aword.getmMiwokTranslation());
        //设置翻译文字
        TextView defaultTextView = listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(aword.getmDefaultTranslation());
        //设置背景颜色
        LinearLayout linearLayout = listItemView.findViewById(R.id.linearLayout_item_text);
        //颜色值转换
        int color = linearLayout.getResources().getColor(mColor);
        linearLayout.setBackgroundColor(color);
        return listItemView;
    }
}
