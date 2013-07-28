package com.diaos_erp.startup;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.google.inject.Inject;

public class StartUpDaoImpl implements StartUpDaoIf
{
    @Inject
    private SqlSession session;

    /* (non-Javadoc)
     * @see com.diaos_erp.startup.StartUpDaoIf#getStartUp()
     */
    @Override
    public List<StartUpItem> getStartUp()
    {
        return session.selectList("com.diaos_erp.startup.dao.mapper.StartUpMapper.selectAll");
    }
}
