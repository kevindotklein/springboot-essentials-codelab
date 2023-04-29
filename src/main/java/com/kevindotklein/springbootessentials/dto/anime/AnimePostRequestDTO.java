package com.kevindotklein.springbootessentials.dto.anime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public record AnimePostRequestDTO(@NotBlank @NotNull String name) {
}
