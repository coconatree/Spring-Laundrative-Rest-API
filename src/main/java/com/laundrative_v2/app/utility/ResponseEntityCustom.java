package com.laundrative_v2.app.utility;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEntityCustom
{
    private String issueDate;
    private HttpMethod method;
    private HttpStatus code;
    private Object content;

    public static ResponseEntityCustom from(String issueDate, HttpMethod method, HttpStatus code, Object content)
    {
        ResponseEntityCustom response = new ResponseEntityCustom();

        response.setIssueDate(issueDate);
        response.setMethod(method);
        response.setCode(code);
        response.setContent(content);

        return response;
    }
}
