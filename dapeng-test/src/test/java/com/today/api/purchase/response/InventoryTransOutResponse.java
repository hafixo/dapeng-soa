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
package com.today.api.purchase.response;

        import java.util.Optional;
        import com.github.dapeng.org.apache.thrift.TException;
        import com.github.dapeng.org.apache.thrift.protocol.TCompactProtocol;
        import com.github.dapeng.util.TCommonTransport;

        /**
         * Autogenerated by Dapeng-Code-Generator (2.2.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING

        *

 盘点请求返回结果

        **/
        public class InventoryTransOutResponse{
        
            /**
            *

 转出结果,1为成功,2为失败

            **/
            public int result ;
            public int getResult(){ return this.result; }
            public void setResult(int result){ this.result = result; }

            public int result(){ return this.result; }
            public InventoryTransOutResponse result(int result){ this.result = result; return this; }
          
            /**
            *

 失败原因.仅当result=2时候有效,默认是""

            **/
            public String failReason ;
            public String getFailReason(){ return this.failReason; }
            public void setFailReason(String failReason){ this.failReason = failReason; }

            public String failReason(){ return this.failReason; }
            public InventoryTransOutResponse failReason(String failReason){ this.failReason = failReason; return this; }
          

        public static byte[] getBytesFromBean(InventoryTransOutResponse bean) throws TException {
          byte[] bytes = new byte[]{};
          TCommonTransport transport = new TCommonTransport(bytes, TCommonTransport.Type.Write);
          TCompactProtocol protocol = new TCompactProtocol(transport);

          new com.today.api.purchase.response.serializer.InventoryTransOutResponseSerializer().write(bean, protocol);
          transport.flush();
          return transport.getByteBuf();
        }

        public static InventoryTransOutResponse getBeanFromBytes(byte[] bytes) throws TException {
          TCommonTransport transport = new TCommonTransport(bytes, TCommonTransport.Type.Read);
          TCompactProtocol protocol = new TCompactProtocol(transport);
          return new com.today.api.purchase.response.serializer.InventoryTransOutResponseSerializer().read(protocol);
        }

        public String toString(){
          StringBuilder stringBuilder = new StringBuilder("{");
            stringBuilder.append("\"").append("result").append("\":").append(this.result).append(",");
    stringBuilder.append("\"").append("failReason").append("\":\"").append(this.failReason).append("\",");
    
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
            stringBuilder.append("}");

          return stringBuilder.toString();
        }
      }
      