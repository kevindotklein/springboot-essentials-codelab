package com.kevindotklein.springbootessentials.dto.anime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record AnimePutRequestDTO(@NotBlank @NotNull String name) {
}
