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

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
}
