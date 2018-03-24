package com.parker.data;

import java.io.Serializable;

public class ResponseContainer implements Serializable {
    private boolean successful;
    private Object data;
    private Object errors;

    public ResponseContainer() {
        successful = true;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }
}
