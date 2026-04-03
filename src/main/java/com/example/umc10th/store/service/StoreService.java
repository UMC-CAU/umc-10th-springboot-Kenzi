package com.example.umc10th.store.service;

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
}
