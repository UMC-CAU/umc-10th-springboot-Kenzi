package com.example.umc10th.store.dto;

import com.example.umc10th.store.entity.Store;

public class StoreResDTO {

    public record GetStoreListResponse(
            Long id,
            String name,
            String addressCode,
            String storePhotoUrl
    ) {
        public static GetStoreListResponse from(Store store) {
            return new GetStoreListResponse(
                    store.getId(),
                    store.getName(),
                    store.getAddressCode(),
                    store.getStorePhotoUrl()
            );
        }
    }

    public record GetStoreDetailResponse(
            Long id,
            String name,
            String addressCode,
            Long ceoId,
            Long foodId,
            String storePhotoUrl,
            String introduction
    ) {
        public static GetStoreDetailResponse from(Store store) {
            return new GetStoreDetailResponse(
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
