package co.unicauca.notification.infra.dto;

import java.util.List;

public class NotificationRequest {
    private List<String> email;
    private String subject;
    private String message;

    public NotificationRequest() {}

    public NotificationRequest(List<String> email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.message = message;
    }

    public List<String> getEmail() { return email; }
    public void setEmail(List<String> email) { this.email = email; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
