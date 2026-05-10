package com.example.umc10th.missions.converter;

import com.example.umc10th.missions.dto.MissionResDTO;

import java.util.List;

public class MissionConverter {

    public static <T>MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
