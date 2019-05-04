package com.drug.admin.common.resolver;


import com.drug.admin.common.SendMsgUtil;
import com.drug.admin.common.response.DataResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *系统全局异常处理器
 */
@Controller
public class OverallExceptionResolver implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        try {
            SendMsgUtil.sendJsonMessage(httpServletResponse,
                    new DataResponse.Builder<Integer>().setException(e.getMessage())
                            .setResultValue(0).build());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return new ModelAndView();
    }

}
