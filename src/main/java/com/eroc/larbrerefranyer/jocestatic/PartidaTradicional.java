package com.eroc.larbrerefranyer.jocestatic;

import com.eroc.larbrerefranyer.larbreRefranyer.dbRefranys.RefranyDBObject;

import java.util.List;

/**
 * Created by Pol on 05/10/2015.
 */
public interface PartidaTradicional {

    public void setLayout();
    public void jugarPartida();
    public List<RefranyDBObject> getRefranys(int _nivell);
    public void comprovarSolucio(String _boto);
    public void tramitarAcert();
    public void tramitarError();
    public void sumarPunts();
    public void restarPunts();
    public void tramitarFinalPartida(int _nivell, boolean _superat);
}
