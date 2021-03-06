package com.lotus.iot.mpm.eventhandler.datamodel;

import com.lotus.context.EventHandlerContext;
import com.lotus.iot.mpm.api.objectmodel.request.SubscribeRequest;
import com.lotus.iot.mpm.eventhandler.objectmodel.AssetStatusEvent;
import com.lotus.iot.mpm.eventhandler.objectmodel.SampleListener;
import com.lotus.iot.mpm.objectmodel.type.StatusType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * @author Niloufar Mazloumpour
 * @mail niloufar@mazloumpour.net
 * @since 2018-05-22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EventHandlerContext.class)
public class AssetStatusEventServiceTest {

    @Autowired
    AssetStatusEventService assetStatusEventService;

    @Test
    public void subscribe() {
        String assetId = UUID.randomUUID().toString();
        SampleListener observer = new SampleListener();
        SubscribeRequest request = new SubscribeRequest(assetId, AssetStatusEvent.AssetMayFail, observer);
        assetStatusEventService.subscribe(request);

        assetStatusEventService.statusChange(assetId, StatusType.WARNING);
        assert (observer.isNotified());
    }


}
