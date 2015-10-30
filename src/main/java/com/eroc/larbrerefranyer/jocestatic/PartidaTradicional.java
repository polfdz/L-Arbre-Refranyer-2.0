package com.eroc.larbrerefranyer.jocestatic;

import com.eroc.larbrerefranyer.larbreRefranyer.dbRefranys.RefranyDBObject;

import java.util.List;

/**
 * Created by Pol on 05/10/2015.
 */
public interface PartidaTradicional {

    public void jugarPartida();
    public List<RefranyDBObject> getRefranys(int _nivell);
}
