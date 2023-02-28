package pers.llz.user.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeleteUserDto {
    List<Integer> ids;
}
