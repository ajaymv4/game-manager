package com.instawins.game.manager.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * GAME_STATUS VARCHAR2(30) NOT NULL,
 */

public enum GameStatusType {
    OPEN,CLOSED
}
