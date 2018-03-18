package com.yamini.training.utils;

/**
 * Created by sravan on 17/03/18.
 */

public class Movie {


    public String  mReleaseData;


    public int getmPage() {
        return mPage;
    }

    public void setmPage(int mPage) {
        this.mPage = mPage;
    }

    private int mPage ;

    public int getmMovieId() {
        return mMovieId;
    }

    public void setmMovieId(int mMovieId) {
        this.mMovieId = mMovieId;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "mReleaseData='" + mReleaseData + '\'' +
                ", mPage=" + mPage +
                ", mMovieId=" + mMovieId +
                ", mOverView='" + mOverView + '\'' +
                ", mBackGroundPath='" + mBackGroundPath + '\'' +
                ", mOriginalDate='" + mOriginalDate + '\'' +
                '}';
    }

    private int mMovieId;

    public Movie(int mPage , String mReleaseData, String mOverView, String mBackGroundPath, String mOriginalDate,
    int mMovieId) {
        this.mReleaseData = mReleaseData;
        this.mOverView = mOverView;
        this.mBackGroundPath = mBackGroundPath;
        this.mOriginalDate = mOriginalDate;
        this.mPage = mPage;
        this.mMovieId= mMovieId;

    }

    public String getmReleaseData() {
        return mReleaseData;
    }

    public void setmReleaseData(String mReleaseData) {
        this.mReleaseData = mReleaseData;
    }

    public String getmOverView() {
        return mOverView;
    }

    public void setmOverView(String mOverView) {
        this.mOverView = mOverView;
    }

    public String getmBackGroundPath() {
        return mBackGroundPath;
    }

    public void setmBackGroundPath(String mBackGroundPath) {
        this.mBackGroundPath = mBackGroundPath;
    }

    public String getmOriginalDate() {
        return mOriginalDate;
    }

    public void setmOriginalDate(String mOriginalDate) {
        this.mOriginalDate = mOriginalDate;
    }

    private String  mOverView;
    private String  mBackGroundPath;
    private String  mOriginalDate;

}
