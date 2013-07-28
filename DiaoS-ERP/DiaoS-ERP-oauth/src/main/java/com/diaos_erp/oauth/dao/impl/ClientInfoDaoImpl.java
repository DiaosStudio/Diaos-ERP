package com.diaos_erp.oauth.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.restlet.ext.oauth.Client;

import com.diaos_erp.oauth.client.DiaosClientInfo;
import com.diaos_erp.oauth.dao.ClientInfoDaoIf;
import com.google.inject.Inject;

public class ClientInfoDaoImpl implements ClientInfoDaoIf
{
    @Inject
    private SqlSession session;

    /*
     * (non-Javadoc)
     * 
     * @see com.diaos_erp.oauth.dao.impl.ClientInfoDaoIf#getClientInfo(java.lang. String)
     */
    public DiaosClientInfo getClientInfo(String clientId)
    {
        return session.selectOne("com.diaos_erp.oauth.dao.mapper.ClientInfoMapper.selectTest", clientId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.diaos_erp.oauth.dao.impl.ClientInfoDaoIf#getClientContainsUser(java .lang.String)
     */
    public List<DiaosClientInfo> getClientContainsUser(String userId)
    {
        return session.selectList("com.diaos_erp.oauth.dao.mapper.ClientInfoMapper.selectTest", userId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.diaos_erp.oauth.dao.impl.ClientInfoDaoIf#deleteClient(java.lang.String )
     */
    public boolean deleteClient(String clientId)
    {
        return session.delete("com.diaos_erp.oauth.dao.mapper.ClientInfoMapper.selectTest", clientId) > 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.diaos_erp.oauth.dao.impl.ClientInfoDaoIf#createClient(com.diaos_erp .oauth.client.DiaosClientInfo)
     */
    public boolean createClient(Client client)
    {
        return session.insert("com.diaos_erp.oauth.dao.mapper.ClientInfoMapper.createNew", client) > 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.diaos_erp.oauth.dao.impl.ClientInfoDaoIf#updateClient(com.diaos_erp .oauth.client.DiaosClientInfo)
     */
    public boolean updateClient(DiaosClientInfo client)
    {
        return session.update("com.diaos_erp.oauth.dao.mapper.ClientInfoMapper.selectTest", client) > 0;
    }
}
