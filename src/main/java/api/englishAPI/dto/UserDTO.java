package api.englishAPI.dto;

public record UserDTO(
        String nome,
        String sobrenome,
        String email,
        String picture,
        Long credits,
        Boolean vip,
        Boolean checkin,
        String ip
) {}

