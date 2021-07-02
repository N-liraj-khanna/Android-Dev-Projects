package com.example.recyclerview.Model;

public class ChatView {
    private int imageView;
    private String nameText;
    private String chatText;
    private String timeStampText;
    private String Dashes;

    public ChatView(int imageView, String nameText, String chatText, String timeStampText, String dashes) {
        this.imageView = imageView;
        this.nameText = nameText;
        this.chatText = chatText;
        this.timeStampText = timeStampText;
        Dashes = dashes;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getNameText() {
        return nameText;
    }

    public void setNameText(String nameText) {
        this.nameText = nameText;
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public String getTimeStampText() {
        return timeStampText;
    }

    public void setTimeStampText(String timeStampText) {
        this.timeStampText = timeStampText;
    }

    public String getDashes() {
        return Dashes;
    }

    public void setDashes(String dashes) {
        Dashes = dashes;
    }
}
