databaseChangeLog = {

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-1") {
        createSequence(sequenceName: "hibernate_sequence")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-2") {
        createTable(tableName: "address") {
            column(name: "addr_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "addr_label", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "owner_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-3") {
        createTable(tableName: "address_line") {
            column(name: "al_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "al_seq", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "al_value", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "al_type_rv_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "owner_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-4") {
        createTable(tableName: "address_tag") {
            column(name: "address_tags_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "tag_id", type: "BIGINT")
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-5") {
        createTable(tableName: "announcement") {
            column(name: "ann_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "ann_code", type: "VARCHAR(255)")

            column(name: "ann_expiry_date", type: "timestamp")

            column(name: "ann_announce_date", type: "timestamp") {
                constraints(nullable: "false")
            }

            column(name: "ann_owner_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "ann_description", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-6") {
        createTable(tableName: "custom_property") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "custom_propertyPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "definition_id", type: "VARCHAR(36)")

            column(name: "note", type: "CLOB")

            column(name: "parent_id", type: "BIGINT")
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-7") {
        createTable(tableName: "custom_property_blob") {
            column(name: "id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "value", type: "OID") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-8") {
        createTable(tableName: "custom_property_boolean") {
            column(name: "id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "value", type: "BOOLEAN") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-9") {
        createTable(tableName: "custom_property_container") {
            column(name: "id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-10") {
        createTable(tableName: "custom_property_decimal") {
            column(name: "id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "value", type: "NUMBER(19, 2)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-11") {
        createTable(tableName: "custom_property_definition") {
            column(name: "pd_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "pd_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "pd_primary", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "pd_type", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "pd_label", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "pd_description", type: "VARCHAR(255)")

            column(name: "pd_weight", type: "INT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-12") {
        createTable(tableName: "custom_property_integer") {
            column(name: "id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "value", type: "INT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-13") {
        createTable(tableName: "custom_property_refdata") {
            column(name: "id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "value_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-14") {
        createTable(tableName: "custom_property_refdata_definition") {
            column(name: "pd_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "category_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-15") {
        createTable(tableName: "custom_property_text") {
            column(name: "id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "value", type: "CLOB") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-16") {
        createTable(tableName: "directory_entry") {
            column(name: "de_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "custom_properties_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "de_slug", type: "VARCHAR(255)")

            column(name: "de_foaf_timestamp", type: "BIGINT")

            column(name: "de_foaf_url", type: "VARCHAR(255)")

            column(name: "de_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "de_status_fk", type: "VARCHAR(36)")

            column(name: "de_desc", type: "VARCHAR(255)")

            column(name: "de_parent", type: "VARCHAR(36)")
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-17") {
        createTable(tableName: "directory_entry_tag") {
            column(name: "directory_entry_tags_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "tag_id", type: "BIGINT")
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-18") {
        createTable(tableName: "friend_assertion") {
            column(name: "fa_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "fa_friend_org", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "fa_owner", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-19") {
        createTable(tableName: "naming_authority") {
            column(name: "na_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "na_symbol", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-20") {
        createTable(tableName: "refdata_category") {
            column(name: "rdc_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "rdc_version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rdc_description", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-21") {
        createTable(tableName: "refdata_value") {
            column(name: "rdv_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "rdv_version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rdv_value", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "rdv_owner", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "rdv_label", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-22") {
        createTable(tableName: "service") {
            column(name: "se_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "se_address", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "custom_properties_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "se_name", type: "VARCHAR(255)")

            column(name: "se_type_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "se_business_function_fk", type: "VARCHAR(36)")
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-23") {
        createTable(tableName: "service_account") {
            column(name: "sa_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "sa_account_holder", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "custom_properties_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "sa_service", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "sa_account_details", type: "VARCHAR(255)")
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-24") {
        createTable(tableName: "service_tag") {
            column(name: "service_tags_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "tag_id", type: "BIGINT")
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-25") {
        createTable(tableName: "symbol") {
            column(name: "sym_id", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "sym_priority", type: "VARCHAR(255)")

            column(name: "sym_authority_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "sym_owner_fk", type: "VARCHAR(36)") {
                constraints(nullable: "false")
            }

            column(name: "sym_symbol", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-26") {
        createTable(tableName: "tag") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "tagPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "norm_value", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "value", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-27") {
        addPrimaryKey(columnNames: "addr_id", constraintName: "addressPK", tableName: "address")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-28") {
        addPrimaryKey(columnNames: "al_id", constraintName: "address_linePK", tableName: "address_line")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-29") {
        addPrimaryKey(columnNames: "ann_id", constraintName: "announcementPK", tableName: "announcement")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-30") {
        addPrimaryKey(columnNames: "id", constraintName: "custom_property_blobPK", tableName: "custom_property_blob")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-31") {
        addPrimaryKey(columnNames: "id", constraintName: "custom_property_booleanPK", tableName: "custom_property_boolean")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-32") {
        addPrimaryKey(columnNames: "id", constraintName: "custom_property_containerPK", tableName: "custom_property_container")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-33") {
        addPrimaryKey(columnNames: "id", constraintName: "custom_property_decimalPK", tableName: "custom_property_decimal")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-34") {
        addPrimaryKey(columnNames: "pd_id", constraintName: "custom_property_definitionPK", tableName: "custom_property_definition")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-35") {
        addPrimaryKey(columnNames: "id", constraintName: "custom_property_integerPK", tableName: "custom_property_integer")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-36") {
        addPrimaryKey(columnNames: "id", constraintName: "custom_property_refdataPK", tableName: "custom_property_refdata")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-37") {
        addPrimaryKey(columnNames: "pd_id", constraintName: "custom_property_refdata_definitionPK", tableName: "custom_property_refdata_definition")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-38") {
        addPrimaryKey(columnNames: "id", constraintName: "custom_property_textPK", tableName: "custom_property_text")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-39") {
        addPrimaryKey(columnNames: "de_id", constraintName: "directory_entryPK", tableName: "directory_entry")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-40") {
        addPrimaryKey(columnNames: "fa_id", constraintName: "friend_assertionPK", tableName: "friend_assertion")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-41") {
        addPrimaryKey(columnNames: "na_id", constraintName: "naming_authorityPK", tableName: "naming_authority")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-42") {
        addPrimaryKey(columnNames: "rdc_id", constraintName: "refdata_categoryPK", tableName: "refdata_category")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-43") {
        addPrimaryKey(columnNames: "rdv_id", constraintName: "refdata_valuePK", tableName: "refdata_value")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-44") {
        addPrimaryKey(columnNames: "se_id", constraintName: "servicePK", tableName: "service")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-45") {
        addPrimaryKey(columnNames: "sa_id", constraintName: "service_accountPK", tableName: "service_account")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-46") {
        addPrimaryKey(columnNames: "sym_id", constraintName: "symbolPK", tableName: "symbol")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-47") {
        addUniqueConstraint(columnNames: "pd_name", constraintName: "UC_CUSTOM_PROPERTY_DEFINITIONPD_NAME_COL", tableName: "custom_property_definition")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-48") {
        createIndex(indexName: "rdv_entry_idx", tableName: "refdata_value") {
            column(name: "rdv_value")

            column(name: "rdv_owner")
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-49") {
        createIndex(indexName: "td_label_idx", tableName: "custom_property_definition") {
            column(name: "pd_label")
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-50") {
        createIndex(indexName: "td_primary_idx", tableName: "custom_property_definition") {
            column(name: "pd_primary")
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-51") {
        createIndex(indexName: "td_type_idx", tableName: "custom_property_definition") {
            column(name: "pd_type")
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-52") {
        createIndex(indexName: "td_weight_idx", tableName: "custom_property_definition") {
            column(name: "pd_weight")
        }
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-53") {
        addForeignKeyConstraint(baseColumnNames: "de_status_fk", baseTableName: "directory_entry", constraintName: "FK19lypn8h0g8kvr1cke6ddyjwg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rdv_id", referencedTableName: "refdata_value")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-54") {
        addForeignKeyConstraint(baseColumnNames: "de_parent", baseTableName: "directory_entry", constraintName: "FK1lcdvuk9hkmebm544kwmxoclj", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "de_id", referencedTableName: "directory_entry")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-55") {
        addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "address_line", constraintName: "FK27dakevcmnu3o22tdrpob6npg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "addr_id", referencedTableName: "address")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-56") {
        addForeignKeyConstraint(baseColumnNames: "custom_properties_id", baseTableName: "directory_entry", constraintName: "FK2qp9dd004mntrub21o6djlxqh", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "custom_property_container")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-57") {
        addForeignKeyConstraint(baseColumnNames: "definition_id", baseTableName: "custom_property", constraintName: "FK36grvth72fb7wu5i5xaeqjitw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "pd_id", referencedTableName: "custom_property_definition")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-58") {
        addForeignKeyConstraint(baseColumnNames: "se_type_fk", baseTableName: "service", constraintName: "FK37qd0xlyn5tpy48wega3ss3hy", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rdv_id", referencedTableName: "refdata_value")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-59") {
        addForeignKeyConstraint(baseColumnNames: "ann_owner_fk", baseTableName: "announcement", constraintName: "FK4hir8ts72q8qvhr7skxe8wss9", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "de_id", referencedTableName: "directory_entry")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-60") {
        addForeignKeyConstraint(baseColumnNames: "value_id", baseTableName: "custom_property_refdata", constraintName: "FK5ogn0fedwxxy4fhmq9du4qej2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rdv_id", referencedTableName: "refdata_value")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-61") {
        addForeignKeyConstraint(baseColumnNames: "directory_entry_tags_id", baseTableName: "directory_entry_tag", constraintName: "FK73prfacykqmx20o3gr9dr7b98", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "de_id", referencedTableName: "directory_entry")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-62") {
        addForeignKeyConstraint(baseColumnNames: "tag_id", baseTableName: "address_tag", constraintName: "FK8mggv80lsn331xa42585kim18", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tag")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-63") {
        addForeignKeyConstraint(baseColumnNames: "fa_friend_org", baseTableName: "friend_assertion", constraintName: "FKam7kxpwd75are1h7o0easuo03", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "de_id", referencedTableName: "directory_entry")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-64") {
        addForeignKeyConstraint(baseColumnNames: "sym_owner_fk", baseTableName: "symbol", constraintName: "FKatkxebh688uppornia9wp6u0o", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "de_id", referencedTableName: "directory_entry")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-65") {
        addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "custom_property_refdata_definition", constraintName: "FKbrh88caagajlvrpaydg4tr3qx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rdc_id", referencedTableName: "refdata_category")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-66") {
        addForeignKeyConstraint(baseColumnNames: "parent_id", baseTableName: "custom_property", constraintName: "FKd5u2tgpracxvk1xw8pdreuj5h", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "custom_property_container")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-67") {
        addForeignKeyConstraint(baseColumnNames: "sym_authority_fk", baseTableName: "symbol", constraintName: "FKgd9iwv5imahohd3irh7a4tysq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "na_id", referencedTableName: "naming_authority")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-68") {
        addForeignKeyConstraint(baseColumnNames: "rdv_owner", baseTableName: "refdata_value", constraintName: "FKh4fon2a7k4y8b2sicjm0i6oy8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rdc_id", referencedTableName: "refdata_category")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-69") {
        addForeignKeyConstraint(baseColumnNames: "custom_properties_id", baseTableName: "service_account", constraintName: "FKh8o9kxfjd3rn84sjhf2m8k1kd", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "custom_property_container")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-70") {
        addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "address", constraintName: "FKiscq9dhgj0e6hxlj49ejxavw1", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "de_id", referencedTableName: "directory_entry")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-71") {
        addForeignKeyConstraint(baseColumnNames: "sa_account_holder", baseTableName: "service_account", constraintName: "FKl0sums8w3h2i90a7gudkkvs6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "de_id", referencedTableName: "directory_entry")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-72") {
        addForeignKeyConstraint(baseColumnNames: "custom_properties_id", baseTableName: "service", constraintName: "FKlcsx75pv26118e28ske0wgft7", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "custom_property_container")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-73") {
        addForeignKeyConstraint(baseColumnNames: "sa_service", baseTableName: "service_account", constraintName: "FKlw0rgy9jm8bhf9cn2ok7yr76b", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "se_id", referencedTableName: "service")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-74") {
        addForeignKeyConstraint(baseColumnNames: "se_business_function_fk", baseTableName: "service", constraintName: "FKm4goei4gs0kc3o37owkar9qmn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rdv_id", referencedTableName: "refdata_value")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-75") {
        addForeignKeyConstraint(baseColumnNames: "al_type_rv_fk", baseTableName: "address_line", constraintName: "FKnrum0mlrqrdim99tpv2fsrppf", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "rdv_id", referencedTableName: "refdata_value")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-76") {
        addForeignKeyConstraint(baseColumnNames: "fa_owner", baseTableName: "friend_assertion", constraintName: "FKq0b79ux6oihg46yoks9vg154c", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "de_id", referencedTableName: "directory_entry")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-77") {
        addForeignKeyConstraint(baseColumnNames: "tag_id", baseTableName: "service_tag", constraintName: "FKq56hgx6qad4r28rntiynnitg8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tag")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-78") {
        addForeignKeyConstraint(baseColumnNames: "service_tags_id", baseTableName: "service_tag", constraintName: "FKq58uyhoq6ouyw991t9aps47ka", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "se_id", referencedTableName: "service")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-79") {
        addForeignKeyConstraint(baseColumnNames: "address_tags_id", baseTableName: "address_tag", constraintName: "FKsfnxyiyhbwabho720nkg34mjb", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "addr_id", referencedTableName: "address")
    }

    changeSet(author: "sosguthorpe (generated)", id: "1560352943887-80") {
        addForeignKeyConstraint(baseColumnNames: "tag_id", baseTableName: "directory_entry_tag", constraintName: "FKt8qbn40lvi5a2hi726uqc5igv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "tag")
    }
}
