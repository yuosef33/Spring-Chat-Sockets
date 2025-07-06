package com.project.chatapp.Model;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;


public class ChatMessage {
    private String message;
    private String sender;
    private LocalDateTime time;
    private ChatType chatType;

    public ChatMessage() {
    }

    public ChatMessage(String message, String sender, LocalDateTime time, ChatType chatType) {
        this.message = message;
        this.sender = sender;
        this.time = time;
        this.chatType = chatType;
    }

    public ChatMessage(String message, String sender, ChatType chatType) {
        this.message = message;
        this.sender = sender;
        this.chatType = chatType;
    }

    public ChatMessage(String message, String sender) {
        this.message = message;
        this.sender = sender;
        this.chatType=ChatType.CHAT;
    }
    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public ChatType getChatType() {
        return chatType;
    }

    public void setChatType(ChatType chatType) {
        this.chatType = chatType;
    }
}
