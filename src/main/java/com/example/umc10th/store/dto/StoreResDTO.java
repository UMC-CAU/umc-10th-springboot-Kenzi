package com.example.umc10th.store.dto;

import com.example.umc10th.store.entity.Store;

public class StoreResDTO {

    public record GetStoreList(
            String id,
            String name,
            String addressCode,
            String storePhotoUrl
    ) {
        public static GetStoreList from(Store store) {
            return new GetStoreList(
                    store.getId(),
                    store.getName(),
                    store.getAddressCode(),
                    store.getStorePhotoUrl()
            );
        }
    }

    public record GetStoreDetail(
            String id,
            String name,
            String addressCode,
            String ceoId,
            Long foodId,
            String storePhotoUrl,
            String introduction
    ) {
        public static GetStoreDetail from(Store store) {
            return new GetStoreDetail(
                    store.getId(),
                    store.getName(),
                    store.getAddressCode(),
                    store.getCeoId(),
                    store.getFoodId(),
                    store.getStorePhotoUrl(),
                    store.getIntroduction()
            );
        }
    }
}
