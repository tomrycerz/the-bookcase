package com.tomryc.thebookcase.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BaseEntityTest {

    private BaseEntity baseEntity;

    @BeforeEach
    public void setUp(){
        baseEntity = new BaseEntity();
    }

    @AfterEach
    void tearDown() {
        baseEntity = null;
    }

    @Test
    public void getId(){
        Long idValue = 5L;
        baseEntity.setId(idValue);
        assertEquals(idValue, baseEntity.getId());
    }

    @Test
    public void isNewWhenInstanceCreated(){
        assertTrue(baseEntity.isNew());
    }

    @Test
    public void isNotNewWhenAddedNewEntity(){
        baseEntity = new BaseEntity(1L);
        assertFalse(baseEntity.isNew());
    }

}