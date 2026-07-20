package com.cognizant.springcore;

public class NotificationManager {
    
    private final MessageService messageService;

    public NotificationManager(MessageService messageService) {
        this.messageService = messageService;
    }

    public void notifyUser(String userId, String content) {
        System.out.println("Preparing notification for User: " + userId);
        messageService.sendMessage(userId, content);
    }
}
