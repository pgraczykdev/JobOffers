package pl.joboffers.domain.loginandregister.dto;

import lombok.Builder;

@Builder
public record FindByUsernameRequestDto (String username) {
}
