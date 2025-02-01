package com.example.ProductService.Exceptions;

public class ExceptionDto extends Exception{

    private int ProductId;

    private static final long serialVersionUID = 1L;

    public ExceptionDto(String msg) {
        super(msg);

    }

    public ExceptionDto() {
        super();
    }

    public ExceptionDto(String msg, int productId) {
        super(msg);
        this.ProductId=productId;
    }


}
