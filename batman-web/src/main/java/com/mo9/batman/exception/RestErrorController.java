package com.mo9.batman.exception;

import com.mo9.batman.common.result.ResultConstant;
import com.mo9.batman.common.result.ResultData;
import com.mo9.batman.common.result.Results;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author : xjding
 * @date :   2017-12-12 11:33
 */
@RestController
@RestControllerAdvice
public class RestErrorController implements ErrorController {

    public static final List<Integer> DEFAULT_FAILED_STATUS = Arrays.asList(401, 404, 405);

    private final ErrorAttributes errorAttributes;

    public RestErrorController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public Results error(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));

        Integer status = Integer.valueOf(String.valueOf(body.get("status")));
        if (!StringUtils.isEmpty(status) && DEFAULT_FAILED_STATUS.contains(status)) {
            response.sendError(status, String.valueOf(body.get("message")));
            return null;
        }

        ResultData data = ResultData.builder()
                .append("exception", body.get("exception"))
                .append("path", body.get("path"))
                .append("status", body.get("status"))

                .build();
        return Results.nok(ResultConstant.REQUEST_ERROR, String.valueOf(body.get("message")), data);
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        return parameter != null && !"false".equals(parameter.toLowerCase());
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest aRequest, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(aRequest);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }


}
