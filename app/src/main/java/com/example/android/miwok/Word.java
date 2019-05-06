package com.example.android.miwok;

/**
 * ListView 单项内容类
 */
public class Word {
    private String mMiwokTranslation;
    private String mDefaultTranslation;
    private int mImageResourceId = NO_IMAGE_PROVIDE;
    private int mMiwokVoiceResourceIde;
    public static final int NO_IMAGE_PROVIDE = -1; //超出图片有限数字范围，避免

    public Word(String mMiwokTranslation, String mDefaultTranslation) {
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDefaultTranslation = mDefaultTranslation;
    }

    public Word(String mMiwokTranslation, String mDefaultTranslation, int mImageResourceId) {
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDefaultTranslation = mDefaultTranslation;
        this.mImageResourceId = mImageResourceId;
    }

    public Word(String mMiwokTranslation, String mDefaultTranslation, int mImageResourceId, int mMiwokVoiceResourceIde) {
        this.mMiwokTranslation = mMiwokTranslation;
        this.mDefaultTranslation = mDefaultTranslation;
        this.mImageResourceId = mImageResourceId;
        this.mMiwokVoiceResourceIde = mMiwokVoiceResourceIde;
    }
    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public String getmMiwokTranslation() {
        return mMiwokTranslation;
    }

    public String getmDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getmMiwokVoiceResourceIde() {
        return mMiwokVoiceResourceIde;
    }

    /**
     * @return 判断有无图片
     */
    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDE;
    }
}
