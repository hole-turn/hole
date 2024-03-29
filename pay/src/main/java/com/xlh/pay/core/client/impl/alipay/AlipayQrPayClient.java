package com.xlh.pay.core.client.impl.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.xlh.pay.common.PayChannelEnum;
import com.xlh.pay.core.client.PayCommonResult;
import com.xlh.pay.core.client.dto.PayOrderUnifiedReqDTO;
import lombok.extern.slf4j.Slf4j;

import static com.alibaba.fastjson.JSONObject.toJSONString;

/**
 * 支付宝【扫码支付】的 PayClient 实现类
 * 文档：https://opendocs.alipay.com/apis/02890k
 */
@Slf4j
public class AlipayQrPayClient extends AbstractAlipayClient {

    public AlipayQrPayClient(Long channelId, AlipayPayClientConfig config) {
        super(channelId, PayChannelEnum.ALIPAY_QR.getCode(), config, new AlipayPayCodeMapping());
    }

    @Override
    public PayCommonResult<AlipayTradePrecreateResponse> doUnifiedOrder(PayOrderUnifiedReqDTO reqDTO) {
        // 构建 AlipayTradePrecreateModel 请求
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setOutTradeNo(reqDTO.getMerchantOrderId());
        model.setSubject(reqDTO.getSubject());
        model.setBody(reqDTO.getBody());
        model.setTotalAmount(calculateAmount(reqDTO.getAmount()).toString()); // 单位：元
        // TODO：userIp + expireTime
        // 构建 AlipayTradePrecreateRequest
        AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
        request.setBizModel(model);
        request.setNotifyUrl(reqDTO.getNotifyUrl());
        request.setReturnUrl(reqDTO.getReturnUrl());
        // 执行请求
        AlipayTradePrecreateResponse response;
        try {
            response = client.execute(request);
        } catch (AlipayApiException e) {
            log.error("[unifiedOrder][request({}) 发起支付失败]", toJSONString(reqDTO), e);
            return PayCommonResult.build(e.getErrCode(), e.getErrMsg(), null, codeMapping);
        }
        // TODO sub Code 需要测试下各种失败的情况
        return PayCommonResult.build(response.getCode(), response.getMsg(), response, codeMapping);
    }
}
