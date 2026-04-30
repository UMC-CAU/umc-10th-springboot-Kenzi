package com.example.umc10th.store.service;

import com.example.umc10th.global.exception.ProjectException;
import com.example.umc10th.store.dto.StoreResDTO;
import com.example.umc10th.store.enums.StoreErrorCode;
import com.example.umc10th.store.entity.Store;
import com.example.umc10th.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    public List<Store> getAll() {
        return storeRepository.findAll();
    }

    public List<StoreResDTO.GetStoreList> getStoresByAddressCode(String addressCode) {
        List<StoreResDTO.GetStoreList> stores = storeRepository.findByAddressCode(addressCode)
                .stream()
                .map(StoreResDTO.GetStoreList::from)
                .toList();
        if (stores.isEmpty()) {
            throw new ProjectException(StoreErrorCode.STORE_LIST_NOT_FOUND);
        }
        return stores;
    }

    public StoreResDTO.GetStoreDetail getStoreDetail(String storeId) {
        return storeRepository.findById(storeId)
                .map(StoreResDTO.GetStoreDetail::from)
                .orElseThrow(() -> new ProjectException(StoreErrorCode.STORE_NOT_FOUND));
    }
}
