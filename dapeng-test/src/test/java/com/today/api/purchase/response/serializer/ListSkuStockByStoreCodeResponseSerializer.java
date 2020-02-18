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
package com.today.api.purchase.response.serializer;
        import com.today.api.purchase.request.serializer.*;import com.today.api.common.serializer.*;import com.today.api.purchase.response.serializer.*;import com.today.api.stock.response.serializer.*;import com.today.api.stock.request.serializer.*;import com.today.api.stock.events.serializer.*;import com.today.api.stock.vo.serializer.*;

        import com.github.dapeng.core.*;
        import com.github.dapeng.org.apache.thrift.*;
        import com.github.dapeng.org.apache.thrift.protocol.*;

        import java.util.Optional;
        import java.util.concurrent.CompletableFuture;
        import java.util.concurrent.Future;

        /**
        * Autogenerated by Dapeng-Code-Generator (2.2.0)
        *
        * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
        *
        **/
        public class ListSkuStockByStoreCodeResponseSerializer implements BeanSerializer<com.today.api.purchase.response.ListSkuStockByStoreCodeResponse>{
        
      @Override
      public com.today.api.purchase.response.ListSkuStockByStoreCodeResponse read(TProtocol iprot) throws TException{

      com.today.api.purchase.response.ListSkuStockByStoreCodeResponse bean = new com.today.api.purchase.response.ListSkuStockByStoreCodeResponse();
      TField schemeField;
      iprot.readStructBegin();

      while(true){
        schemeField = iprot.readFieldBegin();
        if(schemeField.type == TType.STOP){ break;}

        switch(schemeField.id){
          
              case 1:
              if(schemeField.type == TType.LIST){
               TList _list0 = iprot.readListBegin();
        java.util.List<com.today.api.purchase.response.SkuStockItem> elem0 = new java.util.ArrayList<>(_list0.size);
        for(int _i0 = 0; _i0 < _list0.size; ++ _i0){
          com.today.api.purchase.response.SkuStockItem elem1 = new com.today.api.purchase.response.SkuStockItem();
        elem1=new SkuStockItemSerializer().read(iprot);
          elem0.add(elem1);
        }
        iprot.readListEnd();
       bean.setSkuStockItemList(elem0);
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
              case 2:
              if(schemeField.type == TType.STRING){
              java.math.BigDecimal elem0 = new java.math.BigDecimal(iprot.readString());
       bean.setTotalStockNum(elem0);
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
              case 3:
              if(schemeField.type == TType.DOUBLE){
               double elem0 = iprot.readDouble();
       bean.setTotalStockSellingPrice(elem0);
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
              case 4:
              if(schemeField.type == TType.STRUCT){
              com.today.api.common.PageResponse elem0 = new com.today.api.common.PageResponse();
        elem0=new PageResponseSerializer().read(iprot);
       bean.setPageResponse(Optional.of(elem0));
            }else{
              TProtocolUtil.skip(iprot, schemeField.type);
            }
              break;
            
          
            default:
            TProtocolUtil.skip(iprot, schemeField.type);
          
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      validate(bean);
      return bean;
    }
    
      @Override
      public void write(com.today.api.purchase.response.ListSkuStockByStoreCodeResponse bean, TProtocol oprot) throws TException{

      validate(bean);
      oprot.writeStructBegin(new TStruct("ListSkuStockByStoreCodeResponse"));

      
            oprot.writeFieldBegin(new TField("skuStockItemList", TType.LIST, (short) 1));
            java.util.List<com.today.api.purchase.response.SkuStockItem> elem0 = bean.getSkuStockItemList();
            
          oprot.writeListBegin(new TList(TType.STRUCT, elem0.size()));
          for(com.today.api.purchase.response.SkuStockItem elem1 : elem0){
           new SkuStockItemSerializer().write(elem1, oprot);
        }
          oprot.writeListEnd();
        
            
            oprot.writeFieldEnd();
          
            oprot.writeFieldBegin(new TField("totalStockNum", TType.STRING, (short) 2));
            java.math.BigDecimal elem1 = bean.getTotalStockNum();
            oprot.writeString(elem1.toString());
            
            oprot.writeFieldEnd();
          
            oprot.writeFieldBegin(new TField("totalStockSellingPrice", TType.DOUBLE, (short) 3));
            Double elem2 = bean.getTotalStockSellingPrice();
            oprot.writeDouble(elem2);
            
            oprot.writeFieldEnd();
          if(bean.getPageResponse().isPresent()){
              oprot.writeFieldBegin(new TField("pageResponse", TType.STRUCT, (short) 4));
              com.today.api.common.PageResponse elem3 = bean.getPageResponse().get();
               new PageResponseSerializer().write(elem3, oprot);
              
            }
            
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }
    
      public void validate(com.today.api.purchase.response.ListSkuStockByStoreCodeResponse bean) throws TException{
      
              if(bean.getSkuStockItemList() == null)
              throw new SoaException(SoaCode.StructFieldNull, "skuStockItemList字段不允许为空");
            
              if(bean.getTotalStockNum() == null)
              throw new SoaException(SoaCode.StructFieldNull, "totalStockNum字段不允许为空");
            
                if(bean.getPageResponse().isPresent())
                new PageResponseSerializer().validate(bean.getPageResponse().get());
              
    }
    
        @Override
        public String toString(com.today.api.purchase.response.ListSkuStockByStoreCodeResponse bean)
        {return bean == null ? "null" : bean.toString();}
      }
      

      