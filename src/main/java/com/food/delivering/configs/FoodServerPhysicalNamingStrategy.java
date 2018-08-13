package com.food.delivering.configs;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

import java.io.Serializable;

public class FoodServerPhysicalNamingStrategy extends SpringPhysicalNamingStrategy implements Serializable {

    public static final FoodServerPhysicalNamingStrategy INSTANCE = new FoodServerPhysicalNamingStrategy();

    @Override
    public Identifier toPhysicalCatalogName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return name;
    }

    @Override
    public Identifier toPhysicalSchemaName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return name;
    }

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        final String newName = this.addPrefix( name.getText() );
        return jdbcEnvironment.getIdentifierHelper().toIdentifier(newName, name.isQuoted());
    }

    @Override
    public Identifier toPhysicalSequenceName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return name;
    }

    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        return name;
    }

    private String addPrefix(String name) {
        String prefix = "tr_";
        String newName = prefix.concat(name);
        return newName;
    }
}
