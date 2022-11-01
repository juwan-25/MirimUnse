package kr.hs.s2104.mirimunse;

public class FriendItem {
    String name;
    int message;
    int resourceId;

    public FriendItem(int resourceId, String name, int message) {
        this.name = name;
        this.message= message;
        this.resourceId = resourceId;
    }

    public int getResourceId() {return resourceId;}

    public int getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

}