package com.company.dto;

public class ResponseDTO {
    private Object object;
    private Integer errorCode;
    private String errorMessage;
    private String successMessage;

    private ResponseDTO(){}

    public static ResponseDTO create(Object object){
        ResponseDTO response = new ResponseDTO();
        response.object = object;
        return response;
    }
    public static ResponseDTO create(Object object, String successMessage){
        ResponseDTO response = new ResponseDTO();
        response.object = object;
        response.successMessage = successMessage;
        return response;
    }
    public static ResponseDTO create(Object object, String errorMessage, Integer errorCode){
        ResponseDTO response = new ResponseDTO();
        response.object = object;
        response.errorMessage = errorMessage;
        response.errorCode = errorCode;
        return response;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }
}
