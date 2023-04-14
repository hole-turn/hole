package com.xlh.pay.core.client.impl.alipay;

import cn.hutool.core.date.DateUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.xlh.pay.common.PayChannelEnum;
import com.xlh.pay.core.client.PayCommonResult;
import com.xlh.pay.core.client.dto.PayOrderUnifiedReqDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 支付宝【手机网站】的 PayClient 实现类
 * 文档：https://opendocs.alipay.com/apis/api_1/alipay.trade.wap.pay
 *
 */
@Slf4j
public class AlipayWapPayClient extends AbstractAlipayClient {


    public AlipayWapPayClient(Long channelId, AlipayPayClientConfig config) {
        super(channelId, PayChannelEnum.ALIPAY_WAP.getCode(), config, new AlipayPayCodeMapping());
    }

    @Override
    public PayCommonResult<AlipayTradeWapPayResponse> doUnifiedOrder(PayOrderUnifiedReqDTO reqDTO) {
        // 构建 AlipayTradeWapPayModel 请求
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(reqDTO.getMerchantOrderId());
        model.setSubject(reqDTO.getSubject());
        model.setBody(reqDTO.getBody());
        model.setTotalAmount(calculateAmount(reqDTO.getAmount()).toString());
        model.setProductCode("QUICK_WAP_PAY"); // TODO 这里咋整
        // TODO 似乎这里不用传sellerId
        // https://opendocs.alipay.com/apis/api_1/alipay.trade.wap.pay
        //model.setSellerId("2088102147948060");
        model.setTimeExpire(DateUtil.format(reqDTO.getExpireTime(), "yyyy-MM-dd HH:mm:ss"));
        // TODO userIp
        // 构建 AlipayTradeWapPayRequest
        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setBizModel(model);
        request.setNotifyUrl(reqDTO.getNotifyUrl());
        request.setReturnUrl(reqDTO.getReturnUrl());

        // 执行请求
        AlipayTradeWapPayResponse response;
        try {
            response = client.pageExecute(request);
        } catch (AlipayApiException e) {
            return PayCommonResult.build(e.getErrCode(), e.getErrMsg(), null, codeMapping);
        }

        // TODO sub Code
        if (response.isSuccess() && Objects.isNull(response.getCode()) && Objects.nonNull(response.getBody())) {
            //成功alipay wap 成功 code 为 null , body 为form 表单
            return PayCommonResult.build("-9999", "Success", response, codeMapping);
        } else {
            return PayCommonResult.build(response.getCode(), response.getMsg(), response, codeMapping);
        }
    }


}
