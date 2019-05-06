package com.example.android.miwok;


import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Fragment,实现listview布局，监听，mediaplayer播放音频，焦点获取
 */

public class PhrasesFragment extends Fragment {

    private ArrayList<Word> words;
    private ListView listView;
    private WordAdapter wordAdapter;
    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //加载主布局
        View rootView = inflater.inflate(R.layout.word_list, container,false);
        initData(rootView);
        mediaFun();
        return rootView;
    }

    /**
     * 在生命周期onstop释放音频资源
     */
    @Override
    public void onStop() {
        mediaRelease();
        super.onStop();
    }

    /**
     * 初始化功能组件
     * @param rootView 主布局
     */
    public void initData(View rootView) {
        words = new ArrayList<>();
        words.add(new Word("minto wuksus","Where are you going ?",-1,R.raw.phrase_where_are_you_going));
        words.add(new Word("tinnə oyaase'nə","What is your name?",-1,R.raw.phrase_what_is_your_name));
        words.add(new Word("oyaaset...","My name is...",-1,R.raw.phrase_my_name_is));
        words.add(new Word("michəksəs?","How are you feeling?",-1,R.raw.phrase_how_are_you_feeling));
        words.add(new Word("kuchi achit","I’m feeling good.",-1,R.raw.phrase_im_feeling_good));
        words.add(new Word("əənəs'aa?","Are you coming?",-1,R.raw.phrase_are_you_coming));
        words.add(new Word("həə’ əənəm","Yes, I’m coming.",-1,R.raw.phrase_yes_im_coming));
        words.add(new Word("əənəm","I’m coming.",-1,R.raw.phrase_im_coming));
        words.add(new Word("yoowutis","Let’s go.",-1,R.raw.phrase_lets_go));
        words.add(new Word("ənni'nem","Come here",-1,R.raw.phrase_come_here));
        //建立listview适配器
        wordAdapter = new WordAdapter(getActivity(),words,R.color.category_phrases);
        //获取音频管理服务
        audioManager = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        //初始化主布局listview
        listView = rootView.findViewById(R.id.list);
    }
    /**
     * 音频播放相关功能
     */
    public void mediaFun() {
        //建立播放器对象
        mediaPlayer = new MediaPlayer();
        //listview适配
        listView.setAdapter(wordAdapter);
        //listview监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Word word = words.get(i);
                //释放旧音频资源
                mediaRelease();
                //获取音频焦点
                int result = audioManager.requestAudioFocus(mOnAudioFocusChangeListener, AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                //获得焦点，播放新音频
                if (result == audioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //audioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);
                    mediaPlayer = MediaPlayer.create(getActivity(),word.getmMiwokVoiceResourceIde());//匿名内部类里只用this模糊不清，报错
                    mediaPlayer.start();
                    //监听音频播放结束
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        //实现监听功能
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            mediaRelease();
                            Toast.makeText(getActivity(), "播放完毕", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    //实现音频焦点监听器
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            //短暂的失去音频焦点可以暂停或与新使用者共同使用
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                //暂停并重置到开始
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) { //获得焦点
                mediaPlayer.start();//继续播放
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) { //失去焦点
                mediaRelease();//释放音频资源
            }
        }
    };

    /**
     * 释放音频资源
     */
    @TargetApi(15)
    public void mediaRelease() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        audioManager.abandonAudioFocus(mOnAudioFocusChangeListener);//必须解除焦点占用，否则其它的音频会出问题
    }
}
