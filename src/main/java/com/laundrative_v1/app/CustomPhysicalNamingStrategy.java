package com.laundrative_v1.app;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import java.util.Locale;

public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy {

    @Override
    public Identifier toPhysicalCatalogName(final Identifier identifier, final JdbcEnvironment jdbcEnv)
    {
        return Identifier.toIdentifier(identifier.getText().toLowerCase(Locale.ENGLISH));
    }

    @Override
    public Identifier toPhysicalColumnName(final Identifier identifier, final JdbcEnvironment jdbcEnv)
    {
        return Identifier.toIdentifier(identifier.getText().toLowerCase(Locale.ENGLISH));
    }

    @Override
    public Identifier toPhysicalSchemaName(final Identifier identifier, final JdbcEnvironment jdbcEnv)
    {
        return Identifier.toIdentifier(identifier.getText().toLowerCase(Locale.ENGLISH));
    }

    @Override
    public Identifier toPhysicalSequenceName(final Identifier identifier, final JdbcEnvironment jdbcEnv)
    {
        return Identifier.toIdentifier(identifier.getText().toLowerCase(Locale.ENGLISH));
    }

    @Override
    public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv)
    {
        return Identifier.toIdentifier(identifier.getText().toLowerCase(Locale.ENGLISH));
    }
}