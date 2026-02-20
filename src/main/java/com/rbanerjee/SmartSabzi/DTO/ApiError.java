package com.rbanerjee.SmartSabzi.DTO;

import java.time.Instant;

public record ApiError(Instant timestamp, Integer status, String error, String message) {
}
