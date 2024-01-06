package com.CBSEGroup11pos.util;

import org.springframework.stereotype.Component;

import com.CBSEGroup11pos.entity.Purchase;
import com.CBSEGroup11pos.wrapper.PurchaseWrapper;

@Component
public class PurchaseTransformer {
    public PurchaseWrapper transformEntityToObj(Purchase entity) {
        PurchaseWrapper obj = new PurchaseWrapper();
        // obj.setId(entity.getId());
        obj.setDate(entity.getDate());
        obj.setTime(entity.getTime());
        obj.setCashierid(entity.getCashierid());
        obj.setBarcode(entity.getBarcode());
        obj.setQuantity(entity.getQuantity());
        obj.setTotalamount(entity.getTotalamount());

        return obj;
    }

    public Purchase transformObjToEntity(PurchaseWrapper obj) {
        Purchase entity = new Purchase();
        // entity.setId(obj.getId());
        entity.setDate(obj.getDate());
        entity.setTime(obj.getTime());
        entity.setCashierid(obj.getCashierid());
        entity.setBarcode(obj.getBarcode());
        entity.setQuantity(obj.getQuantity());
        entity.setTotalamount(obj.getTotalamount());

        return entity;
    }
}
