package com.studiobrend.abraj;

import com.studiobrend.abraj.model.Name;
import java.util.List;

public class ApiResponse {
    private int code;
    private String status;
    private List<Name> data;

    // Getter za code
    public int getCode() {
        return code;
    }

    // Setter za code
    public void setCode(int code) {
        this.code = code;
    }

    // Getter za status
    public String getStatus() {
        return status;
    }

    // Setter za status
    public void setStatus(String status) {
        this.status = status;
    }

    // Getter za data (lista imena)
    public List<Name> getData() {
        return data;
    }

    // Setter za data
    public void setData(List<Name> data) {
        this.data = data;
    }
}
