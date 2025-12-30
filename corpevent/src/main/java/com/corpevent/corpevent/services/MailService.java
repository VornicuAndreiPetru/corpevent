package com.corpevent.corpevent.services;

public interface MailService {
    void sendAccessCode(String to, String code);
}
