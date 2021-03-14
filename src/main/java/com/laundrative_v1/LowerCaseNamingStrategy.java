package com.laundrative_v1;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Locale;

public class LowerCaseNamingStrategy extends PhysicalNamingStrategyStandardImpl implements Serializable
{

    public static final LowerCaseNamingStrategy INSTANCE = new LowerCaseNamingStrategy();

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context)
    {
        return context.getIdentifierHelper().toIdentifier(StringUtils.lowerCase(name.getText(), Locale.ENGLISH));
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context)
    {
        return context.getIdentifierHelper().toIdentifier(StringUtils.lowerCase(name.getText(), Locale.ENGLISH));
    }
}