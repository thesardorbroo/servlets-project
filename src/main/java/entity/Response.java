package entity;

public class Response<C> {

    private Integer code;

    private Boolean success;

    private String message;

    private C data;

    public Response(){}

    public Response(Integer code, Boolean success, String message, C data){
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public C getData() {
        return data;
    }

    public void setData(C data) {
        this.data = data;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder(String.format("{\ncode: %d\nsuccess: %b\nmessage: %s\ndata: \n\t", code, success, message));
        builder.append(data + "\n");
        return builder + "";
    }
}
