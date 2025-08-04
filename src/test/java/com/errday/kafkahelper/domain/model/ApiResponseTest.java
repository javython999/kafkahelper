package com.errday.kafkahelper.domain.model;

import com.errday.kafkahelper.adapter.in.web.dto.ApiResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ApiResponseTest {

    @Test
    void success() {
        String message = "success";
        Integer data = 1;

        ApiResponse<Integer> apiResponse = ApiResponse.success(message, data);

        assertThat(apiResponse.isSuccess()).isTrue();
        assertThat(apiResponse.message()).isEqualTo(message);
        assertThat(apiResponse.data().getClass()).isEqualTo(data.getClass());
    }

    @Test
    void error() {
        String message = "error";
        Integer data = 0;

        ApiResponse<Integer> apiResponse = ApiResponse.error(message, data);
        assertThat(apiResponse.isSuccess()).isFalse();
        assertThat(apiResponse.message()).isEqualTo(message);
        assertThat(apiResponse.data().getClass()).isEqualTo(data.getClass());
    }

}