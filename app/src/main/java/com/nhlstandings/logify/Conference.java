package com.nhlstandings.logify;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Scott on 12/25/2015.
 */
public class Conference implements Parcelable {

    private String name;

    public List<Division> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private List<Division> divisions;

    public Conference(){
        divisions = new ArrayList<>();
    }

    protected Conference(Parcel in) {
        name = in.readString();
        if (in.readByte() == 0x01) {
            divisions = new ArrayList<Division>();
            in.readList(divisions, Division.class.getClassLoader());
        } else {
            divisions = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (divisions == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(divisions);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Conference> CREATOR = new Parcelable.Creator<Conference>() {
        @Override
        public Conference createFromParcel(Parcel in) {
            return new Conference(in);
        }

        @Override
        public Conference[] newArray(int size) {
            return new Conference[size];
        }
    };
}
