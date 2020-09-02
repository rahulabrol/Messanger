package com.messanger.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Rahul Abrol on 10/24/17.
 * <p>
 * Parcelable model for user.
 */

public class User implements Parcelable {
    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(final Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(final int size) {
            return new User[size];
        }
    };
    private String name, email, id;

    /**
     * constructor.
     */
    public User() {
    }

    /**
     * constructor of user mode;
     *
     * @param name  name
     * @param email email
     * @param id    id of user.
     */
    public User(final String name, final String email, final String id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    /**
     * Const for parsable.
     *
     * @param in parcel instance.
     */
    protected User(final Parcel in) {
        this.name = in.readString();
        this.email = in.readString();
        this.id = in.readString();
    }

    /**
     * get name.
     *
     * @return string.
     */
    public String getName() {
        return name;
    }

    /**
     * get email.
     *
     * @return string
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get UID of user.
     *
     * @return string UID
     */
    public String getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeString(this.name);
        dest.writeString(this.email);
        dest.writeString(this.id);
    }
}
