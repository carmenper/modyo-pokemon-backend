package com.cemp.modyo.pokemon.exception;

import com.cemp.modyo.pokemon.enums.ExceptionEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExceptionTest {

    @Test
    void test() throws ApplicationException {


        try {
            throw new ApplicationException(ExceptionEnum.COMMUNICATION_EXCEPTION);
        } catch (ApplicationException e){
            e.getDetail();
            e.getId();
            e.getMessage();

            Assertions.assertEquals(ExceptionEnum.COMMUNICATION_EXCEPTION.getId(), e.getId());
        }


        try {
            throw new BadRequestException(ExceptionEnum.INVALID_PARAMS);
        } catch (BadRequestException e){
            e.getDetail();
            e.getId();
            e.getMessage();
            Assertions.assertEquals(ExceptionEnum.INVALID_PARAMS.getId(), e.getId());
        }

        try {
            throw new DataException(ExceptionEnum.INVALID_DATA, "");
        } catch (DataException e){
            e.getDetail();
            e.getId();
            e.getMessage();
            e.getData();
            Assertions.assertEquals(ExceptionEnum.INVALID_DATA.getId(), e.getId());
        }

        try {
            throw new NotFoundException("1");
        } catch (NotFoundException e){
            e.getDetail();
            e.getId();
            e.getMessage();
            e.getResourceId();
            Assertions.assertEquals("1", e.getResourceId());
        }
    }

}
