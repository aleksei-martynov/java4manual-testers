package com.db.edu.etl;

public class ExtractedUser {
    public ExtractedUser(String userID, String userName) {
        UserID = userID;
        UserName = userName;
    }

    private String UserID;
    private String UserName;

    public String getUserID() {
        return UserID;
    }

    public String getUserName() {
        return UserName;
    }

    @Override
    public String toString() {
        return "ExtractedUser{" + "UserID='" + UserID + '\'' + ", UserName='" + UserName + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExtractedUser that = (ExtractedUser) o;

        if (UserID != null ? !UserID.equals(that.UserID) : that.UserID != null) return false;
        return UserName != null ? UserName.equals(that.UserName) : that.UserName == null;
    }

    @Override
    public int hashCode() {
        int result = UserID != null ? UserID.hashCode() : 0;
        result = 31 * result + (UserName != null ? UserName.hashCode() : 0);
        return result;
    }
}
