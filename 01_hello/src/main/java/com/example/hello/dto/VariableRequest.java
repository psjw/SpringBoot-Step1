package com.example.hello.dto;

    /*
    {
        "header" : {
            "responseCode" : "OK"
        },
        "body" : {
            내용이 가변
        }
    }
     */

public class VariableRequest<T> {

    private Header header;
    private T resBody;

    public static class Header{
        private String responseCode;

        public String getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(String responseCode) {
            this.responseCode = responseCode;
        }

        @Override
        public String toString() {
            return "Header{" +
                    "responseCode='" + responseCode + '\'' +
                    '}';
        }
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getResBody() {
        return resBody;
    }

    public void setResBody(T resBody) {
        this.resBody = resBody;
    }

    @Override
    public String toString() {
        return "VariableRequest{" +
                "header=" + header +
                ", body=" + resBody +
                '}';
    }
}
