package com.github.haseoo.minecraft.statusapi.views;

import lombok.EqualsAndHashCode;
import lombok.Value;

import static com.github.haseoo.minecraft.statusapi.utils.Constants.DEFAULT_ERROR_MESSAGE;

@EqualsAndHashCode(callSuper = true)
@Value
public class ErrorView extends AbstractResponse {
    public ErrorView(Boolean online, String description) {
        super(online, description);
    }

    public static ErrorView defaultInstance() {
        return new ErrorView(false,
                DEFAULT_ERROR_MESSAGE);
    }

    public static ErrorView formException(Throwable e) {
        return new ErrorView(false,
                e.getLocalizedMessage());
    }
}
