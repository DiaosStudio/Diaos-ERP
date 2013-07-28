package com.diaos_erp.startup;

import java.util.List;

import org.mybatis.guice.transactional.Transactional;

import com.google.inject.ImplementedBy;

@ImplementedBy(StartUpDaoImpl.class)
public interface StartUpDaoIf
{
    @Transactional
    public abstract List<StartUpItem> getStartUp();
}