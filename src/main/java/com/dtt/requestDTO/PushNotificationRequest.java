package com.dtt.requestDTO;


public class PushNotificationRequest {

	private String title;
    
	private String message;

	private NotificationDataDTO data;

    private String topic;
    
    private String token;
    
    private String priority;
    
    private String to;

    public PushNotificationRequest() { }

    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getTopic() {
        return topic;
    }

    public String getToken() {
        return token;
    }

	public NotificationDataDTO getData() {
		return data;
	}

	public void setData(NotificationDataDTO data) {
		this.data = data;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "PushNotificationRequest [title=" + title + ", message=" + message + ", data=" + data + ", topic="
				+ topic + ", token=" + token + ", priority=" + priority + ", to=" + to + "]";
	}	  
}