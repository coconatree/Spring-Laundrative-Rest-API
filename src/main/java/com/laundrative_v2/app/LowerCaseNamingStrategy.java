package com.laundrative_v2.app;

import java.util.Locale;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class LowerCaseNamingStrategy extends PhysicalNamingStrategyStandardImpl
{
    private static final long serialVersionUID = -2990119491852768604L;

    @Override
    public Identifier toPhysicalTableName(Identifier name,  JdbcEnvironment context)
    {
        return context.getIdentifierHelper().toIdentifier(name.getText().toLowerCase(Locale.ENGLISH));
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context)
    {
        return context.getIdentifierHelper().toIdentifier(name.getText().toLowerCase(Locale.ENGLISH));
    }
}
