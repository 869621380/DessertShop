package org.example.dessertshopspringboot.Domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
/*
返回结果集
 */
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    public static <T> Result<T> success(T data) {
        return new Result<>(0, "操作成功", data);
    }

    public static <T> Result<T> success() {
        return new Result(0, "操作成功", null);
    }

    public static <T> Result<T> error(String msg) {
        return new Result(1, msg, null);
    }
}
