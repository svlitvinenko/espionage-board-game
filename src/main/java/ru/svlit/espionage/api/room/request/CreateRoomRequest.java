package ru.svlit.espionage.api.room.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Запрос на создание комнаты.
 *
 * @author Sergei Litvinenko on 01.01.2021.
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@ApiModel(value = "Create Room Request", description = "Модель запроса на создание комнаты")
public class CreateRoomRequest {

    @ApiModelProperty(
            example = "Kikoriki",
            value = "Придуманное пользователем название комнаты"
    )
    private String name;

    @ApiModelProperty(
            example = "1234567890",
            value = "Идентификатор выбранной пользователем игровой локации"
    )
    private String locationId;
}
