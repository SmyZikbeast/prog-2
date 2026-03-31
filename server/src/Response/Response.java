package Response;

public class Response {
    public String getDataType() {
        return DataType;
    }

    public Object getData() {
        return Data;
    }

    String DataType;
    Object Data;
    public Response(String dt, Object d){
        this.DataType = dt;
        this.Data = d;
    }
}
