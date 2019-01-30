/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.dapeng.impl.filters;


import com.github.dapeng.core.BeanSerializer;
import com.github.dapeng.core.TransactionContext;
import com.github.dapeng.core.filter.Filter;
import com.github.dapeng.core.filter.FilterChain;
import com.github.dapeng.core.filter.FilterContext;
import com.github.dapeng.impl.plugins.netty.SoaResponseWrapper;
import com.github.dapeng.org.apache.thrift.TException;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

import static io.netty.channel.ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE;

public class HeadFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(HeadFilter.class);

    @Override
    public void onEntry(FilterContext filterContext, FilterChain next) {
        try {
            if (LOGGER.isDebugEnabled()) {
                TransactionContext transactionContext = (TransactionContext) filterContext.getAttribute("context");
                LOGGER.debug(getClass().getSimpleName() + "::onEntry[seqId:" + transactionContext.seqId() + "]");
            }
            next.onEntry(filterContext);
        } catch (TException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void onExit(FilterContext filterContext, FilterChain prev) {
        // 第一个filter不需要调onExit
        // (这里不能通过TransactionContext.currentInstance()的方式.因为已经给remove掉了.
        TransactionContext transactionContext = (TransactionContext) filterContext.getAttribute("context");
        ChannelHandlerContext channelHandlerContext = (ChannelHandlerContext) filterContext.getAttribute("channelHandlerContext");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(getClass().getSimpleName()
                    + "::onExit:[seqId:" + transactionContext.seqId()
                    + ",channel:[" + channelHandlerContext.channel() + "]"
                    + ", execption:" + transactionContext.soaException()
                    + ",\n result:" + filterContext.getAttribute("result") + "]\n");
        }

        SoaResponseWrapper responseWrapper = new SoaResponseWrapper(transactionContext,
                Optional.ofNullable(filterContext.getAttribute("result")),
                Optional.ofNullable((BeanSerializer) filterContext.getAttribute("respSerializer")));
        channelHandlerContext.writeAndFlush(responseWrapper).addListener(FIRE_EXCEPTION_ON_FAILURE);
    }
}
