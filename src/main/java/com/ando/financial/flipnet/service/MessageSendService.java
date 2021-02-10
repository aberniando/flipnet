package com.ando.financial.flipnet.service;

import com.ando.financial.flipnet.model.request.MessageSendRequest;
import com.ando.financial.flipnet.model.response.MessageSendResponse;

public interface MessageSendService {

    MessageSendResponse send(MessageSendRequest request);

}
