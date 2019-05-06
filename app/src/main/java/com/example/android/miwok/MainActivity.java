/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TableLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用的功能viewpager,tablayout,FragmentPagerAdapter.
 * 需要implementation 'com.android.support:design:27.1.1'
 */

public class MainActivity extends AppCompatActivity {

    List<String> mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化主布局
        setContentView(R.layout.activity_main);
        //初始化子布局viewpager
        ViewPager viewPager = findViewById(R.id.viewpager);
        //加载标题切换拦，需要加依赖库
        TabLayout myTab = findViewById(R.id.mytab);
        mTitle = new ArrayList<>();
        mTitle.add("Numbers");
        mTitle.add("Family");
        mTitle.add("Colors");
        mTitle.add("Phrases");
        //viewpager适配Fragement,具备页面切换功能
        //建立适配器，重写1切换相应的页面，2.页面个数，3.页面栏标题
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new NumbersFragment();
                    case 1:
                        return new FamilyFragment();
                    case 2:
                        return new ColorsFragment();
                    default:
                        return new PhrasesFragment();
                }
            }

            @Override
            public int getCount() {
                return 4;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return mTitle.get(position);
            }
        });
        //关联viewpager
        myTab.setupWithViewPager(viewPager);
    }
}
