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
package org.apache.rocketmq.store;

/**
 * When write a message to the commit log, returns results
 */
public class AppendMessageResult {
    // Return code
    // 消息追加结果
    // PUT_OK:追加成功
    // END_OF_FILE:超过文件大小
    // MESSAGE_SIZE_EXCEEDED: 消息长度超过最大长度
    // PROPERTIES_SIZE_EXCEEDED:消息属性超长
    // UNKONWN_ERROR:未知异常
    private AppendMessageStatus status;
    // Where to start writing
    // 消息物理偏移量
    private long wroteOffset;
    // Write Bytes

    private int wroteBytes;
    // Message ID
    // 消息ID
    private String msgId;
    // Message storage timestamp
    // 消息存储时间戳
    private long storeTimestamp;
    // Consume queue's offset(step by one)
    // 消息队列逻辑偏移量
    private long logicsOffset;
    // 当前未使用
    private long pagecacheRT = 0;
    // 消息条数，批量消息发送时消息条数
    private int msgNum = 1;

    public AppendMessageResult(AppendMessageStatus status) {
        this(status, 0, 0, "", 0, 0, 0);
    }

    public AppendMessageResult(AppendMessageStatus status, long wroteOffset, int wroteBytes, String msgId,
        long storeTimestamp, long logicsOffset, long pagecacheRT) {
        this.status = status;
        this.wroteOffset = wroteOffset;
        this.wroteBytes = wroteBytes;
        this.msgId = msgId;
        this.storeTimestamp = storeTimestamp;
        this.logicsOffset = logicsOffset;
        this.pagecacheRT = pagecacheRT;
    }

    public long getPagecacheRT() {
        return pagecacheRT;
    }

    public void setPagecacheRT(final long pagecacheRT) {
        this.pagecacheRT = pagecacheRT;
    }

    public boolean isOk() {
        return this.status == AppendMessageStatus.PUT_OK;
    }

    public AppendMessageStatus getStatus() {
        return status;
    }

    public void setStatus(AppendMessageStatus status) {
        this.status = status;
    }

    public long getWroteOffset() {
        return wroteOffset;
    }

    public void setWroteOffset(long wroteOffset) {
        this.wroteOffset = wroteOffset;
    }

    public int getWroteBytes() {
        return wroteBytes;
    }

    public void setWroteBytes(int wroteBytes) {
        this.wroteBytes = wroteBytes;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public long getStoreTimestamp() {
        return storeTimestamp;
    }

    public void setStoreTimestamp(long storeTimestamp) {
        this.storeTimestamp = storeTimestamp;
    }

    public long getLogicsOffset() {
        return logicsOffset;
    }

    public void setLogicsOffset(long logicsOffset) {
        this.logicsOffset = logicsOffset;
    }

    public int getMsgNum() {
        return msgNum;
    }

    public void setMsgNum(int msgNum) {
        this.msgNum = msgNum;
    }

    @Override
    public String toString() {
        return "AppendMessageResult{" +
            "status=" + status +
            ", wroteOffset=" + wroteOffset +
            ", wroteBytes=" + wroteBytes +
            ", msgId='" + msgId + '\'' +
            ", storeTimestamp=" + storeTimestamp +
            ", logicsOffset=" + logicsOffset +
            ", pagecacheRT=" + pagecacheRT +
            ", msgNum=" + msgNum +
            '}';
    }
}
