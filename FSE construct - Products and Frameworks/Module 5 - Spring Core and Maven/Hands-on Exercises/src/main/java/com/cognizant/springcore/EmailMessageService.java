package com.cognizant.springcore;

public class EmailMessageService implements MessageService {
    
    private String senderAddress;

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    @Override
    public void sendMessage(String recipient, String message) {
        System.out.println("Email sent from [" + senderAddress + "] to [" + recipient + "]: " + message);
    }
}
